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
import org.mt4j.css.parser.CSSParserConnection;
import org.mt4j.css.style.CSSStyle;
import org.mt4j.css.util.CSSStyleManager;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;
import org.mt4j.util.math.Vertex;

import processing.core.PFont;
import processing.core.PImage;


public class TestBackgroundImage  extends AbstractScene{
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
	
	public TestBackgroundImage(MTApplication mtApplication, String name) {
		super(mtApplication, name);

		this.app = mtApplication;
		
			
			
			//Set up components
			parent = new MTComponent(app);
			this.getCanvas().addChild(parent);
			app.getCssStyleManager().setGloballyEnabled(true);
			app.getCssStyleManager().loadStyles("backgroundimage_test.css");
			logger.addAppender(ca);
			
			
			MTRectangle r = new MTRectangle(500, 500, 500, 500, app);
			this.getCanvas().addChild(r);
			PImage bgImage = app.loadImage("data/256x256.jpg");
			MTImage img = new MTImage(bgImage,app);
			r.addChild(img);
			img.setPositionRelativeToParent(r.getVerticesLocal()[0].addLocal(new Vector3D(400,400)));
			img.setPickable(false);
			Clip clip = new Clip(r);
			r.setChildClip(clip);
			//img.setClip(clip);
			
			//clip.enableClip(app.g);
			
//			for (Vector3D v3d: img.getClip().getClipShape().getBounds().getVectorsGlobal()) {
//				logger.debug("Clip: " + v3d);
//			}
//			for (Vector3D v3d: r.getBounds().getVectorsGlobal()) {
//				logger.debug("Parent: " + v3d);
//			}
//			for (Vector3D v3d: img.getBounds().getVectorsGlobal()) {
//				logger.debug("MTImage: " + v3d);
//			}
			//r.setFillColor(MTColor.WHITE);
			
			Vertex[] vtcs = {new Vertex(100,100), new Vertex(200, 20), new Vertex(300, 200) ,new Vertex(100,100)};
			MTPolygon p = new MTPolygon(app, vtcs);
			this.getCanvas().addChild(p);

			
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
