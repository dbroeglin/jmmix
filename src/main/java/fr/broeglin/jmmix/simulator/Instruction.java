package fr.broeglin.jmmix.simulator;

public interface Instruction {

	public void op(Simulator sim, int x, int y, int z);

}
