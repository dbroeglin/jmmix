package fr.broeglin.jmmix.simulator.instructions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BranchInstructionsTest extends AbstractInstructionsTest {

	@Test
	public void BZ_should_branch_if_0() {
		proc.setRegister(1, 0);

		assertBranch(BranchInstructions::BZ, 0x01, 0x123);
	}

	@Test
	public void BZ_should_not_branch_if_not_0() {
		proc.setRegister(1, 1);

		assertNotBranch(BranchInstructions::BZ, 0x01, 0x123);
	}

	@Test
	public void BZB_should_branch_if_0() {
		proc.setRegister(1, 0);

		assertBranchBack(BranchInstructions::BZB, 0x01, 0x123);
	}

	@Test
	public void BZB_should_not_branch_if_not_0() {
		proc.setRegister(1, 1);

		assertNotBranch(BranchInstructions::BZB, 0x01, 0x123);
	}

	@Test
	public void BNZ_should_branch_if_not_0() {
		proc.setRegister(1, 1);

		assertBranch(BranchInstructions::BNZ, 0x01, 0x123);
	}

	@Test
	public void BNZ_should_not_branch_if_0() {
		proc.setRegister(1, 0);

		assertNotBranch(BranchInstructions::BNZ, 0x01, 0x123);
	}

	@Test
	public void BNZB_should_branch_if_not_0() {
		proc.setRegister(1, 1);

		assertBranchBack(BranchInstructions::BNZB, 0x01, 0x123);
	}

	@Test
	public void BNZB_should_not_branch_if_0() {
		proc.setRegister(1, 0);

		assertNotBranch(BranchInstructions::BNZB, 0x01, 0x123);
	}

	@Test
	public void BN_should_branch_if_negative() {
		proc.setRegister(1, -1);

		assertBranch(BranchInstructions::BN, 0x01, 0x123);
	}

	@Test
	public void BN_should_not_branch_if_0() {
		proc.setRegister(1, 0);

		assertNotBranch(BranchInstructions::BN, 0x01, 0x123);
	}

	@Test
	public void BNB_should_branch_if_negative() {
		proc.setRegister(1, -1);

		assertBranchBack(BranchInstructions::BNB, 0x01, 0x123);
	}

	@Test
	public void BNB_should_not_branch_if_0() {
		proc.setRegister(1, 0);

		assertNotBranch(BranchInstructions::BNB, 0x01, 0x123);
	}

	
	@Test
	public void BP_should_branch_if_positive() {
		proc.setRegister(1, 1);

		assertBranch(BranchInstructions::BP, 0x01, 0x123);
	}

	@Test
	public void BP_should_not_branch_if_0() {
		proc.setRegister(1, 0);

		assertNotBranch(BranchInstructions::BP, 0x01, 0x123);
	}

	@Test
	public void BPB_should_branch_if_positive() {
		proc.setRegister(1, 1);

		assertBranchBack(BranchInstructions::BPB, 0x01, 0x123);
	}

	@Test
	public void BPB_should_not_branch_if_0() {
		proc.setRegister(1, 0);

		assertNotBranch(BranchInstructions::BPB, 0x01, 0x123);
	}
	
	@Test
	public void BOD_should_branch_if_odd() {
		proc.setRegister(1, 1);
		
		assertBranch(BranchInstructions::BOD, 0x01, 0x123);
	}

	@Test
	public void BOD_should_not_branch_if_even() {
		proc.setRegister(1, 0);
		
		assertNotBranch(BranchInstructions::BOD, 0x01, 0x123);
	}
	
	@Test
	public void BODB_should_branch_if_odd() {
		proc.setRegister(1, 1);
		
		assertBranchBack(BranchInstructions::BODB, 0x01, 0x123);
	}

	@Test
	public void BODB_should_not_branch_if_even() {
		proc.setRegister(1, 0);
		
		assertNotBranch(BranchInstructions::BODB, 0x01, 0x123);
	}	
	
	private void assertBranch(Instruction inst, int x, int yz) {
		proc.incInstPtr(0x1111);
		proc.decodeInstruction(0xffffff & (x << 16 | yz));

		inst.op(proc, mem, proc.x(), proc.y(), proc.z());
		proc.incInstPtr(1); // like in the simulator

		assertThat(proc.instPtr(), equalTo(0x4444l + yz * 4));
	}

	private void assertBranchBack(Instruction inst, int x, int yz) {
		proc.incInstPtr(0x1111);
		proc.decodeInstruction(0xffffff & (x << 16 | yz));

		inst.op(proc, mem, proc.x(), proc.y(), proc.z());
		proc.incInstPtr(1); // like in the simulator

		assertThat(proc.instPtr(),
				equalTo(0x4444l + ((long) yz - 0x10000l) * 4));
	}

	private void assertNotBranch(Instruction inst, int op, int yz) {
		proc.incInstPtr(0x1111);
		proc.decodeInstruction(op << 16 | yz);

		inst.op(proc, mem, 1, 0x1, 0x23);

		assertThat(proc.instPtr(), equalTo(0x4444l));
	}
}
