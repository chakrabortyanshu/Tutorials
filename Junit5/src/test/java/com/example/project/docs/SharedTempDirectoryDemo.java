package com.example.project.docs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.example.project.docs.api.ListWriter;

/**
 * @TempDir is not supported on constructor parameters. If you wish to retain a
 *          single reference to a temp directory across lifecycle methods and
 *          the current test method, please use field injection, by annotating a
 *          non-private instance field with @TempDir.
 * @author ac185300
 *
 */
public class SharedTempDirectoryDemo {

	@TempDir
	static Path sharedTempDir;

	@Test
	void writeItemsToFile() throws IOException {
		Path file = sharedTempDir.resolve("test.txt");

		new ListWriter(file).write("a", "b", "c");

		assertEquals(Collections.singletonList("a,b,c"), Files.readAllLines(file));
	}

	@Test
	void anotherTestThatUsesTheSameTempDir() {
		// use sharedTempDir
	}
}
