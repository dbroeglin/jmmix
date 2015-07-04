package fr.broeglin.jmmix.simulator;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.xml.transform.stream.StreamSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

import fr.broeglin.jmmix.simulator.trace.InstructionTrace;
import fr.broeglin.jmmix.simulator.trace.InstructionTracer;
import fr.broeglin.jmmix.simulator.trace.RegisterTrace;
import fr.broeglin.jmmix.simulator.trace.Trace;

public abstract class AbstractMmoTest {

	@Retention(value = RUNTIME)
	@Target(value = ElementType.TYPE)
	public @interface MmixSource {
		String value();
	}

	// 1. 0000000000000100: f0000004 (JMP) -> #110
	private static final Pattern INST_PAT = Pattern
			.compile(".*1. ([0-9a-f]{16}): ([0-9a-f]{8}) .*",
					Pattern.UNIX_LINES);
	private static final Pattern REG_PAT = Pattern
			.compile(".*mmix> l\\[([0-9]{1,3})\\]=#([0-9a-f]{1,16}).*",
					Pattern.UNIX_LINES);

	// mmix> l[24]=#0
	private static final File MMIX_DIR = new File("src/test/mmix");
	protected File objectFile;
	protected List<InstructionTrace> mmixInstructions;
	protected List<RegisterTrace> mmixRegisters;
	protected Simulator simulator;

	@Before
	public void assemble() throws Exception {
		MmixSource source = getClass().getAnnotation(MmixSource.class);

		if (source == null || source.value().isEmpty()) {
			throw new IllegalArgumentException(
					"@MmixSource annotation is mandatory");
		}

		objectFile = assembleSourceFile(source.value());
	}

	@After
	public void after() throws Exception {
		executeObjectFile(objectFile);
		compareExecutions();
	}

	@Test
	public void should_run_like_mmix() throws Exception {
		simulator = new Simulator();
		InstructionTracer tracer = new InstructionTracer();

		simulator.setTracer(tracer);
		new Loader(new DataInputStream(new FileInputStream(objectFile)),
				simulator).load();

		simulator.execute();
		System.out.println(simulator.dump());
	}

	void executeObjectFile(File objectFile) throws IOException,
			InterruptedException {
		System.out.println("Running '" + objectFile + "'...");
		ProcessBuilder pb = new ProcessBuilder("bash", "-lc", "mmix -t1000 -I "
				+ objectFile.getPath());

		pb.redirectErrorStream(true);
		pb.redirectInput(ProcessBuilder.Redirect.PIPE);

		Process process = pb.start();

		OutputStreamWriter out = new OutputStreamWriter(
				process.getOutputStream());

		for (int i = 0; i < 232; i++) {
			out.write(String.format("l%d#\n", i));
			out.flush();
		}
		out.close();
		mmixInstructions = null;
		Map<Class<?>, List<Object>> result =
				new BufferedReader(
						new InputStreamReader(process.getInputStream()))
						.lines()
						.map(l -> parseInterpreterLine(l))
						.filter(t -> t != null)
						.collect(
								Collectors.groupingBy(t -> t.getClass()));

		process.waitFor(5, SECONDS);

		mmixInstructions = (List) result.get(InstructionTrace.class);
		mmixRegisters = (List) result.get(RegisterTrace.class);

		process.destroy();
	}

	private Trace parseInterpreterLine(String str) {
		Matcher m;
		if ((m = INST_PAT.matcher(str)).matches()) {
			return new InstructionTrace(
					Long.parseUnsignedLong(m.group(1), 16),
					Integer.parseUnsignedInt(m.group(2), 16));
		} else if ((m = REG_PAT.matcher(str)).matches()) {
			return new RegisterTrace(Integer.valueOf(m.group(1)),
					new BigInteger(
							m.group(2), 16).longValue());
		}
		return null;
	}

	private File assembleSourceFile(String sourceFile) throws IOException,
			InterruptedException {
		System.out.format("Assembling %s...\n", sourceFile);
		ProcessBuilder pb = new ProcessBuilder("bash", "-lc", "mmixal "
				+ sourceFile);

		pb.directory(MMIX_DIR);
		pb.redirectErrorStream(true);

		Process process = pb.start();

		process.waitFor(5, SECONDS);

		ByteStreams.copy(process.getInputStream(), System.out);
		assertThat(process.exitValue(), equalTo(0));
		process.destroy();

		return new File(MMIX_DIR, Files.getNameWithoutExtension(sourceFile)
				+ ".mmo");
	}

	public void compareExecutions() {
		if (simulator.getTracer() == null) {
			return;
		}

		List<InstructionTrace> sims = ((InstructionTracer) simulator
				.getTracer()).getInstructions();
		int size = Math.min(mmixInstructions.size(), sims.size());
		StringBuilder sb = new StringBuilder();
		boolean diff = false;

		for (int i = 0; i < size; i++) {
			InstructionTrace inst = mmixInstructions.get(i);
			InstructionTrace simInst = sims.get(i);

			if (!inst.equals(simInst)) {
				sb.append(inst).append(" <> ").append(simInst).append("\n");
				diff = true;
			}
		}
		if (mmixInstructions.size() > size) {
			sb.append(mmixInstructions.size() - size).append(
					" instructions more in MMIX:\n");
		}
		if (sims.size() > size) {
			sb.append(sims.size() - size).append(
					" instructions more in JMMIX\n");
		}

		for (int i = 0; i < 128; i++) {
			long value = simulator.getProcessor().register(i);

			if (mmixRegisters.get(i).value != value) {
				diff = true;
				sb.append("register difference: ").append(mmixRegisters.get(i))
						.append(" <> ").append(String.format("#%x", value))
						.append("\n");
			}
		}

		if (diff) {
			System.out.println(sb);
			fail("There where execution differences:\n" + sb);
		}
	}

}
