package fr.broeglin.jmmix.simulator.instructions;

import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;

public class BranchInstructions {

	public static void BN(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(x) < 0) {
			proc.incInstPtr(proc.yz() - 1);
			proc.cost(0, 3);
		} else {
			proc.cost(0, 1);
		}
	}

	public static void BNB(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(x) < 0) {
			proc.incInstPtr(proc.yz() - 0x10000 - 1);
			proc.cost(0, 3);
		} else {
			proc.cost(0, 1);
		}
	}

	public static void BZ(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(x) == 0) {
			proc.incInstPtr(proc.yz() - 1);
			proc.cost(0, 3);
		} else {
			proc.cost(0, 1);
		}
	}

	public static void BZB(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(x) == 0) {
			proc.incInstPtr(proc.yz() - 0x10000 - 1);
			proc.cost(0, 3);
		} else {
			proc.cost(0, 1);
		}
	}

	public static void BNZ(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(x) != 0) {
			proc.incInstPtr(proc.yz() - 1);
			proc.cost(0, 3);
		} else {
			proc.cost(0, 1);
		}
	}

	public static void BNZB(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(x) != 0) {
			proc.incInstPtr(proc.yz() - 0x10000 - 1);
			proc.cost(0, 3);
		} else {
			proc.cost(0, 1);
		}
	}

	public static void BP(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(x) > 0) {
			proc.incInstPtr(proc.yz() - 1);
			proc.cost(0, 3);
		} else {
			proc.cost(0, 1);
		}
	}

	public static void BPB(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(x) > 0) {
			proc.incInstPtr(proc.yz() - 0x10000 - 1);
			proc.cost(0, 3);
		} else {
			proc.cost(0, 1);
		}
	}
	
	public static void BOD(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(x) % 2 != 0) {
			proc.incInstPtr(proc.yz() - 1);
			proc.cost(0, 3);
		} else {
			proc.cost(0, 1);
		}
	}

	public static void BODB(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(x) % 2 != 0) {
			proc.incInstPtr(proc.yz() - 0x10000 - 1);
			proc.cost(0, 3);
		} else {
			proc.cost(0, 1);
		}
	}
}
