package fr.broeglin.jmmix.simulator;

import static fr.broeglin.jmmix.simulator.InstructionSet.*;
import static fr.broeglin.jmmix.simulator.InstructionSet.AND;
import static fr.broeglin.jmmix.simulator.InstructionSet.ANDI;
import static fr.broeglin.jmmix.simulator.InstructionSet.CMP;
import static fr.broeglin.jmmix.simulator.InstructionSet.CMPI;
import static fr.broeglin.jmmix.simulator.InstructionSet.CMPU;
import static fr.broeglin.jmmix.simulator.InstructionSet.CMPUI;
import static fr.broeglin.jmmix.simulator.InstructionSet.INCH;
import static fr.broeglin.jmmix.simulator.InstructionSet.INCL;
import static fr.broeglin.jmmix.simulator.InstructionSet.INCMH;
import static fr.broeglin.jmmix.simulator.InstructionSet.INCML;
import static fr.broeglin.jmmix.simulator.InstructionSet.JMP;
import static fr.broeglin.jmmix.simulator.InstructionSet.JMPB;
import static fr.broeglin.jmmix.simulator.InstructionSet.OR;
import static fr.broeglin.jmmix.simulator.InstructionSet.ORI;
import static fr.broeglin.jmmix.simulator.InstructionSet.SETH;
import static fr.broeglin.jmmix.simulator.InstructionSet.SETL;
import static fr.broeglin.jmmix.simulator.InstructionSet.SETMH;
import static fr.broeglin.jmmix.simulator.InstructionSet.SETML;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

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
	public void should_SETL() {
		proc.setRegister(1, 0xfedcfedcfedcfedcl);

		SETL(proc, mem, 0x01, 0x89, 0xab);

		assertThat(proc.register(1), equalTo(0xfedcfedcfedc89abl));
	}

	@Test
	public void should_SETML() {
		proc.setRegister(1, 0xfedc_fedc_fedc_fedcl);

		SETML(proc, mem, 0x01, 0x89, 0xab);

		assertThat(proc.register(1), equalTo(0xfedc_fedc_89ab_fedcl));
	}

	@Test
	public void should_SETMH() {
		proc.setRegister(1, 0xfedcfedcfedcfedcl);

		SETMH(proc, mem, 0x01, 0x89, 0xab);
		assertThat(proc.register(1), equalTo(0xfedc_89ab_fedc_fedcl));
	}

	@Test
	public void should_SETH() {
		proc.setRegister(1, 0xfedcfedcfedcfedcl);

		SETH(proc, mem, 0x01, 0x89, 0xab);

		assertThat(proc.register(1), equalTo(0x89abfedcfedcfedcl));
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
		proc.setRegister(1, 0x0l);
		proc.setRegister(2, 0xff00000000000001l);
		proc.setRegister(3, 0x0ff0000000000002l);

		OR(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.register(1),
				equalTo(0xff00000000000001l | 0x0ff0000000000002l));
	}

	@Test
	public void should_ORI() {
		proc.setRegister(1, 0x0l);
		proc.setRegister(2, 0xff00000000000007l);

		ORI(proc, mem, 0x01, 0x02, 0x0a);

		assertThat(proc.register(1), equalTo(0xff0000000000000fl));
	}

	@Test
	public void should_AND() {
		proc.setRegister(1, 0x0l);
		proc.setRegister(2, 0xff00000000000001l);
		proc.setRegister(3, 0x0ff0000000000002l);

		AND(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.register(1),
				equalTo(0xff00000000000001l & 0x0ff0000000000002l));
	}

	@Test
	public void should_ANDI() {
		proc.setRegister(1, 0x0l);
		proc.setRegister(2, 0xff00000000000007l);

		ANDI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.register(1), equalTo(0x3l));
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
		proc.setRegister(2, Long.MIN_VALUE);
		proc.setRegister(3, Long.MAX_VALUE);

		CMPU(proc, mem, 0x1, 0x2, 0x3);

		// MIN is > to MAX if unsigned
		assertThat(proc.register(1), equalTo(1l));
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
}
