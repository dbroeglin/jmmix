package fr.broeglin.jmmix.simulator.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JavaIntConversionTest {

	@Test
	public void dummy() {
		int v = -1;
		long l = 0x1_0001_0001l;
		
		l = l & 0xffff_ffff_0000_0000l | v & 0xffff_ffffl;
		
		assertEquals(0x1_ffff_ffffl, l);
	}
	
}
