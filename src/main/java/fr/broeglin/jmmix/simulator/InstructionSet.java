package fr.broeglin.jmmix.simulator;

import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rBB;

public final class InstructionSet {

	public static Instruction instruction(int op) {
		if (op < 0 || op > instructions.length) {
			throw new UnknownInstruction(op);
		}
		return instructions[op];
	}

	private static final Instruction[] instructions = new Instruction[] {
			InstructionSet::TRAP, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			InstructionSet::ADD,
			null,
			InstructionSet::ADDU,
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
			// 0x3x
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
			null, null, null, null,
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
			null,
			null,
			null,
			// 0xcx
			InstructionSet::OR, InstructionSet::ORI, null, null, null, null, null, null,
			InstructionSet::AND, InstructionSet::ANDI, null,
			null,
			null,
			null,
			null,
			null,
			// 0xdx
			null, null, null, null, null, null, null, null, null, null, null,
			null, null,
			null,
			null,
			null,
			// 0xex
			InstructionSet::SETH, InstructionSet::SETMH, InstructionSet::SETML,
			InstructionSet::SETL, InstructionSet::INCH, InstructionSet::INCMH,
			InstructionSet::INCML, InstructionSet::INCL, null, null, null,
			null, null, null, null, null,
			// 0xfx
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, InstructionSet::SWYM, null };

	public static void ADD(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) + proc.register(z));
	}

	public static void ADDU(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) + proc.register(z));
	}

	public static void OR(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) | proc.register(z));
	}

	public static void ORI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) | (byte)z);
	}

	public static void AND(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) & proc.register(z));
	}

	public static void ANDI(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(y) & (byte)z);
	}

	public static void SETL(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(x) & 0xffffffffffff0000l
				| (y << 8 | z));
	}

	public static void SETML(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(x) & 0xffffffff0000ffffl
				| ((long) y << 24 | (long) z << 16));
	}

	public static void SETMH(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(x) & 0xffff0000ffffffffl
				| ((long) y << 40 | (long) z << 32));
	}

	public static void SETH(Processor proc, Memory mem, int x, int y, int z) {
		proc.setRegister(x, proc.register(x) & 0x0000ffffffffffffl
				| ((long) y << 56 | (long) z << 48));
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

	public static void TRAP(Processor proc, Memory mem, int x, int y, int z) {
		switch (x) {
		case 0:
			proc.setSpecialRegister(rBB, 255);
			// TODO: proc.setSpecialRegister(rWW, inst_ptr) ;
			proc.setRunning(false);
			break;
		}
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

}
