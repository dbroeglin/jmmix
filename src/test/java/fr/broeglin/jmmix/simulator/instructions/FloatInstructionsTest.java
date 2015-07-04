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
	public void FADD_should_substract_floats() {
		checkOp(FloatInstructions::FSUB, 1.0, 3.0, 2.0);
	}
	
	@Test
	public void FREM_should_return_IEEE_remainder() {
		checkOp(FloatInstructions::FREM, -1.0, 3, 2);
		checkOp(FloatInstructions::FREM, 0.5, 39.5, 13.0);
		checkOp(FloatInstructions::FREM, -0.5, -39.5, 13.0);
		checkOp(FloatInstructions::FREM, 1.0, 12.5, 11.5);
	}

	@Test
	public void FIX_should_convert_to_long_NEAR_default() {
		checkFIX(FloatInstructions::FIX, -11.5, -12l, 4);
		checkFIX(FloatInstructions::FIX, -12.0, -12l, 4);
		checkFIX(FloatInstructions::FIX, -12.2, -12l, 4);
		checkFIX(FloatInstructions::FIX, -12.5, -12l, 4);
		checkFIX(FloatInstructions::FIX, -12.8, -13l, 4);
		checkFIX(FloatInstructions::FIX, 11.5, 12l, 4);
		checkFIX(FloatInstructions::FIX, 12.0, 12l, 4);
		checkFIX(FloatInstructions::FIX, 12.2, 12l, 4);
		checkFIX(FloatInstructions::FIX, 12.5, 12l, 4);
		checkFIX(FloatInstructions::FIX, 12.8, 13l, 4);
	} 
	
	@Test
	public void FIX_should_convert_to_long_OFF() {
		checkFIX(FloatInstructions::FIX, -11.5, -11l, 1);
		checkFIX(FloatInstructions::FIX, -12.0, -12l, 1);
		checkFIX(FloatInstructions::FIX, -12.2, -12l, 1);
		checkFIX(FloatInstructions::FIX, -12.5, -12l, 1);
		checkFIX(FloatInstructions::FIX, -12.8, -12l, 1);
		checkFIX(FloatInstructions::FIX, 11.5, 11l, 1);
		checkFIX(FloatInstructions::FIX, 12.0, 12l, 1);
		checkFIX(FloatInstructions::FIX, 12.2, 12l, 1);
		checkFIX(FloatInstructions::FIX, 12.5, 12l, 1);
		checkFIX(FloatInstructions::FIX, 12.8, 12l, 1);
	}

	@Test
	public void FIX_should_convert_to_long_UP() {
		checkFIX(FloatInstructions::FIX, -12.0, -12l, 2);
		checkFIX(FloatInstructions::FIX, -12.2, -12l, 2);
		checkFIX(FloatInstructions::FIX, -12.5, -12l, 2);
		checkFIX(FloatInstructions::FIX, -12.8, -12l, 2);
		checkFIX(FloatInstructions::FIX, 12.0, 12l, 2);
		checkFIX(FloatInstructions::FIX, 12.2, 13l, 2);
		checkFIX(FloatInstructions::FIX, 12.5, 13l, 2);
		checkFIX(FloatInstructions::FIX, 12.8, 13l, 2);
	}

	@Test
	public void FIX_should_convert_to_long_DOWN() {
		checkFIX(FloatInstructions::FIX, -12.0, -12l, 3);
		checkFIX(FloatInstructions::FIX, -12.2, -13l, 3);
		checkFIX(FloatInstructions::FIX, -12.5, -13l, 3);
		checkFIX(FloatInstructions::FIX, -12.8, -13l, 3);
		checkFIX(FloatInstructions::FIX, 12.0, 12l, 3);
		checkFIX(FloatInstructions::FIX, 12.2, 12l, 3);
		checkFIX(FloatInstructions::FIX, 12.5, 12l, 3);
		checkFIX(FloatInstructions::FIX, 12.8, 12l, 3);
	}

	@Test
	public void FIX_should_convert_to_long_NEAR() {
		checkFIX(FloatInstructions::FIX, -11.5, -12l, 4);
		checkFIX(FloatInstructions::FIX, -12.0, -12l, 4);
		checkFIX(FloatInstructions::FIX, -12.2, -12l, 4);
		checkFIX(FloatInstructions::FIX, -12.5, -12l, 4);
		checkFIX(FloatInstructions::FIX, -12.8, -13l, 4);
		checkFIX(FloatInstructions::FIX, 11.5, 12l, 4);
		checkFIX(FloatInstructions::FIX, 12.0, 12l, 4);
		checkFIX(FloatInstructions::FIX, 12.2, 12l, 4);
		checkFIX(FloatInstructions::FIX, 12.5, 12l, 4);
		checkFIX(FloatInstructions::FIX, 12.8, 13l, 4);
	}
	
	@Test
	public void FEQL_should_set_1_if_equal() {
		checkOp(FloatInstructions::FEQL, 1l, Double.MAX_VALUE, Double.MAX_VALUE);
		checkOp(FloatInstructions::FEQL, 1l, Double.MIN_VALUE, Double.MIN_VALUE);
		checkOp(FloatInstructions::FEQL, 1l, 0.0, 0.0);
		checkOp(FloatInstructions::FEQL, 1l, 0.0001, 0.0001);
	}

	@Test
	public void FEQL_should_set_0_if_not_equal() {
		checkOp(FloatInstructions::FEQL, 0l, Double.MAX_VALUE, Double.MIN_VALUE);
		checkOp(FloatInstructions::FEQL, 0l, Double.MIN_VALUE, Double.MAX_VALUE);
		checkOp(FloatInstructions::FEQL, 0l, 0.0, 0.000000001);
		checkOp(FloatInstructions::FEQL, 0l, 0.0002, 0.0001);
	}


	private void checkFIX(Instruction inst, double roundedValue,
			long expectedValue, int y) {
		proc.setRegister(2, doubleToRawLongBits(roundedValue));

		inst.op(proc, mem, 1, y, 2);

		assertThat(proc.register(1), equalTo(expectedValue));
	}
	

	private void checkOp(Instruction inst, double x, double y, double z) {
		proc.setRegister(2, Double.doubleToRawLongBits(y));
		proc.setRegister(3, Double.doubleToRawLongBits(z));

		inst.op(proc, mem, 1, 2, 3);

		assertThat(Double.longBitsToDouble(proc.register(1)), equalTo(x));
	}
	
	private void checkOp(Instruction inst, long x, double y, double z) {
		proc.setRegister(2, Double.doubleToRawLongBits(y));
		proc.setRegister(3, Double.doubleToRawLongBits(z));

		inst.op(proc, mem, 1, 2, 3);

		assertThat(proc.register(1), equalTo(x));
	}
}
