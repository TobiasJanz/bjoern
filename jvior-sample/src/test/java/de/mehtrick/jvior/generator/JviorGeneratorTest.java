package de.mehtrick.jvior.generator;

import org.junit.Test;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */

public class JviorGeneratorTest {
	@Test
	public void testSomeLibraryMethod() {
		JviorGenerator.gen(new String[] { "path=src/test/resources/jvior.yaml","folder=src/test/resources/","package=de.mehtrick.jvior","gendir=src/gen/java","extendedTestclass=de.mehtrick.jvior.AbstractTestclass" });
	}

}
