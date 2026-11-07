package fr.broeglin.jmmix.simulator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fr.broeglin.jmmix.simulator.instructions.InstructionSet;

public class OpcodeDispatchTest {

	private final Simulator simulator = new Simulator();
	private final Processor proc = simulator.getProcessor();

	@Test
	public void should_execute_CMPUI_with_immediate_operand() {
		proc.setRegister(2, 3);
		proc.setRegister(4, 0);
		simulator.getMemory().store32(0, 0x33010204);

		simulator.executeInstruction();

		assertThat(proc.register(1), equalTo(-1l));
	}

	@Test
	public void should_execute_CMPUI_with_unsigned_immediate_255() {
		proc.setRegister(2, 255);
		proc.setRegister(255, 0);
		simulator.getMemory().store32(0, 0x330102ff);

		simulator.executeInstruction();

		assertThat(proc.register(1), equalTo(0l));
	}

	@Test
	public void should_execute_CMPU_with_register_operand() {
		proc.setRegister(2, 3);
		proc.setRegister(4, 0);
		simulator.getMemory().store32(0, 0x32010204);

		simulator.executeInstruction();

		assertThat(proc.register(1), equalTo(1l));
		assertThat(proc.instPtr(), equalTo(4l));
	}

	@Test
	public void should_execute_encoded_program_until_halt() {
		proc.setRegister(255, 0x100);
		simulator.getMemory().store32(0x100, 0xe3010007);
		simulator.getMemory().store32(0x104, 0x23020105);
		simulator.getMemory().store32(0x108, 0x00000000);

		simulator.execute();

		assertThat(proc.register(2), equalTo(12l));
		assertThat(proc.isRunning(), equalTo(false));
		assertThat(proc.instPtr(), equalTo(0x10cl));
	}

	@Test(expected = UnknownInstruction.class)
	public void should_reject_negative_opcode() {
		InstructionSet.instruction(-1);
	}

	@Test(expected = UnknownInstruction.class)
	public void should_reject_opcode_256() {
		InstructionSet.instruction(256);
	}

	@Test(expected = UnknownInstruction.class)
	public void should_reject_opcode_257() {
		InstructionSet.instruction(257);
	}

	@Test(expected = UnknownInstruction.class)
	public void should_reject_unimplemented_opcode_during_execution() {
		simulator.getMemory().store32(0, 0xff000000);

		simulator.executeInstruction();
	}
}
