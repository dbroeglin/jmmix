package fr.broeglin.jmmix.simulator;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

public class MemoryConsistencyTest {

	@Test
	public void should_match_byte_model_across_widths_alignment_and_node_boundaries() {
		Memory memory = new Memory();
		Map<Long, Integer> bytes = new HashMap<>();
		Random random = new Random(0x4d4d4958);
		long[] segments = { 0, Memory.DATA_SEGMENT, Memory.POOL_SEGMENT,
				Memory.STACK_SEGMENT, Long.MIN_VALUE, -4096 };
		int[] widths = { 1, 2, 4, 8 };

		for (int i = 0; i < 2000; i++) {
			long address = segments[random.nextInt(segments.length)]
					+ 2040 + random.nextInt(24);
			int width = widths[random.nextInt(widths.length)];
			long value = random.nextLong();
			long aligned = address & -((long) width);
			for (int j = 0; j < width; j++) {
				bytes.put(aligned + j, (int) ((value >>> (8 * (width - j - 1))) & 255));
			}
			switch (width) {
			case 1: memory.store8(address, (int) value); break;
			case 2: memory.store16(address, (int) value); break;
			case 4: memory.store32(address, (int) value); break;
			default: memory.store64(address, value); break;
			}
			for (int loadWidth : widths) {
				long expected = 0;
				long loadAddress = address & -((long) loadWidth);
				for (int j = 0; j < loadWidth; j++) {
					expected = (expected << 8) | bytes.getOrDefault(loadAddress + j, 0);
				}
				switch (loadWidth) {
				case 1: assertEquals((byte) expected, memory.load8(address)); break;
				case 2: assertEquals((short) expected, memory.load16(address)); break;
				case 4: assertEquals((int) expected, memory.load32(address)); break;
				default: assertEquals(expected, memory.load64(address)); break;
				}
			}
		}
	}
}
