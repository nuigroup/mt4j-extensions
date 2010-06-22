package de.molokoid.test;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.junit.Before;
import org.junit.Test;
import org.mt4j.MTApplication;
import org.mt4j.util.MTColor;

import de.molokoid.css.parserConnector;
import de.molokoid.data.CSSStyle;
import de.molokoid.data.Selector;
import de.molokoid.data.SelectorType;

public class parserConnectorTest {
	Logger logger = Logger.getLogger("MT4J Extensions");
	SimpleLayout l = new SimpleLayout();
	ConsoleAppender ca = new ConsoleAppender(l);
	
	StartTestApp app = new StartTestApp();
	parserConnector pc;
	List<CSSStyle> styles;
	
	@Before
	public void setUp() {
		logger.addAppender(ca);
		pc = new parserConnector("selectortest.css", app);
		styles= pc.getCssh().getStyles();
	}
	
	
	protected void tearDown() {
		//app.destroy();
	}
	
	@Test
	public void testParserConnector() throws InterruptedException {
		assert(pc != null);
	}
	
	@Test
	public void testColors() {
		Selector reference = new Selector("TEST2", SelectorType.ID);
		for (CSSStyle s: styles) {
			if (s.equals(reference)) {
				assert(s.getColor().equals(new MTColor(224,244,244,255)));
			}
		}
		
	}

}
