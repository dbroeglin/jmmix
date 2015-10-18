package fr.broeglin.jmmix.simulator.instructions;

import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rR;
import static java.lang.Double.doubleToRawLongBits;
import static java.lang.Double.isNaN;
import static java.lang.Double.longBitsToDouble;
import fr.broeglin.jmmix.simulator.InvalidInstruction;
import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;
import fr.broeglin.jmmix.simulator.SpecialRegisterName;

public class ArithmeticInstructions {

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
