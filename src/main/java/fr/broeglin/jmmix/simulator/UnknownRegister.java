package fr.broeglin.jmmix.simulator;

public class UnknownRegister extends RuntimeException {

	public UnknownRegister(int i) {
		super("Register " + i + " does not exist");
	}
}
