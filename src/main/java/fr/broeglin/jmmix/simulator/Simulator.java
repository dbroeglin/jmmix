package fr.broeglin.jmmix.simulator;

import java.util.logging.Logger;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.*;

public class Simulator {
	private static final Logger logger = Logger.getLogger(Simulator.class
			.getName());

	private final Memory memory;
	private final Processor processor;

	public Simulator() {
		this(new Processor(), new Memory());
	}

	Simulator(Processor processor, Memory memory) {
		this.processor = processor;
		this.memory = memory;
	}

	public void execute(int instruction) {
		logger.finest(() -> String.format("Executing %08x", instruction));

		int op = (instruction & 0xff000000) >>> 24;
		int x = (instruction & 0x00ff0000) >>> 16;
		int y = (instruction & 0x0000ff00) >>> 8;
		int z = instruction & 0x000000ff;

		InstructionSet.instruction(op).op(processor, memory, x, y, z);
	}

	public void execute() {
		long instPtr = processor.register(255);

		initializeSpecialRegisters();

		do {
			execute(memory.load32(instPtr));
			instPtr += 4;
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
			long reg = getProcessor().specialRegister(name);

			if (reg != 0l) {
				sb.append(String.format("%4s = %016x\n", name, reg));
			}
		}
		for (int i = 0; i < Processor.NB_REGISTERS; i++) {
			long reg = getProcessor().register(i);

			if (reg != 0l) {
				sb.append(String.format("%4s = %016x\n", "$" + i, reg));
			}
		}

		return sb.toString();
	}
}