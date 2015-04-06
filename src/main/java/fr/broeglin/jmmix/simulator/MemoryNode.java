package fr.broeglin.jmmix.simulator;

public class MemoryNode {

	private static final int NODE_SIZE = 256;
	private long location;
	private long[] memory = new long[NODE_SIZE];

	public MemoryNode(long location) {
		this.location = location;
	}

	public Long getLocation() {
		return location;
	}

	public byte load8(long addr) {
		int offset = offset(addr);
		int shift = (~offset & 0b111) * 8;

		return (byte) ((longAt(offset) & (0xffl << shift)) >>> shift);
	}

	// Complicated shift calculus:
	// ((7 - (offset % 8)) & 0b110) * 8
	// ^-- byte size
	// ^-- "align" address on 2 or 4
	// ^-- we store memory as longs
	// ^-- MMIX is big endian
	// Simplified to:
	// (~offset & 0b110) * 8
	// This might or might not be required to ensure
	// the methods are small enough to be inlined...

	public short load16(long addr) {
		int offset = offset(addr);
		int shift = (~offset & 0b110) * 8;

		return (short) ((longAt(offset) & (0xffffl << shift)) >>> shift);
	}

	public int load32(long addr) {
		int offset = offset(addr);
		int shift = (~offset & 0b100) * 8;

		return (int) ((longAt(offset) & (0xffffffffl << shift)) >>> shift);
	}

	public long load64(long addr) {
		return memory[offset(addr) / 8];
	}

	public void store8(long addr, int value) {
		int offset = offset(addr);
		int shift = (~offset & 0b111) * 8;

		setLongAt(offset, (longAt(offset) & ~(0xffl << shift))
				| ((long) value << shift));
	}

	public void store16(long addr, int value) {
		int offset = offset(addr);
		int shift = (~offset & 0b110) * 8;

		setLongAt(offset, (longAt(offset) & ~(0xffffl << shift))
				| ((long) value << shift));
	}

	public void store32(long addr, int value) {
		int offset = offset(addr);
		int shift = (~offset & 0b100) * 8;

		setLongAt(offset, (longAt(offset) & ~(0xffffffffl << shift))
				| ((long) value << shift));
	}

	public void store64(long addr, long value) {
		memory[offset(addr) / 8] = value;
	}

	private long longAt(int offset) {
		return memory[offset / 8];
	}

	private void setLongAt(int offset, long value) {
		memory[offset / 8] = value;
	}

	private int offset(long addr) {
		return (int) (addr - location);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < NODE_SIZE; i++) {
			sb.append(String.format("M8[#%016x] = #%016x\n", i * 8, memory[i]));
		}
		return sb.toString();
	}
}
