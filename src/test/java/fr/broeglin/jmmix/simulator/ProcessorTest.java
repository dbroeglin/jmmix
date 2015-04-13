package fr.broeglin.jmmix.simulator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ProcessorTest {

	Processor proc = new Processor();

	@Test(expected = UnknownRegister.class)
	public void register_nimus_one_should_not_exist() {
		proc.register(-1);
	}

	@Test
	public void register_0_should_initially_contain_0() {
		assertThat(proc.register(0), equalTo(0l));
	}

	@Test
	public void register_1_should_initially_contain_0() {
		assertThat(proc.register(1), equalTo(0l));
	}

	@Test
	public void register_255_should_initially_contain_0() {
		assertThat(proc.register(255), equalTo(0l));
	}

	@Test(expected = UnknownRegister.class)
	public void register_256_should_not_exist() {
		proc.register(256);
	}

	@Test
	public void should_set_and_return_register() {
		proc.setRegister(1, 255);

		assertThat(proc.register(1), equalTo(255l));
	}

	@Test
	public void should_set_and_return_register_huge_value() {
		proc.setRegister(1, 0x0123_4567_89AB_CDEFl);

		assertThat(proc.register(1), equalTo(0x0123_4567_89AB_CDEFl));
	}
}
