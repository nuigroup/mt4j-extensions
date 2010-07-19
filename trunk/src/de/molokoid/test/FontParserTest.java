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

import de.molokoid.css.CSSParserConnection;
import de.molokoid.data.CSSKeywords.CSSSelectorType;
import de.molokoid.data.CSSStyle;
import de.molokoid.data.CSSSelector;


public class FontParserTest extends TestCase{
	Logger logger = Logger.getLogger("MT4J Extensions");
	SimpleLayout l = new SimpleLayout();
	ConsoleAppender ca = new ConsoleAppender(l);

	Logger fileLogger = Logger.getLogger("FileLogger");


	StartTestApp app = new StartTestApp();
	CSSParserConnection pc;
	List<CSSStyle> styles;
	MTColor w = new MTColor(255,255,255,255);
	HashMap<CSSSelector, IFont> hm = new HashMap<CSSSelector, IFont>();

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

		pc = new CSSParserConnection("fonttest.css", app);
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
		CSSSelector sans = new CSSSelector("sans", CSSSelectorType.ID);
		CSSSelector sansbold = new CSSSelector("sansbold", CSSSelectorType.ID);
		CSSSelector sanslight = new CSSSelector("sanslight", CSSSelectorType.ID);
		CSSSelector sansitalic = new CSSSelector("sansitalic", CSSSelectorType.ID);
		CSSSelector sansitalicbold = new CSSSelector("sansitalicbold", CSSSelectorType.ID);
		CSSSelector sansitaliclight = new CSSSelector("sansitaliclight", CSSSelectorType.ID);
		CSSSelector sansoblique = new CSSSelector("sansoblique", CSSSelectorType.ID);
		CSSSelector sansobliquebold = new CSSSelector("sansobliquebold", CSSSelectorType.ID);
		CSSSelector sansobliquelight = new CSSSelector("sansobliquelight", CSSSelectorType.ID);
		CSSSelector sansnormal = new CSSSelector("sansnormal", CSSSelectorType.ID);
		CSSSelector sansnormalbold = new CSSSelector("sansnormalbold", CSSSelectorType.ID);
		CSSSelector sansnormallight = new CSSSelector("sansnormallight", CSSSelectorType.ID);

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
		CSSSelector serif = new CSSSelector("serif", CSSSelectorType.ID);
		CSSSelector serifbold = new CSSSelector("serifbold", CSSSelectorType.ID);
		CSSSelector seriflight = new CSSSelector("seriflight", CSSSelectorType.ID);
		CSSSelector serifitalic = new CSSSelector("serifitalic", CSSSelectorType.ID);
		CSSSelector serifitalicbold = new CSSSelector("serifitalicbold", CSSSelectorType.ID);
		CSSSelector serifitaliclight = new CSSSelector("serifitaliclight", CSSSelectorType.ID);
		CSSSelector serifoblique = new CSSSelector("serifoblique", CSSSelectorType.ID);
		CSSSelector serifobliquebold = new CSSSelector("serifobliquebold", CSSSelectorType.ID);
		CSSSelector serifobliquelight = new CSSSelector("serifobliquelight", CSSSelectorType.ID);
		CSSSelector serifnormal = new CSSSelector("serifnormal", CSSSelectorType.ID);
		CSSSelector serifnormalbold = new CSSSelector("serifnormalbold", CSSSelectorType.ID);
		CSSSelector serifnormallight = new CSSSelector("serifnormallight", CSSSelectorType.ID);

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
		CSSSelector mono = new CSSSelector("mono", CSSSelectorType.ID);
		CSSSelector monobold = new CSSSelector("monobold", CSSSelectorType.ID);
		CSSSelector monolight = new CSSSelector("monolight", CSSSelectorType.ID);
		CSSSelector monoitalic = new CSSSelector("monoitalic", CSSSelectorType.ID);
		CSSSelector monoitalicbold = new CSSSelector("monoitalicbold", CSSSelectorType.ID);
		CSSSelector monoitaliclight = new CSSSelector("monoitaliclight", CSSSelectorType.ID);
		CSSSelector monooblique = new CSSSelector("monooblique", CSSSelectorType.ID);
		CSSSelector monoobliquebold = new CSSSelector("monoobliquebold", CSSSelectorType.ID);
		CSSSelector monoobliquelight = new CSSSelector("monoobliquelight", CSSSelectorType.ID);
		CSSSelector mononormal = new CSSSelector("mononormal", CSSSelectorType.ID);
		CSSSelector mononormalbold = new CSSSelector("mononormalbold", CSSSelectorType.ID);
		CSSSelector mononormallight = new CSSSelector("mononormallight", CSSSelectorType.ID);

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
		CSSSelector arial = new CSSSelector("arial", CSSSelectorType.ID);
		CSSSelector arial12 = new CSSSelector("arial12", CSSSelectorType.ID);
		CSSSelector arialgreen = new CSSSelector("arialgreen", CSSSelectorType.ID);
		CSSSelector arialsmaller = new CSSSelector("arialsmaller", CSSSelectorType.ID);
		
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
