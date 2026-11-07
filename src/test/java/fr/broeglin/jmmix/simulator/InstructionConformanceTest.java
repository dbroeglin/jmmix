package fr.broeglin.jmmix.simulator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class InstructionConformanceTest extends AbstractMmoTest {

	private final String code;
	private final boolean checkArithmeticStatus;

	public InstructionConformanceTest(String name, String code, boolean checkArithmeticStatus) {
		this.code = code;
		this.checkArithmeticStatus = checkArithmeticStatus;
	}

	@Parameters(name = "{0}")
	public static Collection<Object[]> cases() {
		List<Object[]> cases = new ArrayList<>();
		for (String op : new String[] { "ORI", "ORNI", "NORI", "XORI",
				"ANDI", "ANDNI", "NANDI", "NXORI", "MULI", "CMPUI" }) {
			for (int immediate : new int[] { 127, 128, 255 }) {
				cases.add(new Object[] { op + "_" + immediate,
						register(2, 0x123456789abcdef0l)
								+ "\t" + op.substring(0, op.length() - 1)
								+ " $1,$2," + immediate + "\n", false });
			}
		}
		for (String op : new String[] { "OR", "ORN", "NOR", "XOR",
				"AND", "ANDN", "NAND", "NXOR", "ADD", "ADDU", "SUB", "SUBU",
				"2ADDU", "4ADDU", "8ADDU", "16ADDU", "CMP", "CMPU" }) {
			for (boolean immediate : new boolean[] { false, true }) {
				cases.add(new Object[] { op + "_control_" + (immediate ? "immediate" : "register"),
						register(2, 0x123456789abcdef0l) + register(3, 17)
								+ "\t" + op + " $1,$2," + (immediate ? "17" : "$3") + "\n", true });
			}
		}
		for (String op : new String[] { "LDB", "LDBU", "LDW", "LDWU",
				"LDT", "LDTU", "LDO", "LDOU", "STB", "STBU", "STW", "STWU",
				"STT", "STTU", "STO", "STOU" }) {
			for (boolean immediate : new boolean[] { false, true }) {
				for (int offset : new int[] { 0, 7, 8 }) {
					cases.add(new Object[] { op + "_boundary_" + offset + "_" + immediate,
							register(2, Memory.DATA_SEGMENT + 2040)
									+ register(3, offset) + register(5, 0x89abcdef01234567l)
									+ "\tSTO $5,$2,0\n\tSTO $5,$2,8\n"
									+ register(1, 0x8123456789abcdefl)
									+ "\t" + op + " $1,$2," + (immediate ? offset : "$3") + "\n"
									+ "\tLDOU $10,$2,0\n\tLDOU $11,$2,8\n", true });
				}
			}
		}
		for (String op : new String[] { "CSN", "CSZ", "CSP", "CSOD",
				"CSNN", "CSNZ", "CSNP", "CSEV", "ZSN", "ZSZ", "ZSP", "ZSOD",
				"ZSNN", "ZSNZ", "ZSNP", "ZSEV" }) {
			for (boolean immediate : new boolean[] { false, true }) {
				cases.add(new Object[] { op + "_control_" + immediate,
						register(1, 0x1234) + register(2, -1) + register(3, 255)
								+ "\t" + op + " $1,$2," + (immediate ? "255" : "$3") + "\n", true });
			}
		}
		for (int width : new int[] { 8, 16, 32 }) {
			String op = width == 8 ? "STB" : width == 16 ? "STW" : "STT";
			long minimum = -(1l << (width - 1));
			long maximum = (1l << (width - 1)) - 1;
			for (long value : new long[] { minimum - 1, minimum, -1, 0, maximum, maximum + 1 }) {
				for (boolean immediate : new boolean[] { false, true }) {
					cases.add(new Object[] { op + "_signed_range_" + value + "_" + immediate,
							register(1, value) + register(2, Memory.DATA_SEGMENT)
									+ register(3, 0) + "\t" + op + " $1,$2,"
									+ (immediate ? "0" : "$3") + "\n\tLDOU $10,$2,0\n", true });
				}
			}
		}
		for (String op : new String[] { "SL", "SLI", "SLU", "SLUI",
				"SR", "SRI", "SRU", "SRUI" }) {
			for (int amount : new int[] { 0, 63, 64, 65, 255 }) {
				cases.add(new Object[] { op + "_" + amount,
						register(2, 0x8000000000000001l) + register(3, amount)
								+ "\t" + (op.endsWith("I") ? op.substring(0, op.length() - 1) : op)
								+ " $1,$2,"
								+ (op.endsWith("I") ? amount : "$3") + "\n", true });
			}
		}
		for (long[] operands : new long[][] { { 7, 3 }, { -7, 3 }, { 7, -3 },
				{ -7, -3 }, { 7, 0 }, { Long.MIN_VALUE, -1 } }) {
			cases.add(new Object[] { "DIV_" + operands[0] + "_" + operands[1],
					register(2, operands[0]) + register(3, operands[1])
							+ "\tDIV $1,$2,$3\n", true });
		}
		for (String op : new String[] { "ADD", "SUB", "MUL" }) {
			cases.add(new Object[] { op + "_overflow",
					register(2, Long.MAX_VALUE) + register(3, op.equals("SUB") ? -1 : 2)
							+ "\t" + op + " $1,$2,$3\n", true });
		}
		for (String op : new String[] { "FCMP", "FEQL", "FUN" }) {
			for (long bits : new long[] { 0x8000000000000000l, 0x7ff8000000000000l }) {
				cases.add(new Object[] { op + "_" + Long.toHexString(bits),
						register(2, bits) + register(3, 0)
								+ "\t" + op + " $1,$2,$3\n", true });
			}
		}
		for (String op : new String[] { "FIX", "FINT", "FSQRT", "FLOT" }) {
			for (int rounding : new int[] { 1, 2, 3 }) {
				cases.add(new Object[] { op + "_default_rounding_" + rounding,
						register(4, (long) rounding << 16) + "\tPUT rA,$4\n"
								+ register(3, op.equals("FLOT") ? 9007199254740993l
										: Double.doubleToRawLongBits(2.75))
								+ "\t" + op + " $1,0,$3\n", true });
			}
		}
		cases.add(new Object[] { "FINT_large_finite_round_off",
				register(3, Double.doubleToRawLongBits(0x1.0p64))
						+ "\tFINT $1,1,$3\n", true });
		cases.add(new Object[] { "FINT_negative_zero_round_off",
				register(3, 0x8000000000000000l) + "\tFINT $1,1,$3\n", true });
		return cases;
	}

	private static String register(int index, long value) {
		return String.format("\tSETH $%d,#%04x\n\tINCMH $%d,#%04x\n"
				+ "\tINCML $%d,#%04x\n\tINCL $%d,#%04x\n",
				index, (value >>> 48) & 0xffff, index, (value >>> 32) & 0xffff,
				index, (value >>> 16) & 0xffff, index, value & 0xffff);
	}

	@Test(timeout = 15000)
	public void should_match_reference_simulator() throws Exception {
		File source = File.createTempFile("jmmix-conformance-", ".mms");
		try {
			Files.write(source.toPath(), ("\tLOC #100\nMain SWYM 0\n" + code
					+ "\tTRAP 0,Halt,0\n").getBytes(StandardCharsets.US_ASCII));
			objectFile = assembleSourceFile(source.getAbsolutePath());
			runParallel();
			if (checkArithmeticStatus) {
				assertThat(simulator.getProcessor().specialRegister(SpecialRegisterName.rA),
						equalTo(mmixRegisters.get(256 + SpecialRegisterName.rA.ordinal()).value));
			}
		} finally {
			Files.deleteIfExists(source.toPath());
			if (objectFile != null) {
				Files.deleteIfExists(objectFile.toPath());
			}
		}
	}
}
