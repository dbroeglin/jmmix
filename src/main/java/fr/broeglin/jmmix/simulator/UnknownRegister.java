package fr.broeglin.jmmix.simulator;

public class UnknownRegister extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnknownRegister(int i) {
		super("Register " + i + " does not exist");
	}
}
