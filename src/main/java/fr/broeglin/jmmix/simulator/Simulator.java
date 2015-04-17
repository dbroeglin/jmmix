package fr.broeglin.jmmix.simulator;

import java.util.logging.Logger;

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
		initializeSpecialRegisters();

		long currentPosition = processor.register(255);

		do {
			execute(memory.load32(currentPosition));
			currentPosition += 4;
		} while (processor.isRunning());
	}

	private void initializeSpecialRegisters() {
		processor.rA = 0;
		processor.rB = 0;
		processor.rD = 0;
		processor.rE = 0;
		processor.rF = 0;
		processor.rH = 0;
		processor.rI = 0;
		processor.rJ = 0;
		processor.rM = 0;
		processor.rP = 0;
		processor.rQ = 0;
		processor.rR = 0;
		processor.rL = 2;
	}

	public Memory getMemory() {
		return this.memory;
	}

	public Processor getProcessor() {
		return this.processor;
	}

	public String dump() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < Processor.NB_REGISTERS; i++) {
			long reg = getProcessor().register(i);

			if (reg != 0l) {
				sb.append(String.format("$%d = %016x\n", i, reg));
			}
		}

		return sb.toString();
	}
}
