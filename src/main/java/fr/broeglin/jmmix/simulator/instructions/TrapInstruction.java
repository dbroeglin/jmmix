package fr.broeglin.jmmix.simulator.instructions;

import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rBB;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rJ;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rWW;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rXX;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rYY;
import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rZZ;

import java.io.IOException;

import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;
import fr.broeglin.jmmix.simulator.SpecialRegisterName;
import fr.broeglin.jmmix.simulator.UnknownInstruction;

public class TrapInstruction {

	private static final int SIGN_BIT = 0x8000_0000;

	public class Traps {
		final static int Halt = 0;
		final static int Fopen = 1;
		final static int Fclose = 2;
		final static int Fread = 3;
		final static int Fgets = 4;
		final static int Fgetws = 5;
		final static int Fwrite = 6;
		final static int Fputs = 7;
		final static int Fputws = 8;
		final static int Fseek = 9;
		final static int Ftell = 10;
	}

	public static void TRAP(Processor proc, Memory mem, int x, int y, int z) {
		byte[] bytes;

		try {
			switch (y) {
			case Traps.Halt:
				// TODO: proc.setSpecialRegister(rWW, inst_ptr) ;
				proc.setRunning(false);
				break;
			case Traps.Fputs:
				switch (z) {
				case 0: // ignore StdIn
					break;
				case 1:
					bytes = mem.byteStringAt(proc.register(255));
					System.out.write(bytes);
					proc.setRegister(255, bytes.length);
					break;
				case 2:
					bytes = mem.byteStringAt(proc.register(255));
					System.err.write(bytes);
					proc.setRegister(255, bytes.length);
					break;
				default:
					throw new RuntimeException(
							"Cannot yet write to file handle");
				}
				break;
			default:
				throw new UnknownInstruction(0, x, y, z);
			}
		} catch (IOException e) {
			// TODO: handle some errors in the simulator
			// return -1 in $255 ?
			throw new RuntimeException("Error while handling trap", e);
		}
		proc.setSpecialRegister(rBB, proc.register(255));
		proc.setSpecialRegister(rWW, proc.instPtr());
		proc.setSpecialRegisterHigh(rXX, SIGN_BIT);
		proc.setSpecialRegisterLow(rXX, proc.instruction());
		proc.setSpecialRegister(rYY, y); // ???
		proc.setSpecialRegister(rZZ, z); // ???
		proc.cost(0, 5);
	}
}
