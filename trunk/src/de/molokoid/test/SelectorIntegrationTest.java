package de.molokoid.test;

import java.util.HashMap;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.junit.Before;
import org.junit.Test;
import org.mt4j.MTApplication;
import org.mt4j.sceneManagement.Iscene;
import org.mt4j.test.AbstractWindowTestcase;
import org.mt4j.test.testUtil.DummyScene;
import org.mt4j.test.testUtil.TestRunnable;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;
import org.mt4j.components.MTCanvas;
import org.mt4j.components.MTComponent;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;

import de.molokoid.css.parserConnector;
import de.molokoid.data.CSSStyle;
import de.molokoid.data.CSSStyleManager;
import de.molokoid.data.Selector;
import de.molokoid.extensions.*;

public class SelectorIntegrationTest extends AbstractWindowTestcase {
	private MTComponent parent;
	private MTApplication app;
	private Iscene scene;
	CSSStyleManager cssm;
	Logger logger = Logger.getLogger("MT4J Extensions");
	SimpleLayout l = new SimpleLayout();
	ConsoleAppender ca = new ConsoleAppender(l);
	parserConnector pc;
	List<CSSStyle> styles;
	MTColor w = new MTColor(255,255,255,255);
	
	@Override
	public void inStartUp(MTApplication app) {
		this.app = app;
		//Add a scene to the mt application
		this.scene = new DummyScene(app, "Dummy Scene");
		app.addScene(scene);
		
		//Set up components
		parent = new MTComponent(app);
		getCanvas().addChild(parent);
		
		logger.addAppender(ca);
		
		pc = new parserConnector("integrationtest.css", app);
		styles= pc.getCssh().getStyles();
		cssm = new CSSStyleManager(styles,app);
	}
	
	public MTCanvas getCanvas(){
		return this.scene.getCanvas();
	}
	

	
	@Test
	public void testDirectStyleSheets() {
		MTCSSRectangle r = new MTCSSRectangle(0,0,100,100, app, cssm);
		getCanvas().addChild(r);
		assertTrue(r.getFillColor().equals(MTColor.GREEN));
		
		
		
	}
	
	@Test
	public void testClassSelector() {
		MTCSSEllipse e = new MTCSSEllipse(app, new Vector3D(500,500), 50, 50, cssm);
		getCanvas().addChild(e);
		assertTrue(e.getFillColor().equals(MTColor.WHITE));
	}
	
	@Test
	public void testUniversalSelector() {
		MTCSSLine l = new MTCSSLine(app, 100,100, 200,200, cssm);
		getCanvas().addChild(l);
		MTCSSEllipse e = new MTCSSEllipse(app, new Vector3D(500,500), 50, 50, cssm);
		getCanvas().addChild(e);
		assertTrue(l.getStrokeColor().equals(MTColor.BLUE));
		assertTrue(e.getStrokeColor().equals(MTColor.BLUE));
	}
	
	@Test
	public void testFontIntegration() {
		this.runTest(new TestRunnable() {
			@Override
			public void runMTTestCode() {
				IFont font = cssm.getDefaultFont(app);
				IFont comparableFont = FontManager.getInstance().createFont(app,
						"dejavu/DejaVuSans.ttf", 16, // Font size
						MTColor.WHITE, // Font fill color
						MTColor.WHITE);
				assertTrue(font.equals(comparableFont));
				
				MTCSSTextArea ta = new MTCSSTextArea(app, cssm);
				getCanvas().addChild(ta);
				assertTrue(ta.getFillColor().equals(MTColor.PURPLE));
				
				MTCSSTextArea ta2 = new MTCSSTextArea(app, cssm);
				ta2.setCSSID("fonttest");
				getCanvas().addChild(ta2);
				ta2.applyStyleSheet();
				IFont lightfont = FontManager.getInstance().createFont(app,"dejavu/DejaVuSans-ExtraLight.ttf", 16,MTColor.WHITE,MTColor.WHITE);
				
				assertTrue(ta2.getFont().equals(lightfont));
			}
		});
		
		
	}
	
	@Test
	public void testCascadingSelectors() {
		this.runTest(new TestRunnable() {
			@Override
			public void runMTTestCode() {
				MTCSSRectangle r1 = new MTCSSRectangle(100,100,100,100, app, cssm);
				MTCSSRectangle r2 = new MTCSSRectangle(100,100,100,100, app, cssm);
				MTCSSRectangle r3 = new MTCSSRectangle(100,100,100,100, app, cssm);
				
				MTCSSEllipse e = new MTCSSEllipse(app, new Vector3D(200,200), 50,50, cssm);
				
				MTCSSTextArea ta = new MTCSSTextArea(app, cssm);
				MTCSSTextArea t2 = new MTCSSTextArea(app, cssm);
				
				getCanvas().addChild(r1);
				getCanvas().addChild(r2);
				
				r1.addChild(ta);
				r2.addChild(e);
				e.addChild(r3);
				e.addChild(t2);

				//ta.applyStyleSheet();

				//t2.applyStyleSheet();

				//r3.applyStyleSheet();
				
				assertTrue(ta.getFillColor().equals(MTColor.LIME));
				assertTrue(r3.getFillColor().equals(MTColor.GREY));
				assertTrue(t2.getFillColor().equals(MTColor.BLUE));
			}
		});
			
		
	}
}
