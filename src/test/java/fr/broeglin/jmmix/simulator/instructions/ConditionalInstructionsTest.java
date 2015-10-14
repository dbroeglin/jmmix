package fr.broeglin.jmmix.simulator.instructions;

import org.junit.Test;

public class ConditionalInstructionsTest extends AbstractInstructionsTest {

	@Test
	public void CSZ_should_set_if_0() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::CSZ,
				0x33l,
				0x0l,
				0x33l);
	}

	@Test
	public void CSZ_should_not_set_if_not_0() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::CSZ,
				0x2al,
				0x1l,
				0x33l);
	}

	@Test
	public void CSZI_should_set_if_0() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::CSZI,
				0x33l,
				0x0l,
				0x33);
	}

	@Test
	public void CSZI_should_not_set_if_not_0() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::CSZI,
				0x2al,
				0x1l,
				0x33);
	}

	
	@Test
	public void ZSZ_should_set_if_0() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::ZSZ,
				0x33l,
				0x0l,
				0x33l);
	}

	@Test
	public void ZSZ_should_zero_if_not_0() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::ZSZ,
				0x0l,
				0x1l,
				0x33l);
	}

	@Test
	public void ZSZI_should_set_if_0() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::ZSZI,
				0x33l,
				0x0l,
				0x33);
	}

	@Test
	public void ZSZI_should_zero_if_not_0() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::ZSZI,
				0x0,
				0x1l,
				0x33);
	}

	@Test
	public void CSN_should_not_set_if_0() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::CSN,
				0x2al,
				0x0l,
				0x33l);
	}

	@Test
	public void CSN_should_set_if_negative() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::CSN,
				0x33l,
				-0x1l,
				0x33l);
	}

	@Test
	public void CSNI_should_not_set_if_0() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::CSNI,
				0x2al,
				0x0l,
				0x33);
	}

	@Test
	public void CSNI_should_set_if_negative() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::CSNI,
				0x33l,
				-0x1l,
				0x33);
	}

	
	@Test
	public void ZSN_should_zero_if_0() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::ZSN,
				0x0,
				0x0l,
				0x33l);
	}

	@Test
	public void ZSN_should_set_if_negative() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::ZSN,
				0x33l,
				-0x1l,
				0x33l);
	}

	@Test
	public void ZSNI_should_zero_if_0() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::ZSNI,
				0x0,
				0x0l,
				0x33);
	}

	@Test
	public void ZSNI_should_set_if_negative() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::ZSNI,
				0x33,
				-0x1l,
				0x33);
	}
}
