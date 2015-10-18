package fr.broeglin.jmmix.simulator.instructions;

import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rR;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fr.broeglin.jmmix.simulator.SpecialRegisterName;

public class ArithmeticInstructionsTest extends AbstractInstructionsTest {

	@Test
	public void DIV_should_set_zero_if_z_is_zero() {
		checkOp(ArithmeticInstructions::DIV, 0x0l, 0x2l, 0x0l);
		checkOp(ArithmeticInstructions::DIV, 0x0l, 0xffff_ffff_ffff_ffffl, 0x0l);
	}

	@Test
	public void DIV_should_divide() {
		checkOp(ArithmeticInstructions::DIV, 0x2l, 0x4l, 0x2l);
		assertThat(proc.specialRegister(rR), equalTo(0l));
		
		checkOp(ArithmeticInstructions::DIV, 0x2l, -0x4l, -0x2l);
		assertThat(proc.specialRegister(rR), equalTo(0l));

		checkOp(ArithmeticInstructions::DIV, -0x2l, -0x4l, 0x2l);
		assertThat(proc.specialRegister(rR), equalTo(0l));

		checkOp(ArithmeticInstructions::DIV, 0x0, 0xffff_ffff_ffff_ffffl, 0x2l);
		assertThat(proc.specialRegister(rR), equalTo(-1l));
		
		checkOp(ArithmeticInstructions::DIV, 0x0, 0xffff_ffff_ffff_ffffl, 0x2l);
		assertThat(proc.specialRegister(rR), equalTo(-1l));

		checkOp(ArithmeticInstructions::DIV, 0x07ff_ffff_ffff_ffffl, 0x0fff_ffff_ffff_ffffl, 0x2l);
		assertThat(proc.specialRegister(rR), equalTo(1l));
	
		checkOp(ArithmeticInstructions::DIV, 12l, 1228l, 96l);
		assertThat(proc.specialRegister(rR), equalTo(76l));
	}

}
