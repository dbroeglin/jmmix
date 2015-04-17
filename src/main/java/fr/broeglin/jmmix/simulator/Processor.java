package fr.broeglin.jmmix.simulator;

public class Processor {

	public static final int NB_REGISTERS = 256;
	private final long[] registers = new long[NB_REGISTERS];
	private boolean running = true;


	long specialRegisters[] = new long[SpecialRegisterName.values().length];

	public long register(int i) {
		checkRegisterIndex(i);
		return registers[i];
	}

	public long specialRegister(SpecialRegisterName name) {
		return specialRegisters[name.ordinal()];
	}

	public void setSpecialRegister(SpecialRegisterName name, long value) {
		specialRegisters[name.ordinal()] = value;
	}

	public void setRegister(int i, long value) {
		checkRegisterIndex(i);
		registers[i] = value;
	}

	private void checkRegisterIndex(int i) {
		if (i < 0 || i >= Processor.NB_REGISTERS) {
			throw new UnknownRegister(i);
		}
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
