package de.molokoid.test;

import static org.junit.Assert.*;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.junit.Test;
import org.mt4j.MTApplication;

import de.molokoid.css.parserConnector;

public class parserConnectorTest {

	@Test
	public void testParserConnector() {
		Logger logger = Logger.getLogger("MT4J Extensions");
		SimpleLayout l = new SimpleLayout();
		ConsoleAppender ca = new ConsoleAppender(l);
		logger.addAppender(ca);
		MTApplication app = new StartTestApp();
		
		parserConnector pc = new parserConnector("selectortest.css", app);
		fail("Not Yet Implemented");
	}

}
