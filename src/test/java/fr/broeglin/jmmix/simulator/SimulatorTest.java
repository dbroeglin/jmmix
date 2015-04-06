package fr.broeglin.jmmix.simulator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SimulatorTest {

	Simulator simulator = new Simulator();

	@Test(expected = UnknownRegister.class)
	public void register_nimus_one_should_not_exist() {
		simulator.register(-1);
	}

	@Test
	public void register_0_should_initially_contain_0() {
		assertThat(simulator.register(0), equalTo(0l));
	}

	@Test
	public void register_1_should_initially_contain_0() {
		assertThat(simulator.register(1), equalTo(0l));
	}

	@Test
	public void register_255_should_initially_contain_0() {
		assertThat(simulator.register(255), equalTo(0l));
	}

	@Test(expected = UnknownRegister.class)
	public void register_256_should_not_exist() {
		simulator.register(256);
	}

	@Test
	public void should_set_and_return_register() {
		simulator.setRegister(1, 255);

		assertThat(simulator.register(1), equalTo(255l));
	}

	@Test
	public void should_set_and_return_register_huge_value() {
		simulator.setRegister(1, 0x0123_4567_89AB_CDEFl);

		assertThat(simulator.register(1), equalTo(0x0123_4567_89AB_CDEFl));
	}

	@Test
	public void should_display_huge_value() {
		simulator.setRegister(1, 0x0123_4567_89AB_CDEFl);
		long l = simulator.register(1);

		assertThat(simulator.displayOcta(l),
				equalTo("#0123456789ABCDEF"));
	}
	
	@Test
	public void should_execute_add() {
		simulator.setRegister(1, 40);
		simulator.setRegister(2, 2);
		simulator.setRegister(3, 0xffff_ffff_ffff_ffffl);

		simulator.execute(0x22ff0102);
		simulator.execute(0x22fe0302);
		
		simulator.execute(0x20fd0102);
		simulator.execute(0x20fc0302);
		
		assertThat(simulator.register(255), equalTo(42l));		
		assertThat(simulator.register(254), equalTo(1l));		
		assertThat(simulator.register(253), equalTo(42l));		
		assertThat(simulator.register(252), equalTo(1l));		
	}
	
	@Test
	public void dummy() {
		long a = 0xffff_ffff_ffff_ffffl;
		
		assertTrue(a + 3l == 2l);
		assertTrue(a - 1l == 0xffff_ffff_ffff_fffel);
	}

}
