package de.molokoid.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.mt4j.MTApplication;
import org.mt4j.components.MTCanvas;
import org.mt4j.components.MTComponent;
import org.mt4j.components.visibleComponents.widgets.MTBackgroundImage;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.sceneManagement.Iscene;
import org.mt4j.test.testUtil.DummyScene;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vertex;

import processing.core.PImage;

import de.molokoid.css.CSSParserConnection;
import de.molokoid.data.CSSStyle;
import de.molokoid.data.CSSStyleManager;
import de.molokoid.data.CSSSelector;
import de.molokoid.extensions.MTCSSPolygon;
import de.molokoid.extensions.MTCSSRectangle;
import de.molokoid.extensions.MTCSSTextArea;

public class IntegrationTestApp extends AbstractScene{
	List<CSSStyle> CSSStyleManager = new ArrayList<CSSStyle>();
	
	private MTComponent parent;
	private MTApplication app;

	CSSStyleManager cssm;
	Logger logger = Logger.getLogger("MT4J Extensions");
	SimpleLayout l = new SimpleLayout();
	ConsoleAppender ca = new ConsoleAppender(l);
	CSSParserConnection pc;
	List<CSSStyle> styles;
	MTColor w = new MTColor(255,255,255,255);
	
	public IntegrationTestApp(MTApplication mtApplication, String name) {
		super(mtApplication, name);

		this.app = mtApplication;
			//this.getCanvas().addChild(new MTBackgroundImage(app, app.loadImage("256x256.jpg"), true));
			
			
			//Set up components
			parent = new MTComponent(app);
			this.getCanvas().addChild(parent);
			
			logger.addAppender(ca);
			
			pc = new CSSParserConnection("integrationtest.css", app);
			styles= pc.getCssh().getStyles();
			cssm = new CSSStyleManager(styles, app);
		
			MTCSSRectangle r = new MTCSSRectangle(500, 500, 500, 500, app, cssm);
			this.getCanvas().addChild(r);
			PImage bgImage = app.loadImage("beamer_test.jpg");
			//r.tiledBackground(bgImage);
			
			Vertex[] vtcs = {new Vertex(100,100), new Vertex(200, 20), new Vertex(300, 200) ,new Vertex(100,100)};
			MTCSSPolygon p = new MTCSSPolygon(app, vtcs, cssm);
			this.getCanvas().addChild(p);
			p.tiledBackground(bgImage);
			
	}


	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutDown() {
		// TODO Auto-generated method stub
		
	}

}
