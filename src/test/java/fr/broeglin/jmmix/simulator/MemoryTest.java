package fr.broeglin.jmmix.simulator;

import static fr.broeglin.jmmix.simulator.Memory.DATA_SEGMENT;
import static fr.broeglin.jmmix.simulator.Memory.INSTRUCTION_SEGMENT;
import static fr.broeglin.jmmix.simulator.Memory.STACK_SEGMENT;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MemoryTest {

	Memory memory = new Memory();

	@Test
	public void node_location_multiple_of_8_times_256() {
		long BLOCK_SIZE = 8 * 256;
		
		assertThat(memory.nodeLocationFor(0), equalTo(0l));
		assertThat(memory.nodeLocationFor(1), equalTo(0l));
		assertThat(memory.nodeLocationFor(BLOCK_SIZE - 1), equalTo(0l));
		assertThat(memory.nodeLocationFor(BLOCK_SIZE), equalTo(BLOCK_SIZE));
		assertThat(memory.nodeLocationFor(DATA_SEGMENT), equalTo(DATA_SEGMENT));
		assertThat(memory.nodeLocationFor(DATA_SEGMENT + 1),
				equalTo(DATA_SEGMENT));
		assertThat(memory.nodeLocationFor(DATA_SEGMENT + BLOCK_SIZE),
				equalTo(DATA_SEGMENT + BLOCK_SIZE));
		assertThat(memory.nodeLocationFor(STACK_SEGMENT + 1),
				equalTo(STACK_SEGMENT));
	}

	@Test
	public void no_node_overlap() {
		memory.store64(DATA_SEGMENT, 1234);

		assertThat(memory.load64(DATA_SEGMENT), equalTo(1234l));
		assertThat(memory.load64(INSTRUCTION_SEGMENT), equalTo(0l));
	}

	@Test
	public void store_load_somewhere_unspecific() {
		memory.store64(DATA_SEGMENT, 1234);

		assertThat(memory.load64(DATA_SEGMENT), equalTo(1234l));
		assertThat(memory.load64(INSTRUCTION_SEGMENT), equalTo(0l));
	}

	@Test
	public void load_stack_segment_should_not_create_memory_node() {
		assertThat(memory.getAllocatedSize(),
				equalTo(MemoryNode.NODE_SIZE * 2l));
		assertThat(memory.load64(STACK_SEGMENT), equalTo(0l));

		assertThat(memory.getAllocatedSize(),
				equalTo(MemoryNode.NODE_SIZE * 2l));
	}

	@Test
	public void store_stack_segment_should_not_create_memory_node() {
		assertThat(memory.getAllocatedSize(),
				equalTo(MemoryNode.NODE_SIZE * 2l));
		memory.store8(STACK_SEGMENT, 0);

		assertThat(memory.getAllocatedSize(),
				equalTo(MemoryNode.NODE_SIZE * 3l));
	}

}
