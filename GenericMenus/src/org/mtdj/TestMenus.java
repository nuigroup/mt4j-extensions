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
import org.mtdj.menu.MenuItem;
import org.mtdj.menu.SquareMenu;

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
			
			gestureListener gl = new gestureListener(null);
			
			List<MenuItem> menus = new ArrayList<MenuItem>();
			menus.add(new MenuItem("Start", gl));
			menus.add(new MenuItem("Open", gl));
			menus.add(new MenuItem("Close", gl));
			menus.add(new MenuItem("Exit", gl));
			menus.add(new MenuItem("Rough", gl));
			menus.add(new MenuItem("Trade", gl));
			menus.add(new MenuItem("Cancel", gl));
			menus.add(new MenuItem("Undo", gl));
			
			SquareMenu sm = new SquareMenu(app, new Vector3D(200,200),  menus, 100);
			this.getCanvas().addChild(sm);
			
			MTTextArea ta = new MTTextArea(app);
			ta.setText("Hallo");
			this.getCanvas().addChild(ta);
			ta.setGestureAllowance(TapProcessor.class, true);
			ta.registerInputProcessor(new TapProcessor(app));
			ta.addGestureListener(TapProcessor.class, new gestureListener(ta));
			
			ta.setNoFill(true);
			
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
		MTTextArea ta;
		public gestureListener(MTTextArea ta) {
			super();
			this.ta = ta;
			System.out.println("Initialized gestureListener");
		}
		
		
		@Override
		public boolean processGestureEvent(MTGestureEvent ge) {
			if (ta != null) ta.setNoFill(!ta.isNoFill());
			if (ge instanceof TapEvent) {
				TapEvent te = (TapEvent) ge;
				if (te.getTapID() == TapEvent.BUTTON_CLICKED) {
					System.out.println("Click");
				}
			}
			return true;
		}
		
	}
}