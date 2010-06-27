package de.molokoid.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
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

public class SelectorTest extends TestCase {
	Logger logger = Logger.getLogger("MT4J Extensions");
	SimpleLayout l = new SimpleLayout();
	ConsoleAppender ca = new ConsoleAppender(l);
	
		
	StartTestApp app = new StartTestApp();
	parserConnector pc;
	List<CSSStyle> styles;
	HashMap<Selector, MTColor> hm = new HashMap<Selector, MTColor>();
	
	@Before
	public void setUp() {
		logger.addAppender(ca);
		
		pc = new parserConnector("selectortest.css", app);
		styles= pc.getCssh().getStyles();

		
		for (CSSStyle s: styles) {
			hm.put(s.getSelector(), s.getBackgroundColor());
		}
	}
	
	
	protected void tearDown() {
		//app.destroy();
	}
	
	@Test
	public void testSimpleSelectors() {
		Selector h1 = new Selector("H1", SelectorType.TYPE);
		Selector id = new Selector("ID", SelectorType.ID);
		Selector c0 = new Selector("c0", SelectorType.CLASS);
		
		MTColor green = new MTColor(0,128,0,255);
		MTColor red = new MTColor(255,0,0,255);
		MTColor blue = new MTColor(0,0,255,255);
		int exists = 0;
		for (CSSStyle s: styles) {
			if (s.getSelector().equals(h1)) {
				assertTrue(s.getBackgroundColor().equals(red));
				exists++;
			}
			if (s.getSelector().equals(id)) {
				assertTrue(s.getBackgroundColor().equals(blue));
				exists++;
			}
			if (s.getSelector().equals(c0)) {
				exists++;
				assertTrue(s.getBackgroundColor().equals(green));
			}
		}
		assertTrue(exists == 3);
	}
	
	@Test
	public void testMultipleSelectors() {
		Selector h2 = new Selector("H2", SelectorType.TYPE);
		Selector id3 = new Selector("ID3", SelectorType.ID);
		Selector c4 = new Selector("c4", SelectorType.CLASS);
		
		MTColor black = new MTColor(0,0,0,255);
		MTColor purple = new MTColor(128,0,128,255);
		MTColor silver = new MTColor(192,192,192,255);
		int exists = 0;
		
		for (CSSStyle s: styles) {
			if (s.getSelector().equals(h2)) {
				assertTrue(s.getBackgroundColor().equals(black));
				exists++;
			}
			if (s.getSelector().equals(id3)) {
				assertTrue(s.getBackgroundColor().equals(purple));
				exists++;
			}
			if (s.getSelector().equals(c4)) {
				exists++;
				assertTrue(s.getBackgroundColor().equals(silver));
			}
		}
		assertTrue(exists == 3);
		
	}
	
	@Test
	public void testUniversalSelector() {
		Selector star = new Selector("*", SelectorType.UNIVERSAL);
		Selector h5 = new Selector("H5", SelectorType.TYPE);
		Selector id5 = new Selector("ID5", SelectorType.ID);
		Selector c5 = new Selector("c5", SelectorType.CLASS);
		
		MTColor olive = new MTColor(128,128,0,255);
		MTColor white = new MTColor(255,255,255,255);
		MTColor gray = new MTColor(128,128,128,255);
		MTColor maroon = new MTColor(128,0,0,255);
		
		
		int exists = 0;
		
		for (CSSStyle s: styles) {
			if (s.getSelector().equals(star)) {
				assertTrue(s.getBackgroundColor().equals(olive));
				exists++;
				
			}
			if (s.getSelector().equals(h5)) {
				assertTrue(s.getBackgroundColor().equals(white));
				exists++;
			}
			if (s.getSelector().equals(id5)) {
				exists++;
				assertTrue(s.getBackgroundColor().equals(gray));
			}
			if (s.getSelector().equals(c5)) {
				exists++;
				assertTrue(s.getBackgroundColor().equals(maroon));
			}
		}
		
		assertTrue(exists == 4);
		
		
		
		
	}
	
	@Test
	public void testCascadingSelectors () {
		Selector h6h6 = new Selector("H6", SelectorType.TYPE);
		h6h6.setSecondary("H6");
		h6h6.setSecondaryType(SelectorType.TYPE);
		Selector h6c6  = new Selector("H6", SelectorType.TYPE);
		h6c6.setSecondary("c6");
		h6c6.setSecondaryType(SelectorType.CLASS);
		Selector h6id6 = new Selector("H6", SelectorType.TYPE);
		h6id6.setSecondary("ID6");
		h6id6.setSecondaryType(SelectorType.ID);
		
		Selector c6h6 = new Selector("c6", SelectorType.CLASS);
		c6h6.setSecondary("H6");
		c6h6.setSecondaryType(SelectorType.TYPE);
		Selector c6c6  = new Selector("c6", SelectorType.CLASS);
		c6c6.setSecondary("c6");
		c6c6.setSecondaryType(SelectorType.CLASS);
		Selector c6id6 = new Selector("c6", SelectorType.CLASS);
		c6id6.setSecondary("ID6");
		c6id6.setSecondaryType(SelectorType.ID);
		
		Selector id6h6 = new Selector("ID6", SelectorType.ID);
		id6h6.setSecondary("H6");
		id6h6.setSecondaryType(SelectorType.TYPE);
		Selector id6c6  = new Selector("ID6", SelectorType.ID);
		id6c6.setSecondary("c6");
		id6c6.setSecondaryType(SelectorType.CLASS);
		Selector id6id6 = new Selector("ID6", SelectorType.ID);
		id6id6.setSecondary("ID6");
		id6id6.setSecondaryType(SelectorType.ID);
		
		Selector h6id7  = new Selector("H6", SelectorType.TYPE);
		h6id7.setSecondary("ID7");
		h6id7.setSecondaryType(SelectorType.ID);
		Selector listrong = new Selector("LI", SelectorType.TYPE);
		listrong.setSecondary("STRONG");
		listrong.setSecondaryType(SelectorType.TYPE);
		
		
		MTColor purple = new MTColor(128,0,128,255);
		MTColor green = new MTColor(0,128,0,255);
		MTColor red = new MTColor(255,0,0,255);
		MTColor blue = new MTColor(0,0,255,255);
		
		assertTrue(hm.get(h6h6).equals(red));
		assertTrue(hm.get(h6c6).equals(blue));
		assertTrue(hm.get(h6id6).equals(green));
		assertTrue(hm.get(c6h6).equals(red));
		assertTrue(hm.get(c6c6).equals(blue));
		assertTrue(hm.get(c6id6).equals(green));
		assertTrue(hm.get(id6h6).equals(red));
		assertTrue(hm.get(id6c6).equals(blue));
		assertTrue(hm.get(id6id6).equals(green));
		assertTrue(hm.get(h6id7).equals(purple));
		assertTrue(hm.get(listrong).equals(purple));
		
	}
	@Test
	public void testChildSelectors() {
		MTColor green = new MTColor(0,128,0,255);
		MTColor red = new MTColor(255,0,0,255);
		MTColor blue = new MTColor(0,0,255,255);
		
		Selector h7h8 = new Selector("H7", SelectorType.TYPE);
		h7h8.setChild(new Selector("H8", SelectorType.TYPE));
		
		Selector h7id7c7 = new Selector("H7", SelectorType.TYPE);
		Selector child = new Selector("ID7", SelectorType.ID);
		child.setSecondary("c7", SelectorType.CLASS);
		h7id7c7.setChild(child);
		
		Selector h7h8id7 = new Selector("H7", SelectorType.TYPE);
		h7h8id7.setSecondary("H8", SelectorType.TYPE);
		h7h8id7.setChild(new Selector("ID7", SelectorType.ID));
		h7h8id7.getChild().setSecondary("STRONG", SelectorType.TYPE);
		assertTrue(hm.get(h7h8).equals(red));
		assertTrue(hm.get(h7id7c7).equals(blue));
		assertTrue(hm.get(h7h8id7).equals(green));
		
	}
}