package fr.broeglin.jmmix.simulator;

import java.io.File;
import java.nio.charset.Charset;

import org.junit.After;
import org.junit.Test;

import com.google.common.io.Files;

public class ParallelRunTest extends AbstractMmoTest {
	private static final String BEGIN = "	LOC 	#100";
	private static final String TRAP = "	TRAP 0,Halt,0";

	@Test
	public void do_nothing() throws Exception {
		assemble("Main " + TRAP);
	}

	@Test
	public void dummy() throws Exception {
		assemble("Main	SYNCD	128,$1,$2\n" + TRAP);
	}

	// plumbing

	File sourceFile;

	@After
	public void cleanup() throws Exception {
		runParallel();
		// sourceFile.delete();
	}

	private void assemble(String code) throws Exception {
		sourceFile = File.createTempFile("test", ".mms");

		code = BEGIN + "\n" + code;
		System.out.println("Code:\n" + code + "\n\n");
		Files.write(code, sourceFile, Charset.forName("ASCII"));
		objectFile = assembleSourceFile(sourceFile.getAbsolutePath());
	}

}
