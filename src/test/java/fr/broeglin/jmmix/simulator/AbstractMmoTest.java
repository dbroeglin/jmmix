package fr.broeglin.jmmix.simulator;

import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rA;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rL;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rYY;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rZZ;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

import fr.broeglin.jmmix.simulator.trace.InstructionTrace;
import fr.broeglin.jmmix.simulator.trace.InstructionTracer;
import fr.broeglin.jmmix.simulator.trace.RegisterTrace;
import fr.broeglin.jmmix.simulator.trace.Trace;

public abstract class AbstractMmoTest {

	// 1. 0000000000000100: f0000004 (JMP) -> #110
	private static final Pattern INST_PAT = Pattern
			.compile(".*1. ([0-9a-f]{16}): ([0-9a-f]{8}) .*",
					Pattern.UNIX_LINES);
	// $255=g[255]=#5 or $32=#0
	private static final Pattern GEN_REG_PAT = Pattern
			.compile(
					".*mmix> \\$([0-9]{1,3})=([lg]\\[[0-9]{1,3}\\]=)?#([0-9a-f]{1,16}).*",
					Pattern.UNIX_LINES);
	private static final Pattern SPEC_REG_PAT = Pattern
			.compile(".*mmix> g\\[([0-9]{1,3})\\]=#([0-9a-f]{1,16}).*",
					Pattern.UNIX_LINES);

	// mmix> l[24]=#0
	private static final File MMIX_DIR = new File("src/test/mmix");
	protected File objectFile;
	protected List<InstructionTrace> mmixInstructions;
	protected List<RegisterTrace> mmixRegisters;
	protected Simulator simulator;

	public void runParallel(String sourceName) throws Exception {
		objectFile = assembleSourceFile(
				MMIX_DIR + File.separator + sourceName);
		runParallel();
	}

	public void runParallel()
			throws IOException, FileNotFoundException, InterruptedException {
		simulator = new Simulator();
		InstructionTracer tracer = new InstructionTracer();

		simulator.setTracer(tracer);
		new Loader(new DataInputStream(new FileInputStream(objectFile)),
				simulator).load();

		simulator.execute();
		System.out.println(simulator.dump());

		executeObjectFile(objectFile);
		compareExecutions();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	void executeObjectFile(File objectFile) throws IOException,
			InterruptedException {
		System.out.println("Running '" + objectFile + "'...");
		ProcessBuilder pb = new ProcessBuilder("bash", "-lc", "mmix -t1000 -I "
				+ objectFile.getPath());

		pb.redirectErrorStream(true);
		pb.redirectInput(ProcessBuilder.Redirect.PIPE);

		Process process = pb.start();

		askForRegisterDump(new OutputStreamWriter(process.getOutputStream()));
		Map<Class<?>, List<Object>> result = parseProcessOutput(process);

		process.waitFor(5, SECONDS);

		mmixInstructions = (List) result.getOrDefault(InstructionTrace.class,
				Collections.EMPTY_LIST);
		mmixRegisters = (List) result.getOrDefault(RegisterTrace.class,
				Collections.EMPTY_LIST);

		process.destroy();
	}

	private void askForRegisterDump(OutputStreamWriter out) throws IOException {
		for (int i = 0; i < Processor.NB_REGISTERS; i++) {
			out.write(String.format("$%d#\n", i));
			out.flush();
		}
		for (SpecialRegisterName reg : SpecialRegisterName.values()) {
			out.write(String.format("%s#\n", reg.name()));
			out.flush();
		}
		out.close();
	}

	private Map<Class<?>, List<Object>> parseProcessOutput(Process process) {
		return new BufferedReader(
				new InputStreamReader(process.getInputStream()))
						.lines()
						.map(l -> parseInterpreterLine(l))
						.filter(t -> t != null)
						.collect(Collectors.groupingBy(t -> t.getClass()));
	}

	private Trace parseInterpreterLine(String str) {
		Matcher m;

		if ((m = INST_PAT.matcher(str)).matches()) {
			return new InstructionTrace(
					Long.parseUnsignedLong(m.group(1), 16),
					Integer.parseUnsignedInt(m.group(2), 16));
		} else if ((m = GEN_REG_PAT.matcher(str)).matches()) {
			return new RegisterTrace(Integer.valueOf(m.group(1)),
					new BigInteger(
							m.group(3), 16).longValue());
		} else if ((m = SPEC_REG_PAT.matcher(str)).matches()) {
			return new RegisterTrace(Integer.valueOf(m.group(1)),
					new BigInteger(
							m.group(2), 16).longValue(),
					true);
		}
		return null;
	}

	File assembleSourceFile(String sourceFilename) throws IOException,
			InterruptedException {
		System.out.format("Assembling %s...\n", sourceFilename);

		File sourceFile = new File(sourceFilename);
		File directory = sourceFile.getParentFile();
		ProcessBuilder pb = new ProcessBuilder("bash", "-lc", "mmixal "
				+ sourceFile.getName());

		pb.directory(directory);
		pb.redirectErrorStream(true);

		Process process = pb.start();

		process.waitFor(5, SECONDS);

		ByteStreams.copy(process.getInputStream(), System.out);
		assertThat(process.exitValue(), equalTo(0));
		process.destroy();

		return new File(directory,
				Files.getNameWithoutExtension(sourceFilename) + ".mmo");
	}

	public void compareExecutions() {
		if (simulator.getTracer() == null) {
			return;
		}

		List<InstructionTrace> sims = ((InstructionTracer) simulator
				.getTracer()).getInstructions();
		int minSize = Math.min(mmixInstructions.size(), sims.size());
		int maxSize = Math.max(mmixInstructions.size(), sims.size());
		StringBuilder sb = new StringBuilder();
		boolean diff = false;

		for (int i = 0; i < minSize; i++) {
			InstructionTrace inst = mmixInstructions.get(i);
			InstructionTrace simInst = sims.get(i);

			if (!inst.equals(simInst)) {
				sb.append(inst).append(" <> ").append(simInst).append("\n");
				diff = true;
			}
		}
		if (mmixInstructions.size() > minSize) {
			diff = true;
			sb.append(mmixInstructions.size() - minSize).append(
					" extra instructions in MMIX:\n");
			for (int i = minSize; i < maxSize; i++) {
				sb.append("Extra MMIX: ").append(mmixInstructions.get(i))
						.append("\n");
			}
		}
		if (sims.size() > minSize) {
			diff = true;
			sb.append(sims.size() - minSize).append(
					" extra instructions in JMMIX\n");
			for (int i = minSize; i < maxSize; i++) {
				sb.append("Extra JMMIX: ").append(sims.get(i)).append("\n");
			}
		}

		for (int i = 0; i < Processor.NB_REGISTERS; i++) {
			long value = simulator.getProcessor().register(i);

			if (mmixRegisters.get(i).value != value) {
				diff = true;
				sb.append("Register difference: ")
						.append(mmixRegisters.get(i))
						.append(" <> ")
						.append(String.format("#%x", value))
						.append("\n");
			}
		}
		for (SpecialRegisterName regName : SpecialRegisterName.values()) {
			long value = simulator.getProcessor().specialRegister(regName);
			RegisterTrace refReg = mmixRegisters.get(256 + regName.ordinal());

			if (
			// rYY & rZZ are ignore because they are not reliably implemented by
			// mmix
			regName != rYY && regName != rZZ
					&& regName != rL // TODO: rL is not yet implemented
					&& regName != rA // TODO: rA is not yet implemented
					&& refReg.value != value) {
				diff = true;
				sb.append("Special register difference: ")
						.append(refReg)
						.append(" <> ")
						.append(String.format("#%x", value))
						.append("\n");
			}
		}
		if (diff) {
			System.out.println(sb);
			fail("There where execution differences:\n" + sb);
		}
	}
}
