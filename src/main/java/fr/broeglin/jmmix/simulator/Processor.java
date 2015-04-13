package fr.broeglin.jmmix.simulator;

public class Processor {

	public static final int NB_REGISTERS = 256;
	private final long[] registers = new long[NB_REGISTERS];
	private boolean running = true;
	
	long rB;
	long rD;
	long rE;
	long rH;
	long rJ;
	long rM;
	long rR;
	long rBB;
	long rC;
	long rN;
	long rO;
	long rS;
	long rI;
	long rT;
	long rTT;
	long rK;
	long rQ;
	long rU;
	long rV;
	long rG;
	long rL;
	long rA;
	long rF;
	long rP;
	long rW;
	long rX;
	long rY;
	long rZ;
	long rWW;
	long rXX;
	long rYY;
	long rZZ;

	public long register(int i) {
		checkRegisterIndex(i);
		return registers[i];
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
