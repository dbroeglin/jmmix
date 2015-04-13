package fr.broeglin.jmmix.simulator;

public interface Instruction {

	public void op(Processor proc, Memory mem, int x, int y, int z);

}
