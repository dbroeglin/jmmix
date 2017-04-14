package fr.broeglin.jmmix.simulator.instructions;

import static fr.broeglin.jmmix.simulator.Memory.DATA_SEGMENT;
import static fr.broeglin.jmmix.simulator.Processor.rA_V_MASK;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDB;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDBI;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDBU;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDBUI;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDO;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDOI;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDOU;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDOUI;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDT;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDTI;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDTU;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDTUI;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDW;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDWI;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDWU;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDWUI;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.STB;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.STBI;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.STW;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.STWI;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.broeglin.jmmix.simulator.SpecialRegisterName;

public class LoadStoreInstructionsTest extends AbstractInstructionsTest {

	@Test
	public void should_LDBI() {
		mem.store64(0x00, 0x0001_0002_0003_0004l);
		mem.store64(0x08, 0x0005_0006_0007_00ffl & -0x08l);

		LDBI(proc, mem, 0x02, 0x01, 0x09);
		LDBI(proc, mem, 0x03, 0x01, 0x0f);

		assertThat(proc.register(2), equalTo(0x0000_0000_0000_0005l));
		assertThat(proc.register(3), equalTo(-0x0000_0000_0000_0008l));
	}

	@Test
	public void should_LDBUI() {
		mem.store64(0x00, 0x0001_0002_0003_0004l);
		mem.store64(0x08, 0x0005_0006_0007_00ffl & -0x08l);

		LDBUI(proc, mem, 0x02, 0x01, 0x09);
		LDBUI(proc, mem, 0x03, 0x01, 0x0f);

		assertThat(proc.register(2), equalTo(0x0000_0000_0000_0005l));
		assertThat(proc.register(3), equalTo(0x0000_0000_0000_00f8l));
	}

	@Test
	public void should_LDB() {
		mem.store64(0x00, 0x0001_0002_0003_0004l);
		mem.store64(0x08, 0x0005_0006_0007_00ffl & -0x08l);

		proc.setRegister(0x0a, 0x09);
		proc.setRegister(0x0b, 0x0f);
		
		LDB(proc, mem, 0x02, 0x01, 0x0a);
		LDB(proc, mem, 0x03, 0x01, 0x0b);

		assertThat(proc.register(2), equalTo(0x0000_0000_0000_0005l));
		assertThat(proc.register(3), equalTo(-0x0000_0000_0000_0008l));
	}

	@Test
	public void should_LDBU() {
		mem.store64(0x00, 0x0001_0002_0003_0004l);
		mem.store64(0x08, 0x0005_0006_0007_00ffl & -0x08l);

		proc.setRegister(0x0a, 0x09);
		proc.setRegister(0x0b, 0x0f);
		
		LDBU(proc, mem, 0x02, 0x01, 0x0a);
		LDBU(proc, mem, 0x03, 0x01, 0x0b);

		assertThat(proc.register(2), equalTo(0x0000_0000_0000_0005l));
		assertThat(proc.register(3), equalTo(0x0000_0000_0000_00f8l));
	}

	@Test
	public void should_LDWI() {
		mem.store64(0x00, 0x0001_0002_0003_0004l);
		mem.store64(0x08, 0x0005_0006_0007_00ffl & -0x08);

		LDWI(proc, mem, 0x02, 0x01, 0x09);
		LDWI(proc, mem, 0x03, 0x01, 0x0e);

		assertThat(proc.register(2), equalTo(0x0000_0000_0000_0005l));
		assertThat(proc.register(3), equalTo(0x0000_0000_0000_00f8l));
	}
	
	@Test
	public void should_LDWUI() {
		mem.store64(0x00, 0x0001_0002_0003_0004l);
		mem.store64(0x08, 0x0005_0006_0007_ffffl & -0x08);

		LDWUI(proc, mem, 0x02, 0x01, 0x09);
		LDWUI(proc, mem, 0x03, 0x01, 0x0e);

		assertThat(proc.register(2), equalTo(0x0000_0000_0000_0005l));
		assertThat(proc.register(3), equalTo(0x0000_0000_0000_fff8l));
	}

	@Test
	public void should_LDW() {
		mem.store64(0x00, 0x0001_0002_0003_0004l);
		mem.store64(0x08, 0x0005_0006_0007_00ffl & -0x08l);

		proc.setRegister(0x0a, 0x09);
		proc.setRegister(0x0b, 0x0f);

		LDW(proc, mem, 0x02, 0x01, 0x0a);
		LDW(proc, mem, 0x03, 0x01, 0x0b);

		assertThat(proc.register(2), equalTo(0x0000_0000_0000_0005l));
		assertThat(proc.register(3), equalTo(0x0000_0000_0000_00f8l));
	}

	@Test
	public void should_LDWU() {
		mem.store64(0x00, 0x0001_0002_0003_0004l);
		mem.store64(0x08, 0x0005_0006_0007_ffffl & -0x08l);

		proc.setRegister(0x0a, 0x09);
		proc.setRegister(0x0b, 0x0f);

		LDWU(proc, mem, 0x02, 0x01, 0x0a);
		LDWU(proc, mem, 0x03, 0x01, 0x0b);

		assertThat(proc.register(2), equalTo(0x0000_0000_0000_0005l));
		assertThat(proc.register(3), equalTo(0x0000_0000_0000_fff8l));
	}

	@Test
	public void should_LDTI() {
		mem.store64(0x00, 0x0001_0002_0003_0004l);
		mem.store64(0x08, 0x0005_0006_0007_0008l);

		LDTI(proc, mem, 0x02, 0x01, 0x05);
		LDTI(proc, mem, 0x03, 0x01, 0x0f);

		assertThat(proc.register(2), equalTo(0x0000_0000_0003_0004l));
		assertThat(proc.register(3), equalTo(0x0000_0000_0007_0008l));
	}

	@Test
	public void should_LDTUI() {
		mem.store64(0x00, 0x0001_0002_0003_0004l);
		mem.store64(0x08, 0x0005_0006_ffff_ffffl & -0x09);

		LDTUI(proc, mem, 0x02, 0x01, 0x05);
		LDTUI(proc, mem, 0x03, 0x01, 0x0f);

		assertThat(proc.register(2), equalTo(0x0000_0000_0003_0004l));
		assertThat(proc.register(3), equalTo(0x0000_0000_ffff_fff7l));
	}

	@Test
	public void should_LDT() {
		mem.store64(0x00, 0x0001_0002_0003_0004l);
		mem.store64(0x08, 0x0005_0006_0007_0008l);

		proc.setRegister(0x0a, 0x05);
		proc.setRegister(0x0b, 0x0f);

		LDT(proc, mem, 0x02, 0x01, 0x0a);
		LDT(proc, mem, 0x03, 0x01, 0x0b);

		assertThat(proc.register(2), equalTo(0x0000_0000_0003_0004l));
		assertThat(proc.register(3), equalTo(0x0000_0000_0007_0008l));
	}

	@Test
	public void should_LDTU() {
		mem.store64(0x00, 0x0001_0002_0003_0004l);
		mem.store64(0x08, 0x0005_0006_ffff_0008l);

		proc.setRegister(0x0a, 0x05);
		proc.setRegister(0x0b, 0x0f);

		LDTU(proc, mem, 0x02, 0x01, 0x0a);
		LDTU(proc, mem, 0x03, 0x01, 0x0b);

		assertThat(proc.register(2), equalTo(0x0000_0000_0003_0004l));
		assertThat(proc.register(3), equalTo(0x0000_0000_ffff_0008l));
	}

	@Test
	public void should_LDO() {
		mem.store64(0x00, 0x0001_0002_0003_0004l);
		mem.store64(0x08, 0x0005_0006_0007_0008l);

		proc.setRegister(0x0a, 0x01);
		proc.setRegister(0x0b, 0x0f);
		proc.setRegister(0x0c, 0x05);
		
		LDO(proc, mem, 0x02, 0x01, 0x0a);
		LDO(proc, mem, 0x03, 0x01, 0x0b);

		proc.setRegister(0x01, 5);
		LDO(proc, mem, 0x04, 0x01, 0x0c);

		assertThat(proc.register(2), equalTo(0x0001_0002_0003_0004l));
		assertThat(proc.register(3), equalTo(0x0005_0006_0007_0008l));
		assertThat(proc.register(4), equalTo(0x0005_0006_0007_0008l));
	}

	@Test
	public void should_LDOU() {
		mem.store64(0x00, 0x0001_0002_0003_0004l);
		mem.store64(0x08, 0xf005_0006_0007_0008l);

		proc.setRegister(0x0a, 0x01);
		proc.setRegister(0x0b, 0x0f);
		proc.setRegister(0x0c, 0x05);
		
		LDOU(proc, mem, 0x02, 0x01, 0x0a);
		LDOU(proc, mem, 0x03, 0x01, 0x0b);

		proc.setRegister(0x01, 5);
		LDOU(proc, mem, 0x04, 0x01, 0x0c);

		assertThat(proc.register(2), equalTo(0x0001_0002_0003_0004l));
		assertThat(proc.register(3), equalTo(0xf005_0006_0007_0008l));
		assertThat(proc.register(4), equalTo(0xf005_0006_0007_0008l));
	}

	@Test
	public void should_LDOI() {
		mem.store64(0x00, 0x0001_0002_0003_0004l);
		mem.store64(0x08, 0x0005_0006_0007_0008l);

		LDOI(proc, mem, 0x02, 0x01, 0x01);
		LDOI(proc, mem, 0x03, 0x01, 0x0f);
		
		proc.setRegister(0x01, 5);
		LDOI(proc, mem, 0x04, 0x01, 0x05);

		assertThat(proc.register(2), equalTo(0x0001_0002_0003_0004l));
		assertThat(proc.register(3), equalTo(0x0005_0006_0007_0008l));
		assertThat(proc.register(4), equalTo(0x0005_0006_0007_0008l));
	}

	@Test
	public void should_LDOUI() {
		mem.store64(0x00, 0x0001_0002_0003_0004l);
		mem.store64(0x08, -0x0000_0000_0000_0008l);

		LDOUI(proc, mem, 0x02, 0x01, 0x01);
		LDOUI(proc, mem, 0x03, 0x01, 0x0f);
		
		proc.setRegister(0x01, 5);
		LDOI(proc, mem, 0x04, 0x01, 0x05);

		assertThat(proc.register(2), equalTo(0x0001_0002_0003_0004l));
		assertThat(proc.register(3), equalTo(-0x0000_0000_0000_0008l));
		assertThat(proc.register(4), equalTo(-0x0000_0000_0000_0008l));
	}
	
	@Test
	public void should_STB() {
		mem.store64(DATA_SEGMENT, 0x0123_4567_89ab_cdefl);

		proc.setRegister(0x01, 0xf7l);
		proc.setRegister(0x02, DATA_SEGMENT);
		proc.setRegister(0x03, 3);
		
		STB(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT), equalTo(0x0123_45f7_89ab_cdefl));
		assertThat(proc.specialRegister(SpecialRegisterName.rA) & 0x40, equalTo(0l));
	}

	@Test
	public void STB_should_overflow() {
		mem.store64(DATA_SEGMENT, 0x0123_4567_89ab_cdefl);

		proc.setRegister(0x01, 0xffff_ffff_ffff_0000l);
		proc.setRegister(0x02, DATA_SEGMENT);
		proc.setRegister(0x03, 3);
		
		STB(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT), equalTo(0x0123_4500_89ab_cdefl));
		assertThat(proc.specialRegister(SpecialRegisterName.rA) & 0x40, equalTo(0x40l));
	}
	
	@Test
	public void should_STBI() {
		mem.store64(DATA_SEGMENT, 0x0123_4567_89ab_cdefl);

		proc.setRegister(0x01, 0xf7l);
		proc.setRegister(0x02, DATA_SEGMENT);
		
		STBI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT), equalTo(0x0123_45f7_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));
	}

	@Test
	public void STBI_should_overflow() {
		mem.store64(DATA_SEGMENT, 0x0123_4567_89ab_cdefl);

		proc.setRegister(0x01, 0xffff_ffff_ffff_0000l);
		proc.setRegister(0x02, DATA_SEGMENT);
		
		STBI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT), equalTo(0x0123_4500_89ab_cdefl));
		assertTrue(proc.rA(rA_V_MASK));
	}

	@Test
	public void should_STW() {
		mem.store64(DATA_SEGMENT, 0x0123_4567_89ab_cdefl);

		proc.setRegister(0x01, 0xf5f7l);
		proc.setRegister(0x02, DATA_SEGMENT);
		proc.setRegister(0x03, 3);
		
		STW(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT), equalTo(0x0123_f5f7_89ab_cdefl));
		assertThat(proc.specialRegister(SpecialRegisterName.rA) & 0x40, equalTo(0l));
	}

	@Test
	public void STW_should_overflow() {
		mem.store64(DATA_SEGMENT, 0x0123_4567_89ab_cdefl);

		proc.setRegister(0x01, 0xffff_ffff_ff00_0000l);
		proc.setRegister(0x02, DATA_SEGMENT);
		proc.setRegister(0x03, 3);
		
		STW(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT), equalTo(0x0123_0000_89ab_cdefl));
		assertThat(proc.specialRegister(SpecialRegisterName.rA) & 0x40, equalTo(0x40l));
	}
	
	@Test
	public void should_STWI() {
		mem.store64(DATA_SEGMENT, 0x0123_4567_89ab_cdefl);

		proc.setRegister(0x01, 0xf5f7l);
		proc.setRegister(0x02, DATA_SEGMENT);
		
		STWI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT), equalTo(0x0123_f5f7_89ab_cdefl));
		assertFalse(proc.rA(rA_V_MASK));
	}

	@Test
	public void STBW_should_overflow() {
		mem.store64(DATA_SEGMENT, 0x0123_4567_89ab_cdefl);

		proc.setRegister(0x01, 0xffff_ffff_ff00_0000l);
		proc.setRegister(0x02, DATA_SEGMENT);
		
		STWI(proc, mem, 0x01, 0x02, 0x03);

		assertThat(mem.load64(DATA_SEGMENT), equalTo(0x0123_0000_89ab_cdefl));
		assertTrue(proc.rA(rA_V_MASK));
	}
}
