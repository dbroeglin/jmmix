package fr.broeglin.jmmix.simulator;

public class MemoryNode {

	private long location;
	private long[] memory = new long[256];

	public MemoryNode(long location) {
		this.location = location;
	}

	public Long getLocation() {
		return location;
	}

	public void store64(long addr, long value) {
		int offset = (int) (addr - location) / 8;
		
		memory[offset] = value;
	}

	public byte load8(long addr) {
		int offset = (int) (addr - location);
		int shift = (offset % 8) * 8;

		return (byte) ((memory[offset / 8] & (0xffl << shift)) >> shift);
	}

	public int load16(long addr) {
		int offset = (int) (addr - location);
		int shift = (offset % 8) * 8;

		return (int) ((memory[offset / 8] & (0xffffl << shift)) >> shift);
	}

	public int load32(long addr) {
		int offset = (int) (addr - location);
		int shift = (offset % 8) * 8;

		return (int) ((memory[offset / 8] & (0xffffffffl << shift)) >> shift);
	}

	public long load64(long addr) {
		int offset = (int) (addr - location);

		return memory[offset / 8];
	}
}
