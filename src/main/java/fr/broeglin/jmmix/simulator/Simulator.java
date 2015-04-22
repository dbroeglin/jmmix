package fr.broeglin.jmmix.simulator;

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
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rP;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rQ;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rR;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rT;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rTT;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rV;

import java.util.logging.Logger;

import fr.broeglin.jmmix.simulator.trace.Tracer;

public class Simulator {
	private static final Logger logger = Logger.getLogger(Simulator.class
			.getName());

	private final Memory memory;
	private final Processor processor;
	private Tracer tracer = null;

	public Simulator() {
		this(new Processor(), new Memory());
	}

	Simulator(Processor processor, Memory memory) {
		this.processor = processor;
		this.memory = memory;
	}

	public void execute(int instruction) {
		traceInstruction(instruction);

		int op = (instruction & 0xff000000) >>> 24;
		int x = (instruction & 0x00ff0000) >>> 16;
		int y = (instruction & 0x0000ff00) >>> 8;
		int z = instruction & 0x000000ff;

		Instruction inst = InstructionSet.instruction(op);
		if (inst == null) {
			throw new UnknownInstruction(op);
		}

		inst.op(processor, memory, x, y, z);
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
			execute(memory.load32(processor.instPtr()));
			processor.incInstPtr(1);
		} while (processor.isRunning());
	}

	void initializeSpecialRegisters() {
		processor.setSpecialRegister(rK, 0xffffffffffffffffl);
		processor.setSpecialRegister(rL, 0x2l);
		processor.setSpecialRegister(rT, 0x8000000500000000l);
		processor.setSpecialRegister(rV, 0x369c200400000000l);
		processor.setSpecialRegister(rTT, 0x8000000600000000l);

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