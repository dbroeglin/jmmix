package fr.broeglin.jmmix.simulator.instructions;

import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;

public class BranchInstructions {

	public static void BN(Processor proc, Memory mem, int x, int y, int z) {
		branchForward(proc, proc.register(x) < 0);
	}

	public static void BNB(Processor proc, Memory mem, int x, int y, int z) {
		branchBack(proc, proc.register(x) < 0);
	}

	public static void BZ(Processor proc, Memory mem, int x, int y, int z) {
		branchForward(proc, proc.register(x) == 0);
	}

	public static void BZB(Processor proc, Memory mem, int x, int y, int z) {
		branchBack(proc, proc.register(x) == 0);
	}

	public static void BP(Processor proc, Memory mem, int x, int y, int z) {
		branchForward(proc, proc.register(x) > 0);
	}

	public static void BPB(Processor proc, Memory mem, int x, int y, int z) {
		branchBack(proc, proc.register(x) > 0);
	}

	public static void BOD(Processor proc, Memory mem, int x, int y, int z) {
		branchForward(proc, proc.register(x) % 2 != 0);
	}

	public static void BODB(Processor proc, Memory mem, int x, int y, int z) {
		branchBack(proc, proc.register(x) % 2 != 0);
	}

	public static void BNN(Processor proc, Memory mem, int x, int y, int z) {
		branchForward(proc, proc.register(x) >= 0);
	}

	public static void BNNB(Processor proc, Memory mem, int x, int y, int z) {
		branchBack(proc, proc.register(x) >= 0);
	}

	public static void BNZ(Processor proc, Memory mem, int x, int y, int z) {
		branchForward(proc, proc.register(x) != 0);
	}

	public static void BNZB(Processor proc, Memory mem, int x, int y, int z) {
		branchBack(proc, proc.register(x) != 0);
	}
	
	public static void BNP(Processor proc, Memory mem, int x, int y, int z) {
		branchForward(proc, proc.register(x) <= 0);
	}

	public static void BNPB(Processor proc, Memory mem, int x, int y, int z) {
		branchBack(proc, proc.register(x) <= 0);
	}	

	public static void BEV(Processor proc, Memory mem, int x, int y, int z) {
		branchForward(proc, proc.register(x) % 2 == 0);
	}

	public static void BEVB(Processor proc, Memory mem, int x, int y, int z) {
		branchBack(proc, proc.register(x) % 2 == 0);
	}
	
	public static void PBN(Processor proc, Memory mem, int x, int y, int z) {
		probableBranchForward(proc, proc.register(x) < 0);
	}

	public static void PBNB(Processor proc, Memory mem, int x, int y, int z) {
		probableBranchBack(proc, proc.register(x) < 0);
	}

	public static void PBZ(Processor proc, Memory mem, int x, int y, int z) {
		probableBranchForward(proc, proc.register(x) == 0);
	}

	public static void PBZB(Processor proc, Memory mem, int x, int y, int z) {
		probableBranchBack(proc, proc.register(x) == 0);
	}

	public static void PBP(Processor proc, Memory mem, int x, int y, int z) {
		probableBranchForward(proc, proc.register(x) > 0);
	}

	public static void PBPB(Processor proc, Memory mem, int x, int y, int z) {
		probableBranchBack(proc, proc.register(x) > 0);
	}

	public static void PBOD(Processor proc, Memory mem, int x, int y, int z) {
		probableBranchForward(proc, proc.register(x) % 2 != 0);
	}

	public static void PBODB(Processor proc, Memory mem, int x, int y, int z) {
		probableBranchBack(proc, proc.register(x) % 2 != 0);
	}

	public static void PBNN(Processor proc, Memory mem, int x, int y, int z) {
		probableBranchForward(proc, proc.register(x) >= 0);
	}

	public static void PBNNB(Processor proc, Memory mem, int x, int y, int z) {
		probableBranchBack(proc, proc.register(x) >= 0);
	}

	public static void PBNZ(Processor proc, Memory mem, int x, int y, int z) {
		probableBranchForward(proc, proc.register(x) != 0);
	}

	public static void PBNZB(Processor proc, Memory mem, int x, int y, int z) {
		probableBranchBack(proc, proc.register(x) != 0);
	}
	
	public static void PBNP(Processor proc, Memory mem, int x, int y, int z) {
		probableBranchForward(proc, proc.register(x) <= 0);
	}

	public static void PBNPB(Processor proc, Memory mem, int x, int y, int z) {
		probableBranchBack(proc, proc.register(x) <= 0);
	}	

	public static void PBEV(Processor proc, Memory mem, int x, int y, int z) {
		probableBranchForward(proc, proc.register(x) % 2 == 0);
	}

	public static void PBEVB(Processor proc, Memory mem, int x, int y, int z) {
		probableBranchBack(proc, proc.register(x) % 2 == 0);
	}	
	
	
	private static void branchForward(Processor proc, boolean condition) {
		if (condition) {
			proc.incInstPtr(proc.yz() - 1);
			proc.cost(0, 3);
		} else {
			proc.cost(0, 1);
		}
	}

	private static void branchBack(Processor proc, boolean condition) {
		if (condition) {
			proc.incInstPtr(proc.yz() - 0x10000 - 1);
			proc.cost(0, 3);
		} else {
			proc.cost(0, 1);
		}
	}
	
	
	private static void probableBranchForward(Processor proc, boolean condition) {
		if (condition) {
			proc.incInstPtr(proc.yz() - 1);
			proc.cost(0, 1);
		} else {
			proc.cost(0, 3);
		}
	}

	private static void probableBranchBack(Processor proc, boolean condition) {
		if (condition) {
			proc.incInstPtr(proc.yz() - 0x10000 - 1);
			proc.cost(0, 1);
		} else {
			proc.cost(0, 3);
		}
	}
}
