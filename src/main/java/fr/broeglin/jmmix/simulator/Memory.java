package fr.broeglin.jmmix.simulator;

import java.io.ByteArrayOutputStream;
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

	private MemoryNode getOrCreateNodeFor(long addr) {
		long location = nodeLocationFor(addr);
		MemoryNode node = memoryMap.get(location);

		if (node == null) {
			node = addNodeFor(location);
		}
		return node;
	}

	private MemoryNode getNodeFor(long addr) {
		long location = nodeLocationFor(addr);
		return memoryMap.get(location);
	}

	public byte load8(long addr) {
		MemoryNode node = getNodeFor(addr);
		
		if (node == null) {
			return 0;
		}
		return getOrCreateNodeFor(addr).load8(addr);
	}

	public short load16(long addr) {
		MemoryNode node = getNodeFor(addr);
		
		if (node == null) {
			return 0;
		}
		return getOrCreateNodeFor(addr).load16(addr);
	}

	public int load32(long addr) {
		MemoryNode node = getNodeFor(addr);
		
		if (node == null) {
			return 0;
		}
		return getOrCreateNodeFor(addr).load32(addr);
	}

	public long load64(long addr) {
		MemoryNode node = getNodeFor(addr);
		
		if (node == null) {
			return 0;
		}
		return getOrCreateNodeFor(addr).load64(addr);
	}

	public void store8(long addr, int value) {
		getOrCreateNodeFor(addr).store8(addr, value);
	}

	public void store16(long addr, int value) {
		getOrCreateNodeFor(addr).store16(addr, value);
	}

	public void store32(long addr, int value) {
		getOrCreateNodeFor(addr).store32(addr, value);
	}

	public void store64(long addr, long value) {
		getOrCreateNodeFor(addr).store64(addr, value);
	}

	public byte[] byteStringAt(long addr) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte b;

		while ((b = load8(addr++)) != 0) {
			baos.write(b);
		}

		return baos.toByteArray();
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
		return addr & ~0x7FF; // ignore last 11 bits (8 * NODE_SIZE)
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
