package fr.broeglin.jmmix.simulator.utils;

import java.util.Arrays;


public class DoubleHexaConstants {

	public static void main(String[] args) {
		
		for(Double v: Arrays.asList(-12.5, -11.5, 11.5, 12.5)) {
			System.out.format("ROUND  %f : %d\n", v, Math.round(v));
			System.out.format("FLOOR  %f : %f\n", v, Math.floor(v));
			System.out.format("CEIL   %f : %f\n", v, Math.ceil(v));
			System.out.format("RINT   %f : %f\n", v, Math.rint(v));
		}

		dumpDouble(1.5);
		dumpDouble(10.25E15);
		dumpDouble(1.5 + 10.25E15);
		dumpDouble(Double.MAX_VALUE);
		dumpDouble(Double.MAX_VALUE + Double.MAX_VALUE);
		dumpDouble(Double.MAX_VALUE + 1e308);
		dumpDouble(12.0);
		dumpDouble(12.1);
		dumpDouble(12.5);
		dumpDouble(12.9);
		dumpDouble(-12.0);
		dumpDouble(-12.1);
		dumpDouble(-12.5);
		dumpDouble(-12.9);
		dumpDouble(Double.POSITIVE_INFINITY - Double.POSITIVE_INFINITY);

	}

	private static final void dumpDouble(double val) {
		long l = Double.doubleToRawLongBits(val);
		System.err.format("%12e : %f : %016x\n", val, val, l);
	}
}
