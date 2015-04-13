package fr.broeglin.jmmix.simulator;

import static fr.broeglin.jmmix.simulator.Memory.DATA_SEGMENT;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class MemoryNodeTest {

	private static final long VALUE_64 = 0xF123456789ABCDEFl;
	private static final int VALUE_32 = 0x01234567;
	private static final int VALUE_16 = 0xFEDC;

	MemoryNode memory = new MemoryNode(Memory.DATA_SEGMENT);

	@Before
	public void set_start_of_data_segment() {
		memory.store64(DATA_SEGMENT, VALUE_64);

		memory.store32(DATA_SEGMENT + 16, VALUE_32);
		memory.store32(DATA_SEGMENT + 20, VALUE_32);

		memory.store16(DATA_SEGMENT + 32, VALUE_16);
		memory.store16(DATA_SEGMENT + 34, VALUE_16 - 1);
		memory.store16(DATA_SEGMENT + 36, VALUE_16 - 2);
		memory.store16(DATA_SEGMENT + 38, VALUE_16 - 3);

		memory.store8(DATA_SEGMENT + 48, 0xf0);
		memory.store8(DATA_SEGMENT + 49, 0xe1);
		memory.store8(DATA_SEGMENT + 50, 0xd2);
		memory.store8(DATA_SEGMENT + 51, 0xc3);
		memory.store8(DATA_SEGMENT + 52, 0xb4);
		memory.store8(DATA_SEGMENT + 53, 0xa5);
		memory.store8(DATA_SEGMENT + 54, 0x96);
		memory.store8(DATA_SEGMENT + 55, 0x87);
	}

	@Test
	public void should_load_bytes_from_octa_store() {
		assertThat(memory.load8(DATA_SEGMENT + 0), equalTo((byte) 0xf1));
		assertThat(memory.load8(DATA_SEGMENT + 1), equalTo((byte) 0x23));
		assertThat(memory.load8(DATA_SEGMENT + 2), equalTo((byte) 0x45));
		assertThat(memory.load8(DATA_SEGMENT + 3), equalTo((byte) 0x67));
		assertThat(memory.load8(DATA_SEGMENT + 4), equalTo((byte) 0x89));
		assertThat(memory.load8(DATA_SEGMENT + 5), equalTo((byte) 0xab));
		assertThat(memory.load8(DATA_SEGMENT + 6), equalTo((byte) 0xcd));
		assertThat(memory.load8(DATA_SEGMENT + 7), equalTo((byte) 0xef));
	}

	@Test
	public void should_load_bytes_from_tetra_store() {
		assertThat(memory.load8(DATA_SEGMENT + 16), equalTo((byte) 0x01));
		assertThat(memory.load8(DATA_SEGMENT + 17), equalTo((byte) 0x23));
		assertThat(memory.load8(DATA_SEGMENT + 18), equalTo((byte) 0x45));
		assertThat(memory.load8(DATA_SEGMENT + 19), equalTo((byte) 0x67));
		assertThat(memory.load8(DATA_SEGMENT + 20), equalTo((byte) 0x01));
		assertThat(memory.load8(DATA_SEGMENT + 21), equalTo((byte) 0x23));
		assertThat(memory.load8(DATA_SEGMENT + 22), equalTo((byte) 0x45));
		assertThat(memory.load8(DATA_SEGMENT + 23), equalTo((byte) 0x67));
	}

	@Test
	public void should_load_bytes_from_wyde_store() {
		assertThat(memory.load8(DATA_SEGMENT + 32), equalTo((byte) 0xfe));
		assertThat(memory.load8(DATA_SEGMENT + 33), equalTo((byte) 0xdc));
		assertThat(memory.load8(DATA_SEGMENT + 34), equalTo((byte) 0xfe));
		assertThat(memory.load8(DATA_SEGMENT + 35), equalTo((byte) 0xdb));
		assertThat(memory.load8(DATA_SEGMENT + 36), equalTo((byte) 0xfe));
		assertThat(memory.load8(DATA_SEGMENT + 37), equalTo((byte) 0xda));
		assertThat(memory.load8(DATA_SEGMENT + 38), equalTo((byte) 0xfe));
		assertThat(memory.load8(DATA_SEGMENT + 39), equalTo((byte) 0xd9));
	}

	@Test
	public void should_load_bytes_from_byte_store() {
		assertThat(memory.load8(DATA_SEGMENT + 48), equalTo((byte) 0xf0));
		assertThat(memory.load8(DATA_SEGMENT + 49), equalTo((byte) 0xe1));
		assertThat(memory.load8(DATA_SEGMENT + 50), equalTo((byte) 0xd2));
		assertThat(memory.load8(DATA_SEGMENT + 51), equalTo((byte) 0xc3));
		assertThat(memory.load8(DATA_SEGMENT + 52), equalTo((byte) 0xb4));
		assertThat(memory.load8(DATA_SEGMENT + 53), equalTo((byte) 0xa5));
		assertThat(memory.load8(DATA_SEGMENT + 54), equalTo((byte) 0x96));
		assertThat(memory.load8(DATA_SEGMENT + 55), equalTo((byte) 0x87));
	}

	@Test
	public void should_load_wydes_from_octa_store() {
		assertThat(memory.load16(DATA_SEGMENT + 0), equalTo((short) 0xf123));
		assertThat(memory.load16(DATA_SEGMENT + 1), equalTo((short) 0xf123));
		assertThat(memory.load16(DATA_SEGMENT + 2), equalTo((short) 0x4567));
		assertThat(memory.load16(DATA_SEGMENT + 3), equalTo((short) 0x4567));
		assertThat(memory.load16(DATA_SEGMENT + 4), equalTo((short) 0x89AB));
		assertThat(memory.load16(DATA_SEGMENT + 5), equalTo((short) 0x89AB));
		assertThat(memory.load16(DATA_SEGMENT + 6), equalTo((short) 0xCDEF));
		assertThat(memory.load16(DATA_SEGMENT + 7), equalTo((short) 0xCDEF));
	}

	@Test
	public void should_load_wydes_from_tetra_store() {
		assertThat(memory.load16(DATA_SEGMENT + 16), equalTo((short) 0x0123));
		assertThat(memory.load16(DATA_SEGMENT + 17), equalTo((short) 0x0123));
		assertThat(memory.load16(DATA_SEGMENT + 18), equalTo((short) 0x4567));
		assertThat(memory.load16(DATA_SEGMENT + 19), equalTo((short) 0x4567));
		assertThat(memory.load16(DATA_SEGMENT + 20), equalTo((short) 0x0123));
		assertThat(memory.load16(DATA_SEGMENT + 21), equalTo((short) 0x0123));
		assertThat(memory.load16(DATA_SEGMENT + 22), equalTo((short) 0x4567));
		assertThat(memory.load16(DATA_SEGMENT + 23), equalTo((short) 0x4567));
	}

	@Test
	public void should_load_wydes_from_wyde_store() {
		assertThat(memory.load16(DATA_SEGMENT + 32), equalTo((short) 0xfedc));
		assertThat(memory.load16(DATA_SEGMENT + 33), equalTo((short) 0xfedc));
		assertThat(memory.load16(DATA_SEGMENT + 34), equalTo((short) 0xfedb));
		assertThat(memory.load16(DATA_SEGMENT + 35), equalTo((short) 0xfedb));
		assertThat(memory.load16(DATA_SEGMENT + 36), equalTo((short) 0xfeda));
		assertThat(memory.load16(DATA_SEGMENT + 37), equalTo((short) 0xfeda));
		assertThat(memory.load16(DATA_SEGMENT + 38), equalTo((short) 0xfed9));
		assertThat(memory.load16(DATA_SEGMENT + 39), equalTo((short) 0xfed9));
	}

	@Test
	public void should_load_wydes_from_byte_store() {
		assertThat(memory.load16(DATA_SEGMENT + 48), equalTo((short) 0xf0e1));
		assertThat(memory.load16(DATA_SEGMENT + 49), equalTo((short) 0xf0e1));
		assertThat(memory.load16(DATA_SEGMENT + 50), equalTo((short) 0xd2c3));
		assertThat(memory.load16(DATA_SEGMENT + 51), equalTo((short) 0xd2c3));
		assertThat(memory.load16(DATA_SEGMENT + 52), equalTo((short) 0xb4a5));
		assertThat(memory.load16(DATA_SEGMENT + 53), equalTo((short) 0xb4a5));
		assertThat(memory.load16(DATA_SEGMENT + 54), equalTo((short) 0x9687));
		assertThat(memory.load16(DATA_SEGMENT + 55), equalTo((short) 0x9687));
	}

	@Test
	public void should_load_tetras_from_octa_store() {
		assertThat(memory.load32(DATA_SEGMENT + 0), equalTo(0xf1234567));
		assertThat(memory.load32(DATA_SEGMENT + 1), equalTo(0xf1234567));
		assertThat(memory.load32(DATA_SEGMENT + 2), equalTo(0xf1234567));
		assertThat(memory.load32(DATA_SEGMENT + 3), equalTo(0xf1234567));
		assertThat(memory.load32(DATA_SEGMENT + 4), equalTo(0x89abcdef));
		assertThat(memory.load32(DATA_SEGMENT + 5), equalTo(0x89abcdef));
		assertThat(memory.load32(DATA_SEGMENT + 6), equalTo(0x89abcdef));
		assertThat(memory.load32(DATA_SEGMENT + 7), equalTo(0x89abcdef));
	}

	@Test
	public void should_load_tetras_from_tetra_store() {
		assertThat(memory.load32(DATA_SEGMENT + 16), equalTo(0x01234567));
		assertThat(memory.load32(DATA_SEGMENT + 17), equalTo(0x01234567));
		assertThat(memory.load32(DATA_SEGMENT + 18), equalTo(0x01234567));
		assertThat(memory.load32(DATA_SEGMENT + 19), equalTo(0x01234567));
		assertThat(memory.load32(DATA_SEGMENT + 20), equalTo(0x01234567));
		assertThat(memory.load32(DATA_SEGMENT + 21), equalTo(0x01234567));
		assertThat(memory.load32(DATA_SEGMENT + 22), equalTo(0x01234567));
		assertThat(memory.load32(DATA_SEGMENT + 23), equalTo(0x01234567));
	}

	@Test
	public void should_load_tetras_from_wyde_store() {
		assertThat(memory.load32(DATA_SEGMENT + 32), equalTo(0xfedcfedb));
		assertThat(memory.load32(DATA_SEGMENT + 33), equalTo(0xfedcfedb));
		assertThat(memory.load32(DATA_SEGMENT + 34), equalTo(0xfedcfedb));
		assertThat(memory.load32(DATA_SEGMENT + 35), equalTo(0xfedcfedb));
		assertThat(memory.load32(DATA_SEGMENT + 36), equalTo(0xfedafed9));
		assertThat(memory.load32(DATA_SEGMENT + 37), equalTo(0xfedafed9));
		assertThat(memory.load32(DATA_SEGMENT + 38), equalTo(0xfedafed9));
		assertThat(memory.load32(DATA_SEGMENT + 39), equalTo(0xfedafed9));
	}

	@Test
	public void should_load_tetras_from_byte_store() {
		assertThat(memory.load32(DATA_SEGMENT + 48), equalTo(0xf0e1d2c3));
		assertThat(memory.load32(DATA_SEGMENT + 49), equalTo(0xf0e1d2c3));
		assertThat(memory.load32(DATA_SEGMENT + 50), equalTo(0xf0e1d2c3));
		assertThat(memory.load32(DATA_SEGMENT + 51), equalTo(0xf0e1d2c3));
		assertThat(memory.load32(DATA_SEGMENT + 52), equalTo(0xb4a59687));
		assertThat(memory.load32(DATA_SEGMENT + 53), equalTo(0xb4a59687));
		assertThat(memory.load32(DATA_SEGMENT + 54), equalTo(0xb4a59687));
		assertThat(memory.load32(DATA_SEGMENT + 55), equalTo(0xb4a59687));
	}

	@Test
	public void should_load_octas_from_octa_store() {
		assertThat(memory.load64(DATA_SEGMENT + 0), equalTo(VALUE_64));
		assertThat(memory.load64(DATA_SEGMENT + 1), equalTo(VALUE_64));
		assertThat(memory.load64(DATA_SEGMENT + 2), equalTo(VALUE_64));
		assertThat(memory.load64(DATA_SEGMENT + 3), equalTo(VALUE_64));
		assertThat(memory.load64(DATA_SEGMENT + 4), equalTo(VALUE_64));
		assertThat(memory.load64(DATA_SEGMENT + 5), equalTo(VALUE_64));
		assertThat(memory.load64(DATA_SEGMENT + 6), equalTo(VALUE_64));
		assertThat(memory.load64(DATA_SEGMENT + 7), equalTo(VALUE_64));
	}

	@Test
	public void should_load_octas_from_tetra_store() {
		final long value = 0x0123456701234567l;

		assertThat(memory.load64(DATA_SEGMENT + 16), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 17), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 18), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 19), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 20), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 21), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 22), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 23), equalTo(value));
	}

	@Test
	public void should_load_octas_from_wyde_store() {
		final long value = 0xfedcfedbfedafed9l;

		assertThat(memory.load64(DATA_SEGMENT + 32), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 33), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 34), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 35), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 36), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 37), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 38), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 39), equalTo(value));
	}

	@Test
	public void should_load_octas_from_byte_store() {
		final long value = 0xf0e1d2c3b4a59687l;

		assertThat(memory.load64(DATA_SEGMENT + 48), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 49), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 50), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 51), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 52), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 53), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 54), equalTo(value));
		assertThat(memory.load64(DATA_SEGMENT + 55), equalTo(value));
	}

	@Test
	public void should_store_two_tetras_and_read_octat() {
		long addr = 0x100;

		memory = new MemoryNode(0x0);

		memory.store32(addr, 0xe3020002);
		memory.store32(addr + 4, 0xe3010028);
		assertThat(memory.load64(addr), equalTo(0xe3020002e3010028l));
	}
}
