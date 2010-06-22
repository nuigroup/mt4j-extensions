package de.molokoid.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

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

public class parserConnectorTest {
	Logger logger = Logger.getLogger("MT4J Extensions");
	SimpleLayout l = new SimpleLayout();
	ConsoleAppender ca = new ConsoleAppender(l);
	
	Logger fileLogger = Logger.getLogger("FileLogger");
	
	
	
	
	
	
	StartTestApp app = new StartTestApp();
	parserConnector pc;
	List<CSSStyle> styles;
	
	@Before
	public void setUp() {
		logger.addAppender(ca);
		FileAppender fa;
		try {
			fa = new FileAppender(l, "logs/logfile.txt", false);
			fileLogger.addAppender(fa);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fileLogger.addAppender(ca);
		
		pc = new parserConnector("selectortest.css", app);
		styles= pc.getCssh().getStyles();
	}
	
	
	protected void tearDown() {
		//app.destroy();
	}
	
	@Test
	public void testParserConnector() throws InterruptedException {
		assertTrue (pc != null);
	}
	
	@Test
	public void testHexColors() {
		Selector reference = new Selector("testhexcolor", SelectorType.ID);
		boolean exists = false;
		for (CSSStyle s: styles) {
			if (s.getSelector().equals(reference)) {
				exists = true;
				assertTrue (s.getBackgroundColor().equals(new MTColor(224,224,224,255)));
			}
		}
		assertTrue (exists);
	}
	@Test
	public void testRGBColors() {
		Selector reference = new Selector("testrgbcolor", SelectorType.ID);
		boolean exists = false;
		for (CSSStyle s: styles) {
			if (s.getSelector().equals(reference)) {
				exists = true;
				assertTrue (s.getBackgroundColor().equals(new MTColor(0.6f * 255f,0.6f * 255f,0.4f*255f,255)));
			}
		}
		assertTrue (exists);
	}
	@Test
	public void testNameColors() {
		Selector white = new Selector("testNameColorWhite", SelectorType.ID);
		Selector red = new Selector("testNameColorRed", SelectorType.ID);
		Selector green = new Selector("testNameColorGreen", SelectorType.ID);
		Selector blue = new Selector("testNameColorBlue", SelectorType.ID);
		Selector purple = new Selector("testNameColorPurple", SelectorType.ID);
		int exists = 0;
		for (CSSStyle s: styles) {
			if (s.getSelector().equals(white)) {
				exists++;
				assertTrue (s.getBackgroundColor().equals(new MTColor(255,255,255,255)));
			}
			if (s.getSelector().equals(red)) {
				exists++;
				assertTrue (s.getBackgroundColor().equals(new MTColor(255,0,0,255)));
			}
			if (s.getSelector().equals(green)) {
				exists++;
				assertTrue (s.getBackgroundColor().equals(new MTColor(0,128,0,255)));
			}
			if (s.getSelector().equals(blue)) {
				exists++;
				assertTrue (s.getBackgroundColor().equals(new MTColor(0,0,255,255)));
			}
			if (s.getSelector().equals(purple)) {
				exists++;
				assertTrue (s.getBackgroundColor().equals(new MTColor(128,0,128,255)));
			}
		}
		assertTrue (exists == 5);
	}
	@Test
	public void testDimensions () {
		Selector testWidth = new Selector("testWidth", SelectorType.ID);
		Selector testHeight = new Selector("testHeight", SelectorType.ID);
		Selector testWidthPercentage = new Selector("testWidthPercentage", SelectorType.ID);
		Selector testHeightPercentage = new Selector("testHeightPercentage", SelectorType.ID);
		int exists = 0;
		for (CSSStyle s: styles) {
			if (s.getSelector().equals(testWidth)) {
				exists++;
				assertTrue (s.getWidth() == 100f);
			}
			if (s.getSelector().equals(testHeight)) {
				exists++;
				assertTrue (s.getHeight() == 100f);
			}
			if (s.getSelector().equals(testWidthPercentage)) {
				exists++;
				assertTrue (s.getWidth() == 25f && s.isWidthPercentage());
			}
			if (s.getSelector().equals(testHeightPercentage)) {
				exists++;
				assertTrue (s.getHeight() == 25f && s.isHeightPercentage());
			}

		}
		assertTrue (exists == 4);
		
		
	}
	@Test
	public void testMeasures() {
		Selector testMeasuresPx = new Selector("testMeasuresPx", SelectorType.ID);
		Selector testMeasuresCm = new Selector("testMeasuresCm", SelectorType.ID);
		Selector testMeasuresIn = new Selector("testMeasuresIn", SelectorType.ID);
		Selector testMeasuresMm = new Selector("testMeasuresMm", SelectorType.ID);
		Selector testMeasuresPt = new Selector("testMeasuresPt", SelectorType.ID);
		Selector testMeasuresPc = new Selector("testMeasuresPc", SelectorType.ID);
		Selector testMeasuresEm = new Selector("testMeasuresEm", SelectorType.ID);
		Selector testMeasuresInt = new Selector("testMeasuresInt", SelectorType.ID);
		Selector testMeasuresReal = new Selector("testMeasuresReal", SelectorType.ID);
		Selector testMeasuresPerc = new Selector("testMeasuresPerc", SelectorType.ID);
		int exists = 0;
		for (CSSStyle s: styles) {
		if (s.getSelector().equals(testMeasuresPx)) {
			exists++;
			assertTrue (s.getWidth() == 100f);
		}
		if (s.getSelector().equals(testMeasuresCm)) {
			exists++;
			assertTrue (s.getWidth() == (10f/254f) * 100f * 100f);
		}
		if (s.getSelector().equals(testMeasuresIn)) {
			exists++;
			assertTrue (s.getWidth() == 100f * 100);
		}
		if (s.getSelector().equals(testMeasuresMm)) {
			exists++;
			assertTrue (s.getWidth() == (1f/254f) * 100f * 100f);
		}
		if (s.getSelector().equals(testMeasuresPt)) {
			exists++;
			assertTrue (s.getWidth() == (1f/72f) * 100f * 100f);
		}
		if (s.getSelector().equals(testMeasuresPc)) {
			exists++;
			assertTrue (s.getWidth() == (12f/72f) * 100f * 100f);
		}
		if (s.getSelector().equals(testMeasuresEm)) {
			exists++;
			assertTrue (s.getWidth() == 16f/72f * 100f * 100f);
		}
		if (s.getSelector().equals(testMeasuresInt)) {
			exists++;
			assertTrue (s.getWidth() == 100f);
		}
		if (s.getSelector().equals(testMeasuresReal)) {
			exists++;
			assertTrue (s.getWidth() < 123.4001 && s.getWidth() > 123.3999);
		}
		if (s.getSelector().equals(testMeasuresPerc)) {
			exists++;
			assertTrue (s.getWidth() == 25f && s.isWidthPercentage());
		}

		}
		assertTrue (exists == 10);
		
		
	}
	
}
