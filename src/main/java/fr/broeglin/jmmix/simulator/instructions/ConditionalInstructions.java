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

	public static void CSP(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(y) > 0) {
			proc.setRegister(x, proc.register(z));
		}
		proc.cost(0, 1);
	}

	public static void CSPI(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(y) > 0) {
			proc.setRegister(x, (byte) z & 0xff);
		}
		proc.cost(0, 1);
	}

	public static void CSOD(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(y) % 2 != 0) {
			proc.setRegister(x, proc.register(z));
		}
		proc.cost(0, 1);
	}

	public static void CSODI(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(y) % 2 != 0) {
			proc.setRegister(x, (byte) z & 0xff);
		}
		proc.cost(0, 1);
	}

	public static void CSNN(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(y) >= 0) {
			proc.setRegister(x, proc.register(z));
		}
		proc.cost(0, 1);
	}

	public static void CSNNI(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(y) >= 0) {
			proc.setRegister(x, (byte) z & 0xff);
		}
		proc.cost(0, 1);
	}

	public static void CSNZ(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(y) != 0) {
			proc.setRegister(x, proc.register(z));
		}
		proc.cost(0, 1);
	}

	public static void CSNZI(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(y) != 0) {
			proc.setRegister(x, (byte) z & 0xff);
		}
		proc.cost(0, 1);
	}

	public static void CSNP(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(y) <= 0) {
			proc.setRegister(x, proc.register(z));
		}
		proc.cost(0, 1);
	}

	public static void CSNPI(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(y) <= 0) {
			proc.setRegister(x, (byte) z & 0xff);
		}
		proc.cost(0, 1);
	}

	public static void CSEV(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(y) % 2 == 0) {
			proc.setRegister(x, proc.register(z));
		}
		proc.cost(0, 1);
	}

	public static void CSEVI(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(y) % 2 == 0) {
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

	public static void ZSP(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) > 0 ? proc.register(z) : 0);
		proc.cost(0, 1);
	}

	public static void ZSPI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) > 0 ? (byte) z & 0xff : 0);
		proc.cost(0, 1);
	}

	public static void ZSOD(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) % 2 != 0 ? proc.register(z) : 0);
		proc.cost(0, 1);
	}

	public static void ZSODI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) % 2 != 0 ? (byte) z & 0xff : 0);
		proc.cost(0, 1);
	}

	public static void ZSNN(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) >= 0 ? proc.register(z) : 0);
		proc.cost(0, 1);
	}

	public static void ZSNNI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) >= 0 ? (byte) z & 0xff : 0);
		proc.cost(0, 1);
	}

	public static void ZSNZ(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) != 0 ? proc.register(z) : 0);
		proc.cost(0, 1);
	}

	public static void ZSNZI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) != 0 ? (byte) z & 0xff : 0);
		proc.cost(0, 1);
	}

	public static void ZSNP(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) <= 0 ? proc.register(z) : 0);
		proc.cost(0, 1);
	}

	public static void ZSNPI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) <= 0 ? (byte) z & 0xff : 0);
		proc.cost(0, 1);
	}

	public static void ZSEV(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) % 2 == 0 ? proc.register(z) : 0);
		proc.cost(0, 1);
	}

	public static void ZSEVI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) % 2 == 0 ? (byte) z & 0xff : 0);
		proc.cost(0, 1);
	}
}
