package fr.broeglin.jmmix.simulator.instructions;

import static fr.broeglin.jmmix.simulator.Processor.V_BIT;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.easymock.internal.matchers.GreaterOrEqual;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import fr.broeglin.jmmix.simulator.Processor;

public class ShiftInstructionsTest extends AbstractInstructionsTest {

	@Test
	public void should_SL() {
		proc.setRegister(1, 0x12345678);
		proc.setRegister(2, 0b1111_1111_1111_1111l);
		proc.setRegister(3, 0x3l);

		ShiftInstructions.SL(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.register(1), equalTo(0b111_1111_1111_1111_1000l));
		assertThat(proc.rA(V_BIT), is(false));
	}
	
	@Test
	public void should_SL_with_overflow() {
		proc.setRegister(1, 0x12345678);
		proc.setRegister(2, 0xBFFF_FFFF_FFFF_FFFFl);
		proc.setRegister(3, 0x3l);

		ShiftInstructions.SL(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.register(1), equalTo(0xFFFF_FFFF_FFFF_FFF8l));
		assertThat(proc.rA(V_BIT), is(true));
	}
	
	@Test
	public void should_SLI() {
		proc.setRegister(1, 0x12345678);
		proc.setRegister(2, 0b1111_1111_1111_1111l);

		ShiftInstructions.SLI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.register(1), equalTo(0b111_1111_1111_1111_1000l));
		assertThat(proc.rA(V_BIT), is(false));
	}

	@Test
	public void should_SLI_with_overflow() {
		proc.setRegister(1, 0x12345678);
		proc.setRegister(2, 0xBFFF_FFFF_FFFF_FFFFl);

		ShiftInstructions.SLI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.register(1), equalTo(0xFFFF_FFFF_FFFF_FFF8l));
		assertThat(proc.rA(V_BIT), is(true));
	}

	
	@Test
	public void should_SLU() {
		// TODO
		fail();
	}

	@Test
	public void should_SLUI() {
		// TODO
		fail();
	}
}
