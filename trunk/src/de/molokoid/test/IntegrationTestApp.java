package de.molokoid.test;

import java.util.ArrayList;
import java.util.List;

import org.mt4j.MTApplication;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MTColor;

import de.molokoid.data.CSSStyle;
import de.molokoid.data.CSSStyleManager;
import de.molokoid.data.Selector;
import de.molokoid.data.SelectorType;
import de.molokoid.extensions.MTCSSRectangle;

public class IntegrationTestApp extends AbstractScene{
	List<CSSStyle> CSSStyleManager = new ArrayList<CSSStyle>();
	MTApplication app = null;
	public IntegrationTestApp(MTApplication mtApplication, String name) {
		super(mtApplication, name);
		this.app = mtApplication;
		
		CSSStyleManager cm = new CSSStyleManager();
		
		
		
		CSSStyle style = new CSSStyle(new Selector("MTRectangle", SelectorType.CLASS), app);
		style.setWidth(200);
		style.setHeight(500);
		style.setBackgroundColor(new MTColor(0,128,0,255));
		style.setBorderColor(new MTColor(200,200,200,255));
		style.setBorderWidth(2);
		style.setVisibility(true);
		
		MTCSSRectangle mca = new MTCSSRectangle(style, 100,100, mtApplication, cm);
		this.getCanvas().addChild(mca);

		
		CSSStyle emptyStyle = new CSSStyle(new Selector("MTCSSRectangle", SelectorType.CLASS), app);
		mca.setStyleSheet(emptyStyle);
		mca.applyStylesheet();

		emptyStyle.setWidth(200);
		emptyStyle.setHeight(200);
		mca.applyStylesheet();

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
