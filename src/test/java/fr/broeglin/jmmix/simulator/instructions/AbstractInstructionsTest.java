package fr.broeglin.jmmix.simulator.instructions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;

public abstract class AbstractInstructionsTest {
	protected Processor proc = new Processor();
	protected Memory mem = new Memory();
	
	protected void checkOp(Instruction inst, long x, long y, long z) {
		proc.setRegister(2, y);
		proc.setRegister(3, z);

		inst.op(proc, mem, 1, 2, 3);

		assertThat(proc.register(1), equalTo(x));
	}

	protected void checkOpI(Instruction inst, long x, long y, int z) {
		proc.setRegister(2, y);

		inst.op(proc, mem, 1, 2, z);

		assertThat(proc.register(1), equalTo(x));
	}
}
