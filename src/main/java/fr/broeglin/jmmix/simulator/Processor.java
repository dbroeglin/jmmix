package fr.broeglin.jmmix.simulator;

import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rA;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rI;

public class Processor {

	public static final int NB_REGISTERS = 256;
	
	// Mask for special register rA, if set integer overflow occured
	public static final long rA_V_MASK = 0x40l;
	
	private final long[] registers = new long[NB_REGISTERS];
	private long instPtr;
	private int instruction;
	private boolean running = true;

	private int op;
	private int x;
	private int y;
	private int z;

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

	public void incSpecialRegister(SpecialRegisterName name, long value) {
		specialRegisters[name.ordinal()] += value;
	}

	public void setSpecialRegisterHigh(SpecialRegisterName name, int value) {
		specialRegisters[name.ordinal()] = specialRegisters[name.ordinal()]
				& 0x0000_0000_ffff_ffffl | ((long) value << 32);
	}

	public void setSpecialRegisterLow(SpecialRegisterName name, int value) {
		specialRegisters[name.ordinal()] = specialRegisters[name.ordinal()]
				& 0xffff_ffff_0000_0000l | 0xffff_ffffl & value;
	}
	
	public void setRa(long mask, boolean v) {
		long current = specialRegister(rA);
		
		setSpecialRegister(rA, v ? current | mask : current & ~mask);
	}
	
	public boolean rA(long mask) {
		return (specialRegister(rA) & mask) == mask; 
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

	public long instPtr() {
		return instPtr;
	}

	public void incInstPtr(int offset) {
		instPtr += offset * 4;
	}

	public void setInstPtr(long instPtr) {
		this.instPtr = instPtr;
	}

	public int op() {
		return op;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public int z() {
		return z;
	}

	public int yz() {
		return 0xffff & (y << 8 | z);
	}
	
	public void loadInstruction(Memory memory) {
		decodeInstruction(instruction = memory.load32(instPtr));
	}

	public void decodeInstruction(int instruction) {
		op = (instruction & 0xff000000) >>> 24;
		x = (instruction & 0x00ff0000) >>> 16;
		y = (instruction & 0x0000ff00) >>> 8;
		z = instruction & 0x000000ff;
	}

	public int instruction() {
		return instruction;
	}
	
	public void cost(int mu, int nu) {
		// TODO: really calculate cost
		incSpecialRegister(rI, -nu);
	}

}
