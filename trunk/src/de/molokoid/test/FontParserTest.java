package de.molokoid.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.junit.Before;
import org.junit.Test;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.util.MTColor;

import de.molokoid.css.parserConnector;
import de.molokoid.data.CSSStyle;
import de.molokoid.data.Selector;
import de.molokoid.data.SelectorType;


public class FontParserTest extends TestCase{
	Logger logger = Logger.getLogger("MT4J Extensions");
	SimpleLayout l = new SimpleLayout();
	ConsoleAppender ca = new ConsoleAppender(l);

	Logger fileLogger = Logger.getLogger("FileLogger");


	StartTestApp app = new StartTestApp();
	parserConnector pc;
	List<CSSStyle> styles;
	MTColor w = new MTColor(255,255,255,255);

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

		pc = new parserConnector("fonttest.css", app);
		styles= pc.getCssh().getStyles();
	}


	protected void tearDown() {
		//app.destroy();
	}

	@Test
	public void testFontFamilesSans() {
		Selector sans = new Selector("sans", SelectorType.ID);
		Selector sansbold = new Selector("sansbold", SelectorType.ID);
		Selector sanslight = new Selector("sanslight", SelectorType.ID);
		Selector sansitalic = new Selector("sansitalic", SelectorType.ID);
		Selector sansitalicbold = new Selector("sansitalicbold", SelectorType.ID);
		Selector sansitaliclight = new Selector("sansitaliclight", SelectorType.ID);
		Selector sansoblique = new Selector("sansoblique", SelectorType.ID);
		Selector sansobliquebold = new Selector("sansobliquebold", SelectorType.ID);
		Selector sansobliquelight = new Selector("sansobliquelight", SelectorType.ID);
		Selector sansnormal = new Selector("sansnormal", SelectorType.ID);
		Selector sansnormalbold = new Selector("sansnormalbold", SelectorType.ID);
		Selector sansnormallight = new Selector("sansnormallight", SelectorType.ID);
		int exists = 0;
		IFont sansFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSans.ttf", 16,w,w);
		IFont sansBoldFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSans-Bold.ttf", 16,w,w);
		IFont sansItalicFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSans-Oblique.ttf", 16,w,w);
		IFont sansItalicBoldFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSans-BoldOblique.ttf", 16,w,w);
		IFont sansLightFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSans-ExtraLight.ttf", 16,w,w);

		for (CSSStyle s: styles) {
			if (s.getSelector().equals(sans)) {
				assertTrue(sameFont(s.getFont(), sansFont));
				exists++;
			}
			if (s.getSelector().equals(sansbold)) {
				assertTrue(sameFont(s.getFont(), sansBoldFont));
				exists++;
			}
			if (s.getSelector().equals(sanslight)) {
				assertTrue(sameFont(s.getFont(), sansLightFont));
				exists++;
			}
			if (s.getSelector().equals(sansitalic)) {
				exists++;
				assertTrue(sameFont(s.getFont(), sansItalicFont));
			}
			if (s.getSelector().equals(sansitalicbold)) {
				exists++;
				assertTrue(sameFont(s.getFont(), sansItalicBoldFont));
			}
			if (s.getSelector().equals(sansitaliclight)) {
				exists++;
				assertTrue(sameFont(s.getFont(), sansItalicFont));
			}
			if (s.getSelector().equals(sansoblique)) {
				exists++;
				assertTrue(sameFont(s.getFont(), sansItalicFont));
			}
			if (s.getSelector().equals(sansobliquebold)) {
				exists++;
				assertTrue(sameFont(s.getFont(), sansItalicBoldFont));
			}
			if (s.getSelector().equals(sansobliquelight)) {
				exists++;
				assertTrue(sameFont(s.getFont(), sansItalicFont));
			}
			if (s.getSelector().equals(sansnormal)) {
				exists++;
				assertTrue(sameFont(s.getFont(), sansFont));
			}
			if (s.getSelector().equals(sansnormalbold)) {
				exists++;
				assertTrue(sameFont(s.getFont(), sansBoldFont));
			}
			if (s.getSelector().equals(sansnormallight)) {
				exists++;
				assertTrue(sameFont(s.getFont(), sansLightFont));
			}


		}
		assertTrue(exists == 12);

	}
	
	@Test
	public void testFontFamilesSerif() {
		Selector serif = new Selector("serif", SelectorType.ID);
		Selector serifbold = new Selector("serifbold", SelectorType.ID);
		Selector seriflight = new Selector("seriflight", SelectorType.ID);
		Selector serifitalic = new Selector("serifitalic", SelectorType.ID);
		Selector serifitalicbold = new Selector("serifitalicbold", SelectorType.ID);
		Selector serifitaliclight = new Selector("serifitaliclight", SelectorType.ID);
		Selector serifoblique = new Selector("serifoblique", SelectorType.ID);
		Selector serifobliquebold = new Selector("serifobliquebold", SelectorType.ID);
		Selector serifobliquelight = new Selector("serifobliquelight", SelectorType.ID);
		Selector serifnormal = new Selector("serifnormal", SelectorType.ID);
		Selector serifnormalbold = new Selector("serifnormalbold", SelectorType.ID);
		Selector serifnormallight = new Selector("serifnormallight", SelectorType.ID);
		int exists = 0;
		IFont serifFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSerif.ttf", 16,w,w);
		IFont serifBoldFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSerif-Bold.ttf", 16,w,w);
		IFont serifItalicFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSerif-Italic.ttf", 16,w,w);
		IFont serifItalicBoldFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSerif-BoldItalic.ttf", 16,w,w);
		IFont serifLightFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSerif.ttf", 16,w,w);

		for (CSSStyle s: styles) {
			if (s.getSelector().equals(serif)) {
				assertTrue(sameFont(s.getFont(), serifFont));
				exists++;
			}
			if (s.getSelector().equals(serifbold)) {
				assertTrue(sameFont(s.getFont(), serifBoldFont));
				exists++;
			}
			if (s.getSelector().equals(seriflight)) {
				assertTrue(sameFont(s.getFont(), serifLightFont));
				exists++;
			}
			if (s.getSelector().equals(serifitalic)) {
				exists++;
				assertTrue(sameFont(s.getFont(), serifItalicFont));
			}
			if (s.getSelector().equals(serifitalicbold)) {
				exists++;
				assertTrue(sameFont(s.getFont(), serifItalicBoldFont));
			}
			if (s.getSelector().equals(serifitaliclight)) {
				exists++;
				assertTrue(sameFont(s.getFont(), serifItalicFont));
			}
			if (s.getSelector().equals(serifoblique)) {
				exists++;
				assertTrue(sameFont(s.getFont(), serifItalicFont));
			}
			if (s.getSelector().equals(serifobliquebold)) {
				exists++;
				assertTrue(sameFont(s.getFont(), serifItalicBoldFont));
			}
			if (s.getSelector().equals(serifobliquelight)) {
				exists++;
				assertTrue(sameFont(s.getFont(), serifItalicFont));
			}
			if (s.getSelector().equals(serifnormal)) {
				exists++;
				assertTrue(sameFont(s.getFont(), serifFont));
			}
			if (s.getSelector().equals(serifnormalbold)) {
				exists++;
				assertTrue(sameFont(s.getFont(), serifBoldFont));
			}
			if (s.getSelector().equals(serifnormallight)) {
				exists++;
				assertTrue(sameFont(s.getFont(), serifLightFont));
			}


		}
		assertTrue(exists == 12);
	}
	@Test
	public void testFontFamilesMono() {
		Selector mono = new Selector("mono", SelectorType.ID);
		Selector monobold = new Selector("monobold", SelectorType.ID);
		Selector monolight = new Selector("monolight", SelectorType.ID);
		Selector monoitalic = new Selector("monoitalic", SelectorType.ID);
		Selector monoitalicbold = new Selector("monoitalicbold", SelectorType.ID);
		Selector monoitaliclight = new Selector("monoitaliclight", SelectorType.ID);
		Selector monooblique = new Selector("monooblique", SelectorType.ID);
		Selector monoobliquebold = new Selector("monoobliquebold", SelectorType.ID);
		Selector monoobliquelight = new Selector("monoobliquelight", SelectorType.ID);
		Selector mononormal = new Selector("mononormal", SelectorType.ID);
		Selector mononormalbold = new Selector("mononormalbold", SelectorType.ID);
		Selector mononormallight = new Selector("mononormallight", SelectorType.ID);
		int exists = 0;
		IFont monoFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSansMono.ttf", 16,w,w);
		IFont monoBoldFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSansMono-Bold.ttf", 16,w,w);
		IFont monoItalicFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSansMono-Oblique.ttf", 16,w,w);
		IFont monoItalicBoldFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSansMono-BoldOblique.ttf", 16,w,w);
		IFont monoLightFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSansMono.ttf", 16,w,w);

		for (CSSStyle s: styles) {
			if (s.getSelector().equals(mono)) {
				assertTrue(sameFont(s.getFont(), monoFont));
				exists++;
			}
			if (s.getSelector().equals(monobold)) {
				assertTrue(sameFont(s.getFont(), monoBoldFont));
				exists++;
			}
			if (s.getSelector().equals(monolight)) {
				assertTrue(sameFont(s.getFont(), monoLightFont));
				exists++;
			}
			if (s.getSelector().equals(monoitalic)) {
				exists++;
				assertTrue(sameFont(s.getFont(), monoItalicFont));
			}
			if (s.getSelector().equals(monoitalicbold)) {
				exists++;
				assertTrue(sameFont(s.getFont(), monoItalicBoldFont));
			}
			if (s.getSelector().equals(monoitaliclight)) {
				exists++;
				assertTrue(sameFont(s.getFont(), monoItalicFont));
			}
			if (s.getSelector().equals(monooblique)) {
				exists++;
				assertTrue(sameFont(s.getFont(), monoItalicFont));
			}
			if (s.getSelector().equals(monoobliquebold)) {
				exists++;
				assertTrue(sameFont(s.getFont(), monoItalicBoldFont));
			}
			if (s.getSelector().equals(monoobliquelight)) {
				exists++;
				assertTrue(sameFont(s.getFont(), monoItalicFont));
			}
			if (s.getSelector().equals(mononormal)) {
				exists++;
				assertTrue(sameFont(s.getFont(), monoFont));
			}
			if (s.getSelector().equals(mononormalbold)) {
				exists++;
				assertTrue(sameFont(s.getFont(), monoBoldFont));
			}
			if (s.getSelector().equals(mononormallight)) {
				exists++;
				assertTrue(sameFont(s.getFont(), monoLightFont));
			}


		}
		assertTrue(exists == 12);
	}
	@Test
	public void testFontFamilesOther() {
		Selector arial = new Selector("arial", SelectorType.ID);
		Selector arial12 = new Selector("arial12", SelectorType.ID);
		Selector arialgreen = new Selector("arialgreen", SelectorType.ID);
		Selector arialsmaller = new Selector("arialsmaller", SelectorType.ID);
		
		IFont arialFont = FontManager.getInstance().createFont(app,"arial.ttf", 16,w,w);
		IFont arial12Font = FontManager.getInstance().createFont(app, "arial.ttf", 12, w, w);
		MTColor g = new MTColor(0,128,0,255);
		IFont arialgreenFont = FontManager.getInstance().createFont(app, "arial.ttf", 16, g, g);
		IFont arialsmallerFont = FontManager.getInstance().createFont(app, "arial.ttf", 8, w, w);
		int exists = 0;
		for (CSSStyle s: styles) {
			if (s.getSelector().equals(arial)) {
				exists++;
				assertTrue(sameFont(s.getFont(), arialFont));
			}
			if (s.getSelector().equals(arial12)) {
				exists++;
				assertTrue(sameFont(s.getFont(), arial12Font));
			}
			if (s.getSelector().equals(arialgreen)) {
				exists++;
				assertTrue(sameFont(s.getFont(), arialgreenFont));
			}
			if (s.getSelector().equals(arialsmaller)) {
				exists++;
				assertTrue(sameFont(s.getFont(), arialsmallerFont));
			}
		}
		
		assertTrue(exists == 4);
	}
	
	public boolean sameFont(IFont f1, IFont f2) {
		boolean same = true;
		same = same && f1.getFillColor().equals(f2.getFillColor());
		same = same && f1.getStrokeColor().equals(f2.getStrokeColor());
		same = same && f1.getOriginalFontSize() == f2.getOriginalFontSize();
		same = same && f1.getFontFileName().equalsIgnoreCase(f2.getFontFileName());
				

		return same;
	}
}
