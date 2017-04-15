package fr.broeglin.jmmix.simulator;

import static fr.broeglin.jmmix.simulator.Memory.POOL_SEGMENT;
import static fr.broeglin.jmmix.simulator.Memory.STACK_SEGMENT;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rA;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rB;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rD;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rE;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rF;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rH;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rI;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rJ;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rK;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rL;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rM;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rN;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rO;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rP;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rQ;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rR;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rS;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rT;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rTT;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rU;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rV;

import java.nio.charset.Charset;
import java.util.logging.Logger;

import fr.broeglin.jmmix.simulator.instructions.Instruction;
import fr.broeglin.jmmix.simulator.instructions.InstructionSet;
import fr.broeglin.jmmix.simulator.trace.Tracer;

public class Simulator {
	private static final Logger logger = Logger.getLogger(Simulator.class
			.getName());

	private final Memory memory;
	private final Processor processor;
	private Tracer tracer = null;

	public Simulator() {
		this(new Processor(), new Memory(), new String[] { "sim" });
	}

	Simulator(Processor processor, Memory memory, String[] args) {
		if (args == null || args.length < 1) {
			throw new IllegalArgumentException(
					"args should at least contain program name");
		}
		this.processor = processor;
		this.memory = memory;

		loadCommandLineArguments(args);
		// this.processor.setRegister(2, 2); // TODO: ???
	}

	private void loadCommandLineArguments(String[] args) {
		long lastPosition = POOL_SEGMENT + 8;

		this.processor.setRegister(0, args.length);
		this.processor.setRegister(1, lastPosition);
		lastPosition += 8 * args.length;
		this.memory.store64(lastPosition, 0l);

		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			lastPosition += 8;

			this.memory.store64(Memory.POOL_SEGMENT + 8 + 8 * i,
					lastPosition);
			byte[] bytes = arg.getBytes(Charset.forName("UTF-8"));
			for (int j = 0; j < bytes.length; j++) {
				this.memory.store8(lastPosition + j, bytes[j]);
			}
			lastPosition += bytes.length;
			lastPosition = lastPosition - (lastPosition % 8) + 8;
		}
		this.memory.store64(POOL_SEGMENT, lastPosition);
		// TODO: initialize arguments
	}

	private void traceInstruction(int instruction) {
		logger.finest(() -> String.format("         1. %016x: %08x",
				processor.instPtr(), instruction));
		if (tracer != null) {
			tracer.instruction(processor.instPtr(), instruction);
		}
	}

	public void execute() {
		processor.setInstPtr(processor.register(255));

		initializeSpecialRegisters();

		do {
			executeInstruction();
		} while (processor.isRunning());
	}

	void executeInstruction() {
		processor.loadInstruction(memory);
		traceInstruction(processor.instruction());
		processor.incInstPtr(1);

		Instruction inst = InstructionSet.instruction(processor.op());
		if (inst == null) {
			throw new UnknownInstruction(processor.op());
		}

		inst.op(processor, memory, processor.x(), processor.y(),
				processor.z());

		// TODO: should be mod 2^27 ?
		processor.incSpecialRegister(rU, 1);
				
		// TODO: should this be done here ?
	}

	void initializeSpecialRegisters() {
		processor.setSpecialRegister(rK, 0xffffffffffffffffl);
		processor.setSpecialRegister(rL, 0x2l);

		// rN.h contains = (VERSION << 24) + (SUBVERSION << 16) + (SUBSUBVERSION
		// << 8)
		// rN.l contains the compilation date. Here we set it to the same value
		// as MMIX ???
		processor.setSpecialRegister(rN, 0x0100_0100_581E_0000l);
		processor.setSpecialRegister(rT, 0x8000000500000000l);

		processor.setSpecialRegister(rV, 0x369c200400000000l);
		processor.setSpecialRegister(rTT, 0x8000000600000000l);

		processor.setSpecialRegister(rS, STACK_SEGMENT);
		processor.setSpecialRegister(rO, STACK_SEGMENT);
		processor.setSpecialRegister(rI, 0);

		for (SpecialRegisterName name : new SpecialRegisterName[] { rA, rB, rD,
				rE, rF, rH, rI, rJ, rM, rP, rQ, rR }) {
			processor.setSpecialRegister(name, 0l);
		}
	}

	public Memory getMemory() {
		return this.memory;
	}

	public Processor getProcessor() {
		return this.processor;
	}

	public String dump() {
		StringBuilder sb = new StringBuilder();

		for (SpecialRegisterName name : SpecialRegisterName.values()) {
			dumpRegister(sb, name.toString(),
					getProcessor().specialRegister(name));
		}
		for (int i = 0; i < Processor.NB_REGISTERS; i++) {
			dumpRegister(sb, "$" + i, getProcessor().register(i));
		}
		getMemory().dump(sb);

		return sb.toString();
	}

	private void dumpRegister(StringBuilder sb, String name, long value) {
		if (value != 0l) {
			sb.append(String.format("%4s = %016x\n", name, value));
		}

	}

	public Tracer getTracer() {
		return tracer;
	}

	public void setTracer(Tracer tracer) {
		this.tracer = tracer;
	}

}