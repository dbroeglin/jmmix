package fr.broeglin.jmmix.simulator.instructions;

import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;

public class LoadInstructions {

	public static void LDB(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, mem.load8(proc.register(y) + proc.register(z)));
		proc.cost(1, 1);
	}

	public static void LDBU(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x,
				0xff & mem.load8(proc.register(y) + proc.register(z)));
		proc.cost(1, 1);
	}

	public static void LDBI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, mem.load8(proc.register(y) + z));
		proc.cost(1, 1);
	}

	public static void LDBUI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, 0xff & mem.load8(proc.register(y) + z));
		proc.cost(1, 1);
	}

	public static void LDW(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, mem.load16(proc.register(y) + proc.register(z)));
		proc.cost(1, 1);
	}

	public static void LDWU(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x,
				0xffff & mem.load16(proc.register(y) + proc.register(z)));
		proc.cost(1, 1);
	}

	public static void LDWI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, mem.load16(proc.register(y) + z));
		proc.cost(1, 1);
	}

	public static void LDWUI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, 0xffff & mem.load16(proc.register(y) + z));
		proc.cost(1, 1);
	}

	public static void LDT(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, mem.load32(proc.register(y) + proc.register(z)));
		proc.cost(1, 1);
	}

	public static void LDTU(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x,
				0xffff_ffffl & mem.load32(proc.register(y) + proc.register(z)));
		proc.cost(1, 1);
	}

	public static void LDTUI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, 0xffff_ffffl & mem.load32(proc.register(y) + z));
		proc.cost(1, 1);
	}

	public static void LDTI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, mem.load32(proc.register(y) + z));
		proc.cost(1, 1);
	}

	public static void LDO(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, mem.load64(proc.register(y) + proc.register(z)));
		proc.cost(1, 1);
	}

	public static void LDOU(Processor proc, Memory mem, int x, int y, int z) {
		LDO(proc, mem, x, y, z);
	}

	public static void LDOUI(Processor proc, Memory mem, int x, int y, int z) {
		LDOI(proc, mem, x, y, z);
	}

	public static void LDOI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, mem.load64(proc.register(y) + z));
		proc.cost(1, 1);
	}
}
