package fr.broeglin.jmmix.simulator;

import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rBB;
import static org.easymock.EasyMock.anyInt;
import static org.easymock.EasyMock.anyLong;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
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

		simulator = new Simulator(proc, mem, new String[] { "sim" });

		reset(proc);
		reset(mem);
		simulator.initializeSpecialRegisters();
		expect(proc.register(anyInt())).andStubDelegateTo(actualProcessor);
		expect(proc.isRunning()).andStubDelegateTo(actualProcessor);
		expect(proc.instPtr()).andStubDelegateTo(actualProcessor);
		expect(mem.load32(anyInt())).andStubDelegateTo(actualMemory);
				
		proc.setRunning(eq(false));
		expectLastCall().andStubDelegateTo(actualProcessor);

		proc.setSpecialRegister(rBB, 255);
		expectLastCall().andStubDelegateTo(actualProcessor);

		proc.setInstPtr(anyLong());
		expectLastCall().andStubDelegateTo(actualProcessor);

		proc.incInstPtr(anyInt());
		expectLastCall().andStubDelegateTo(actualProcessor);

		replay(proc, mem);
	}

	@Test
	public void should_not_change_state() throws Exception {
		simulator.execute();

		verify(proc, mem);
	}

	// plumbing

	@Rule
	public EasyMockRule rule = new EasyMockRule(this);

	@Mock
	private Processor proc;

	@Mock
	private Memory mem;

}
