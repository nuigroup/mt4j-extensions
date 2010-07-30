package org.mtdj.menu.util;

import java.util.ArrayList;
import java.util.List;

import org.mt4j.MTApplication;
import org.mt4j.components.MTComponent;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.clipping.Clip;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.components.visibleComponents.widgets.menus.MenuItem;
import org.mt4j.components.visibleComponents.widgets.menus.MTSquareMenu.gestureListener;
import org.mt4j.css.style.CSSFont;
import org.mt4j.css.style.CSSStyle;
import org.mt4j.css.util.CSSFontManager;
import org.mt4j.css.util.CSSHelper;
import org.mt4j.css.util.CSSStylableComponent;
import org.mt4j.css.util.CSSKeywords.CSSFontWeight;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.rotateProcessor.RotateProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.scaleProcessor.ScaleProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Tools3D;
import org.mt4j.util.math.Vector3D;
import org.mt4j.util.math.Vertex;
import org.mt4j.util.opengl.GLTexture;

import processing.core.PImage;

public class HexagonMenu extends MTRectangle implements CSSStylableComponent {

	MTApplication app;
	List<MTPolygon> menuContents = new ArrayList<MTPolygon>();
	List<ArrayList<MTPolygon>> layout = new ArrayList<ArrayList<MTPolygon>>();
	float size;
	int current = 0;
	int maxPerLine = 0;
	float bezel = 4f;

	public HexagonMenu(MTApplication app, Vector3D position,
			List<MenuItem> menuItems, float size) {
		super(position.x, position.y, (float) (int) Math
				.sqrt(menuItems.size() + 1) * size, (float) (int) Math
				.sqrt(menuItems.size() + 1) * size, app);
		this.app = app;
		this.size = size;
		this.setCssForceDisable(true);

		this.setNoFill(true);
		this.setNoStroke(true);

		gestureListener gl = new gestureListener(this);

		for (MenuItem s : menuItems) {
			
			if (s != null && s.getType() == MenuItem.TEXT) {
				MTPolygon container = getHexagon(size);
				this.addChild(container);
				container.removeAllGestureEventListeners(DragProcessor.class);

				container.setGestureAllowance(TapProcessor.class, true);
				container.registerInputProcessor(new TapProcessor(app));
				container.addGestureListener(TapProcessor.class,
						s.getGestureListener());

				for (String t : s.getMenuText().split("\n")) {
					MTTextArea menuItem = new MTTextArea(app);
					menuItem.setText(t);
					menuItem.setCssForceDisable(true);
					menuItem.setFillColor(new MTColor(0, 0, 0, 0));
					menuItem.setStrokeColor(new MTColor(0, 0, 0, 0));
					menuItem.setPickable(false);
					container.addChild(menuItem);

				}
				container.setGestureAllowance(DragProcessor.class, true);
				container.registerInputProcessor(new DragProcessor(app));
				container.addGestureListener(DragProcessor.class, gl);
				container.setChildClip(new Clip(container));

				container.setGestureAllowance(RotateProcessor.class, false);
				container.setGestureAllowance(ScaleProcessor.class, false);
				menuContents.add(container);
			} else if (s != null && s.getType() == MenuItem.PICTURE) {

				if (s.getMenuImage() != null) {
					PImage texture = null;
					MTPolygon container = getHexagon(size);
					container.setCssForceDisable(true);
					int height = (int)container.getHeightXY(TransformSpace.LOCAL);
					System.out.println("Height: " + height + " Image Width: " + s.getMenuImage().width + " Image Height: " + s.getMenuImage().height);
					if (s.getMenuImage().width != size
							|| s.getMenuImage().height != height) {
						texture = cropImage(s.getMenuImage(), (int)size, height, true);
					} else {
						texture = s.getMenuImage();
					}
					System.out.println("Height: " + height + " Texture Width: " + texture.width + " Texture Height: " + texture.height);
					
					this.addChild(container);
					
					for (Vertex v:container.getVerticesLocal()) {
						System.out.println("Before:\nVertex: " + v  + "\nTexture Coordinates: " + v.getTexCoordU() + " " + v.getTexCoordV());
						v.setTexCoordU(v.getX() / (float)texture.width);
						if (v.getTexCoordU() > 1) v.setTexCoordU(1);
						v.setTexCoordV(v.getY() / (float)texture.height);
						if (v.getTexCoordV() > 1) v.setTexCoordV(1);
						System.out.println("After:\nVertex: " + v  + "\nTexture Coordinates: " + v.getTexCoordU() + " " + v.getTexCoordV());
					}
					
					
					container.getGeometryInfo().setTextureCoordsNormalized(true);
					container.setTexture(texture);
					
					container
							.removeAllGestureEventListeners(DragProcessor.class);

					container.setGestureAllowance(TapProcessor.class, true);
					container.registerInputProcessor(new TapProcessor(app));
					container.addGestureListener(TapProcessor.class,
							s.getGestureListener());

					container.setGestureAllowance(DragProcessor.class, true);
					container.registerInputProcessor(new DragProcessor(app));
					container.addGestureListener(DragProcessor.class, gl);
					container.setChildClip(new Clip(container));

					container.setGestureAllowance(RotateProcessor.class, false);
					container.setGestureAllowance(ScaleProcessor.class, false);
					menuContents.add(container);

				}

			}

		}
		this.setCssForceDisable(true);
		this.styleChildren(getNecessaryFontSize(menuItems, size));
		
	}
	
	
	private PImage cropImage(PImage image, int width, int height, boolean resize) {
	
		PImage returnImage = app.createImage(width, height, app.RGB);
		if (resize || image.width < size || image.height < size) {
			if (((float)image.width / (float)width) < ((float)image.height / (float)height)) {
				image.resize(
						width,
						(int) ((float) image.height / ((float) image.width / (float) width)));
			} else {
				image.resize(
						(int) ((float) image.width / ((float) image.height / (float) height)),
						height);
			}

		}
		
		System.out.println("Image Size after Resize: " + image.width + "x" + image.height);
		
		int x = (image.width / 2) - (width / 2);
		int y = (image.height / 2) - (height / 2);

		if (x + width > image.width)
			x = image.width - width;
		if (x < 0)
			x = 0;
		if (x + width > image.width)
			width = image.width - x;
		if (y + height > image.height)
			x = image.height - height;
		if (y < 0)
			y = 0;
		if (y + height > image.height)
			height = image.height - y;

		
		returnImage.copy(image, x, y, width, height, 0, 0, width, height);

		return returnImage;
	}

	public class gestureListener implements IGestureEventListener {
		MTPolygon r;
		boolean react;

		public gestureListener(MTPolygon r) {
			super();
			this.r = r;
			System.out.println("Initialized gestureListener");
		}

		@Override
		public boolean processGestureEvent(MTGestureEvent ge) {
			// TODO: Remove Dirty Hack
			if (ge instanceof DragEvent && react) {
				DragEvent de = (DragEvent) ge;
				r.translate(de.getTranslationVect());
			}
			react = !react;
			return true;
		}
	}

	private void styleChildren(int fontsize) {
		organizeHexagons();
		CSSStyle vss = this.getCssHelper().getVirtualStyleSheet();
		CSSFont cf = this.getCssHelper().getVirtualStyleSheet().getCssfont();
		cf.setFontsize(fontsize);
		cf.setWeight(CSSFontWeight.BOLD);
		CSSFontManager cfm = new CSSFontManager(app);
		IFont font = cfm.selectFont(cf);
		int i = 0;
		// System.out.println("Fill Color: " + vss.getBackgroundColor());
		for (MTPolygon c : menuContents) {

			MTPolygon rect = c;

			rect.setStrokeColor(vss.getBorderColor());
			rect.setStrokeWeight(vss.getBorderWidth());
			
			
			
			//Set Font and Position for the child MTTextAreas
			if (((MTPolygon) c).getTexture() == null) {
				rect.setFillColor(vss.getBackgroundColor());
				for (MTComponent d : c.getChildren()) {
					if (d instanceof MTTextArea) {
						MTTextArea ta = (MTTextArea) d;
						ta.setFont(font);
					}
				}

				float height = calcTotalHeight(c.getChildren());
				float ypos = rect.getHeightXY(TransformSpace.LOCAL) / 2f - height / 2f;
				for (MTComponent d : c.getChildren()) {
					if (d instanceof MTTextArea) {
						MTTextArea ta = (MTTextArea) d;

						ta.setPositionRelativeToParent(new Vector3D(size / 2f,
								ypos + ta.getHeightXY(TransformSpace.LOCAL)
										/ 2f));
						ypos += ta.getHeightXY(TransformSpace.LOCAL);

					}

				}
			} else {
				//Set Fill Color for Pictures
				MTColor fillColor = MTColor.WHITE;
				//fillColor.setAlpha(vss.getOpacity());
				rect.setFillColor(fillColor);
			}

		}
		
		float hypotenuse = (float)((size/2f) / Math.cos(Math.toRadians(30)));
		float gegenkathete = (float) (Math.sin(Math.toRadians(30)) * hypotenuse);
		int currentRow = 0;
		for (List<MTPolygon> lr : layout) {
			int currentColumn = 0;
			for (MTPolygon r : lr) {
				r.setPositionRelativeToParent((new Vector3D(this
						.getVerticesLocal()[0].x
						+ (size / 2f)
						+ (bezel / 2f)
						+ currentColumn++
						* (size + bezel)
						+ (maxPerLine - lr.size()) * (size / 2f + bezel / 2f),
						this.getVerticesLocal()[0].y + (hypotenuse / 2 + bezel / 2f)
								+ currentRow * (hypotenuse + gegenkathete + bezel))));
			}
			currentRow++;
		}

	}
	
	private List<MTPolygon> next(int next) {
		List<MTPolygon> returnValues = new ArrayList<MTPolygon>();
		for (int i = 0; i < next; i++) {
			returnValues.add(menuContents.get(current++));
		}

		return returnValues;
	}

	private float calcTotalHeight(MTComponent[] components) {
		float height = 0;
		for (MTComponent c : components) {
			if (c instanceof MTTextArea)
				height += ((MTTextArea) c).getHeightXY(TransformSpace.LOCAL);
		}

		return height;
	}
	
	private int getNecessaryFontSize(List<MenuItem> strings, float size) {
		int maxNumberCharacters = 0;

		for (MenuItem s : strings) {

			if (s.getType() == MenuItem.TEXT) {
				if (s.getMenuText().contains("\n")) {
					for (String t : s.getMenuText().split("\n")) {

						if (t.length() > maxNumberCharacters)
							maxNumberCharacters = t.length();

					}
				} else {

					if (s.getMenuText().length() > maxNumberCharacters)
						maxNumberCharacters = s.getMenuText().length();

				}
			}
		}

		float spc = size / (float) maxNumberCharacters; // Space Per Character
		int returnValue = (int)(-0.5 + 1.725 * spc); //Determined using Linear Regression
		return returnValue;

	}
	
	private void organizeHexagons() {
		//Distribute the items on the cell rows		
		layout.clear();
		layout.add(new ArrayList<MTPolygon>());
		layout.add(new ArrayList<MTPolygon>());
		layout.add(new ArrayList<MTPolygon>());
		layout.add(new ArrayList<MTPolygon>());
		current = 0;
		switch (menuContents.size()) {
		case 0:
		case -1:
			break;

		case 1:
			layout.get(0).addAll(next(1));
			maxPerLine = 1;
			break;
		case 2:
			layout.get(0).addAll(next(2));
			maxPerLine = 2;
			break;
		case 3:
			layout.get(0).addAll(next(1));
			layout.get(1).addAll(next(2));
			maxPerLine = 2;
			break;
		case 4:
			layout.get(0).addAll(next(1));
			layout.get(1).addAll(next(2));
			layout.get(2).addAll(next(1));
			maxPerLine = 2;
			break;
		case 5:
			layout.get(0).addAll(next(2));
			layout.get(1).addAll(next(3));
			maxPerLine = 3;
			break;
		case 6:
			layout.get(0).addAll(next(1));
			layout.get(1).addAll(next(2));
			layout.get(1).addAll(next(3));
			maxPerLine = 3;
			break;
		case 7:
			layout.get(0).addAll(next(2));
			layout.get(1).addAll(next(3));
			layout.get(2).addAll(next(2));
			maxPerLine = 3;
			break;
		case 8:
			layout.get(0).addAll(next(3));
			layout.get(1).addAll(next(2));
			layout.get(2).addAll(next(3));
			maxPerLine = 3;
			break;
		case 9:
			layout.get(0).addAll(next(2));
			layout.get(1).addAll(next(3));
			layout.get(2).addAll(next(4));
			maxPerLine = 3;
			break;
		case 10:
			layout.get(0).addAll(next(3));
			layout.get(1).addAll(next(4));
			layout.get(2).addAll(next(3));
			maxPerLine = 4;
			break;
		case 11:
			layout.get(0).addAll(next(4));
			layout.get(1).addAll(next(3));
			layout.get(2).addAll(next(4));
			maxPerLine = 4;
			break;
		case 12:
			layout.get(0).addAll(next(3));
			layout.get(1).addAll(next(4));
			layout.get(2).addAll(next(5));
			maxPerLine = 4;
			break;
		case 13:
			layout.get(0).addAll(next(4));
			layout.get(1).addAll(next(5));
			layout.get(2).addAll(next(4));
			maxPerLine = 5;
			break;
		case 14:
			layout.get(0).addAll(next(3));
			layout.get(1).addAll(next(4));
			layout.get(2).addAll(next(3));
			layout.get(3).addAll(next(4));
			maxPerLine = 4;
			break;
		case 15:
			layout.get(0).addAll(next(4));
			layout.get(1).addAll(next(5));
			layout.get(2).addAll(next(6));
			maxPerLine = 5;
			break;
		case 16:
			layout.get(1).addAll(next(5));
			layout.get(2).addAll(next(6));
			layout.get(3).addAll(next(5));
			maxPerLine = 4;
			break;

		}

	}
	
	private MTPolygon getHexagon(float size) {
		//Create a new Polygon
		float hypotenuse = (float)((size/2f) / Math.cos(Math.toRadians(30)));
		float ankathete = (float)(Math.cos(Math.toRadians(30)) * hypotenuse);
		float gegenkathete = (float) (Math.sin(Math.toRadians(30)) * hypotenuse);
		
		Vertex v1 = new Vertex(0, gegenkathete);
		Vertex v2 = new Vertex(size/2f, 0);
		Vertex v3 = new Vertex(size, gegenkathete);
		Vertex v4 = new Vertex(size, gegenkathete + hypotenuse);
		Vertex v5 = new Vertex(size/2f, 2 * gegenkathete + hypotenuse);
		Vertex v6 = new Vertex(0, gegenkathete + hypotenuse);
		Vertex v7 = new Vertex(0, gegenkathete);
		
		
		MTPolygon hexagon = new MTPolygon(app, new Vertex[] {v1,v2,v3,v4,v5,v6,v7});
		
		return hexagon;
	}
	
	public class TapListener implements IGestureEventListener {
		List<PolygonListeners> children;
		public TapListener(List<PolygonListeners> children) {
			this.children = children;
		}
		
		
		@Override
		public boolean processGestureEvent(MTGestureEvent ge) {
			if (ge instanceof TapEvent) {
				TapEvent te = (TapEvent)ge;
				if (te.getTapID() == TapEvent.BUTTON_CLICKED) {
					
					for (PolygonListeners pl: children) {
						
						if (pl.component.getIntersectionGlobal(Tools3D.getCameraPickRay(app, pl.component, te.getLocationOnScreen().getX(), te.getLocationOnScreen().getY())) != null) {
							pl.listener.processGestureEvent(ge);
						}
					}
				}
			}
			return false;
		}
		
		
		
		
	}
	
	public class PolygonListeners {
		public MTPolygon component;
		public IGestureEventListener listener;
		
		public PolygonListeners(MTPolygon component, IGestureEventListener listener) {
			this.component = component;
			this.listener = listener;
		}
		
	}
	
	
}


