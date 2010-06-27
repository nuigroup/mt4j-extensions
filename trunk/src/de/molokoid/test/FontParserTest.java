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
	HashMap<Selector, IFont> hm = new HashMap<Selector, IFont>();

	public FontParserTest() {
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
		hm.clear();
		for (CSSStyle s: styles) {
			hm.put(s.getSelector(), s.getFont());
		}
	}
	
	@Before
	public void setUp() {

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

		IFont sansFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSans.ttf", 16,w,w);
		IFont sansBoldFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSans-Bold.ttf", 16,w,w);
		IFont sansItalicFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSans-Oblique.ttf", 16,w,w);
		IFont sansItalicBoldFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSans-BoldOblique.ttf", 16,w,w);
		IFont sansLightFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSans-ExtraLight.ttf", 16,w,w);


				assertTrue(sameFont(hm.get(sans), sansFont));

				assertTrue(sameFont(hm.get(sansbold), sansBoldFont));

				assertTrue(sameFont(hm.get(sanslight), sansLightFont));

				assertTrue(sameFont(hm.get(sansitalic), sansItalicFont));

				assertTrue(sameFont(hm.get(sansitalicbold), sansItalicBoldFont));

				assertTrue(sameFont(hm.get(sansitaliclight), sansItalicFont));

				assertTrue(sameFont(hm.get(sansoblique), sansItalicFont));

				assertTrue(sameFont(hm.get(sansobliquebold), sansItalicBoldFont));

				assertTrue(sameFont(hm.get(sansobliquelight), sansItalicFont));

				assertTrue(sameFont(hm.get(sansnormal), sansFont));

				assertTrue(sameFont(hm.get(sansnormalbold), sansBoldFont));

				assertTrue(sameFont(hm.get(sansnormallight), sansLightFont));


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

		IFont serifFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSerif.ttf", 16,w,w);
		IFont serifBoldFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSerif-Bold.ttf", 16,w,w);
		IFont serifItalicFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSerif-Italic.ttf", 16,w,w);
		IFont serifItalicBoldFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSerif-BoldItalic.ttf", 16,w,w);
		IFont serifLightFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSerif.ttf", 16,w,w);

				assertTrue(sameFont(hm.get(serif), serifFont));

				assertTrue(sameFont(hm.get(serifbold), serifBoldFont));

				assertTrue(sameFont(hm.get(seriflight), serifLightFont));

				assertTrue(sameFont(hm.get(serifitalic), serifItalicFont));

				assertTrue(sameFont(hm.get(serifitalicbold), serifItalicBoldFont));

				assertTrue(sameFont(hm.get(serifitaliclight), serifItalicFont));

				assertTrue(sameFont(hm.get(serifoblique), serifItalicFont));

				assertTrue(sameFont(hm.get(serifobliquebold), serifItalicBoldFont));

				assertTrue(sameFont(hm.get(serifobliquelight), serifItalicFont));

				assertTrue(sameFont(hm.get(serifnormal), serifFont));

				assertTrue(sameFont(hm.get(serifnormalbold), serifBoldFont));

				assertTrue(sameFont(hm.get(serifnormallight), serifLightFont));

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

		IFont monoFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSansMono.ttf", 16,w,w);
		IFont monoBoldFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSansMono-Bold.ttf", 16,w,w);
		IFont monoItalicFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSansMono-Oblique.ttf", 16,w,w);
		IFont monoItalicBoldFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSansMono-BoldOblique.ttf", 16,w,w);
		IFont monoLightFont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSansMono.ttf", 16,w,w);


				assertTrue(sameFont(hm.get(mono), monoFont));

				assertTrue(sameFont(hm.get(monobold), monoBoldFont));

				assertTrue(sameFont(hm.get(monolight), monoLightFont));

				assertTrue(sameFont(hm.get(monoitalic), monoItalicFont));

				assertTrue(sameFont(hm.get(monoitalicbold), monoItalicBoldFont));

				assertTrue(sameFont(hm.get(monoitaliclight), monoItalicFont));

				assertTrue(sameFont(hm.get(monooblique), monoItalicFont));

				assertTrue(sameFont(hm.get(monoobliquebold), monoItalicBoldFont));

				assertTrue(sameFont(hm.get(monoobliquelight), monoItalicFont));

				assertTrue(sameFont(hm.get(mononormal), monoFont));

				assertTrue(sameFont(hm.get(mononormalbold), monoBoldFont));

				assertTrue(sameFont(hm.get(mononormallight), monoLightFont));

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

				assertTrue(sameFont(hm.get(arial), arialFont));

				assertTrue(sameFont(hm.get(arial12), arial12Font));

				assertTrue(sameFont(hm.get(arialgreen), arialgreenFont));

				assertTrue(sameFont(hm.get(arialsmaller), arialsmallerFont));

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
