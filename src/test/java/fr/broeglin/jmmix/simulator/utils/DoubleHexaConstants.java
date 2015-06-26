package fr.broeglin.jmmix.simulator.utils;


public class DoubleHexaConstants {

	public static void main(String[] args) {

		dumpDouble(1.5);
		dumpDouble(10.25E15);
		dumpDouble(1.5 + 10.25E15);
		dumpDouble(Double.MAX_VALUE);
		dumpDouble(Double.MAX_VALUE + Double.MAX_VALUE);

	}

	private static final void dumpDouble(double val) {
		System.err.format("%f : %016x\n", val, Double.doubleToRawLongBits(val));
	}
}
