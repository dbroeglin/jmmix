package fr.broeglin.jmmix.simulator;

import java.io.DataInputStream;
import java.io.FileInputStream;

import org.junit.Test;

import fr.broeglin.jmmix.simulator.AbstractMmoTest.MmixSource;

@MmixSource("dummy.mms")
public class DummyMmoTest extends AbstractMmoTest {

	@Test
	public void test() throws Exception {
		Simulator simulator = new Simulator();
		new Loader(new DataInputStream(new FileInputStream(objectFile)),
				simulator).load();

		simulator.execute();

		System.err.println(simulator.dump());
	}
}
