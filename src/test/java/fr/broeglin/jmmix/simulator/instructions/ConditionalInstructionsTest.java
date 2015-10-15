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

	@Test
	public void CSP_should_not_set_if_0() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::CSP,
				0x2al,
				0x0l,
				0x33l);
	}

	@Test
	public void CSP_should_set_if_positive() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::CSP,
				0x33l,
				0x1l,
				0x33l);
	}

	@Test
	public void CSPI_should_not_set_if_0() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::CSPI,
				0x2al,
				0x0l,
				0x33);
	}

	@Test
	public void CSPI_should_set_if_positive() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::CSPI,
				0x33l,
				0x1l,
				0x33);
	}

	@Test
	public void ZSP_should_zero_if_0() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::ZSP,
				0x0,
				0x0l,
				0x33l);
	}

	@Test
	public void ZSP_should_set_if_positive() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::ZSP,
				0x33l,
				0x1l,
				0x33l);
	}

	@Test
	public void ZSPI_should_zero_if_0() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::ZSPI,
				0x0,
				0x0l,
				0x33);
	}

	@Test
	public void ZSPI_should_set_if_positive() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::ZSPI,
				0x33,
				0x1l,
				0x33);
	}

	@Test
	public void CSOD_should_not_set_if_even() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::CSOD,
				0x2al,
				0x0l,
				0x33l);
		checkOp(ConditionalInstructions::CSOD,
				0x2al,
				-0x2l,
				0x33l);
		checkOp(ConditionalInstructions::CSOD,
				0x2al,
				0x2l,
				0x33l);
	}

	@Test
	public void CSOD_should_set_if_odd() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::CSOD,
				0x33l,
				0x1l,
				0x33l);
		checkOp(ConditionalInstructions::CSOD,
				0x33l,
				-0x1l,
				0x33l);
	}

	@Test
	public void CSODI_should_not_set_if_even() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::CSODI,
				0x2al,
				0x0l,
				0x33);
		checkOpI(ConditionalInstructions::CSODI,
				0x2al,
				0x2l,
				0x33);
		checkOpI(ConditionalInstructions::CSODI,
				0x2al,
				-0x2l,
				0x33);
	}

	@Test
	public void CSODI_should_set_if_odd() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::CSODI,
				0x33l,
				0x1l,
				0x33);
		checkOpI(ConditionalInstructions::CSODI,
				0x33l,
				-0x1l,
				0x33);
	}

	@Test
	public void ZSOD_should_zero_if_even() {
		proc.setRegister(1, 0x2al);
		checkOp(ConditionalInstructions::ZSOD,
				0x0,
				0x0l,
				0x33l);

		proc.setRegister(1, 0x2al);
		checkOp(ConditionalInstructions::ZSOD,
				0x0,
				-0x2l,
				0x33l);

		proc.setRegister(1, 0x2al);
		checkOp(ConditionalInstructions::ZSOD,
				0x0,
				0x2l,
				0x33l);
	}

	@Test
	public void ZSOD_should_set_if_odd() {
		proc.setRegister(1, 0x2al);
		checkOp(ConditionalInstructions::ZSOD,
				0x33l,
				0x1l,
				0x33l);

		proc.setRegister(1, 0x2al);
		checkOp(ConditionalInstructions::ZSOD,
				0x33l,
				-0x1l,
				0x33l);
	}

	@Test
	public void ZSODI_should_zero_if_even() {
		proc.setRegister(1, 0x2al);
		checkOpI(ConditionalInstructions::ZSODI,
				0x0,
				0x0l,
				0x33);

		proc.setRegister(1, 0x2al);
		checkOpI(ConditionalInstructions::ZSODI,
				0x0,
				-0x2l,
				0x33);

		proc.setRegister(1, 0x2al);
		checkOpI(ConditionalInstructions::ZSODI,
				0x0,
				0x2l,
				0x33);
	}

	@Test
	public void ZSODI_should_set_if_odd() {
		proc.setRegister(1, 0x2al);
		checkOpI(ConditionalInstructions::ZSODI,
				0x33,
				0x1l,
				0x33);

		proc.setRegister(1, 0x2al);
		checkOpI(ConditionalInstructions::ZSODI,
				0x33,
				-0x1l,
				0x33);
	}

	@Test
	public void CSNN_should_not_set_if_negative() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::CSNN,
				0x2al,
				-0x1l,
				0x33l);
	}

	@Test
	public void CSNN_should_set_if_positive() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::CSNN,
				0x33l,
				0x1l,
				0x33l);
	}

	@Test
	public void CSNN_should_set_if_zero() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::CSNN,
				0x33l,
				0x0l,
				0x33l);
	}

	@Test
	public void CSNNI_should_not_set_if_negative() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::CSNNI,
				0x2al,
				-0x1l,
				0x33);
	}

	@Test
	public void CSNNI_should_set_if_zero() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::CSNNI,
				0x33l,
				0x0l,
				0x33);
	}

	@Test
	public void CSNNI_should_set_if_positive() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::CSNNI,
				0x33l,
				0x1l,
				0x33);
	}

	@Test
	public void ZSNN_should_zero_if_negative() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::ZSNN,
				0x0,
				-0x1l,
				0x33l);
	}

	@Test
	public void ZSNN_should_set_if_zero() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::ZSNN,
				0x33l,
				0x0l,
				0x33l);
	}

	@Test
	public void ZSNN_should_set_if_positive() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::ZSNN,
				0x33l,
				0x1l,
				0x33l);
	}

	@Test
	public void ZSNNI_should_zero_if_negative() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::ZSNNI,
				0x0,
				-0x1l,
				0x33);
	}

	@Test
	public void ZSNNI_should_set_if_zero() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::ZSNNI,
				0x33,
				0x0l,
				0x33);
	}

	@Test
	public void ZSNNI_should_set_if_positive() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::ZSNNI,
				0x33,
				0x1l,
				0x33);
	}

	@Test
	public void CSNZ_should_set_if_negative() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::CSNZ,
				0x33l,
				-0x1l,
				0x33l);
	}

	@Test
	public void CSNZ_should_set_if_positive() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::CSNZ,
				0x33l,
				0x1l,
				0x33l);
	}

	@Test
	public void CSNZ_should_not_set_if_zero() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::CSNZ,
				0x2al,
				0x0l,
				0x33l);
	}

	@Test
	public void CSNZI_should_set_if_negative() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::CSNZI,
				0x33l,
				-0x1l,
				0x33);
	}

	@Test
	public void CSNZI_should_not_set_if_zero() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::CSNZI,
				0x2al,
				0x0l,
				0x33);
	}

	@Test
	public void CSNZI_should_set_if_positive() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::CSNZI,
				0x33l,
				0x1l,
				0x33);
	}

	@Test
	public void ZSNZ_should_set_if_negative() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::ZSNZ,
				0x33l,
				-0x1l,
				0x33l);
	}

	@Test
	public void ZSNZ_should_zero_if_zero() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::ZSNZ,
				0x0l,
				0x0l,
				0x33l);
	}

	@Test
	public void ZSNZ_should_set_if_positive() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::ZSNZ,
				0x33l,
				0x1l,
				0x33l);
	}

	@Test
	public void ZSNZI_should_set_if_negative() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::ZSNZI,
				0x33l,
				-0x1l,
				0x33);
	}

	@Test
	public void ZSNZI_should_zero_if_zero() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::ZSNZI,
				0x0l,
				0x0l,
				0x33);
	}

	@Test
	public void ZSNZI_should_set_if_positive() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::ZSNZI,
				0x33l,
				0x1l,
				0x33);
	}

	@Test
	public void CSNP_should_set_if_negative() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::CSNP,
				0x33l,
				-0x1l,
				0x33l);
	}

	@Test
	public void CSNP_should_not_set_if_positive() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::CSNP,
				0x2al,
				0x1l,
				0x33l);
	}

	@Test
	public void CSNP_should_set_if_zero() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::CSNP,
				0x33l,
				0x0l,
				0x33l);
	}

	@Test
	public void CSNPI_should_set_if_negative() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::CSNPI,
				0x33l,
				-0x1l,
				0x33);
	}

	@Test
	public void CSNPI_should_set_if_zero() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::CSNPI,
				0x33l,
				0x0l,
				0x33);
	}

	@Test
	public void CSNPI_should_not_set_if_positive() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::CSNPI,
				0x2al,
				0x1l,
				0x33);
	}

	@Test
	public void ZSNP_should_set_if_negative() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::ZSNP,
				0x33l,
				-0x1l,
				0x33l);
	}

	@Test
	public void ZSNP_should_set_if_zero() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::ZSNP,
				0x33l,
				0x0l,
				0x33l);
	}

	@Test
	public void ZSNP_should_zero_if_positive() {
		proc.setRegister(1, 0x2al);

		checkOp(ConditionalInstructions::ZSNP,
				0x0l,
				0x1l,
				0x33l);
	}

	@Test
	public void ZSNPI_should_set_if_negative() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::ZSNPI,
				0x33l,
				-0x1l,
				0x33);
	}

	@Test
	public void ZSNPI_should_set_if_zero() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::ZSNPI,
				0x33l,
				0x0l,
				0x33);
	}

	@Test
	public void ZSNPI_should_zero_if_positive() {
		proc.setRegister(1, 0x2al);

		checkOpI(ConditionalInstructions::ZSNPI,
				0x0l,
				0x1l,
				0x33);
	}

	@Test
	public void CSEV_should_set_if_even() {
		proc.setRegister(1, 0x2al);
		checkOp(ConditionalInstructions::CSEV,
				0x33l,
				0x0l,
				0x33l);

		proc.setRegister(1, 0x2al);
		checkOp(ConditionalInstructions::CSEV,
				0x33l,
				-0x2l,
				0x33l);

		proc.setRegister(1, 0x2al);
		checkOp(ConditionalInstructions::CSEV,
				0x33l,
				0x2l,
				0x33l);
	}

	@Test
	public void CSEV_should_not_set_if_odd() {
		proc.setRegister(1, 0x2al);
		checkOp(ConditionalInstructions::CSEV,
				0x2al,
				0x1l,
				0x33l);

		proc.setRegister(1, 0x2al);
		checkOp(ConditionalInstructions::CSEV,
				0x2al,
				-0x1l,
				0x33l);
	}

	@Test
	public void CSEVI_should_set_if_even() {
		proc.setRegister(1, 0x2al);
		checkOpI(ConditionalInstructions::CSEVI,
				0x33l,
				0x0l,
				0x33);

		proc.setRegister(1, 0x2al);
		checkOpI(ConditionalInstructions::CSEVI,
				0x33l,
				0x2l,
				0x33);

		proc.setRegister(1, 0x2al);
		checkOpI(ConditionalInstructions::CSEVI,
				0x33l,
				-0x2l,
				0x33);
	}

	@Test
	public void CSEVI_should_not_set_if_odd() {
		proc.setRegister(1, 0x2al);
		checkOpI(ConditionalInstructions::CSEVI,
				0x2al,
				0x1l,
				0x33);

		proc.setRegister(1, 0x2al);
		checkOpI(ConditionalInstructions::CSEVI,
				0x2al,
				-0x1l,
				0x33);
	}

	@Test
	public void ZSEV_should_set_if_even() {
		proc.setRegister(1, 0x2al);
		checkOp(ConditionalInstructions::ZSEV,
				0x33,
				0x0l,
				0x33l);

		proc.setRegister(1, 0x2al);
		checkOp(ConditionalInstructions::ZSEV,
				0x33,
				-0x2l,
				0x33l);

		proc.setRegister(1, 0x2al);
		checkOp(ConditionalInstructions::ZSEV,
				0x33,
				0x2l,
				0x33l);
	}

	@Test
	public void ZSEV_should_zero_if_odd() {
		proc.setRegister(1, 0x2al);
		checkOp(ConditionalInstructions::ZSEV,
				0,
				0x1l,
				0x33l);

		proc.setRegister(1, 0x2al);
		checkOp(ConditionalInstructions::ZSEV,
				0,
				-0x1l,
				0x33l);
	}

	@Test
	public void ZSEVI_should_set_if_even() {
		proc.setRegister(1, 0x2al);
		checkOpI(ConditionalInstructions::ZSEVI,
				0x33,
				0x0l,
				0x33);

		proc.setRegister(1, 0x2al);
		checkOpI(ConditionalInstructions::ZSEVI,
				0x33,
				-0x2l,
				0x33);

		proc.setRegister(1, 0x2al);
		checkOpI(ConditionalInstructions::ZSEVI,
				0x33,
				0x2l,
				0x33);
	}

	@Test
	public void ZSEVI_should_zero_if_odd() {
		proc.setRegister(1, 0x2al);
		checkOpI(ConditionalInstructions::ZSEVI,
				0,
				0x1l,
				0x33);

		proc.setRegister(1, 0x2al);
		checkOpI(ConditionalInstructions::ZSEVI,
				0,
				-0x1l,
				0x33);
	}

}
