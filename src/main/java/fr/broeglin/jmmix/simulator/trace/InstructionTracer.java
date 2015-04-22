package fr.broeglin.jmmix.simulator.trace;

import java.util.ArrayList;

public class InstructionTracer implements Tracer {

	private ArrayList<InstructionTrace> instructions = new ArrayList<>();

	public ArrayList<InstructionTrace> getInstructions() {
		return instructions;
	}

	@Override
	public void instruction(long location, int instruction) {
		instructions.add(new InstructionTrace(location, instruction));
	}
}
