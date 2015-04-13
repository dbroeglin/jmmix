package fr.broeglin.jmmix.simulator;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.junit.Before;

import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

public abstract class AbstractMmoTest {

	@Retention(value = RUNTIME)
	@Target(value = ElementType.TYPE)
	public @interface MmixSource {
		String value();
	}

	private static final File MMIX_DIR = new File("src/test/mmix");

	protected File objectFile;

	@Before
	public void assemble() throws Exception {
		MmixSource source = getClass().getAnnotation(MmixSource.class);

		if (source == null || source.value().isEmpty()) {
			throw new IllegalArgumentException(
					"@MmixSource annotation is mandatory");
		}

		System.out.format("Assembling %s...\n", source.value());
		objectFile = assembleSourceFile(source.value());
	}

	private File assembleSourceFile(String sourceFile) throws IOException,
			InterruptedException {
		ProcessBuilder pb = new ProcessBuilder("bash", "-lc", "mmixal "
				+ sourceFile);

		pb.directory(MMIX_DIR);
		pb.redirectErrorStream(true);

		Process process = pb.start();

		process.waitFor(5, SECONDS);

		ByteStreams.copy(process.getInputStream(), System.out);
		assertThat(process.exitValue(), equalTo(0));

		return new File(MMIX_DIR, Files.getNameWithoutExtension(sourceFile)
				+ ".mmo");
	}

}
