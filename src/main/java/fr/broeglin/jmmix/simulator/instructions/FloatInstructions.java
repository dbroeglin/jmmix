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
						registerAsDouble(proc, y)
								+ registerAsDouble(proc, z)));
		proc.cost(0, 4);
	}

	public static void FMUL(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(
				x,
				doubleToRawLongBits(
						registerAsDouble(proc, y)
								* registerAsDouble(proc, z)));
		proc.cost(0, 4);
	}

	public static void FDIV(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(
				x,
				doubleToRawLongBits(
						registerAsDouble(proc, y) / registerAsDouble(proc, z)));
		proc.cost(0, 40);
	}

	public static void FSQRT(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(
				x,
				doubleToRawLongBits(
						Math.sqrt(registerAsDouble(proc, z))));
		proc.cost(0, 40);
	}

	public static void FSUB(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(
				x,
				doubleToRawLongBits(
						registerAsDouble(proc, y)
								- registerAsDouble(proc, z)));
		proc.cost(0, 4);
	}

	public static void FREM(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(
				x,
				doubleToRawLongBits(
						Math.IEEEremainder(
								registerAsDouble(proc, y),
								registerAsDouble(proc, z))));
		proc.cost(0, 4);
	}

	public static void FLOT(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x,
				Double.doubleToRawLongBits((double) proc.register(z)));
		proc.cost(0, 4);
	}

	public static void FLOTI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, Double.doubleToRawLongBits((double) z));
		proc.cost(0, 4);
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
		proc.cost(0, 4);
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
		proc.cost(0, 4);
	}

	public static void FIXU(Processor proc, Memory mem, int x, int y, int z) {
		throw new RuntimeException("Not yet implemented");
	}

	public static void FEQL(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x,
				registerAsDouble(proc, y) == registerAsDouble(proc, z) ? 1l
						: 0l);
		proc.cost(0, 1);
	}

	public static void FCMP(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x,
				Long.signum(
						Double.compare(
								registerAsDouble(proc, y),
								registerAsDouble(proc, z))));
		proc.cost(0, 1);
	}

	public static void FUN(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x,
				isNaN(registerAsDouble(proc, y))
						|| isNaN(registerAsDouble(proc, z)) ? 1l : 0l);
		proc.cost(0, 1);
	}

	private static double registerAsDouble(Processor proc, int y) {
		return longBitsToDouble(proc.register(y));
	}

}
