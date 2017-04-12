package fr.broeglin.jmmix.simulator.instructions;

import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDB;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDBI;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDO;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDOI;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDT;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDTI;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDW;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDWI;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

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
	public void should_LDWI() {
		mem.store64(0x00, 0x0001_0002_0003_0004l);
		mem.store64(0x08, 0x0005_0006_0007_00ffl & -0x08l);

		LDWI(proc, mem, 0x02, 0x01, 0x09);
		LDWI(proc, mem, 0x03, 0x01, 0x0e);

		assertThat(proc.register(2), equalTo(0x0000_0000_0000_0005l));
		assertThat(proc.register(3), equalTo(0x0000_0000_0000_00f8l));
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
	public void should_LDTI() {
		mem.store64(0x00, 0x0001_0002_0003_0004l);
		mem.store64(0x08, 0x0005_0006_0007_0008l);

		LDTI(proc, mem, 0x02, 0x01, 0x05);
		LDTI(proc, mem, 0x03, 0x01, 0x0f);

		assertThat(proc.register(2), equalTo(0x0000_0000_0003_0004l));
		assertThat(proc.register(3), equalTo(0x0000_0000_0007_0008l));
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
}
