package fr.broeglin.jmmix.simulator.instructions;

import static java.lang.Double.doubleToRawLongBits;
import static java.lang.Double.longBitsToDouble;
import fr.broeglin.jmmix.simulator.InvalidInstruction;
import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;

public class FloatInstructions {

	public static final int ROUND_OFF = 1;
	public static final int ROUND_UP = 2;
	public static final int ROUND_DOWN = 3;
	public static final int ROUND_NEAR = 4;

	public static void FADD(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(
				x,
				doubleToRawLongBits(
				longBitsToDouble(proc.register(y))
						+ longBitsToDouble(proc.register(z))));
	}

	public static void FIX(Processor proc, Memory mem, int x, int y, int z) {
		long result = 0;
		double value = Double.longBitsToDouble(proc.register(z));
		if (y == 0) {
			// TODO: we need to implement default roundings
			y = ROUND_NEAR;
		}

		switch (y) {
		case ROUND_OFF:
			result = (long) value;
			break;
		case ROUND_UP:
			result = Math.round(Math.ceil(value));
			break;
		case ROUND_DOWN:
			result = Math.round(Math.floor(value));
			break;
		case ROUND_NEAR:
			result = Math.round(Math.rint(value));
			break;
		default:
			throw new InvalidInstruction(5, x, y, z);
		}
		proc.setRegister(x, result);
	}

	public static void FIXU(Processor proc, Memory mem, int x, int y, int z) {
		throw new RuntimeException("Not yet implemented");
	}
	
	public static void FEQL(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) == proc.register(z) ? 1l : 0l);
	}

}
