package fr.broeglin.jmmix.simulator.trace;

public class RegisterTrace implements Trace {

	public final int index;
	public final long value;
	
	public RegisterTrace(int index, long value) {
		this.index = index; 
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.format("l[%d]=#%x", index, value);
	}
}
