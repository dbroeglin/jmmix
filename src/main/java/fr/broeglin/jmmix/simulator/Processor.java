package fr.broeglin.jmmix.simulator;

public class Processor {

	public static final int NB_REGISTERS = 256;
	private final long[] registers = new long[NB_REGISTERS];
	private boolean running = true;

	// Arithmetic status register
	long rA;
	
	// Bootstrap register
	long rB;
	
	// Continuation register
	long rC;
	
	// Dividend register
	long rD;
	
	// Epsilon register
	long rE;
	
	// Failure location register
	long rF = 0l; // constant
	
	// Global threshold register
	long rG;
	
	// Himult register
	long rH;

	// Interval counter
	long rI;
	
	// Return-jump register
	long rJ;
	
	// Interrupt mask register
	long rK = 0xffffffffffffffffl; // constant
	
	// Local threshold register
	long rL;
	
	// Multiplex mask register
	long rM;
	
	// Serial number
	long rN;
	
	// Register stack offset
	long rO;
	
	// Prediction register
	long rP;

	// Interrupt request register
	long rQ = 0l; // constant
	
	// Remainder register
	long rR;
	
	// Register stack pointer
	long rS;
	
	// Trap address register
	long rT = 0x8000000500000000l; // constant
	
	// Usage counter
	long rU;

	// Virtual translation register
	long rV = 0x369c200400000000l; // constant

	// Where-interrupted register
	long rW;
	
	// Execution register
	long rX;
	
	// Y operand
	long rY;
	
	// Z operand
	long rZ;

	// Bootstrap register
	long rBB;

	// Dynamic trap address register
	long rTT = 0x8000000600000000l; // constant
	
	// Where-interrupted register
	long rWW;
	
	// Execution register
	long rXX;
	
	// Y operand
	long rYY;
	
	// Z operand
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
