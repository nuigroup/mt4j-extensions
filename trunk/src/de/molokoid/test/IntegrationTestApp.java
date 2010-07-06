package de.molokoid.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.mt4j.MTApplication;
import org.mt4j.components.MTCanvas;
import org.mt4j.components.MTComponent;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.sceneManagement.Iscene;
import org.mt4j.test.testUtil.DummyScene;
import org.mt4j.util.MTColor;

import de.molokoid.css.parserConnector;
import de.molokoid.data.CSSStyle;
import de.molokoid.data.CSSStyleManager;
import de.molokoid.data.Selector;
import de.molokoid.data.SelectorType;
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
	parserConnector pc;
	List<CSSStyle> styles;
	MTColor w = new MTColor(255,255,255,255);
	
	public IntegrationTestApp(MTApplication mtApplication, String name) {
		super(mtApplication, name);

		

			this.app = mtApplication;
			
			//Set up components
			parent = new MTComponent(app);
			this.getCanvas().addChild(parent);
			
			logger.addAppender(ca);
			
			pc = new parserConnector("integrationtest.css", app);
			styles= pc.getCssh().getStyles();
			cssm = new CSSStyleManager(styles);
		
			MTCSSTextArea ta = new MTCSSTextArea(app, cssm.getDefaultFont(app), cssm);
			getCanvas().addChild(ta);
		

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
