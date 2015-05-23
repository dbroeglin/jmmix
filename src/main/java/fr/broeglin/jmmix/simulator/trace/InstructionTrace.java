package fr.broeglin.jmmix.simulator.trace;

public class InstructionTrace implements Trace {
	private final long location;
	private final int instruction;

	public InstructionTrace(long location, int instruction) {
		this.location = location;
		this.instruction = instruction;
	}

	public long getLocation() {
		return location;
	}

	public int getInstruction() {
		return instruction;
	}

	@Override
	public String toString() {
		return String.format("%016x: %08x", location, instruction);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof InstructionTrace)) {
			return false;
		}
		InstructionTrace other = (InstructionTrace) obj;
		return location == other.location && instruction == other.instruction;
	}

	@Override
	public int hashCode() {
		return Long.hashCode(location) + 31 * Integer.hashCode(instruction);
	}
}
