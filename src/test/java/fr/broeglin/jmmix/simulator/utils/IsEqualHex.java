package fr.broeglin.jmmix.simulator.utils;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;

public class IsEqualHex<T> extends IsEqual<T> {
	private final Object expectedValue;

	public IsEqualHex(T equalArg) {
		super(equalArg);
		this.expectedValue = equalArg;
	}

	@Override
	public void describeTo(Description description) {
		if (expectedValue instanceof Long) {
			description.appendValue(formatLong((Long)expectedValue));
		} else {
			super.describeTo(description);
		}
	}

	private String formatLong(Long value) {
		return String.format("%#016x", value).replaceAll("[0-9a-f]{4}(?<!$)", "$0_");
	}
	
    @Override
    public void describeMismatch(Object item, Description description) {
    	if (item instanceof Long) {
            description.appendText("was ").appendValue(formatLong((Long)item));
    		
    	} else {
    		super.describeMismatch(item, description);
    	}
    }
    
	@Factory
	public static <T> Matcher<T> equalToHex(T operand) {
		return new IsEqualHex<T>(operand);
	}
}
