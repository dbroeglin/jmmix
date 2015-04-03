package fr.broeglin.jmmix.simulator;

public final class InstructionSet {

	public static Instruction instruction(int op) {
		if (op < 0 || op > instructions.length) {
			throw new UnknownInstruction(op);
		}
		return instructions[op];
	}

	private static final Instruction[] instructions = new Instruction[] {
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		InstructionSet::ADD,
		null,		
		InstructionSet::ADDU
	};
	
	public static void ADD(Simulator simulator, int x, int y, int z) {
		simulator.setRegister(x, simulator.register(y) + simulator.register(z));
	}

	public static void ADDU(Simulator simulator, int x, int y, int z) {
		simulator.setRegister(x, simulator.register(y) + simulator.register(z));
	}

}
