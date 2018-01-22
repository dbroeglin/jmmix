package fr.broeglin.jmmix.simulator.instructions;
import static fr.broeglin.jmmix.simulator.Processor.V_BIT;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rR;

import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;

public class ShiftInstructions {

	static long _SL(Processor proc, long source, long amount) {
		long value = source << amount;
		
		if ((value >> amount)!= source) {
			proc.setRa(V_BIT, true);			
		}
		return value;
	}
	
	public static void SL(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, _SL(proc, proc.register(y), proc.register(z)));
		proc.cost(0, 1);
	}

	public static void SLI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, _SL(proc, proc.register(y), z));
		proc.cost(0, 1);
	}

	public static void SLU(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) << proc.register(z));
		proc.cost(0, 1);
	}

	public static void SLUI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) << z);
		proc.cost(0, 1);
	}

}
