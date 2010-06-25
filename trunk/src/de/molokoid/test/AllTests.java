package de.molokoid.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for de.molokoid.test");
		//$JUnit-BEGIN$
		suite.addTestSuite(ColorMeasureTest.class);
		suite.addTestSuite(FontParserTest.class);
		//$JUnit-END$
		return suite;
	}

}
