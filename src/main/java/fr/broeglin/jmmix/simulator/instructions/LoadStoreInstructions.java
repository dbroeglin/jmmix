package fr.broeglin.jmmix.simulator.instructions;

import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;

public class LoadStoreInstructions {

	public static void LDBI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, mem.load8(proc.register(y) + z));
		proc.cost(1, 1);
	}
	
	public static void LDOI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, mem.load64(proc.register(y) + z));
		proc.cost(1, 1);
	}
}
