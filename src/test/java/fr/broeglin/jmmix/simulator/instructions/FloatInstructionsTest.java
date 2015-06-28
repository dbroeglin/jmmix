package fr.broeglin.jmmix.simulator.instructions;

import static java.lang.Double.doubleToRawLongBits;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;
import static fr.broeglin.jmmix.simulator.instructions.FloatInstructions.*;

public class FloatInstructionsTest {

	Processor proc = new Processor();
	Memory mem = new Memory();

	@Test
	public void FADD_should_add_floats() {
		checkOp(FloatInstructions::FADD, 3.0, 1.0, 2.0);
	}

	@Test
	public void FIX_should_convert_to_long_NEAR_default() {
		checkFIX(-11.5, -12l, 4);
		checkFIX(-12.0, -12l, 4);
		checkFIX(-12.2, -12l, 4);
		checkFIX(-12.5, -12l, 4);
		checkFIX(-12.8, -13l, 4);
		checkFIX(11.5, 12l, 4);
		checkFIX(12.0, 12l, 4);
		checkFIX(12.2, 12l, 4);
		checkFIX(12.5, 12l, 4);
		checkFIX(12.8, 13l, 4);
	}

	@Test
	public void FIX_should_convert_to_long_OFF() {
		checkFIX(-11.5, -11l, 1);
		checkFIX(-12.0, -12l, 1);
		checkFIX(-12.2, -12l, 1);
		checkFIX(-12.5, -12l, 1);
		checkFIX(-12.8, -12l, 1);
		checkFIX(11.5, 11l, 1);
		checkFIX(12.0, 12l, 1);
		checkFIX(12.2, 12l, 1);
		checkFIX(12.5, 12l, 1);
		checkFIX(12.8, 12l, 1);
	}

	@Test
	public void FIX_should_convert_to_long_UP() {
		checkFIX(-12.0, -12l, 2);
		checkFIX(-12.2, -12l, 2);
		checkFIX(-12.5, -12l, 2);
		checkFIX(-12.8, -12l, 2);
		checkFIX(12.0, 12l, 2);
		checkFIX(12.2, 13l, 2);
		checkFIX(12.5, 13l, 2);
		checkFIX(12.8, 13l, 2);
	}

	@Test
	public void FIX_should_convert_to_long_DOWN() {
		checkFIX(-12.0, -12l, 3);
		checkFIX(-12.2, -13l, 3);
		checkFIX(-12.5, -13l, 3);
		checkFIX(-12.8, -13l, 3);
		checkFIX(12.0, 12l, 3);
		checkFIX(12.2, 12l, 3);
		checkFIX(12.5, 12l, 3);
		checkFIX(12.8, 12l, 3);
	}

	@Test
	public void FIX_should_convert_to_long_NEAR() {
		checkFIX(-11.5, -12l, 4);
		checkFIX(-12.0, -12l, 4);
		checkFIX(-12.2, -12l, 4);
		checkFIX(-12.5, -12l, 4);
		checkFIX(-12.8, -13l, 4);
		checkFIX(11.5, 12l, 4);
		checkFIX(12.0, 12l, 4);
		checkFIX(12.2, 12l, 4);
		checkFIX(12.5, 12l, 4);
		checkFIX(12.8, 13l, 4);
	}

	private void checkFIX(double roundedValue, long expectedValue, int y) {
		proc.setRegister(2, doubleToRawLongBits(roundedValue));

		FIX(proc, mem, 1, y, 2);

		assertThat(proc.register(1), equalTo(expectedValue));
	}

	private void checkOp(Instruction inst, double x, double y, double z) {
		proc.setRegister(2, Double.doubleToRawLongBits(y));
		proc.setRegister(3, Double.doubleToRawLongBits(z));

		inst.op(proc, mem, 1, 2, 3);

		assertThat(Double.longBitsToDouble(proc.register(1)), equalTo(x));
	}
}
