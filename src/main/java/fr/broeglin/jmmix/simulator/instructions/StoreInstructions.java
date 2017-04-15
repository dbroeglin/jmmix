package fr.broeglin.jmmix.simulator.instructions;

import static fr.broeglin.jmmix.simulator.Processor.V_BIT;

import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;

public class StoreInstructions {

	public static void STB(Processor proc, Memory mem, int x, int y, int z) {
		long v = proc.register(x);

		mem.store8(proc.register(y) + proc.register(z), (byte) v);
		if (v != (v << 56 >>> 56)) {
			proc.setRa(V_BIT, true);
		}
		proc.cost(1, 1);
	}

	public static void STBI(Processor proc, Memory mem, int x, int y, int z) {
		long v = proc.register(x);

		mem.store8(proc.register(y) + (0xff & z), (byte) v);
		if (v != (v << 56 >>> 56)) {
			proc.setRa(V_BIT, true);
		}
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
		if (v != (v << 48 >>> 48)) {
			proc.setRa(V_BIT, true);
		}
		proc.cost(1, 1);
	}

	public static void STWI(Processor proc, Memory mem, int x, int y, int z) {
		long v = proc.register(x);

		mem.store16(proc.register(y) + (0xff & z), (short) v);
		if (v != (v << 48 >>> 48)) {
			proc.setRa(V_BIT, true);
		}
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

	public static void STT(Processor proc, Memory mem, int x, int y, int z) {
		long v = proc.register(x);

		mem.store32(proc.register(y) + proc.register(z), (int) v);
		if (v != (v << 32 >>> 32)) {
			proc.setRa(V_BIT, true);
		}
		proc.cost(1, 1);
	}

	public static void STTI(Processor proc, Memory mem, int x, int y, int z) {
		long v = proc.register(x);

		mem.store32(proc.register(y) + (0xff & z), (int) v);
		if (v != (v << 32 >>> 32)) {
			proc.setRa(V_BIT, true);
		}
		proc.cost(1, 1);
	}

	public static void STTU(Processor proc, Memory mem, int x, int y, int z) {
		long v = proc.register(x);

		mem.store32(proc.register(y) + proc.register(z), (int) v);
		proc.cost(1, 1);
	}

	public static void STTUI(Processor proc, Memory mem, int x, int y, int z) {
		long v = proc.register(x);

		mem.store32(proc.register(y) + (0xff & z), (int) v);
		proc.cost(1, 1);
	}

	//
	// Store Octas
	//

	public static void STO(Processor proc, Memory mem, int x, int y, int z) {
		mem.store64(proc.register(y) + proc.register(z), proc.register(x));
		proc.cost(1, 1);
	}

	public static void STOI(Processor proc, Memory mem, int x, int y, int z) {
		mem.store64(proc.register(y) + (0xff & z), proc.register(x));
		proc.cost(1, 1);
	}

	public static void STOU(Processor proc, Memory mem, int x, int y, int z) {
		mem.store64(proc.register(y) + proc.register(z), proc.register(x));
		proc.cost(1, 1);
	}

	public static void STOUI(Processor proc, Memory mem, int x, int y, int z) {
		mem.store64(proc.register(y) + (0xff & z), proc.register(x));
		proc.cost(1, 1);
	}
}
