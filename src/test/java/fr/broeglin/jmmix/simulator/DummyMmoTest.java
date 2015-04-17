package fr.broeglin.jmmix.simulator;

import static org.easymock.EasyMock.anyInt;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.io.DataInputStream;
import java.io.FileInputStream;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import fr.broeglin.jmmix.simulator.AbstractMmoTest.MmixSource;

@MmixSource("dummy.mms")
public class DummyMmoTest extends AbstractMmoTest {

	@Before
	public void loadProgramAndMockAsReadonlySimulator() throws Exception {
		simulator = new Simulator();
		Processor actualProcessor = simulator.getProcessor();
		Memory actualMemory = simulator.getMemory();

		new Loader(new DataInputStream(new FileInputStream(objectFile)),
				simulator).load();

		simulator = new Simulator(processor, memory);

		expect(processor.register(anyInt())).andStubDelegateTo(actualProcessor);
		expect(processor.isRunning()).andStubDelegateTo(actualProcessor);
		expect(memory.load32(anyInt())).andStubDelegateTo(actualMemory);

		processor.setRunning(eq(false));
		EasyMock.expectLastCall().andStubDelegateTo(actualProcessor);

		replay(processor, memory);

	}

	@Test
	public void test() throws Exception {
		simulator.execute();

		verify(processor, memory);
	}

	// plumbing

	@Rule
	public EasyMockRule rule = new EasyMockRule(this);

	@Mock
	private Processor processor;

	@Mock
	private Memory memory;

	private Simulator simulator;

}
