package fr.broeglin.jmmix.simulator.instructions;

import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.ADD;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.CMP;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.CMPI;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.CMPU;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.CMPUI;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.INCH;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.INCL;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.INCMH;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.INCML;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.JMP;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.JMPB;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.NEG;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.NEGI;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.NEGU;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.NEGUI;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.SETH;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.SETL;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.SETMH;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;

public class InstructionSetTest {

	Processor proc = new Processor();
	Memory mem = new Memory();

	// System.err.format("%016x", proc.register(1));

	@Test
	public void should_ADD() {
		proc.setRegister(1, 0x12345678);
		proc.setRegister(2, 0xaaaaaaaaaaaaaaaal);
		proc.setRegister(3, 0x1111111111111111l);

		ADD(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.register(1), equalTo(0xbbbbbbbbbbbbbbbbl));
		// TODO: set register for overflow ?
	}

	@Test
	public void should_ADDU() {
		proc.setRegister(1, 0x12345678);
		proc.setRegister(2, 0xaaaaaaaaaaaaaaaal);
		proc.setRegister(3, 0x1111111111111111l);

		InstructionSet.ADDU(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.register(1), equalTo(0xbbbbbbbbbbbbbbbbl));
	}

	@Test
	public void should_2ADDU() {
		checkOp(InstructionSet::_2ADDU, 0x5l, 0x1l, 0x3l);
		checkOp(InstructionSet::_2ADDU, 0x7l, 0x2l, 0x3l);
		checkOp(InstructionSet::_2ADDU, 0xdl, 0x4l, 0x5l);
	}

	@Test
	public void should_4ADDU() {
		checkOp(InstructionSet::_4ADDU, 0x7l, 0x1l, 0x3l);
		checkOp(InstructionSet::_4ADDU, 0xbl, 0x2l, 0x3l);
		checkOp(InstructionSet::_4ADDU, 0x15l, 0x4l, 0x5l);
	}

	@Test
	public void should_8ADDU() {
		checkOp(InstructionSet::_8ADDU, 0xbl, 0x1l, 0x3l);
		checkOp(InstructionSet::_8ADDU, 0x13l, 0x2l, 0x3l);
		checkOp(InstructionSet::_8ADDU, 0x25l, 0x4l, 0x5l);
	}

	@Test
	public void should_16ADDU() {
		checkOp(InstructionSet::_16ADDU, 0x13l, 0x1l, 0x3l);
		checkOp(InstructionSet::_16ADDU, 0x23l, 0x2l, 0x3l);
		checkOp(InstructionSet::_16ADDU, 0x45l, 0x4l, 0x5l);
	}

	@Test
	public void should_2ADDUI() {
		checkOpI(InstructionSet::_2ADDUI, 0x5l, 0x1l, 0x3);
		checkOpI(InstructionSet::_2ADDUI, 0x7l, 0x2l, 0x3);
		checkOpI(InstructionSet::_2ADDUI, 0xdl, 0x4l, 0x5);
	}

	@Test
	public void should_4ADDUI() {
		checkOpI(InstructionSet::_4ADDUI, 0x7l, 0x1l, 0x3);
		checkOpI(InstructionSet::_4ADDUI, 0xbl, 0x2l, 0x3);
		checkOpI(InstructionSet::_4ADDUI, 0x15l, 0x4l, 0x5);
	}

	@Test
	public void should_8ADDUI() {
		checkOpI(InstructionSet::_8ADDUI, 0xbl, 0x1l, 0x3);
		checkOpI(InstructionSet::_8ADDUI, 0x13l, 0x2l, 0x3);
		checkOpI(InstructionSet::_8ADDUI, 0x25l, 0x4l, 0x5);
	}

	@Test
	public void should_16ADDUI() {
		checkOpI(InstructionSet::_16ADDUI, 0x13l, 0x1l, 0x3);
		checkOpI(InstructionSet::_16ADDUI, 0x23l, 0x2l, 0x3);
		checkOpI(InstructionSet::_16ADDUI, 0x45l, 0x4l, 0x5);
	}

	@Test
	public void should_SUB() {
		checkOp(InstructionSet::SUB,
				0x0000_0000_0000_0003l,
				0xff00_0000_0000_0005l,
				0xff00_0000_0000_0002l);

		checkOp(InstructionSet::SUB,
				0x0000_0000_0000_0003l,
				0x0000_0000_0000_0005l,
				0x0000_0000_0000_0002l);
	}

	@Test
	public void should_SUBI() {
		checkOpI(InstructionSet::SUBI,
				0x0000_0000_0000_0003l,
				0x0000_0000_0000_0005l,
				2);
	}

	@Test
	public void should_SUBU() {
		checkOp(InstructionSet::SUB,
				0x0fff_ffff_ffff_ffffl,
				0x1000_0000_0000_0000l,
				0x0000_0000_0000_0001l);

		checkOp(InstructionSet::SUB,
				0xffff_ffff_ffff_ffffl,
				0x0000_0000_0000_0000l,
				0x0000_0000_0000_0001l);
	}

	@Test
	public void should_SUBUI() {
		checkOpI(InstructionSet::SUBI,
				0x0fff_ffff_ffff_ffffl,
				0x1000_0000_0000_0000l,
				1);
		checkOpI(InstructionSet::SUBI,
				0xffff_ffff_ffff_ffffl,
				0x0000_0000_0000_0000l,
				1);
	}

	@Test
	public void should_SETL() {
		proc.setRegister(1, 0xfedcfedcfedcfedcl);

		SETL(proc, mem, 0x01, 0x89, 0xab);

		assertThat(proc.register(1), equalTo(0x0000_0000_0000_89abl));
	}

	@Test
	public void should_SETML() {
		proc.setRegister(1, 0xfedc_fedc_fedc_fedcl);

		SETML(proc, mem, 0x01, 0x89, 0xab);

		assertThat(proc.register(1), equalTo(0x0000_0000_89ab_0000l));
	}

	@Test
	public void should_SETMH() {
		proc.setRegister(1, 0xfedcfedcfedcfedcl);

		SETMH(proc, mem, 0x01, 0x89, 0xab);
		assertThat(proc.register(1), equalTo(0x0000_89ab_0000_0000l));
	}

	@Test
	public void should_SETH() {
		proc.setRegister(1, 0xfedcfedcfedcfedcl);

		SETH(proc, mem, 0x01, 0x89, 0xab);

		assertThat(proc.register(1), equalTo(0x89ab_0000_0000_0000l));
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

		assertThat(proc.register(1), equalTo(0xfedcfedcfedcfedcl + 0x89ab0000l));
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
		checkOp(InstructionSet::OR,
				0xfff0_0000_0000_0003l,
				0xff00_0000_0000_0001l,
				0x0ff0_0000_0000_0002l);

	}

	@Test
	public void should_ORI() {
		checkOpI(InstructionSet::ORI,
				0xff00_0000_0000_000fl,
				0xff00_0000_0000_0007l,
				0x0a);
	}

	@Test
	public void should_ORN() {
		checkOp(InstructionSet::ORN,
				0xff0f_ffff_ffff_fffdl,
				0xff00_0000_0000_0001l,
				0x0ff0_0000_0000_0002l);

	}

	@Test
	public void should_ORNI() {
		checkOpI(InstructionSet::ORNI,
				0xffff_ffff_ffff_fff7l,
				0xff00_0000_0000_0007l,
				0x0a);
	}

	@Test
	public void should_XOR() {
		checkOp(InstructionSet::XOR,
				0xf0f0_0000_0000_0003l,
				0xff00_0000_0000_0001l,
				0x0ff0_0000_0000_0002l);

	}

	@Test
	public void should_XORI() {
		checkOpI(InstructionSet::XORI,
				0xff00_0000_0000_000dl,
				0xff00_0000_0000_0007l,
				0x0a);
	}

	@Test
	public void should_NXOR() {
		checkOp(InstructionSet::NXOR,
				0x0f0f_ffff_ffff_fffcl,
				0xff00_0000_0000_0001l,
				0x0ff0_0000_0000_0002l);

	}

	@Test
	public void should_NXORI() {
		checkOpI(InstructionSet::NXORI,
				0x00ff_ffff_ffff_fff2l,
				0xff00_0000_0000_0007l,
				0x0a);
	}

	@Test
	public void should_NOR() {
		checkOp(InstructionSet::NOR,
				0x000f_ffff_ffff_fffcl,
				0xff00_0000_0000_0001l,
				0x0ff0_0000_0000_0002l);

	}

	@Test
	public void should_NORI() {
		checkOpI(InstructionSet::NORI,
				0x00ff_ffff_ffff_fff0l,
				0xff00_0000_0000_0007l,
				0x0a);
	}

	@Test
	public void should_AND() {
		checkOp(InstructionSet::AND,
				0x0f00_0000_0000_0000l,
				0xff00_0000_0000_0001l,
				0x0ff0_0000_0000_0002l);
	}

	@Test
	public void should_ANDI() {
		checkOpI(InstructionSet::ANDI,
				0x0000_0000_0000_0003l,
				0xff00_0000_0000_0007l,
				0x03);
	}

	@Test
	public void should_NAND() {
		checkOp(InstructionSet::NAND,
				0xf0ff_ffff_ffff_ffffl,
				0xff00_0000_0000_0001l,
				0x0ff0_0000_0000_0002l);
	}

	@Test
	public void should_NANDI() {
		checkOpI(InstructionSet::NANDI,
				0xffff_ffff_ffff_fffcl,
				0xff00_0000_0000_0007l,
				0x03);
	}

	@Test
	public void should_ANDN() {
		checkOp(InstructionSet::ANDN,
				0xf000_0000_0000_0001l,
				0xff00_0000_0000_0001l,
				0x0ff0_0000_0000_0002l);
	}

	@Test
	public void should_ANDNI() {
		checkOpI(InstructionSet::ANDNI,
				0xff00_0000_0000_0004l,
				0xff00_0000_0000_0007l,
				0x03);
	}

	@Test
	public void CSZ_should_set_if_0() {
		proc.setRegister(1, 0x2al);

		checkOp(InstructionSet::CSZ,
				0x33l,
				0x0l,
				0x33l);
	}

	@Test
	public void CSZ_should_not_set_if_not_0() {
		proc.setRegister(1, 0x2al);

		checkOp(InstructionSet::CSZ,
				0x2al,
				0x1l,
				0x33l);
	}

	@Test
	public void CSZI_should_set_if_0() {
		proc.setRegister(1, 0x2al);

		checkOpI(InstructionSet::CSZI,
				0x33l,
				0x0l,
				0x33);
	}

	@Test
	public void CSZI_should_not_set_if_not_0() {
		proc.setRegister(1, 0x2al);

		checkOpI(InstructionSet::CSZI,
				0x2al,
				0x1l,
				0x33);
	}

	@Test
	public void should_JMP() {
		proc.setInstPtr(0);

		JMP(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.instPtr(), equalTo(0x04080cl - 4));
	}

	@Test
	public void should_JMPB() {
		proc.setInstPtr(0x10000000l);

		JMPB(proc, mem, 0xfe, 0xfd, 0xfd);

		assertThat(proc.instPtr(), equalTo(0x10000000l - 0x04080cl - 4));
	}

	@Test
	public void should_CMP_lower_than() {
		proc.setRegister(2, Long.MIN_VALUE);
		proc.setRegister(3, Long.MAX_VALUE);

		CMP(proc, mem, 0x1, 0x2, 0x3);

		assertThat(proc.register(1), equalTo(-1l));
	}

	@Test
	public void should_CMP_equal() {
		proc.setRegister(2, Long.MIN_VALUE);
		proc.setRegister(3, Long.MIN_VALUE);

		CMP(proc, mem, 0x1, 0x2, 0x3);

		assertThat(proc.register(1), equalTo(0l));
	}

	@Test
	public void should_CMP_greater_than() {
		proc.setRegister(2, Long.MAX_VALUE);
		proc.setRegister(3, Long.MIN_VALUE);

		CMP(proc, mem, 0x1, 0x2, 0x3);

		assertThat(proc.register(1), equalTo(1l));
	}

	@Test
	public void should_CMPI_lower_than() {
		proc.setRegister(2, 3);

		CMPI(proc, mem, 0x1, 0x2, 4);

		assertThat(proc.register(1), equalTo(-1l));
	}

	@Test
	public void should_CMPI_equal() {
		proc.setRegister(2, 3);

		CMPI(proc, mem, 0x1, 0x2, 3);

		assertThat(proc.register(1), equalTo(0l));
	}

	@Test
	public void should_CMPI_greater_than() {
		proc.setRegister(2, 3);

		CMPI(proc, mem, 0x1, 0x2, 2);

		assertThat(proc.register(1), equalTo(1l));
	}

	@Test
	public void should_CMPU_greate_than() {
		// MIN is > to MAX if unsigned
		checkOp(InstructionSet::CMPU, 1l, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	@Test
	public void should_CMPU_equal() {
		proc.setRegister(2, Long.MIN_VALUE);
		proc.setRegister(3, Long.MIN_VALUE);

		CMPU(proc, mem, 0x1, 0x2, 0x3);

		assertThat(proc.register(1), equalTo(0l));
	}

	@Test
	public void should_CMPU_lower_than() {
		proc.setRegister(2, Long.MAX_VALUE);
		proc.setRegister(3, Long.MIN_VALUE);

		CMPU(proc, mem, 0x1, 0x2, 0x3);

		// MIN is > to MAX if unsigned
		assertThat(proc.register(1), equalTo(-1l));
	}

	@Test
	public void should_CMPUI_lower_than() {
		proc.setRegister(2, 3);

		CMPUI(proc, mem, 0x1, 0x2, 4);

		assertThat(proc.register(1), equalTo(-1l));
	}

	@Test
	public void should_CMPUI_equal() {
		proc.setRegister(2, 3);

		CMPUI(proc, mem, 0x1, 0x2, 3);

		assertThat(proc.register(1), equalTo(0l));
	}

	@Test
	public void should_CMPUI_greater_than() {
		proc.setRegister(2, 3);

		CMPUI(proc, mem, 0x1, 0x2, 2);

		assertThat(proc.register(1), equalTo(1l));
	}

	@Test
	public void should_CMPUI_greater_than1() {
		proc.setRegister(2, Long.MIN_VALUE);

		CMPUI(proc, mem, 0x1, 0x2, 1);

		// if unsigned MIN is > 1
		assertThat(proc.register(1), equalTo(1l));
	}

	@Test
	public void should_NEG() {
		proc.setRegister(1, Long.MAX_VALUE);
		proc.setRegister(2, 1);
		proc.setRegister(3, 0);
		proc.setRegister(4, -1);
		proc.setRegister(5, -Long.MAX_VALUE);

		NEG(proc, mem, 11, 0, 1);
		NEG(proc, mem, 12, 0, 2);
		NEG(proc, mem, 13, 0, 3);
		NEG(proc, mem, 14, 0, 4);
		NEG(proc, mem, 15, 0, 5);

		NEG(proc, mem, 16, 3, 1);
		NEG(proc, mem, 17, 3, 2);

		assertThat(proc.register(11), equalTo(-Long.MAX_VALUE));
		assertThat(proc.register(12), equalTo(-1l));
		assertThat(proc.register(13), equalTo(0l));
		assertThat(proc.register(14), equalTo(1l));
		assertThat(proc.register(15), equalTo(Long.MAX_VALUE));
		assertThat(proc.register(16), equalTo(-Long.MAX_VALUE + 3));
		assertThat(proc.register(17), equalTo(2l));
	}

	@Test
	public void should_NEGI() {

		NEGI(proc, mem, 11, 0, 1);
		NEGI(proc, mem, 12, -1, 1);
		NEGI(proc, mem, 13, 1, 1);
		NEGI(proc, mem, 14, 2, -1);

		assertThat(proc.register(11), equalTo(-1l));
		assertThat(proc.register(12), equalTo(-2l));
		assertThat(proc.register(13), equalTo(0l));
		assertThat(proc.register(14), equalTo(3l));
	}

	@Test
	public void should_NEGU() {
		proc.setRegister(1, Long.MAX_VALUE);
		proc.setRegister(2, 1);
		proc.setRegister(3, 0);
		proc.setRegister(4, -1);
		proc.setRegister(5, -Long.MAX_VALUE);

		NEGU(proc, mem, 11, 0, 1);
		NEGU(proc, mem, 12, 0, 2);
		NEGU(proc, mem, 13, 0, 3);
		NEGU(proc, mem, 14, 0, 4);
		NEGU(proc, mem, 15, 0, 5);

		NEGU(proc, mem, 16, 3, 1);
		NEGU(proc, mem, 17, 3, 2);

		assertThat(proc.register(11), equalTo(-Long.MAX_VALUE));
		assertThat(proc.register(12), equalTo(-1l));
		assertThat(proc.register(13), equalTo(0l));
		assertThat(proc.register(14), equalTo(1l));
		assertThat(proc.register(15), equalTo(Long.MAX_VALUE));
		assertThat(proc.register(16), equalTo(-Long.MAX_VALUE + 3));
		assertThat(proc.register(17), equalTo(2l));
	}

	@Test
	public void should_NEGUI() {
		NEGUI(proc, mem, 11, 0, 1);
		NEGUI(proc, mem, 12, -1, 1);
		NEGUI(proc, mem, 13, 1, 1);
		NEGUI(proc, mem, 14, 2, -1);

		assertThat(proc.register(11), equalTo(-1l));
		assertThat(proc.register(12), equalTo(-2l));
		assertThat(proc.register(13), equalTo(0l));
		assertThat(proc.register(14), equalTo(3l));
	}

	private void checkOp(Instruction inst, long x, long y, long z) {
		proc.setRegister(2, y);
		proc.setRegister(3, z);

		inst.op(proc, mem, 1, 2, 3);

		assertThat(proc.register(1), equalTo(x));
	}

	private void checkOpI(Instruction inst, long x, long y, int z) {
		proc.setRegister(2, y);

		inst.op(proc, mem, 1, 2, z);

		assertThat(proc.register(1), equalTo(x));
	}

}
