package fr.broeglin.jmmix.simulator;

import static java.lang.String.format;

import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.util.logging.Logger;

public class Loader {

	private static final Logger logger = Logger.getLogger(Loader.class
			.getName());

	public static final int MM = 0x98000000;

	public static final int LOP_QUOTE = 0x00000; // the quotation lopcode
	public static final int LOP_LOC = 0x10000; // the location lopcode
	public static final int LOP_SKIP = 0x20000; // the skip lopcode
	public static final int LOP_FIXO = 0x30000; // the octabyte-fix lopcode
	public static final int LOP_FIXR = 0x40000; // the relative-fix lopcode
	public static final int LOP_FIXRX = 0x50000; // extended relative-fix
													// lopcode
	public static final int LOP_FILE = 0x60000; // the file name lopcode
	public static final int LOP_LINE = 0x70000; // the file position lopcode
	public static final int LOP_SPEC = 0x80000; // the special hook lopcode
	public static final int LOP_PRE = 0x90000; // the preamble lopcode
	public static final int LOP_POST = 0xa0000; // the postamble lopcode
	public static final int LOP_STAB = 0xb0000; // the symbol table lopcode
	public static final int LOP_end = 0xc0000; // the end-it-all lopcode

	private long currentPosition = 0x0;
	private Simulator simulator;
	private DataInput dataInput;

	public Loader(DataInput dataInput, Simulator simulator) {
		this.dataInput = dataInput;
		this.simulator = simulator;
	}

	public void load() throws IOException {
		int tetra = readTetra();

		if (!isLop(tetra) && lopCode(tetra) != LOP_PRE) {
			throw new LoaderException("Expected MMO preamble");
		}
		if (y(tetra) != 1) {
			throw new LoaderException("Unsupported MMO version: " + y(tetra));
		}
		int z = z(tetra);
		// TODO: here we ignore the file date
		while (z-- > 0) {
			readTetra();
		}

		try {
			while (true) {
				load(readTetra());
			}
		} catch (EOFException e) {
			// do nothing
		}
	}

	public void load(int tetra) throws IOException {
		if (isLop(tetra)) {
			switch (lopCode(tetra)) {
			case LOP_SKIP:
				incrementCurrentPosition(yz(tetra));
				break;
			case LOP_FILE:
				loadFile(tetra);
				break;
			case LOP_LINE:
				// TODO: ignore line number
				break;
			case LOP_POST:
				readPostamble(tetra);
				break;
			case LOP_STAB:
				// ignore until END
				while (true) {
					readTetra();
				}
			default:
				throw new IllegalArgumentException(String.format(
						"Unknown LOP_CODE %x", lopCode(tetra) >>> 16));
			}
		} else {
			logger.finest(() -> format("Storing in #%016x value #%08x",
					currentPosition, tetra));

			simulator.getMemory().store32(currentPosition, tetra);
			logger.finest(() -> format("Value at   #%016x is    #%08x",
					currentPosition,
					simulator.getMemory().load64(currentPosition)));
			incrementCurrentPosition(4);
		}
	}

	private void readPostamble(int tetra) throws IOException {
		int g = z(tetra);
		simulator.getProcessor().rG = g;
		while (g < 256) {
			simulator.getProcessor().setRegister(g,
					(long) readTetra() << 32 | (long) readTetra());
			g++;
		}
	}

	private void loadFile(int tetra) throws IOException {
		int z = z(tetra);
		// TODO: here we just ignore the filename
		while (z-- > 0) {
			readTetra();
		}
	}

	boolean isLop(int tetra) {
		return (tetra & 0xff000000) == MM;
	}

	private int readTetra() throws IOException {
		int tetra = dataInput.readInt();

		logger.finest(() -> format("Loading #%08x", tetra));

		return tetra;
	}

	public long getCurrentPosition() {
		return this.currentPosition;
	}

	void incrementCurrentPosition(int increment) {
		currentPosition += increment;
		logger.finest(() -> format("Current position: #%016x", currentPosition));
	}

	int lopCode(int tetra) {
		return tetra & 0xff0000;
	}

	int y(int tetra) {
		return (tetra >>> 8) & 0xff;
	}

	int z(int tetra) {
		return tetra & 0xff;
	}

	int yz(int tetra) {
		return tetra & 0xffff;
	}

}
