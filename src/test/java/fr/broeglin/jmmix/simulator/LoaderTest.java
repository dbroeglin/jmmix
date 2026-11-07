package fr.broeglin.jmmix.simulator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class LoaderTest {

	private Simulator simulator = new Simulator();
	private Loader loader = new Loader(null, simulator);

	@Test
	public void should_recognize_LOP() {
		assertTrue(loader.isLop(0x98000000));
		assertTrue(loader.isLop(0x98123456));
		assertTrue(loader.isLop(0x98999999));
	}

	@Test
	public void should_not_recognize_not_LOP() {
		assertFalse(loader.isLop(0x99123456));
		assertFalse(loader.isLop(0x97123456));
		assertFalse(loader.isLop(0x11980000));
		assertFalse(loader.isLop(0x99000001));
		assertFalse(loader.isLop(0x97ffffff));
	}

	@Test
	public void should_extract_LOP_code() {
		assertThat(loader.lopCode(0x99aa9999), equalTo(0xaa0000));
		assertThat(loader.lopCode(0x00aa0000), equalTo(0xaa0000));
		assertThat(loader.lopCode(0xffff), equalTo(0x0));
	}

	@Test
	public void should_read_greg_even_when_lower_tetra_is_negative() throws Exception {
		InputStream is = prepareData(
				0x98090101l,
				0x58EBE059l,
				0x980A00FFl,
				0x7F6001B4l,
				0xC67BC809l);

		Simulator simulator = new Simulator();
		Loader loader = new Loader(new DataInputStream(is), simulator);
		loader.load();

		assertThat(simulator.getProcessor().register(255), equalTo(0x7F6001B4C67BC809l));
	}
	
	@Test(expected = LoaderException.class)
	public void should_reject_non_LOP_preamble_even_with_PRE_code() throws Exception {
		new Loader(new DataInputStream(prepareData(0x00090100l)), simulator).load();
	}

	@Test(expected = LoaderException.class)
	public void should_reject_LOP_that_is_not_preamble() throws Exception {
		new Loader(new DataInputStream(prepareData(0x98010100l)), simulator).load();
	}

	@Test(expected = IOException.class)
	public void should_reject_truncated_register_postamble() throws Exception {
		new Loader(new DataInputStream(prepareData(
				0x98090100l, 0x980a00ffl, 0x12345678l)), simulator).load();
	}

	@Test
	public void should_load_location_with_unsigned_low_tetra() throws Exception {
		Loader loader = new Loader(new DataInputStream(prepareData(
				0x98090100l, 0x98012002l, 0l, 0x80000000l, 0x12345678l)), simulator);
		loader.load();

		assertThat(simulator.getMemory().load32(0x2000000080000000l),
				equalTo(0x12345678));
	}

	// plumbing
	
	InputStream prepareData(long... longs) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);

		for (long l : longs) {
			dos.writeInt((int) l);

		}
		dos.close();

		return new ByteArrayInputStream(baos.toByteArray());
	}	
}
