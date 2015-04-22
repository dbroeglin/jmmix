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
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

import fr.broeglin.jmmix.simulator.trace.InstructionTrace;
import fr.broeglin.jmmix.simulator.trace.InstructionTracer;

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
	private static final File MMIX_DIR = new File("src/test/mmix");
	protected File objectFile;
	protected List<InstructionTrace> instructions;
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
		ProcessBuilder pb = new ProcessBuilder("bash", "-lc", "mmix -t1000 "
				+ objectFile.getPath());

		pb.redirectErrorStream(true);

		Process process = pb.start();
		instructions =
				new BufferedReader(
						new InputStreamReader(process.getInputStream()))
						.lines()
						.map(l -> parseInstruction(l))
						.filter(t -> t != null)
						.collect(Collectors.toList());

		process.waitFor(5, SECONDS);

		process.destroy();
	}

	private InstructionTrace parseInstruction(String str) {
		Matcher m = INST_PAT.matcher(str);
		if (m.matches()) {
			return new InstructionTrace(
					Long.parseUnsignedLong(m.group(1), 16),
					Integer.parseUnsignedInt(m.group(2), 16));
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
		int size = Math.min(instructions.size(), sims.size());
		StringBuilder sb = new StringBuilder();
		boolean diff = false;
		
		for (int i = 0; i < size; i++) {
			InstructionTrace inst = instructions.get(i);
			InstructionTrace simInst = sims.get(i);

			if (!inst.equals(simInst)) {
				sb.append(inst).append(" <> ").append(simInst).append("\n");
				diff = true;
			} else {
				sb.append(inst).append("\n");
			}
		}
		if (instructions.size() > size) {
			sb.append(instructions.size() - size).append(" instructions more in MMIX:\n");
		}
		if (sims.size() > size) {
			sb.append(sims.size() - size).append(" instructions more in JMMIX\n");
		}
		if (diff) {
			System.out.println(sb);
			fail("There where execution differences");
		}
	}

}
