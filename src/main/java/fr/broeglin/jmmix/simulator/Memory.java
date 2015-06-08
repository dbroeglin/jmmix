package fr.broeglin.jmmix.simulator;

import java.util.TreeMap;

public class Memory {

	public static final long INSTRUCTION_SEGMENT = 0x0000_0000_0000_0000l;
	public static final long DATA_SEGMENT = 0x2000_0000_0000_0000l;
	public static final long POOL_SEGMENT = 0x4000_0000_0000_0000l;
	public static final long STACK_SEGMENT = 0x6000_0000_0000_0000l;

	TreeMap<Long, MemoryNode> memoryMap = new TreeMap<>();

	public Memory() {
		addNodeFor(INSTRUCTION_SEGMENT);
		addNodeFor(DATA_SEGMENT);
	}

	private MemoryNode addNodeFor(long location) {
		MemoryNode node = new MemoryNode(location);

		memoryMap.put(location, node);

		return node;
	}

	private MemoryNode nodeFor(long addr) {
		long location = nodeLocationFor(addr);
		MemoryNode node = memoryMap.get(location);

		if (node == null) {
			node = addNodeFor(location);
		}
		return node;
	}

	public byte load8(long addr) {
		return nodeFor(addr).load8(addr);
	}

	public short load16(long addr) {
		return nodeFor(addr).load16(addr);
	}

	public int load32(long addr) {
		return nodeFor(addr).load32(addr);
	}

	public long load64(long addr) {
		return nodeFor(addr).load64(addr);
	}

	public void store8(long addr, int value) {
		nodeFor(addr).store8(addr, value);
	}

	public void store16(long addr, int value) {
		nodeFor(addr).store16(addr, value);
	}

	public void store32(long addr, int value) {
		nodeFor(addr).store32(addr, value);
	}

	public void store64(long addr, long value) {
		nodeFor(addr).store64(addr, value);
	}

	@Override
	public String toString() {
		// TODO: finish implementation
		StringBuilder sb = new StringBuilder();

		for (MemoryNode node : memoryMap.values()) {
			sb.append(node.toString());
		}
		return sb.toString();
	}

	public long nodeLocationFor(long addr) {
		return addr & ~0xFFF; // ignore last 12 bits (8 * NODE_SIZE)
	}

	public long getAllocatedSize() {
		return memoryMap.size() * MemoryNode.NODE_SIZE;
	}

	public void dump(StringBuilder sb) {
		for (MemoryNode node : memoryMap.values()) {
			sb.append(node.toString());
		}
	}
}
