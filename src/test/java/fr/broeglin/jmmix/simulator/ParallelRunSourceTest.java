package fr.broeglin.jmmix.simulator;

import org.junit.Test;

public class ParallelRunSourceTest extends AbstractMmoTest {
	
	@Test
	public void arith() throws Exception {
		runParallel("arith.mms");
	}
	
	@Test
	public void bitwise() throws Exception {
		runParallel("bitwise.mms");
	}

	@Test
	public void branches() throws Exception {
		runParallel("branches.mms");
	}
	
	@Test
	public void conversion() throws Exception {
		runParallel("conversion.mms");
	}
	
	@Test
	public void dummy() throws Exception {
		runParallel("dummy.mms");
	}
	
	@Test
	public void float_() throws Exception {
		runParallel("float.mms");
	}

	@Test
	public void jumps() throws Exception {
		runParallel("jumps.mms");
	}
	
	@Test
	public void traps() throws Exception {
		runParallel("traps.mms");
	}

}
