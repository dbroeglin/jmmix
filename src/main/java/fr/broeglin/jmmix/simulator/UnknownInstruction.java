package fr.broeglin.jmmix.simulator;

public class UnknownInstruction extends RuntimeException {

	public UnknownInstruction(int i) {
		super(String.format("Instruction %02x does not exist", i));
	}
}
