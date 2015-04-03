package fr.broeglin.jmmix.simulator;

import java.util.function.Function;

public class Simulator {

	public static final int NB_REGISTERS = 256;

	private final long[] registers = new long[NB_REGISTERS];

	public long register(int i) {
		checkRegisterIndex(i);
		return registers[i];
	}

	public void setRegister(int i, long value) {
		checkRegisterIndex(i);
		this.registers[i] = value;
	}

	private void checkRegisterIndex(int i) {
		if (i < 0 || i >= NB_REGISTERS) {
			throw new UnknownRegister(i);
		}
	}

	public Object displayOcta(long value) {
		return String.format("#%016X", value);
	}

	public void execute(int instruction) {

		int op = (instruction & 0xff000000) >> 24;
		int x = (instruction & 0x00ff0000) >> 16;
		int y = (instruction & 0x0000ff00) >> 8;
		int z = instruction & 0x000000ff;

		InstructionSet.instruction(op).op(this, x, y, z);
	}


}
