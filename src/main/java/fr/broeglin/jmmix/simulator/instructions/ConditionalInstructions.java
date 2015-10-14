package fr.broeglin.jmmix.simulator.instructions;

import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;

public class ConditionalInstructions {
	public static void CSZ(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(y) == 0) {
			proc.setRegister(x, proc.register(z));
		}
		proc.cost(0, 1);
	}

	public static void CSZI(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(y) == 0) {
			proc.setRegister(x, (byte) z & 0xff);
		}
		proc.cost(0, 1);
	}

	public static void CSN(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(y) < 0) {
			proc.setRegister(x, proc.register(z));
		}
		proc.cost(0, 1);
	}

	public static void CSNI(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(y) < 0) {
			proc.setRegister(x, (byte) z & 0xff);
		}
		proc.cost(0, 1);
	}
	
	public static void ZSZ(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) == 0 ? proc.register(z) : 0);
		proc.cost(0, 1);
	}

	public static void ZSZI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) == 0 ? (byte) z & 0xff : 0);
		proc.cost(0, 1);
	}
	
	public static void ZSN(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) < 0 ? proc.register(z) : 0);
		proc.cost(0, 1);
	}

	public static void ZSNI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) < 0 ? (byte) z & 0xff : 0);
		proc.cost(0, 1);
	}
}
