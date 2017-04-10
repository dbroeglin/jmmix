package fr.broeglin.jmmix.simulator.instructions;

import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDBI;
import static fr.broeglin.jmmix.simulator.instructions.LoadStoreInstructions.LDOI;
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
	public void should_LDOI() {
		mem.store64(0x00, 0x0001_0002_0003_0004l);
		mem.store64(0x08, 0x0005_0006_0007_0008l);

		LDOI(proc, mem, 0x02, 0x01, 0x09);
		LDOI(proc, mem, 0x03, 0x01, 0x0f);

		assertThat(proc.register(2), equalTo(0x0005_0006_0007_0008l));
		assertThat(proc.register(3), equalTo(0x0005_0006_0007_0008l));
	}
}
