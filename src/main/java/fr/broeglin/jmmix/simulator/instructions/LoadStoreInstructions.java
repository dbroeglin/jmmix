package fr.broeglin.jmmix.simulator.instructions;

import static fr.broeglin.jmmix.simulator.Processor.rA_V_MASK;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rA;

import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;
import fr.broeglin.jmmix.simulator.SpecialRegisterName;

public class LoadStoreInstructions {

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

	public static void STB(Processor proc, Memory mem, int x, int y, int z) {
		long v = proc.register(x);

		mem.store8(proc.register(y) + proc.register(z), (byte) v);
		proc.setRa(rA_V_MASK, v != (v << 56 >>> 56));
		proc.cost(1, 1);
	}

	public static void STBI(Processor proc, Memory mem, int x, int y, int z) {
		long v = proc.register(x);

		mem.store8(proc.register(y) + (0xff & z), (byte) v);
		proc.setRa(rA_V_MASK, v != (v << 56 >>> 56));
		proc.cost(1, 1);
	}

	public static void STBU(Processor proc, Memory mem, int x, int y, int z) {
		mem.store8(proc.register(y) + proc.register(z),
				(byte) proc.register(x));
		proc.cost(1, 1);
	}

	public static void STBUI(Processor proc, Memory mem, int x, int y, int z) {
		mem.store8(proc.register(y) + (0xff & z), (short) proc.register(x));
		proc.cost(1, 1);
	}

	public static void STW(Processor proc, Memory mem, int x, int y, int z) {
		long v = proc.register(x);

		mem.store16(proc.register(y) + proc.register(z), (short) v);
		proc.setRa(rA_V_MASK, v != (v << 48 >>> 48));
		proc.cost(1, 1);
	}

	public static void STWI(Processor proc, Memory mem, int x, int y, int z) {
		long v = proc.register(x);

		mem.store16(proc.register(y) + (0xff & z), (short) v);
		proc.setRa(rA_V_MASK, v != (v << 48 >>> 48));
		proc.cost(1, 1);
	}

	public static void STWU(Processor proc, Memory mem, int x, int y, int z) {
		mem.store16(proc.register(y) + proc.register(z),
				(short) proc.register(x));
		proc.cost(1, 1);
	}

	public static void STWUI(Processor proc, Memory mem, int x, int y, int z) {
		mem.store16(proc.register(y) + (0xff & z), (short) proc.register(x));
		proc.cost(1, 1);
	}

}
