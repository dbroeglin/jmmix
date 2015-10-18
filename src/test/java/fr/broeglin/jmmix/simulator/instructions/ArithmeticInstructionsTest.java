package fr.broeglin.jmmix.simulator.instructions;

import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rR;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.INCH;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.INCL;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.INCMH;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.INCML;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ArithmeticInstructionsTest extends AbstractInstructionsTest {

	@Test
	public void should_ADD() {
		proc.setRegister(1, 0x12345678);
		proc.setRegister(2, 0xaaaaaaaaaaaaaaaal);
		proc.setRegister(3, 0x1111111111111111l);

		ArithmeticInstructions.ADD(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.register(1), equalTo(0xbbbbbbbbbbbbbbbbl));
		// TODO: set register for overflow ?
	}

	@Test
	public void should_ADDU() {
		proc.setRegister(1, 0x12345678);
		proc.setRegister(2, 0xaaaaaaaaaaaaaaaal);
		proc.setRegister(3, 0x1111111111111111l);

		ArithmeticInstructions.ADDU(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.register(1), equalTo(0xbbbbbbbbbbbbbbbbl));
	}

	@Test
	public void should_2ADDU() {
		checkOp(ArithmeticInstructions::_2ADDU, 0x5l, 0x1l, 0x3l);
		checkOp(ArithmeticInstructions::_2ADDU, 0x7l, 0x2l, 0x3l);
		checkOp(ArithmeticInstructions::_2ADDU, 0xdl, 0x4l, 0x5l);
	}

	@Test
	public void should_4ADDU() {
		checkOp(ArithmeticInstructions::_4ADDU, 0x7l, 0x1l, 0x3l);
		checkOp(ArithmeticInstructions::_4ADDU, 0xbl, 0x2l, 0x3l);
		checkOp(ArithmeticInstructions::_4ADDU, 0x15l, 0x4l, 0x5l);
	}

	@Test
	public void should_8ADDU() {
		checkOp(ArithmeticInstructions::_8ADDU, 0xbl, 0x1l, 0x3l);
		checkOp(ArithmeticInstructions::_8ADDU, 0x13l, 0x2l, 0x3l);
		checkOp(ArithmeticInstructions::_8ADDU, 0x25l, 0x4l, 0x5l);
	}

	@Test
	public void should_16ADDU() {
		checkOp(ArithmeticInstructions::_16ADDU, 0x13l, 0x1l, 0x3l);
		checkOp(ArithmeticInstructions::_16ADDU, 0x23l, 0x2l, 0x3l);
		checkOp(ArithmeticInstructions::_16ADDU, 0x45l, 0x4l, 0x5l);
	}

	@Test
	public void should_2ADDUI() {
		checkOpI(ArithmeticInstructions::_2ADDUI, 0x5l, 0x1l, 0x3);
		checkOpI(ArithmeticInstructions::_2ADDUI, 0x7l, 0x2l, 0x3);
		checkOpI(ArithmeticInstructions::_2ADDUI, 0xdl, 0x4l, 0x5);
	}

	@Test
	public void should_4ADDUI() {
		checkOpI(ArithmeticInstructions::_4ADDUI, 0x7l, 0x1l, 0x3);
		checkOpI(ArithmeticInstructions::_4ADDUI, 0xbl, 0x2l, 0x3);
		checkOpI(ArithmeticInstructions::_4ADDUI, 0x15l, 0x4l, 0x5);
	}

	@Test
	public void should_8ADDUI() {
		checkOpI(ArithmeticInstructions::_8ADDUI, 0xbl, 0x1l, 0x3);
		checkOpI(ArithmeticInstructions::_8ADDUI, 0x13l, 0x2l, 0x3);
		checkOpI(ArithmeticInstructions::_8ADDUI, 0x25l, 0x4l, 0x5);
	}

	@Test
	public void should_16ADDUI() {
		checkOpI(ArithmeticInstructions::_16ADDUI, 0x13l, 0x1l, 0x3);
		checkOpI(ArithmeticInstructions::_16ADDUI, 0x23l, 0x2l, 0x3);
		checkOpI(ArithmeticInstructions::_16ADDUI, 0x45l, 0x4l, 0x5);
	}

	@Test
	public void should_SUB() {
		checkOp(ArithmeticInstructions::SUB,
				0x0000_0000_0000_0003l,
				0xff00_0000_0000_0005l,
				0xff00_0000_0000_0002l);

		checkOp(ArithmeticInstructions::SUB,
				0x0000_0000_0000_0003l,
				0x0000_0000_0000_0005l,
				0x0000_0000_0000_0002l);
	}

	@Test
	public void should_SUBI() {
		checkOpI(ArithmeticInstructions::SUBI,
				0x0000_0000_0000_0003l,
				0x0000_0000_0000_0005l,
				2);
	}

	@Test
	public void should_SUBU() {
		checkOp(ArithmeticInstructions::SUB,
				0x0fff_ffff_ffff_ffffl,
				0x1000_0000_0000_0000l,
				0x0000_0000_0000_0001l);

		checkOp(ArithmeticInstructions::SUB,
				0xffff_ffff_ffff_ffffl,
				0x0000_0000_0000_0000l,
				0x0000_0000_0000_0001l);
	}

	@Test
	public void should_SUBUI() {
		checkOpI(ArithmeticInstructions::SUBI,
				0x0fff_ffff_ffff_ffffl,
				0x1000_0000_0000_0000l,
				1);
		checkOpI(ArithmeticInstructions::SUBI,
				0xffff_ffff_ffff_ffffl,
				0x0000_0000_0000_0000l,
				1);
	}
	
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


	@Test
	public void should_INCL() {
		proc.setRegister(1, 0xfedcfedcfedcfedcl);

		INCL(proc, mem, 0x01, 0x89, 0xab);

		assertThat(proc.register(1), equalTo(0xfedcfedcfedcfedcl + 0x89ab));
	}

	@Test
	public void should_INCML() {
		proc.setRegister(1, 0xfedcfedcfedcfedcl);

		INCML(proc, mem, 0x01, 0x89, 0xab);

		assertThat(proc.register(1),
				equalTo(0xfedcfedcfedcfedcl + 0x89ab0000l));
	}

	@Test
	public void should_INCMH() {
		proc.setRegister(1, 0xfedcfedcfedcfedcl);

		INCMH(proc, mem, 0x01, 0x89, 0xab);

		assertThat(proc.register(1),
				equalTo(0xfedcfedcfedcfedcl + 0x89ab00000000l));
	}

	@Test
	public void should_INCH() {
		proc.setRegister(1, 0xfedcfedcfedcfedcl);

		INCH(proc, mem, 0x01, 0x89, 0xab);

		assertThat(proc.register(1),
				equalTo(0xfedcfedcfedcfedcl + 0x89ab000000000000l));
	}

	@Test
	public void should_OR() {
		checkOp(ArithmeticInstructions::OR,
				0xfff0_0000_0000_0003l,
				0xff00_0000_0000_0001l,
				0x0ff0_0000_0000_0002l);

	}

	@Test
	public void should_ORI() {
		checkOpI(ArithmeticInstructions::ORI,
				0xff00_0000_0000_000fl,
				0xff00_0000_0000_0007l,
				0x0a);
	}

	@Test
	public void should_ORN() {
		checkOp(ArithmeticInstructions::ORN,
				0xff0f_ffff_ffff_fffdl,
				0xff00_0000_0000_0001l,
				0x0ff0_0000_0000_0002l);

	}

	@Test
	public void should_ORNI() {
		checkOpI(ArithmeticInstructions::ORNI,
				0xffff_ffff_ffff_fff7l,
				0xff00_0000_0000_0007l,
				0x0a);
	}

	@Test
	public void should_XOR() {
		checkOp(ArithmeticInstructions::XOR,
				0xf0f0_0000_0000_0003l,
				0xff00_0000_0000_0001l,
				0x0ff0_0000_0000_0002l);

	}

	@Test
	public void should_XORI() {
		checkOpI(ArithmeticInstructions::XORI,
				0xff00_0000_0000_000dl,
				0xff00_0000_0000_0007l,
				0x0a);
	}

	@Test
	public void should_NXOR() {
		checkOp(ArithmeticInstructions::NXOR,
				0x0f0f_ffff_ffff_fffcl,
				0xff00_0000_0000_0001l,
				0x0ff0_0000_0000_0002l);

	}

	@Test
	public void should_NXORI() {
		checkOpI(ArithmeticInstructions::NXORI,
				0x00ff_ffff_ffff_fff2l,
				0xff00_0000_0000_0007l,
				0x0a);
	}

	@Test
	public void should_NOR() {
		checkOp(ArithmeticInstructions::NOR,
				0x000f_ffff_ffff_fffcl,
				0xff00_0000_0000_0001l,
				0x0ff0_0000_0000_0002l);

	}

	@Test
	public void should_NORI() {
		checkOpI(ArithmeticInstructions::NORI,
				0x00ff_ffff_ffff_fff0l,
				0xff00_0000_0000_0007l,
				0x0a);
	}

	@Test
	public void should_AND() {
		checkOp(ArithmeticInstructions::AND,
				0x0f00_0000_0000_0000l,
				0xff00_0000_0000_0001l,
				0x0ff0_0000_0000_0002l);
	}

	@Test
	public void should_ANDI() {
		checkOpI(ArithmeticInstructions::ANDI,
				0x0000_0000_0000_0003l,
				0xff00_0000_0000_0007l,
				0x03);
	}

	@Test
	public void should_NAND() {
		checkOp(ArithmeticInstructions::NAND,
				0xf0ff_ffff_ffff_ffffl,
				0xff00_0000_0000_0001l,
				0x0ff0_0000_0000_0002l);
	}

	@Test
	public void should_NANDI() {
		checkOpI(ArithmeticInstructions::NANDI,
				0xffff_ffff_ffff_fffcl,
				0xff00_0000_0000_0007l,
				0x03);
	}

	@Test
	public void should_ANDN() {
		checkOp(ArithmeticInstructions::ANDN,
				0xf000_0000_0000_0001l,
				0xff00_0000_0000_0001l,
				0x0ff0_0000_0000_0002l);
	}

	@Test
	public void should_ANDNI() {
		checkOpI(ArithmeticInstructions::ANDNI,
				0xff00_0000_0000_0004l,
				0xff00_0000_0000_0007l,
				0x03);
	}

}
