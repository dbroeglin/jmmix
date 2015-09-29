package fr.broeglin.jmmix.simulator.trace;

import fr.broeglin.jmmix.simulator.SpecialRegisterName;

public class RegisterTrace implements Trace {

	public final int index;
	public final long value;
	public final boolean isSpecial;

	public RegisterTrace(int index, long value) {
		this.index = index;
		this.value = value;
		this.isSpecial = false;
	}

	public RegisterTrace(int index, long value, boolean isSpecial) {
		this.index = index;
		this.value = value;
		this.isSpecial = isSpecial;
	}

	@Override
	public String toString() {
		if (isSpecial) {
			return String.format("g[%d]=%s=#%x", index,
					SpecialRegisterName.values()[index],
					value);
		} else {
			return String.format("$%d=#%x", index,
					value);
		}
	}
}
