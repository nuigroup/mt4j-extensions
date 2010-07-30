package org.mtdj;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.mt4j.MTApplication;
import org.mt4j.components.MTComponent;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.clipping.Clip;
import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.widgets.MTImage;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.components.visibleComponents.widgets.menus.MTHexagonMenu;
import org.mt4j.components.visibleComponents.widgets.menus.MTSquareMenu;
import org.mt4j.components.visibleComponents.widgets.menus.MenuItem;
import org.mt4j.css.parser.CSSParserConnection;
import org.mt4j.css.style.CSSStyle;
import org.mt4j.css.util.CSSStyleManager;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;
import org.mt4j.util.math.Vertex;
import org.mtdj.menu.util.HexagonMenu;


import processing.core.PImage;


public class TestMenus  extends AbstractScene{
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
	
	public TestMenus(MTApplication mtApplication, String name) {
		super(mtApplication, name);

		this.app = mtApplication;
		

			app.getCssStyleManager().setGloballyEnabled(true);
			app.getCssStyleManager().loadStyles("menutest.css");
			logger.addAppender(ca);
			
			PImage i64 = app.loadImage("64x64_1.jpg");
			PImage i100 = app.loadImage("100x100.jpg");
			PImage i200300 = app.loadImage("200x300.jpg");
			
			
			gestureListener gl = new gestureListener("Generic");
			
			List<MenuItem> menus = new ArrayList<MenuItem>();
			menus.add(new MenuItem("Start", new gestureListener("Start")));
			menus.add(new MenuItem("Open", new gestureListener("Open")));
			menus.add(new MenuItem("Close", new gestureListener("Close")));
			menus.add(new MenuItem("Exit", new gestureListener("Exit")));
			menus.add(new MenuItem("Rough", new gestureListener("Rough")));
			menus.add(new MenuItem("Trade", new gestureListener("Trade")));
			menus.add(new MenuItem("Cancel", new gestureListener("Cancel")));
			menus.add(new MenuItem("Undo", new gestureListener("Undo")));
			menus.add(new MenuItem(i64, new gestureListener("Parkschild")));
			menus.add(new MenuItem(i100, new gestureListener("Tudors")));
			menus.add(new MenuItem(i200300, new gestureListener("Kite Surfer")));
			
			//MTSquareMenu sm = new MTSquareMenu(app, new Vector3D(200,200),  menus, 100);
			//this.getCanvas().addChild(sm);
			MTHexagonMenu hm = new MTHexagonMenu(app, new Vector3D(200,200),  menus, 175);
			this.getCanvas().addChild(hm);
			
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutDown() {
		// TODO Auto-generated method stub
		
	}

	public class gestureListener implements IGestureEventListener {
		String string;
		public gestureListener(String string) {
			super();
			this.string = string;
		}
		
		
		@Override
		public boolean processGestureEvent(MTGestureEvent ge) {

			if (ge instanceof TapEvent) {
				TapEvent te = (TapEvent) ge;
				if (te.getTapID() == TapEvent.BUTTON_CLICKED) {
					System.out.println(string);
				}
			}
			return true;
		}
		
	}
}
