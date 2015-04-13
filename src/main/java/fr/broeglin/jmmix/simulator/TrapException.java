package fr.broeglin.jmmix.simulator;

public class TrapException extends Exception {
	private static final long serialVersionUID = 1L;

	private int exitCode;

	TrapException(int exitCode) {
		this.exitCode = exitCode;

	}

	public int getExitCode() {
		return exitCode;
	}
}
