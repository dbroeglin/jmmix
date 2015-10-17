package fr.broeglin.jmmix.simulator.instructions;

import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rI;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BranchInstructionsTest extends AbstractInstructionsTest {

	// normal branches
	
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

	@Test
	public void BNN_should_branch_if_zero() {
		proc.setRegister(1, 0);

		assertBranch(BranchInstructions::BNN, 0x01, 0x123);
	}

	@Test
	public void BNN_should_branch_if_positive() {
		proc.setRegister(1, 1);

		assertBranch(BranchInstructions::BNN, 0x01, 0x123);
	}

	@Test
	public void BNN_should_not_branch_if_negative() {
		proc.setRegister(1, -1);

		assertNotBranch(BranchInstructions::BNN, 0x01, 0x123);
	}

	@Test
	public void BNNB_should_branch_if_zero() {
		proc.setRegister(1, 0);

		assertBranchBack(BranchInstructions::BNNB, 0x01, 0x123);
	}

	@Test
	public void BNNB_should_branch_if_positive() {
		proc.setRegister(1, 1);

		assertBranchBack(BranchInstructions::BNNB, 0x01, 0x123);
	}

	@Test
	public void BNNB_should_not_branch_if_negative() {
		proc.setRegister(1, -1);

		assertNotBranch(BranchInstructions::BNNB, 0x01, 0x123);
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
	public void BNP_should_branch_if_zero() {
		proc.setRegister(1, 0);

		assertBranch(BranchInstructions::BNP, 0x01, 0x123);
	}

	@Test
	public void BNP_should_branch_if_negative() {
		proc.setRegister(1, -1);

		assertBranch(BranchInstructions::BNP, 0x01, 0x123);
	}

	@Test
	public void BNP_should_not_branch_if_positive() {
		proc.setRegister(1, 1);

		assertNotBranch(BranchInstructions::BNP, 0x01, 0x123);
	}

	@Test
	public void BNPB_should_branch_if_zero() {
		proc.setRegister(1, 0);

		assertBranchBack(BranchInstructions::BNPB, 0x01, 0x123);
	}

	@Test
	public void BNPB_should_branch_if_negative() {
		proc.setRegister(1, -1);

		assertBranchBack(BranchInstructions::BNPB, 0x01, 0x123);
	}

	@Test
	public void BNPB_should_not_branch_if_positive() {
		proc.setRegister(1, 1);

		assertNotBranch(BranchInstructions::BNPB, 0x01, 0x123);
	}	
	
	@Test
	public void BEV_should_branch_if_even() {
		proc.setRegister(1, 0);

		assertBranch(BranchInstructions::BEV, 0x01, 0x123);
	}

	@Test
	public void BEV_should_not_branch_if_odd() {
		proc.setRegister(1, 1);

		assertNotBranch(BranchInstructions::BEV, 0x01, 0x123);
	}

	@Test
	public void BEVB_should_branch_if_even() {
		proc.setRegister(1, 0);

		assertBranchBack(BranchInstructions::BEVB, 0x01, 0x123);
	}

	@Test
	public void BEVB_should_not_branch_if_odd() {
		proc.setRegister(1, 1);

		assertNotBranch(BranchInstructions::BEVB, 0x01, 0x123);
	}
	
	// probable branches
	
	@Test
	public void PBZ_should_branch_if_0() {
		proc.setRegister(1, 0);

		assertProbableBranch(BranchInstructions::PBZ, 0x01, 0x123);
	}

	@Test
	public void PBZ_should_not_branch_if_not_0() {
		proc.setRegister(1, 1);

		assertProbableNotBranch(BranchInstructions::PBZ, 0x01, 0x123);
	}

	@Test
	public void PBZB_should_branch_if_0() {
		proc.setRegister(1, 0);

		assertProbableBranchBack(BranchInstructions::PBZB, 0x01, 0x123);
	}

	@Test
	public void PBZB_should_not_branch_if_not_0() {
		proc.setRegister(1, 1);

		assertProbableNotBranch(BranchInstructions::PBZB, 0x01, 0x123);
	}

	@Test
	public void PBN_should_branch_if_negative() {
		proc.setRegister(1, -1);

		assertProbableBranch(BranchInstructions::PBN, 0x01, 0x123);
	}

	@Test
	public void PBN_should_not_branch_if_0() {
		proc.setRegister(1, 0);

		assertProbableNotBranch(BranchInstructions::PBN, 0x01, 0x123);
	}

	@Test
	public void PBNB_should_branch_if_negative() {
		proc.setRegister(1, -1);

		assertProbableBranchBack(BranchInstructions::PBNB, 0x01, 0x123);
	}

	@Test
	public void PBNB_should_not_branch_if_0() {
		proc.setRegister(1, 0);

		assertProbableNotBranch(BranchInstructions::PBNB, 0x01, 0x123);
	}

	@Test
	public void PBP_should_branch_if_positive() {
		proc.setRegister(1, 1);

		assertProbableBranch(BranchInstructions::PBP, 0x01, 0x123);
	}

	@Test
	public void PBP_should_not_branch_if_0() {
		proc.setRegister(1, 0);

		assertProbableNotBranch(BranchInstructions::PBP, 0x01, 0x123);
	}

	@Test
	public void PBPB_should_branch_if_positive() {
		proc.setRegister(1, 1);

		assertProbableBranchBack(BranchInstructions::PBPB, 0x01, 0x123);
	}

	@Test
	public void PBPB_should_not_branch_if_0() {
		proc.setRegister(1, 0);

		assertProbableNotBranch(BranchInstructions::PBPB, 0x01, 0x123);
	}

	@Test
	public void PBOD_should_branch_if_odd() {
		proc.setRegister(1, 1);

		assertProbableBranch(BranchInstructions::PBOD, 0x01, 0x123);
	}

	@Test
	public void PBOD_should_not_branch_if_even() {
		proc.setRegister(1, 0);

		assertProbableNotBranch(BranchInstructions::PBOD, 0x01, 0x123);
	}

	@Test
	public void PBODB_should_branch_if_odd() {
		proc.setRegister(1, 1);

		assertProbableBranchBack(BranchInstructions::PBODB, 0x01, 0x123);
	}

	@Test
	public void PBODB_should_not_branch_if_even() {
		proc.setRegister(1, 0);

		assertProbableNotBranch(BranchInstructions::PBODB, 0x01, 0x123);
	}

	@Test
	public void PBNN_should_branch_if_zero() {
		proc.setRegister(1, 0);

		assertProbableBranch(BranchInstructions::PBNN, 0x01, 0x123);
	}

	@Test
	public void PBNN_should_branch_if_positive() {
		proc.setRegister(1, 1);

		assertProbableBranch(BranchInstructions::PBNN, 0x01, 0x123);
	}

	@Test
	public void PBNN_should_not_branch_if_negative() {
		proc.setRegister(1, -1);

		assertProbableNotBranch(BranchInstructions::PBNN, 0x01, 0x123);
	}

	@Test
	public void PBNNB_should_branch_if_zero() {
		proc.setRegister(1, 0);

		assertProbableBranchBack(BranchInstructions::PBNNB, 0x01, 0x123);
	}

	@Test
	public void PBNNB_should_branch_if_positive() {
		proc.setRegister(1, 1);

		assertProbableBranchBack(BranchInstructions::PBNNB, 0x01, 0x123);
	}

	@Test
	public void PBNNB_should_not_branch_if_negative() {
		proc.setRegister(1, -1);

		assertProbableNotBranch(BranchInstructions::PBNNB, 0x01, 0x123);
	}

	@Test
	public void PBNZ_should_branch_if_not_0() {
		proc.setRegister(1, 1);

		assertProbableBranch(BranchInstructions::PBNZ, 0x01, 0x123);
	}

	@Test
	public void PBNZ_should_not_branch_if_0() {
		proc.setRegister(1, 0);

		assertProbableNotBranch(BranchInstructions::PBNZ, 0x01, 0x123);
	}

	@Test
	public void PBNZB_should_branch_if_not_0() {
		proc.setRegister(1, 1);

		assertProbableBranchBack(BranchInstructions::PBNZB, 0x01, 0x123);
	}

	@Test
	public void PBNZB_should_not_branch_if_0() {
		proc.setRegister(1, 0);

		assertProbableNotBranch(BranchInstructions::PBNZB, 0x01, 0x123);
	}

	@Test
	public void PBNP_should_branch_if_zero() {
		proc.setRegister(1, 0);

		assertProbableBranch(BranchInstructions::PBNP, 0x01, 0x123);
	}

	@Test
	public void PBNP_should_branch_if_negative() {
		proc.setRegister(1, -1);

		assertProbableBranch(BranchInstructions::PBNP, 0x01, 0x123);
	}

	@Test
	public void PBNP_should_not_branch_if_positive() {
		proc.setRegister(1, 1);

		assertProbableNotBranch(BranchInstructions::PBNP, 0x01, 0x123);
	}

	@Test
	public void PBNPB_should_branch_if_zero() {
		proc.setRegister(1, 0);

		assertProbableBranchBack(BranchInstructions::PBNPB, 0x01, 0x123);
	}

	@Test
	public void PBNPB_should_branch_if_negative() {
		proc.setRegister(1, -1);

		assertProbableBranchBack(BranchInstructions::PBNPB, 0x01, 0x123);
	}

	@Test
	public void PBNPB_should_not_branch_if_positive() {
		proc.setRegister(1, 1);

		assertProbableNotBranch(BranchInstructions::PBNPB, 0x01, 0x123);
	}	
	
	@Test
	public void PBEV_should_branch_if_even() {
		proc.setRegister(1, 0);

		assertProbableBranch(BranchInstructions::PBEV, 0x01, 0x123);
	}

	@Test
	public void PEVD_should_not_branch_if_odd() {
		proc.setRegister(1, 1);

		assertProbableNotBranch(BranchInstructions::PBEV, 0x01, 0x123);
	}

	@Test
	public void PBEVB_should_branch_if_even() {
		proc.setRegister(1, 0);

		assertProbableBranchBack(BranchInstructions::PBEVB, 0x01, 0x123);
	}

	@Test
	public void PBEVB_should_not_branch_if_odd() {
		proc.setRegister(1, 1);

		assertProbableNotBranch(BranchInstructions::PBEVB, 0x01, 0x123);
	}	
	
	// plumbing

	private void assertBranch(Instruction inst, int x, int yz) {
		proc.incInstPtr(0x1111);
		proc.decodeInstruction(0xffffff & (x << 16 | yz));

		inst.op(proc, mem, proc.x(), proc.y(), proc.z());
		proc.incInstPtr(1); // like in the simulator

		assertThat(proc.instPtr(), equalTo(0x4444l + yz * 4));
		assertThat(proc.specialRegister(rI), equalTo(0xffff_ffff_ffff_fffdl));
	}

	private void assertBranchBack(Instruction inst, int x, int yz) {
		proc.incInstPtr(0x1111);
		proc.decodeInstruction(0xffffff & (x << 16 | yz));

		inst.op(proc, mem, proc.x(), proc.y(), proc.z());
		proc.incInstPtr(1); // like in the simulator

		assertThat(proc.instPtr(),
				equalTo(0x4444l + ((long) yz - 0x10000l) * 4));
		assertThat(proc.specialRegister(rI), equalTo(0xffff_ffff_ffff_fffdl));
	}

	private void assertNotBranch(Instruction inst, int op, int yz) {
		proc.incInstPtr(0x1111);
		proc.decodeInstruction(op << 16 | yz);

		inst.op(proc, mem, 1, 0x1, 0x23);

		assertThat(proc.instPtr(), equalTo(0x4444l));
		assertThat(proc.specialRegister(rI), equalTo(0xffff_ffff_ffff_ffffl));
	}
	
	private void assertProbableBranch(Instruction inst, int x, int yz) {
		proc.incInstPtr(0x1111);
		proc.decodeInstruction(0xffffff & (x << 16 | yz));

		inst.op(proc, mem, proc.x(), proc.y(), proc.z());
		proc.incInstPtr(1); // like in the simulator

		assertThat(proc.instPtr(), equalTo(0x4444l + yz * 4));
		assertThat(proc.specialRegister(rI), equalTo(0xffff_ffff_ffff_ffffl));
	}

	private void assertProbableBranchBack(Instruction inst, int x, int yz) {
		proc.incInstPtr(0x1111);
		proc.decodeInstruction(0xffffff & (x << 16 | yz));

		inst.op(proc, mem, proc.x(), proc.y(), proc.z());
		proc.incInstPtr(1); // like in the simulator

		assertThat(proc.instPtr(),
				equalTo(0x4444l + ((long) yz - 0x10000l) * 4));
		assertThat(proc.specialRegister(rI), equalTo(0xffff_ffff_ffff_ffffl));
	}

	private void assertProbableNotBranch(Instruction inst, int op, int yz) {
		proc.incInstPtr(0x1111);
		proc.decodeInstruction(op << 16 | yz);

		inst.op(proc, mem, 1, 0x1, 0x23);

		assertThat(proc.instPtr(), equalTo(0x4444l));
		assertThat(proc.specialRegister(rI), equalTo(0xffff_ffff_ffff_fffdl));
	}
}
