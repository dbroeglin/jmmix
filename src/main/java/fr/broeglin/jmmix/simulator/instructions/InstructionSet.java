package fr.broeglin.jmmix.simulator.instructions;

import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;
import fr.broeglin.jmmix.simulator.SpecialRegisterName;
import fr.broeglin.jmmix.simulator.UnknownInstruction;

public final class InstructionSet {

	public static Instruction instruction(int op) {
		if (op < 0 || op > instructions.length) {
			throw new UnknownInstruction(op);
		}

		return instructions[op];
	}

	private static final Instruction[] instructions = new Instruction[] {
			TrapInstruction::TRAP, FloatInstructions::FCMP,
			FloatInstructions::FUN, FloatInstructions::FEQL,
			FloatInstructions::FADD, FloatInstructions::FIX,
			FloatInstructions::FSUB, null,
			FloatInstructions::FLOT, FloatInstructions::FLOTI,
			null, null,
			null, null,
			null, null,

			// 0x1x
			FloatInstructions::FMUL, null,
			null, null,
			FloatInstructions::FDIV, FloatInstructions::FSQRT,
			FloatInstructions::FREM, FloatInstructions::FINT,
			ArithmeticInstructions::MUL, ArithmeticInstructions::MULI,
			null, null,
			ArithmeticInstructions::DIV, null,
			null, null,

			// 0x2x
			ArithmeticInstructions::ADD, ArithmeticInstructions::ADDI,
			ArithmeticInstructions::ADDU, ArithmeticInstructions::ADDUI,
			ArithmeticInstructions::SUB, ArithmeticInstructions::SUBI,
			ArithmeticInstructions::SUBU, ArithmeticInstructions::SUBUI,
			ArithmeticInstructions::_2ADDU, ArithmeticInstructions::_2ADDUI,
			ArithmeticInstructions::_4ADDU, ArithmeticInstructions::_4ADDUI,
			ArithmeticInstructions::_8ADDU, ArithmeticInstructions::_8ADDUI,
			ArithmeticInstructions::_16ADDU, ArithmeticInstructions::_16ADDUI,

			// 0x3x
			InstructionSet::CMP, InstructionSet::CMPI,
			InstructionSet::CMPU, InstructionSet::CMPU,
			InstructionSet::NEG, InstructionSet::NEGI,
			InstructionSet::NEGU, InstructionSet::NEGUI,
			null, null,
			null, null,
			null, null,
			null, null,

			// 0x4x
			BranchInstructions::BN, BranchInstructions::BNB,
			BranchInstructions::BZ, BranchInstructions::BZB,
			BranchInstructions::BP, BranchInstructions::BPB,
			BranchInstructions::BOD, BranchInstructions::BODB,
			BranchInstructions::BNN, BranchInstructions::BNNB,
			BranchInstructions::BNZ, BranchInstructions::BNZB,
			BranchInstructions::BNP, BranchInstructions::BNPB,
			BranchInstructions::BEV, BranchInstructions::BEVB,

			// 0x5x
			BranchInstructions::PBN, BranchInstructions::PBNB,
			BranchInstructions::PBZ, BranchInstructions::PBZB,
			BranchInstructions::PBP, BranchInstructions::PBPB,
			BranchInstructions::PBOD, BranchInstructions::PBODB,
			BranchInstructions::PBNN, BranchInstructions::PBNNB,
			BranchInstructions::PBNZ, BranchInstructions::PBNZB,
			BranchInstructions::PBNP, BranchInstructions::PBNPB,
			BranchInstructions::PBEV, BranchInstructions::PBEVB,

			// 0x6x
			ConditionalInstructions::CSN, ConditionalInstructions::CSNI,
			ConditionalInstructions::CSZ, ConditionalInstructions::CSZI,
			ConditionalInstructions::CSP, ConditionalInstructions::CSPI,
			ConditionalInstructions::CSOD, ConditionalInstructions::CSODI,
			ConditionalInstructions::CSNN, ConditionalInstructions::CSNNI,
			ConditionalInstructions::CSNZ, ConditionalInstructions::CSNZI,
			ConditionalInstructions::CSNP, ConditionalInstructions::CSNPI,
			ConditionalInstructions::CSEV, ConditionalInstructions::CSEVI,

			// 0x7x
			ConditionalInstructions::ZSN, ConditionalInstructions::ZSNI,
			ConditionalInstructions::ZSZ, ConditionalInstructions::ZSZI,
			ConditionalInstructions::ZSP, ConditionalInstructions::ZSPI,
			ConditionalInstructions::ZSOD, ConditionalInstructions::ZSODI,
			ConditionalInstructions::ZSNN, ConditionalInstructions::ZSNNI,
			ConditionalInstructions::ZSNZ, ConditionalInstructions::ZSNZI,
			ConditionalInstructions::ZSNP, ConditionalInstructions::ZSNPI,
			ConditionalInstructions::ZSEV, ConditionalInstructions::ZSEVI,

			// 0x8x
			LoadInstructions::LDB, LoadInstructions::LDBI,
			LoadInstructions::LDBU, LoadInstructions::LDBUI,
			LoadInstructions::LDW, LoadInstructions::LDWI,
			LoadInstructions::LDWU, LoadInstructions::LDWUI,

			LoadInstructions::LDT, LoadInstructions::LDTI,
			LoadInstructions::LDTU, LoadInstructions::LDTUI,
			LoadInstructions::LDO, LoadInstructions::LDOI,
			LoadInstructions::LDOU, LoadInstructions::LDOUI,

			// 0x9x
			null, null,
			null, null,
			null, null,
			null, null,
			null, null,
			InstructionSet::PRELD, InstructionSet::PRELDI,
			InstructionSet::PREGO, InstructionSet::PREGOI,
			null, null,

			// 0xax
			StoreInstructions::STB, StoreInstructions::STBI,
			StoreInstructions::STBU, StoreInstructions::STBUI,
			StoreInstructions::STW, StoreInstructions::STWI,
			StoreInstructions::STWU, StoreInstructions::STWUI,

			StoreInstructions::STT, StoreInstructions::STTI,
			StoreInstructions::STTU, StoreInstructions::STTUI,
			StoreInstructions::STO, StoreInstructions::STOI,
			StoreInstructions::STOU, StoreInstructions::STOUI,

			// 0xbx
			null, null,
			null, null,
			null, null,
			null, null,
			InstructionSet::SYNCD, InstructionSet::SYNCDI,
			InstructionSet::PREST, InstructionSet::PRESTI,
			InstructionSet::SYNCID, InstructionSet::SYNCIDI,
			null, null,

			// 0xcx
			ArithmeticInstructions::OR, ArithmeticInstructions::ORI,
			ArithmeticInstructions::ORN, ArithmeticInstructions::ORNI,
			ArithmeticInstructions::NOR, ArithmeticInstructions::NORI,
			ArithmeticInstructions::XOR, ArithmeticInstructions::XORI,
			ArithmeticInstructions::AND, ArithmeticInstructions::ANDI,
			ArithmeticInstructions::ANDN, ArithmeticInstructions::ANDNI,
			ArithmeticInstructions::NAND, ArithmeticInstructions::NANDI,
			ArithmeticInstructions::NXOR, ArithmeticInstructions::NXORI,

			// 0xdx
			null, null,
			null, null,
			null, null,
			null, null,
			null, null,
			null, null,
			null, null,
			null, null,

			// 0xex
			InstructionSet::SETH, InstructionSet::SETMH,
			InstructionSet::SETML, InstructionSet::SETL,
			InstructionSet::INCH, InstructionSet::INCMH,
			InstructionSet::INCML, InstructionSet::INCL,
			null, null,
			null, null,
			null, null,
			null, null,

			// 0xfx
			InstructionSet::JMP, InstructionSet::JMPB,
			null, null,
			null, null,
			null, null,
			null, null,
			null, null,
			null, InstructionSet::SWYM,
			InstructionSet::GET, null
	};

	public static void GET(Processor proc, Memory mem, int x, int y, int z) {
		// TODO: this does not uphold all GET rules
		proc.setRegister(x,
				proc.specialRegister(SpecialRegisterName.values()[z]));
		proc.cost(0, 1);
	}

	public static void SETL(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, y << 8 | z);
		proc.cost(0, 1);
	}

	public static void SETML(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, (long) y << 24 | (long) z << 16);
		proc.cost(0, 1);
	}

	public static void SETMH(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, (long) y << 40 | (long) z << 32);
		proc.cost(0, 1);
	}

	public static void SETH(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, (long) y << 56 | (long) z << 48);
		proc.cost(0, 1);
	}

	public static void INCL(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(x) + (y << 8 | z));
		proc.cost(0, 1);
	}

	public static void INCML(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(x)
				+ ((long) y << 24 | (long) z << 16));
		proc.cost(0, 1);
	}

	public static void INCMH(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(x)
				+ ((long) y << 40 | (long) z << 32));
		proc.cost(0, 1);
	}

	public static void INCH(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(x)
				+ ((long) y << 56 | (long) z << 48));
		proc.cost(0, 1);
	}

	public static void SYNCD(Processor proc, Memory mem, int x, int y, int z) {
		proc.cost(0, 1);
	}

	public static void SYNCDI(Processor proc, Memory mem, int x, int y, int z) {
		proc.cost(0, 1);
	}

	public static void SYNCID(Processor proc, Memory mem, int x, int y, int z) {
		proc.cost(0, 1);
	}

	public static void SYNCIDI(Processor proc, Memory mem, int x, int y,
			int z) {
		proc.cost(0, 1);
	}

	public static void PREST(Processor proc, Memory mem, int x, int y, int z) {
		proc.cost(0, 1);
	}

	public static void PRESTI(Processor proc, Memory mem, int x, int y, int z) {
		proc.cost(0, 1);
	}

	public static void PREGO(Processor proc, Memory mem, int x, int y, int z) {
		proc.cost(0, 1);
	}

	public static void PREGOI(Processor proc, Memory mem, int x, int y, int z) {
		proc.cost(0, 1);
	}

	public static void PRELD(Processor proc, Memory mem, int x, int y, int z) {
		proc.cost(0, 1);
	}

	public static void PRELDI(Processor proc, Memory mem, int x, int y, int z) {
		proc.cost(0, 1);
	}

	public static void SWYM(Processor proc, Memory mem, int x, int y, int z) {
		proc.cost(0, 1);
	}

	public static void JMP(Processor proc, Memory mem, int x, int y, int z) {
		proc.incInstPtr((x << 16 | y << 8 | z) - 1);
		proc.cost(0, 1);
	}

	public static void JMPB(Processor proc, Memory mem, int x, int y, int z) {
		proc.incInstPtr((0xff000000 | x << 16 | y << 8 | z) - 1);
		proc.cost(0, 1);
	}

	public static void CMP(Processor proc, Memory mem, int x, int y, int z) {
		long a = proc.register(y);
		long b = proc.register(z);

		proc.setRegister(x, (a < b) ? -1 : ((a == b) ? 0 : 1));
		proc.cost(0, 1);
	}

	public static void CMPI(Processor proc, Memory mem, int x, int y, int z) {
		long a = proc.register(y);

		proc.setRegister(x, (a < z) ? -1 : ((a == z) ? 0 : 1));
		proc.cost(0, 1);
	}

	public static void CMPU(Processor proc, Memory mem, int x, int y, int z) {
		long a = proc.register(y) + Long.MIN_VALUE;
		long b = proc.register(z) + Long.MIN_VALUE;

		proc.setRegister(x, (a < b) ? -1 : ((a == b) ? 0 : 1));
		proc.cost(0, 1);
	}

	public static void CMPUI(Processor proc, Memory mem, int x, int y, int z) {
		long a = proc.register(y) + Long.MIN_VALUE;
		long b = z + Long.MIN_VALUE;

		proc.setRegister(x, (a < b) ? -1 : ((a == b) ? 0 : 1));
		proc.cost(0, 1);
	}

	public static void NEG(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, (long) y - proc.register(z));
		proc.cost(0, 1);
	}

	public static void NEGI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, (long) y - (long) z);
		proc.cost(0, 1);
	}

	public static void NEGU(Processor proc, Memory mem, int x, int y, int z) {
		// TODO: double check this one
		NEG(proc, mem, x, y, z);
	}

	public static void NEGUI(Processor proc, Memory mem, int x, int y, int z) {
		// TODO: double check this one
		NEGI(proc, mem, x, y, z);
	}
}
