package fr.broeglin.jmmix.simulator.instructions;

import static fr.broeglin.jmmix.simulator.SpecialRegisterName.rBB;

import java.io.IOException;

import fr.broeglin.jmmix.simulator.Memory;
import fr.broeglin.jmmix.simulator.Processor;
import fr.broeglin.jmmix.simulator.UnknownInstruction;

public class TrapInstruction {

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
		try {
			switch (y) {
			case Traps.Halt:
				proc.setSpecialRegister(rBB, proc.register(255));
				// TODO: proc.setSpecialRegister(rWW, inst_ptr) ;
				proc.setRunning(false);
				break;
			case Traps.Fputs:
				switch (z) {
				case 0: // ignore StdIn
					break;
				case 1:
					System.out.write(mem.byteStringAt(proc.register(255)));
					break;
				case 2:
					System.err.write(mem.byteStringAt(proc.register(255)));
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
			throw new RuntimeException("Error while handling trap", e);
		}
	}
}
