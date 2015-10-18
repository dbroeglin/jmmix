package fr.broeglin.jmmix.simulator.instructions;

import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rR;

import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;

public class ArithmeticInstructions {
	public static void _2ADDU(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) * 2 + proc.register(z));
		proc.cost(0, 1);
	}

	public static void _2ADDUI(Processor proc, Memory mem, int x, int y,
			int z) {
		proc.setRegister(x, proc.register(y) * 2 + z);
		proc.cost(0, 1);
	}

	public static void _4ADDU(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) * 4 + proc.register(z));
		proc.cost(0, 1);
	}

	public static void _4ADDUI(Processor proc, Memory mem, int x, int y,
			int z) {
		proc.setRegister(x, proc.register(y) * 4 + z);
		proc.cost(0, 1);
	}

	public static void _8ADDU(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) * 8 + proc.register(z));
		proc.cost(0, 1);
	}

	public static void _8ADDUI(Processor proc, Memory mem, int x, int y,
			int z) {
		proc.setRegister(x, proc.register(y) * 8 + z);
		proc.cost(0, 1);
	}

	public static void _16ADDU(Processor proc, Memory mem, int x, int y,
			int z) {
		proc.setRegister(x, proc.register(y) * 16 + proc.register(z));
		proc.cost(0, 1);
	}

	public static void _16ADDUI(Processor proc, Memory mem, int x, int y,
			int z) {
		proc.setRegister(x, proc.register(y) * 16 + z);
		proc.cost(0, 1);
	}

	public static void ADD(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) + proc.register(z));
		proc.cost(0, 1);
	}

	public static void ADDI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) + z);
		proc.cost(0, 1);
	}

	public static void ADDU(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) + proc.register(z));
		proc.cost(0, 1);
	}

	public static void ADDUI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) + z);
		proc.cost(0, 1);
	}

	public static void SUB(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) - proc.register(z));
		proc.cost(0, 1);
	}

	public static void SUBI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) - z);
		proc.cost(0, 1);
	}

	public static void SUBU(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) - proc.register(z));
		proc.cost(0, 1);
	}

	public static void SUBUI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) - z);
		proc.cost(0, 1);
	}

	public static void OR(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) | proc.register(z));
		proc.cost(0, 1);
	}
	
	public static void ORI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) | (byte) z);
		proc.cost(0, 1);
	}

	public static void XOR(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) ^ proc.register(z));
		proc.cost(0, 1);
	}

	public static void XORI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) ^ (byte) z);
		proc.cost(0, 1);
	}

	public static void NXOR(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, ~(proc.register(y) ^ proc.register(z)));
		proc.cost(0, 1);
	}

	public static void NXORI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, ~(proc.register(y) ^ (byte) z));
		proc.cost(0, 1);
	}

	public static void ORN(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) | ~proc.register(z));
		proc.cost(0, 1);
	}

	public static void ORNI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) | ~(byte) z);
		proc.cost(0, 1);
	}

	public static void AND(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) & proc.register(z));
		proc.cost(0, 1);
	}

	public static void ANDI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) & (byte) z);
		proc.cost(0, 1);
	}

	public static void NAND(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, ~(proc.register(y) & proc.register(z)));
		proc.cost(0, 1);
	}

	public static void NANDI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, ~(proc.register(y) & (byte) z));
		proc.cost(0, 1);
	}

	public static void ANDN(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) & ~proc.register(z));
		proc.cost(0, 1);
	}

	public static void ANDNI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) & ~(byte) z);
		proc.cost(0, 1);
	}

	public static void NOR(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, ~(proc.register(y) | proc.register(z)));
		proc.cost(0, 1);
	}

	public static void NORI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, ~(proc.register(y) | (byte) z));
		proc.cost(0, 1);
	}


	public static void DIV(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(z) == 0) {
			proc.setRegister(x, 0);
		} else {
			long yy = proc.register(y);
			long zz = proc.register(z);

			proc.setRegister(x, yy / zz);
			proc.setSpecialRegister(rR, yy % zz);
		}
		proc.cost(0, 60);
	}
}
