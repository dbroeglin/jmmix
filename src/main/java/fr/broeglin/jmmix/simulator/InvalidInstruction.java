package fr.broeglin.jmmix.simulator;

public class InvalidInstruction extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidInstruction(int i, int x, int y, int z) {
		super(String.format("Instruction %02x %02x%02x%02x is not valid",
				i, x, y, z));
	}

}
