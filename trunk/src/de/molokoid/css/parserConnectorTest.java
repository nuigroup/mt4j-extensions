package de.molokoid.css;

import static org.junit.Assert.*;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.junit.Test;

public class parserConnectorTest {

	@Test
	public void testParserConnector() {
		Logger logger = Logger.getLogger("MT4J Extensions");
		SimpleLayout l = new SimpleLayout();
		ConsoleAppender ca = new ConsoleAppender(l);
		logger.addAppender(ca);
		
		
		parserConnector pc = new parserConnector("selectortest.css");
		fail("Not Yet Implemented");
	}

}
