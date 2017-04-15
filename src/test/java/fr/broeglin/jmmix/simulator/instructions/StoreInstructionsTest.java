package fr.broeglin.jmmix.simulator.instructions;

import static fr.broeglin.jmmix.simulator.Memory.DATA_SEGMENT;
import static fr.broeglin.jmmix.simulator.Processor.rA_V_MASK;
import static fr.broeglin.jmmix.simulator.instructions.StoreInstructions.STB;
import static fr.broeglin.jmmix.simulator.instructions.StoreInstructions.STBI;
import static fr.broeglin.jmmix.simulator.instructions.StoreInstructions.STBU;
import static fr.broeglin.jmmix.simulator.instructions.StoreInstructions.STBUI;
import static fr.broeglin.jmmix.simulator.instructions.StoreInstructions.STO;
import static fr.broeglin.jmmix.simulator.instructions.StoreInstructions.STOI;
import static fr.broeglin.jmmix.simulator.instructions.StoreInstructions.STOU;
import static fr.broeglin.jmmix.simulator.instructions.StoreInstructions.STOUI;
import static fr.broeglin.jmmix.simulator.instructions.StoreInstructions.STT;
import static fr.broeglin.jmmix.simulator.instructions.StoreInstructions.STTI;
import static fr.broeglin.jmmix.simulator.instructions.StoreInstructions.STTU;
import static fr.broeglin.jmmix.simulator.instructions.StoreInstructions.STTUI;
import static fr.broeglin.jmmix.simulator.instructions.StoreInstructions.STW;
import static fr.broeglin.jmmix.simulator.instructions.StoreInstructions.STWI;
import static fr.broeglin.jmmix.simulator.instructions.StoreInstructions.STWU;
import static fr.broeglin.jmmix.simulator.instructions.StoreInstructions.STWUI;
import static fr.broeglin.jmmix.simulator.utils.IsEqualHex.equalToHex;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class StoreInstructionsTest extends AbstractInstructionsTest {

	@Before
	public void initialize_memory_and_r2() {
		mem.store64(DATA_SEGMENT, 0x0123_4567_89ab_cdefl);

		proc.setRegister(0x02, DATA_SEGMENT);
	}

	//
	// Store Byte
	//

	@Test
	public void STB_should_work() {
		proc.setRegister(0x01, 0xf7l);
		proc.setRegister(0x03, 3);

		STB(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0123_45f7_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));
	}

	@Test
	public void STB_should_overflow() {
		proc.setRegister(0x01, 0xffff_ffff_ffff_0000l);
		proc.setRegister(0x03, 3);

		STB(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0123_4500_89ab_cdefl));
		assertTrue(proc.rA(rA_V_MASK));

	}

	@Test
	public void STBI_should_work() {
		proc.setRegister(0x01, 0xf7l);

		STBI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0123_45f7_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));
	}

	@Test
	public void STBI_should_overflow() {
		proc.setRegister(0x01, 0xffff_ffff_ffff_0000l);

		STBI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0123_4500_89ab_cdefl));
		assertTrue(proc.rA(rA_V_MASK));
	}

	@Test
	public void STBU_should_work() {
		proc.setRegister(0x01, 0xf7l);
		proc.setRegister(0x03, 3);

		STBU(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0123_45f7_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));
	}

	@Test
	public void STBU_should_not_overflow() {
		proc.setRegister(0x01, 0xffff_ffff_ffff_0000l);
		proc.setRegister(0x03, 3);

		STBU(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0123_4500_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));
	}

	@Test
	public void STBUI_should_work() {
		proc.setRegister(0x01, 0xf7l);

		STBUI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0123_45f7_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));
	}

	@Test
	public void STBUI_should_not_overflow() {
		proc.setRegister(0x01, 0xffff_ffff_ffff_0000l);

		STBUI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0123_4500_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));
	}

	//
	// Store Wyde
	//

	@Test
	public void STW_should_work() {
		proc.setRegister(0x01, 0xf5f7l);
		proc.setRegister(0x03, 3);

		STW(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0123_f5f7_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));
	}

	@Test
	public void STW_should_overflow() {
		proc.setRegister(0x01, 0xffff_ffff_ff00_0000l);
		proc.setRegister(0x03, 3);

		STW(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0123_0000_89ab_cdefl));
		assertTrue(proc.rA(rA_V_MASK));
	}

	@Test
	public void STWI_should_work() {
		proc.setRegister(0x01, 0xf5f7l);

		STWUI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0123_f5f7_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));
	}

	@Test
	public void STWI_should_overflow() {
		proc.setRegister(0x01, 0xffff_ffff_ff00_0000l);

		STWI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0123_0000_89ab_cdefl));
		assertTrue(proc.rA(rA_V_MASK));
	}

	@Test
	public void STWU_should_work() {
		proc.setRegister(0x01, 0xf5f7l);
		proc.setRegister(0x03, 3);

		STWU(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0123_f5f7_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));
	}

	@Test
	public void STWU_should_not_overflow() {
		proc.setRegister(0x01, 0xffff_ffff_ff00_0000l);
		proc.setRegister(0x03, 3);

		STWU(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0123_0000_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));
	}

	@Test
	public void STWUI_should_work() {
		proc.setRegister(0x01, 0xf5f7l);

		STWUI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0123_f5f7_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));
	}

	@Test
	public void STWUI_should_not_overflow() {
		proc.setRegister(0x01, 0xffff_ffff_ff00_0000l);

		STWUI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0123_0000_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));
	}

	//
	// Store Tetras
	//

	@Test
	public void STT_should_work() {
		proc.setRegister(0x01, 0xf3f4_f5f7l);
		proc.setRegister(0x03, 3);

		STT(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0xf3f4_f5f7_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));

	}

	@Test
	public void STT_should_overflow() {
		proc.setRegister(0x01, 0xffff_ff00_0000_0000l);
		proc.setRegister(0x03, 3);

		STT(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0000_0000_89ab_cdefl));
		assertTrue(proc.rA(rA_V_MASK));

	}

	@Test
	public void STTI_shoudl_work() {
		proc.setRegister(0x01, 0xf3f4_f5f7l);

		STTI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0xf3f4_f5f7_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));
	}

	@Test
	public void STTI_should_overflow() {
		proc.setRegister(0x01, 0xffff_ff00_0000_0000l);

		STTI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0000_0000_89ab_cdefl));
		assertTrue(proc.rA(rA_V_MASK));
	}

	@Test
	public void STTU_should_work() {
		proc.setRegister(0x01, 0xf3f4_f5f7l);
		proc.setRegister(0x03, 3);

		STTU(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0xf3f4_f5f7_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));

	}

	@Test
	public void STTU_should_not_overflow() {
		proc.setRegister(0x01, 0xffff_ff00_0000_0000l);
		proc.setRegister(0x03, 3);

		STTU(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0000_0000_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));

	}

	@Test
	public void STTUI_shoudl_work() {
		proc.setRegister(0x01, 0xf3f4_f5f7l);

		STTUI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0xf3f4_f5f7_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));
	}

	@Test
	public void STTUI_should_not_overflow() {
		proc.setRegister(0x01, 0xffff_ff00_0000_0000l);

		STTUI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0x0000_0000_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));
	}

	//
	// Store Octas
	//

	@Test
	public void STO_should_work() {
		proc.setRegister(0x01, 0xf1f2_f3f4_f5f6_f7f8l);
		proc.setRegister(0x03, 3);

		STO(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0xf1f2_f3f4_f5f6_f7f8l));
		assertFalse(proc.rA(rA_V_MASK));

	}

	@Test
	public void STO_should_not_overflow() {
		proc.setRegister(0x01, 0xff00_0000_0000_0000l);
		proc.setRegister(0x03, 3);

		STO(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0xff00_0000_0000_0000l));
		assertFalse(proc.rA(rA_V_MASK));

	}

	@Test
	public void STOI_should_work() {
		proc.setRegister(0x01, 0xf1f2_f3f4_f5f6_f7f8l);

		STOI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0xf1f2_f3f4_f5f6_f7f8l));
		assertFalse(proc.rA(rA_V_MASK));
	}

	@Test
	public void STOI_should_not_overflow() {
		proc.setRegister(0x01, 0xf1f2_f3f4_f5f6_f7f8l);

		STOI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0xf1f2_f3f4_f5f6_f7f8l));
		assertFalse(proc.rA(rA_V_MASK));
	}

	@Test
	public void STOU_should_work() {
		proc.setRegister(0x01, 0xf1f2_f3f4_f5f6_f7f8l);
		proc.setRegister(0x03, 3);

		STOU(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0xf1f2_f3f4_f5f6_f7f8l));
		assertFalse(proc.rA(rA_V_MASK));

	}

	@Test
	public void STOU_should_not_overflow() {
		proc.setRegister(0x01, 0xf1f2_f3f4_f5f6_f7f8l);
		proc.setRegister(0x03, 3);

		STOU(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0xf1f2_f3f4_f5f6_f7f8l));
		assertFalse(proc.rA(rA_V_MASK));

	}

	@Test
	public void STOUI_shoudl_work() {
		proc.setRegister(0x01, 0xf1f2_f3f4_f5f6_f7f8l);

		STOUI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0xf1f2_f3f4_f5f6_f7f8l));
		assertFalse(proc.rA(rA_V_MASK));
	}

	@Test
	public void STOUI_should_not_overflow() {
		proc.setRegister(0x01, 0xf1f2_f3f4_f5f6_f7f8l);

		STOUI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT),
				equalToHex(0xf1f2_f3f4_f5f6_f7f8l));
		assertFalse(proc.rA(rA_V_MASK));
	}
}
