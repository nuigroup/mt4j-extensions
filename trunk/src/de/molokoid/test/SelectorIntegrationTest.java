package de.molokoid.test;

import java.util.HashMap;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.junit.Before;
import org.junit.Test;
import org.mt4j.util.MTColor;
import org.mt4j.components.MTCanvas;

import de.molokoid.css.parserConnector;
import de.molokoid.data.CSSStyle;
import de.molokoid.data.CSSStyleManager;
import de.molokoid.data.Selector;
import de.molokoid.extensions.MTCSSRectangle;

public class SelectorIntegrationTest extends TestCase {
	Logger logger = Logger.getLogger("MT4J Extensions");
	SimpleLayout l = new SimpleLayout();
	ConsoleAppender ca = new ConsoleAppender(l);
	MTCanvas c = null;
		
	StartTestApp app = new StartTestApp();
	parserConnector pc;
	List<CSSStyle> styles;
	
	CSSStyleManager cssm = null;
	
	@Before
	public void setUp() {
		logger.addAppender(ca);
		
		pc = new parserConnector("integrationtest.css", app);
		styles= pc.getCssh().getStyles();
		cssm = new CSSStyleManager(styles);
		this.c = app.getCurrentScene().getCanvas();

	}
	
	
	protected void tearDown() {
		//app.destroy();
	}
	
	@Test
	public void testDirectStyleSheets() {
		MTCSSRectangle r = new MTCSSRectangle(0,0,100,100, app, cssm);
		
		
		
		
	}
	
}
