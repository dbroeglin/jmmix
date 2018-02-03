package fr.broeglin.jmmix.simulator.instructions;

import static fr.broeglin.jmmix.simulator.Processor.V_BIT;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

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
		proc.setRegister(1, 0x12345678);
		proc.setRegister(2, 0b1111_1111_1111_1111l);
		proc.setRegister(3, 0x3l);

		ShiftInstructions.SLU(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.register(1), equalTo(0b111_1111_1111_1111_1000l));
		assertThat(proc.rA(V_BIT), is(false));
	}

	@Test
	public void should_SLU_with_overflow() {
		proc.setRegister(1, 0x12345678);
		proc.setRegister(2, 0xBFFF_FFFF_FFFF_FFFFl);
		proc.setRegister(3, 0x3l);

		ShiftInstructions.SLU(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.register(1), equalTo(0xFFFF_FFFF_FFFF_FFF8l));
		// does not cause overflow
		assertThat(proc.rA(V_BIT), is(false));
	}

	@Test
	public void should_SLUI() {
		proc.setRegister(1, 0x12345678);
		proc.setRegister(2, 0b1111_1111_1111_1111l);

		ShiftInstructions.SLUI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.register(1), equalTo(0b111_1111_1111_1111_1000l));
		assertThat(proc.rA(V_BIT), is(false));
	}

	@Test
	public void should_SLUI_with_overflow() {
		proc.setRegister(1, 0x12345678);
		proc.setRegister(2, 0xBFFF_FFFF_FFFF_FFFFl);

		ShiftInstructions.SLUI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.register(1), equalTo(0xFFFF_FFFF_FFFF_FFF8l));
		// does not cause overflow
		assertThat(proc.rA(V_BIT), is(false));
	}

	@Test
	public void should_SR() {
		proc.setRegister(1, 0x12345678);
		proc.setRegister(2, 0b1111_1111_1111_1111l);
		proc.setRegister(3, 0x3l);

		ShiftInstructions.SR(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.register(1), equalTo(0b0001_1111_1111_1111l));
		assertThat(proc.rA(V_BIT), is(false));
	}

	@Test
	public void should_SRI() {
		proc.setRegister(1, 0x12345678);
		proc.setRegister(2, 0b1111_1111_1111_1111l);

		ShiftInstructions.SRI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.register(1), equalTo(0b0001_1111_1111_1111l));
		assertThat(proc.rA(V_BIT), is(false));
	}

	@Test
	public void should_SRI_with_sign_bit() {
		proc.setRegister(1, 0x12345678);
		proc.setRegister(2, -1l);   // 0xFFFF_FFFF_FFFF_FFFF
		proc.setRegister(4, -128l); // 0xFFFF_FFFF_FFFF_FF80

		ShiftInstructions.SRI(proc, mem, 0x01, 0x02, 0x03);
		ShiftInstructions.SRI(proc, mem, 0x05, 0x04, 0x03);

		assertThat(proc.register(1), equalTo(-1l));
		assertThat(proc.register(5), equalTo(-16l));  // 0xFFFF_FFFF_FFFF_FFF0
		assertThat(proc.rA(V_BIT), is(false));
	}

	@Test
	public void should_SRUI() {
		proc.setRegister(1, 0x12345678);
		proc.setRegister(2, 0b1111_1111_1111_1111l);

		ShiftInstructions.SRUI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.register(1), equalTo(0b0001_1111_1111_1111l));
		assertThat(proc.rA(V_BIT), is(false));
	}

	@Test
	public void should_SRUI_with_sign_bit() {
		proc.setRegister(1, 0x12345678);
		proc.setRegister(2, -1l);   // 0xFFFF_FFFF_FFFF_FFFF
		proc.setRegister(4, -128l); // 0xFFFF_FFFF_FFFF_FF80

		ShiftInstructions.SRUI(proc, mem, 0x01, 0x02, 0x03);
		ShiftInstructions.SRUI(proc, mem, 0x05, 0x04, 0x03);

		assertThat(proc.register(1), equalTo(0x1FFF_FFFF_FFFF_FFFFl));
		assertThat(proc.register(5), equalTo(0x1FFF_FFFF_FFFF_FFF0l));
		assertThat(proc.rA(V_BIT), is(false));
	}

	@Test
	public void should_SRU() {
		proc.setRegister(1, 0x12345678);
		proc.setRegister(2, 0b1111_1111_1111_1111l);
		proc.setRegister(3, 0x3l);

		ShiftInstructions.SRU(proc, mem, 0x01, 0x02, 0x03);

		assertThat(proc.register(1), equalTo(0b0001_1111_1111_1111l));
		assertThat(proc.rA(V_BIT), is(false));
	}
}
