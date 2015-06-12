package fr.broeglin.jmmix.simulator.instructions;

import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;

public interface Instruction {

	public void op(Processor proc, Memory mem, int x, int y, int z);

}
