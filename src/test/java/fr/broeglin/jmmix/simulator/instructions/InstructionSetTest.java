package fr.broeglin.jmmix.simulator.instructions;

import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rR;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.CMP;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.CMPI;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.CMPU;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.CMPUI;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.JMP;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.JMPB;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.NEG;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.NEGI;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.NEGU;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.NEGUI;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.SETH;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.SETL;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.SETMH;
import static fr.broeglin.jmmix.simulator.instructions.InstructionSet.SETML;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fr.broeglin.jmmix.simulator.SpecialRegisterName;

public class InstructionSetTest extends AbstractInstructionsTest {

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

	@Test
	public void should_MUL() {
		checkOp(InstructionSet::MUL, 12, 2, 6);
	}

	@Test
	public void should_MULI() {
		checkOpI(InstructionSet::MULI, 12, 2, 6);
	}

	@Test
	public void GET_should_copy_special_register_to_global() {
		proc.setSpecialRegister(rR, 47l);
		
		assertThat(proc.specialRegister(rR), equalTo(47l));
		assertThat(rR.ordinal(), equalTo(6));
		assertThat(SpecialRegisterName.values()[6], equalTo(rR));
		((Instruction) InstructionSet::GET).op(proc, mem, 1, 0, 6);
		
		assertThat(proc.register(1), equalTo(47l));

	}
}
