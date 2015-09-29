package fr.broeglin.jmmix.simulator;

import static fr.broeglin.jmmix.simulator.Memory.POOL_SEGMENT;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SimulatorTest {

	Simulator simulator = new Simulator();

	@Test
	public void reg_0_should_contain_default_argc() {
		assertThat(simulator.getProcessor().register(0), equalTo(1l));
	}

	@Test
	public void reg_1_should_contain_first_arg_address() {
		assertThat(simulator.getProcessor().register(1),
				equalTo(POOL_SEGMENT + 8));
	}

	@Test
	public void position_after_first_arg_is_0() {
		assertThat(simulator.getMemory().load64(POOL_SEGMENT + 8 * 2),
				equalTo(0l));
	}

	@Test
	public void first_octa_of_pool_points_after_last_arg() {
		assertThat(simulator.getMemory().load64(POOL_SEGMENT),
				equalTo(POOL_SEGMENT + 8 * 4));
	}

	@Test
	public void first_arg_should_be_sim() {
		long firstArgumentAddress = simulator.getMemory().load64(
				Memory.POOL_SEGMENT + 8);

		assertThat(simulator.getMemory().load64(firstArgumentAddress),
				equalTo(0x73696d0000000000l)); // "sim",0,...
	}
}
