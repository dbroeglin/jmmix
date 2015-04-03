package fr.broeglin.jmmix.simulator;

import java.util.TreeMap;

public class Memory {

	public static final long DATA_SEGMENT = 0x4000_0000_0000_0000l;

	TreeMap<Long, MemoryNode> memoryMap = new TreeMap<>();
	MemoryNode node = new MemoryNode(DATA_SEGMENT);

	public Memory() {
		MemoryNode node = new MemoryNode(DATA_SEGMENT);
		memoryMap.put(node.getLocation(), node);
	}



	public byte load8(long addr) {
		return node.load8(addr);
	}

	public int load16(long addr) {
		return node.load16(addr);
	}

	public void store64(long addr, long value) {
		node.store64(addr, value);
	}

	public int load32(long addr) {
		return node.load32(addr);
	}



	public long load64(long addr) {
		return node.load64(addr);
	}

}
