package fr.broeglin.jmmix.simulator.instructions;

import static java.lang.Double.MAX_VALUE;
import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.NaN;
import static java.lang.Double.POSITIVE_INFINITY;
import static java.lang.Double.doubleToRawLongBits;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;

public class FloatInstructionsTest {

	Processor proc = new Processor();
	Memory mem = new Memory();

	@Test
	public void FADD_should_add_floats() {
		checkOp(FloatInstructions::FADD, 3.0, 1.0, 2.0);
	}

	@Test
	public void FMUL_should_add_floats() {
		checkOp(FloatInstructions::FMUL, 12.75, 4.25, 3.0);
	}

	@Test
	public void FDIV_should_add_floats() {
		checkOp(FloatInstructions::FDIV, 4.25, 12.75, 3.0);
	}
	
	@Test
	public void FADD_should_substract_floats() {
		checkOp(FloatInstructions::FSUB, 1.0, 3.0, 2.0);
	}

	@Test
	public void FLOT_should_convert_long_to_float() {
		checkOp(FloatInstructions::FLOT, -1.0, 0, -1);
		checkOp(FloatInstructions::FLOT,
				9.223372036854776E18, 0, Long.MAX_VALUE);
		checkOp(FloatInstructions::FLOT,
				-9.223372036854776E18, 0, Long.MIN_VALUE);
	}
	
	@Test
	public void FLOTI_should_convert_long_to_float() {
		checkOpI(FloatInstructions::FLOTI, -128, 0, -128);
		checkOpI(FloatInstructions::FLOTI, -1.0, 0, -1);
		checkOpI(FloatInstructions::FLOTI,
				0, 0, 0);
		checkOpI(FloatInstructions::FLOTI,
				127, 0, 127);
	}

	@Test
	public void FREM_should_return_IEEE_remainder() {
		checkOp(FloatInstructions::FREM, -1.0, 3.0, 2);
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
	public void FINT_should_convert_to_long_NEAR_default() {
		checkFINT(FloatInstructions::FINT, -11.5, -12.0, 4);
		checkFINT(FloatInstructions::FINT, -12.0, -12.0, 4);
		checkFINT(FloatInstructions::FINT, -12.2, -12.0, 4);
		checkFINT(FloatInstructions::FINT, -12.5, -12.0, 4);
		checkFINT(FloatInstructions::FINT, -12.8, -13.0, 4);
		checkFINT(FloatInstructions::FINT, 11.5, 12.0, 4);
		checkFINT(FloatInstructions::FINT, 12.0, 12.0, 4);
		checkFINT(FloatInstructions::FINT, 12.2, 12.0, 4);
		checkFINT(FloatInstructions::FINT, 12.5, 12.0, 4);
		checkFINT(FloatInstructions::FINT, 12.8, 13.0, 4);
	}

	@Test
	public void FINT_should_convert_to_long_OFF() {
		checkFINT(FloatInstructions::FINT, -11.5, -11.0, 1);
		checkFINT(FloatInstructions::FINT, -12.0, -12.0, 1);
		checkFINT(FloatInstructions::FINT, -12.2, -12.0, 1);
		checkFINT(FloatInstructions::FINT, -12.5, -12.0, 1);
		checkFINT(FloatInstructions::FINT, -12.8, -12.0, 1);
		checkFINT(FloatInstructions::FINT, 11.5, 11.0, 1);
		checkFINT(FloatInstructions::FINT, 12.0, 12.0, 1);
		checkFINT(FloatInstructions::FINT, 12.2, 12.0, 1);
		checkFINT(FloatInstructions::FINT, 12.5, 12.0, 1);
		checkFINT(FloatInstructions::FINT, 12.8, 12.0, 1);
	}

	@Test
	public void FINT_should_convert_to_long_UP() {
		checkFINT(FloatInstructions::FINT, -12.0, -12.0, 2);
		checkFINT(FloatInstructions::FINT, -12.2, -12.0, 2);
		checkFINT(FloatInstructions::FINT, -12.5, -12.0, 2);
		checkFINT(FloatInstructions::FINT, -12.8, -12.0, 2);
		checkFINT(FloatInstructions::FINT, 12.0, 12.0, 2);
		checkFINT(FloatInstructions::FINT, 12.2, 13.0, 2);
		checkFINT(FloatInstructions::FINT, 12.5, 13.0, 2);
		checkFINT(FloatInstructions::FINT, 12.8, 13.0, 2);
	}

	@Test
	public void FINT_should_convert_to_long_DOWN() {
		checkFINT(FloatInstructions::FINT, -12.0, -12.0, 3);
		checkFINT(FloatInstructions::FINT, -12.2, -13.0, 3);
		checkFINT(FloatInstructions::FINT, -12.5, -13.0, 3);
		checkFINT(FloatInstructions::FINT, -12.8, -13.0, 3);
		checkFINT(FloatInstructions::FINT, 12.0, 12.0, 3);
		checkFINT(FloatInstructions::FINT, 12.2, 12.0, 3);
		checkFINT(FloatInstructions::FINT, 12.5, 12.0, 3);
		checkFINT(FloatInstructions::FINT, 12.8, 12.0, 3);
	}

	@Test
	public void FINT_should_convert_to_long_NEAR() {
		checkFINT(FloatInstructions::FINT, -11.5, -12.0, 4);
		checkFINT(FloatInstructions::FINT, -12.0, -12.0, 4);
		checkFINT(FloatInstructions::FINT, -12.2, -12.0, 4);
		checkFINT(FloatInstructions::FINT, -12.5, -12.0, 4);
		checkFINT(FloatInstructions::FINT, -12.8, -13.0, 4);
		checkFINT(FloatInstructions::FINT, 11.5, 12.0, 4);
		checkFINT(FloatInstructions::FINT, 12.0, 12.0, 4);
		checkFINT(FloatInstructions::FINT, 12.2, 12.0, 4);
		checkFINT(FloatInstructions::FINT, 12.5, 12.0, 4);
		checkFINT(FloatInstructions::FINT, 12.8, 13.0, 4);
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
		checkOp(FloatInstructions::FEQL, 0l, 0.0002, NaN);
		checkOp(FloatInstructions::FEQL, 0l, NaN, 0.0002);
		checkOp(FloatInstructions::FEQL, 0l, NaN, NaN);
	}

	@Test
	public void FUN_should_set_1_if_not_a_number() {
		checkOp(FloatInstructions::FUN, 1l, Double.NaN, Double.NaN);
		checkOp(FloatInstructions::FUN, 1l, 0.1, Double.NaN);
		checkOp(FloatInstructions::FUN, 1l, Double.NaN, 0.1);
	}

	@Test
	public void FUN_should_set_0_if_is_a_number() {
		checkOp(FloatInstructions::FUN, 0l, Double.POSITIVE_INFINITY,
				Double.NEGATIVE_INFINITY);
		checkOp(FloatInstructions::FUN, 0l, 0.1, 0.3);
		checkOp(FloatInstructions::FUN, 0l, Double.MAX_VALUE, 0.1);
	}

	@Test
	public void FUN_should_set_minus_1_if_smaller() {
		checkOp(FloatInstructions::FCMP, -1l, NEGATIVE_INFINITY, 0.0);
		checkOp(FloatInstructions::FCMP, -1l, 0.1, 0.3);
		checkOp(FloatInstructions::FCMP, -1l, MAX_VALUE, POSITIVE_INFINITY);
		checkOp(FloatInstructions::FCMP, -1l, POSITIVE_INFINITY, NaN);
	}

	@Test
	public void FUN_should_set_1_if_bigger() {
		checkOp(FloatInstructions::FCMP, 1l, 0.0, NEGATIVE_INFINITY);
		checkOp(FloatInstructions::FCMP, 1l, 0.3, 0.1);
		checkOp(FloatInstructions::FCMP, 1l, POSITIVE_INFINITY, MAX_VALUE);
		checkOp(FloatInstructions::FCMP, 1l, NaN, POSITIVE_INFINITY);

	}

	@Test
	public void FUN_should_set_0_if_equal() {
		checkOp(FloatInstructions::FCMP, 0l, NEGATIVE_INFINITY,
				NEGATIVE_INFINITY);
		checkOp(FloatInstructions::FCMP, 0l, 0.1, 0.1);
		checkOp(FloatInstructions::FCMP, 0l, MAX_VALUE, MAX_VALUE);
		checkOp(FloatInstructions::FCMP, 0l, POSITIVE_INFINITY,
				POSITIVE_INFINITY);
		checkOp(FloatInstructions::FCMP, 0l, NaN, NaN);
	}

	private void checkFIX(Instruction inst, double roundedValue,
			long expectedValue, int y) {
		proc.setRegister(2, doubleToRawLongBits(roundedValue));

		inst.op(proc, mem, 1, y, 2);

		assertThat(proc.register(1), equalTo(expectedValue));
	}

	private void checkFINT(Instruction inst, double roundedValue,
			double expectedValue, int y) {
		proc.setRegister(2, doubleToRawLongBits(roundedValue));

		
		inst.op(proc, mem, 1, y, 2);

		assertThat(proc.register(1),
				equalTo(doubleToRawLongBits(expectedValue)));
	}

	private void checkOp(Instruction inst, double x, double y, double z) {
		proc.setRegister(2, Double.doubleToRawLongBits(y));
		proc.setRegister(3, Double.doubleToRawLongBits(z));

		inst.op(proc, mem, 1, 2, 3);

		assertThat(Double.longBitsToDouble(proc.register(1)), equalTo(x));
	}

	private void checkOp(Instruction inst, double x, long y, long z) {
		proc.setRegister(2, y);
		proc.setRegister(3, z);

		inst.op(proc, mem, 1, 2, 3);

		assertThat(Double.longBitsToDouble(proc.register(1)), equalTo(x));
	}
	
	private void checkOpI(Instruction inst, double x, long y, int z) {
		proc.setRegister(2, y);

		inst.op(proc, mem, 1, 2, z);

		assertThat(Double.longBitsToDouble(proc.register(1)), equalTo(x));
	}
	
	private void checkOp(Instruction inst, long x, double y, double z) {
		proc.setRegister(2, Double.doubleToRawLongBits(y));
		proc.setRegister(3, Double.doubleToRawLongBits(z));

		inst.op(proc, mem, 1, 2, 3);

		assertThat(proc.register(1), equalTo(x));
	}
}
