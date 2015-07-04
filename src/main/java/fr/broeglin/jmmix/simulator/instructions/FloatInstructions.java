package fr.broeglin.jmmix.simulator.instructions;

import static java.lang.Double.doubleToRawLongBits;
import static java.lang.Double.isNaN;
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
				registerAsLong(proc, y)
						+ registerAsLong(proc, z)));
	}

	public static void FSUB(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(
				x,
				doubleToRawLongBits(
				registerAsLong(proc, y)
						- registerAsLong(proc, z)));
	}

	public static void FREM(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(
				x,
				doubleToRawLongBits(
				Math.IEEEremainder(
						registerAsLong(proc, y),
						registerAsLong(proc, z))));
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
			throw new InvalidInstruction(0x5, x, y, z);
		}
		proc.setRegister(x, result);
	}

	public static void FINT(Processor proc, Memory mem, int x, int y, int z) {
		double result = 0;
		double value = Double.longBitsToDouble(proc.register(z));
		if (y == 0) {
			// TODO: we need to implement default roundings
			y = ROUND_NEAR;
		}

		switch (y) {
		case ROUND_OFF:
			result = (double) (long) value;
			break;
		case ROUND_UP:
			result = Math.ceil(value);
			break;
		case ROUND_DOWN:
			result = Math.floor(value);
			break;
		case ROUND_NEAR:
			result = Math.rint(value);
			break;
		default:
			throw new InvalidInstruction(0x17, x, y, z);
		}
		proc.setRegister(x, Double.doubleToRawLongBits(result));
	}

	public static void FIXU(Processor proc, Memory mem, int x, int y, int z) {
		throw new RuntimeException("Not yet implemented");
	}

	public static void FEQL(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, registerAsLong(proc, y) == registerAsLong(proc, z) ? 1l : 0l);
	}

	public static void FCMP(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x,
				Long.signum(
						Double.compare(
								registerAsLong(proc, y),
								registerAsLong(proc, z))));
	}

	private static double registerAsLong(Processor proc, int y) {
		return longBitsToDouble(proc.register(y));
	}

	public static void FUN(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x,
				isNaN(registerAsLong(proc, y))
						|| isNaN(registerAsLong(proc, z)) ? 1l : 0l);
	}
}
