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

import de.molokoid.css.parserConnector;
import de.molokoid.data.BorderStyle;
import de.molokoid.data.CSSStyle;
import de.molokoid.data.Selector;
import de.molokoid.data.SelectorType;


public class BorderTest extends TestCase {

	Logger logger = Logger.getLogger("MT4J Extensions");
	SimpleLayout l = new SimpleLayout();
	ConsoleAppender ca = new ConsoleAppender(l);
	
		
	StartTestApp app = new StartTestApp();
	parserConnector pc;
	List<CSSStyle> styles;

	
	@Before
	public void setUp() {
		logger.addAppender(ca);
		
		pc = new parserConnector("bordertest.css", app);
		styles= pc.getCssh().getStyles();

		
	}
	
	
	protected void tearDown() {
		//app.destroy();
	}
	
	@Test 
	public void testWidth() {
		Selector borderwidth = new Selector("borderwidth", SelectorType.ID);
		boolean exists = false;
		for (CSSStyle s: styles) {
			if (s.getSelector().equals(borderwidth)) {
				exists = !exists;
				float comp = (1200f/72f)*4f;
				assertTrue(closeTo(s.getBorderWidth(),comp));
			}
		}
		assertTrue(exists);
	}
	
	@Test
	public void testStyle() {
		Selector borderstyle = new Selector("borderstyle", SelectorType.ID);
		boolean exists = false;
		for (CSSStyle s: styles) {
			if (s.getSelector().equals(borderstyle)) {
				exists = !exists;
				assertTrue(s.getBorderStyle() == BorderStyle.DASHED);
			}
		}
		assertTrue(exists);
	}
	
	@Test
	public void testColor() {
		Selector bordercolor = new Selector("bordercolor", SelectorType.ID);
		boolean exists = false;
		for (CSSStyle s: styles) {
			if (s.getSelector().equals(bordercolor)) {
				exists = !exists;
				assertTrue(s.getBorderColor().equals(new MTColor(255,0,0,255)));
			}
		}
		assertTrue(exists);
	}
	
	private boolean closeTo(float a, float b) {
		float c = a-b;
		if (c < 0) c *= -1f;
		if (c < 0.001) return true;
		return false;
	}
}
