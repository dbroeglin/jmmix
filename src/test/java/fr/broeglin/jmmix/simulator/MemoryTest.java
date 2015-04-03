package fr.broeglin.jmmix.simulator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MemoryTest {

	Memory memory = new Memory();

	@Test
	public void should_set_and_return_start_of_memory() {
		memory.store64(0x4000_0000_000_0000l, 0x0123456789ABCDEFl);

		assertThat(memory.load8(0x4000_0000_0000_0000l), equalTo((byte)0xEF));
		assertThat(memory.load8(0x4000_0000_0000_0001l), equalTo((byte)0xCD));
		assertThat(memory.load8(0x4000_0000_0000_0002l), equalTo((byte)0xAB));
		assertThat(memory.load8(0x4000_0000_0000_0003l), equalTo((byte)0x89));
		assertThat(memory.load8(0x4000_0000_0000_0004l), equalTo((byte)0x67));
		assertThat(memory.load8(0x4000_0000_0000_0005l), equalTo((byte)0x45));
		assertThat(memory.load8(0x4000_0000_0000_0006l), equalTo((byte)0x23));
		assertThat(memory.load8(0x4000_0000_0000_0007l), equalTo((byte)0x01));
		
		assertThat(memory.load16(0x4000_0000_0000_0000l), equalTo(0xCDEF));
		assertThat(memory.load16(0x4000_0000_0000_0002l), equalTo(0x89AB));
		assertThat(memory.load16(0x4000_0000_0000_0004l), equalTo(0x4567));
		assertThat(memory.load16(0x4000_0000_0000_0006l), equalTo(0x0123));

		assertThat(memory.load32(0x4000_0000_0000_0000l), equalTo(0x89ABCDEF));
		assertThat(memory.load32(0x4000_0000_0000_0004l), equalTo(0x01234567));
		
		assertThat(memory.load64(0x4000_0000_0000_0004l), equalTo(0x0123456789ABCDEFl));
		

	}
}
