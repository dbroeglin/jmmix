package fr.broeglin.jmmix.simulator;

import static fr.broeglin.jmmix.simulator.InstructionSet.ADD;
import static fr.broeglin.jmmix.simulator.InstructionSet.AND;
import static fr.broeglin.jmmix.simulator.InstructionSet.ANDI;
import static fr.broeglin.jmmix.simulator.InstructionSet.INCH;
import static fr.broeglin.jmmix.simulator.InstructionSet.INCL;
import static fr.broeglin.jmmix.simulator.InstructionSet.INCMH;
import static fr.broeglin.jmmix.simulator.InstructionSet.INCML;
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
}
