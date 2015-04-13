package fr.broeglin.jmmix.simulator;

public class UnknownInstruction extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnknownInstruction(int i) {
		super(String.format("Instruction %02x does not exist", i));
	}
}
