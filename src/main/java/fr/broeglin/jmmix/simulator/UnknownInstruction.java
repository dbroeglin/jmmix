package fr.broeglin.jmmix.simulator;

public class UnknownInstruction extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnknownInstruction(int i) {
		super(String.format("Instruction %02x does not exist", i));
	}

	public UnknownInstruction(int i, int x, int y, int z) {
		super(String.format("Instruction %02x %02x%02x%02x does not exist",
				i, x, y, z));
	}

}
