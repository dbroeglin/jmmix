package fr.broeglin.jmmix.simulator.instructions;

import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rBB;
import static java.lang.Double.doubleToRawLongBits;
import static java.lang.Double.longBitsToDouble;
import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;
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
			InstructionSet::MUL, InstructionSet::MULI,
			null, null,
			null, null,
			null, null,

			// 0x2x
			InstructionSet::ADD, InstructionSet::ADDI,
			InstructionSet::ADDU, InstructionSet::ADDUI,
			InstructionSet::SUB, InstructionSet::SUBI,
			InstructionSet::SUBU, InstructionSet::SUBUI,
			InstructionSet::_2ADDU, InstructionSet::_2ADDUI,
			InstructionSet::_4ADDU, InstructionSet::_4ADDUI,
			InstructionSet::_8ADDU, InstructionSet::_8ADDUI,
			InstructionSet::_16ADDU, InstructionSet::_16ADDUI,

			// 0x3x
			null, null,
			null, null,
			InstructionSet::NEG, InstructionSet::NEGI,
			InstructionSet::NEGU, InstructionSet::NEGUI,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			// 0x4x
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			// 0x5x
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			// 0x6x
			null, null,
			InstructionSet::CSZ, InstructionSet::CSZI,
			null, null,
			null, null,
			null, null,
			null, null,
			null, null,
			null, null,
			// 0x7x
			null, null, null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,

			// 0x8x
			null, null,
			null, null,
			null, null,
			null, null,

			null, null,
			null, null,
			null, InstructionSet::LDOI,
			null, null,

			// 0x9x
			null, null, null, null, null,
			null,
			null,
			null,
			null,
			null,
			InstructionSet::PRELD,
			InstructionSet::PRELDI,
			InstructionSet::PREGO,
			InstructionSet::PREGOI,
			null,
			null,
			// 0xax
			null, null, null, null, null, null, null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			// 0xbx
			null, null, null, null, null, null, null, null,
			InstructionSet::SYNCD,
			InstructionSet::SYNCDI,
			InstructionSet::PREST,
			InstructionSet::PRESTI,
			InstructionSet::SYNCID,
			InstructionSet::SYNCIDI,
			null,
			null,
			// 0xcx
			InstructionSet::OR, InstructionSet::ORI,
			InstructionSet::ORN, InstructionSet::ORNI,
			InstructionSet::NOR, InstructionSet::NORI,
			InstructionSet::XOR, InstructionSet::XORI,
			InstructionSet::AND, InstructionSet::ANDI,
			InstructionSet::ANDN, InstructionSet::ANDNI,
			InstructionSet::NAND, InstructionSet::NANDI,
			InstructionSet::NXOR, InstructionSet::NXORI,
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
			null,
			null, null,
			InstructionSet::SWYM, null
	};

	public static void _2ADDU(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) * 2 + proc.register(z));
	}

	public static void _2ADDUI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) * 2 + z);
	}

	public static void _4ADDU(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) * 4 + proc.register(z));
	}

	public static void _4ADDUI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) * 4 + z);
	}

	public static void _8ADDU(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) * 8 + proc.register(z));
	}

	public static void _8ADDUI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) * 8 + z);
	}

	public static void _16ADDU(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) * 16 + proc.register(z));
	}

	public static void _16ADDUI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) * 16 + z);
	}

	public static void ADD(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) + proc.register(z));
	}

	public static void ADDI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) + z);
	}

	public static void ADDU(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) + proc.register(z));
	}

	public static void ADDUI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) + z);
	}

	public static void SUB(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) - proc.register(z));
	}

	public static void SUBI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) - z);
	}

	public static void SUBU(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) - proc.register(z));
	}

	public static void SUBUI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) - z);
	}

	public static void OR(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) | proc.register(z));
	}

	public static void ORI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) | (byte) z);
	}

	public static void XOR(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) ^ proc.register(z));
	}

	public static void XORI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) ^ (byte) z);
	}

	public static void NXOR(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, ~(proc.register(y) ^ proc.register(z)));
	}

	public static void NXORI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, ~(proc.register(y) ^ (byte) z));
	}

	public static void ORN(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) | ~proc.register(z));
	}

	public static void ORNI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) | ~(byte) z);
	}

	public static void AND(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) & proc.register(z));
	}

	public static void ANDI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) & (byte) z);
	}

	public static void NAND(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, ~(proc.register(y) & proc.register(z)));
	}

	public static void NANDI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, ~(proc.register(y) & (byte) z));
	}

	public static void ANDN(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) & ~proc.register(z));
	}

	public static void ANDNI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) & ~(byte) z);
	}

	public static void NOR(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, ~(proc.register(y) | proc.register(z)));
	}

	public static void NORI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, ~(proc.register(y) | (byte) z));
	}

	public static void SETL(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, y << 8 | z);
		proc.cost(0, 1);
	}

	public static void SETML(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, (long) y << 24 | (long) z << 16);
	}

	public static void SETMH(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, (long) y << 40 | (long) z << 32);
	}

	public static void SETH(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, (long) y << 56 | (long) z << 48);
	}

	public static void INCL(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(x) + (y << 8 | z));
	}

	public static void INCML(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(x)
				+ ((long) y << 24 | (long) z << 16));
	}

	public static void INCMH(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(x)
				+ ((long) y << 40 | (long) z << 32));
	}

	public static void INCH(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(x)
				+ ((long) y << 56 | (long) z << 48));
	}

	public static void SYNCD(Processor proc, Memory mem, int x, int y, int z) {
		// do nothing
	}

	public static void SYNCDI(Processor proc, Memory mem, int x, int y, int z) {
		// do nothing
	}

	public static void SYNCID(Processor proc, Memory mem, int x, int y, int z) {
		// do nothing
	}

	public static void SYNCIDI(Processor proc, Memory mem, int x, int y, int z) {
		// do nothing
	}

	public static void PREST(Processor proc, Memory mem, int x, int y, int z) {
		// do nothing
	}

	public static void PRESTI(Processor proc, Memory mem, int x, int y, int z) {
		// do nothing
	}

	public static void PREGO(Processor proc, Memory mem, int x, int y, int z) {
		// do nothing
	}

	public static void PREGOI(Processor proc, Memory mem, int x, int y, int z) {
		// do nothing
	}

	public static void PRELD(Processor proc, Memory mem, int x, int y, int z) {
		// do nothing
	}

	public static void PRELDI(Processor proc, Memory mem, int x, int y, int z) {
		// do nothing
	}

	public static void SWYM(Processor proc, Memory mem, int x, int y, int z) {
		// do nothing
	}

	public static void JMP(Processor proc, Memory mem, int x, int y, int z) {
		proc.incInstPtr((x << 16 | y << 8 | z) - 1);
	}

	public static void JMPB(Processor proc, Memory mem, int x, int y, int z) {
		proc.incInstPtr((0xff000000 | x << 16 | y << 8 | z) - 1);
	}

	public static void CMP(Processor proc, Memory mem, int x, int y, int z) {
		long a = proc.register(y);
		long b = proc.register(z);

		proc.setRegister(x, (a < b) ? -1 : ((a == b) ? 0 : 1));
	}

	public static void CMPI(Processor proc, Memory mem, int x, int y, int z) {
		long a = proc.register(y);

		proc.setRegister(x, (a < z) ? -1 : ((a == z) ? 0 : 1));
	}

	public static void CMPU(Processor proc, Memory mem, int x, int y, int z) {
		long a = proc.register(y) + Long.MIN_VALUE;
		long b = proc.register(z) + Long.MIN_VALUE;

		proc.setRegister(x, (a < b) ? -1 : ((a == b) ? 0 : 1));
	}

	public static void CMPUI(Processor proc, Memory mem, int x, int y, int z) {
		long a = proc.register(y) + Long.MIN_VALUE;
		long b = z + Long.MIN_VALUE;

		proc.setRegister(x, (a < b) ? -1 : ((a == b) ? 0 : 1));
	}

	public static void NEG(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, (long) y - proc.register(z));
	}

	public static void NEGI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, (long) y - (long) z);
	}

	public static void NEGU(Processor proc, Memory mem, int x, int y, int z) {
		// TODO: double check this one
		NEG(proc, mem, x, y, z);
	}

	public static void NEGUI(Processor proc, Memory mem, int x, int y, int z) {
		// TODO: double check this one
		NEGI(proc, mem, x, y, z);
	}

	public static void CSZ(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(y) == 0) {
			proc.setRegister(x, proc.register(z));
		}
	}

	public static void CSZI(Processor proc, Memory mem, int x, int y, int z) {
		if (proc.register(y) == 0) {
			proc.setRegister(x, (byte) z);
		}
	}

	public static void LDOI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, mem.load64(proc.register(y) + z));
	}
	
	public static void MUL(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) * proc.register(z));
	}
	
	public static void MULI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) * (byte)z);
	}

}
