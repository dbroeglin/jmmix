package fr.broeglin.jmmix.simulator.trace;

public interface Tracer {

	default public void instruction(long location, int instruction) {
		// do nothings
	}

}
