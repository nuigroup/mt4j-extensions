package de.molokoid.extensions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mt4j.MTApplication;
import org.mt4j.components.MTComponent;
import org.mt4j.components.StateChange;
import org.mt4j.components.StateChangeEvent;
import org.mt4j.components.StateChangeListener;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Tools3D;
import org.mt4j.util.math.Vector3D;
import org.mt4j.util.math.Vertex;
import org.mt4j.util.opengl.GLTexture;
import org.mt4j.util.opengl.GLTextureSettings;
import org.mt4j.util.opengl.GLTexture.EXPANSION_FILTER;
import org.mt4j.util.opengl.GLTexture.SHRINKAGE_FILTER;
import org.mt4j.util.opengl.GLTexture.TEXTURE_TARGET;
import org.mt4j.util.opengl.GLTexture.WRAP_MODE;

import processing.core.PImage;

import de.molokoid.css.parserConnector;
import de.molokoid.data.CSSStyle;
import de.molokoid.data.CSSStyleHierarchy;
import de.molokoid.data.CSSStyleManager;


public class MTCSSRectangle extends MTRectangle implements CSSStylable{
	
	public MTCSSRectangle(float x, float y,	float width, float height, MTApplication mta, CSSStyleManager csm) {
		super(x, y, width, height, mta);

		this.cssStyleManager = csm;
		this.app = mta;
		
		//applyStyleSheet();
		addListeners();
	}
	
	public MTCSSRectangle(CSSStyle style, float x, float y,	MTApplication mta, CSSStyleManager csm) {
		super(x, y, style.getWidth(), style.getHeight(), mta);
		this.privateStyleSheets.add(style);
		this.cssStyleManager = csm;
		this.app = mta;
		
		//applyStyleSheet();
		addListeners();
	}
	
	public MTCSSRectangle(String uri, float x, float y, MTApplication mta, CSSStyleManager csm) {
		super(x,y,0,0, mta);
		parserConnector pc = new parserConnector(uri, mta);
		privateStyleSheets = pc.getCssh().getStyles();
		this.cssStyleManager = csm;
		this.app = mta;
		
		//applyStyleSheet();
		addListeners();
	}
	
	private void addListeners() {
		this.addStateChangeListener(StateChange.ADDED_TO_PARENT, new StateChangeListener() {
			public void stateChanged(StateChangeEvent evt) {
				applyStyleSheet();
			}
		});

	}
	

	List<CSSStyle> privateStyleSheets = new ArrayList<CSSStyle>();
	List<CSSStyleHierarchy> sheets = new ArrayList<CSSStyleHierarchy>();
	public CSSStyle virtualStyleSheet = null;
	CSSStyleManager cssStyleManager;
	MTApplication app;
	
	public List<CSSStyle> getPrivateStyleSheets() {
		return privateStyleSheets;
	}

	public void setPrivateStyleSheets(List<CSSStyle> privateStyleSheets) {
		this.privateStyleSheets = privateStyleSheets;
	}

	public List<CSSStyleHierarchy> getSheets() {
		return sheets;
	}

	public void setSheets(List<CSSStyleHierarchy> sheets) {
		this.sheets = sheets;
	}
	
	public void setStyleSheet(CSSStyle sheet) {
		this.privateStyleSheets.add(sheet);
	}

	@SuppressWarnings("unchecked")
	public void evaluateStyleSheets() {
		sheets = cssStyleManager.getRelevantStyles(this);
		Collections.sort(sheets);
		virtualStyleSheet = new CSSStyle(app);
		for (CSSStyleHierarchy h: sheets) {
			virtualStyleSheet.addStyleSheet(h.getStyle());
		
		}
		for (CSSStyle s: privateStyleSheets) {
			virtualStyleSheet.addStyleSheet(s);
		}

	}
	

	
	public void applyStyleSheet() {
		evaluateStyleSheets();
		
		if (virtualStyleSheet.isWidthPercentage() && virtualStyleSheet.isHeightPercentage()) {
			if (this.getParent() != null) {
				if (virtualStyleSheet.getWidth() > 0) 
					this.setWidthLocal(virtualStyleSheet.getWidth() / 100f * this.getParent().getBounds().getWidthXY(TransformSpace.RELATIVE_TO_PARENT));
				
				if (virtualStyleSheet.getHeight() > 0)
					this.setHeightLocal(virtualStyleSheet.getHeight() / 100f * this.getParent().getBounds().getHeightXY(TransformSpace.RELATIVE_TO_PARENT));
				

			}
		} else  if (virtualStyleSheet.isWidthPercentage()) {
			if (virtualStyleSheet.getWidth() > 0) 
				this.setWidthLocal(virtualStyleSheet.getWidth() / 100f * this.getParent().getBounds().getWidthXY(TransformSpace.RELATIVE_TO_PARENT));
			
			if (virtualStyleSheet.getHeight() > 0)
				this.setHeightLocal(virtualStyleSheet.getHeight());
		} else if (virtualStyleSheet.isHeightPercentage()) {
			if (virtualStyleSheet.getWidth() > 0) 
				this.setWidthLocal(virtualStyleSheet.getWidth());
			
			if (virtualStyleSheet.getHeight() > 0)
				this.setHeightLocal(virtualStyleSheet.getHeight() / 100f * this.getParent().getBounds().getHeightXY(TransformSpace.RELATIVE_TO_PARENT));

		} else {
			if (virtualStyleSheet.getWidth() > 0) 
				this.setWidthLocal(virtualStyleSheet.getWidth());
			
			if (virtualStyleSheet.getHeight() > 0)
				this.setHeightLocal(virtualStyleSheet.getHeight());
		}
		
		this.setFillColor(virtualStyleSheet.getBackgroundColor());
		this.setStrokeColor(virtualStyleSheet.getBorderColor());
		this.setStrokeWeight(virtualStyleSheet.getBorderWidth());
		this.setVisible(virtualStyleSheet.isVisibility());
		
		if (virtualStyleSheet.getBorderStylePattern() >= 0) {
			this.setNoStroke(false);
			this.setLineStipple(virtualStyleSheet.getBorderStylePattern());
		} else {
			this.setNoStroke(true);
		}
		

	}

	public void tiledBackground (PImage bgImage) {
		boolean pot = Tools3D.isPowerOfTwoDimension(bgImage);
		boolean tiled = true;
		this.setFillColor(MTColor.WHITE);
		if (tiled){
			//Generate texture coordinates to repeat the texture over the whole background (works only with OpenGL)
			float u = (float)this.getBounds().getWidthXY(TransformSpace.LOCAL)/(float)bgImage.width;
			float v = (float)this.getBounds().getHeightXY(TransformSpace.LOCAL)/(float)bgImage.height;
			
			Vector3D[] bV = this.getBounds().getVectorsLocal();
			
			
			Vertex[] backgroundVertices = this.getVerticesLocal();
			
			float minx, miny;
			
			if (backgroundVertices.length > 0) {
				minx = backgroundVertices[0].x;
				miny = backgroundVertices[0].y;
			
			for (Vertex vtx: backgroundVertices) {
				if (vtx.x < minx) minx = vtx.x;
				if (vtx.y < miny) miny = vtx.y;
			}
			
			for (Vertex vtx: backgroundVertices) {
				vtx.setTexCoordU((float)getXDistance(minx, vtx) / (float)bgImage.width);
				vtx.setTexCoordV((float)getYDistance(miny, vtx) / (float)bgImage.height);
			}
			
			}
			
			
/*			backgroundVertices[0].setTexCoordU(0);
			backgroundVertices[0].setTexCoordV(0);
			backgroundVertices[1].setTexCoordU(u);
			backgroundVertices[1].setTexCoordV(0);
			backgroundVertices[2].setTexCoordU(u);
			backgroundVertices[2].setTexCoordV(v);
			backgroundVertices[3].setTexCoordU(0);
			backgroundVertices[3].setTexCoordV(v);*/
			
			System.out.println(getXDistance(backgroundVertices[0].x, backgroundVertices[1]) + " " + getYDistance(backgroundVertices[0].y, backgroundVertices[1]));
			System.out.println(getXDistance(backgroundVertices[0].x, backgroundVertices[2]) + " " + getYDistance(backgroundVertices[0].y, backgroundVertices[2]));
			System.out.println(getXDistance(backgroundVertices[0].x, backgroundVertices[3]) + " " + getYDistance(backgroundVertices[0].y, backgroundVertices[3]));

				
			//Update changed texture coordinates for opengl buffer drawing
			if (MT4jSettings.getInstance().isOpenGlMode())
				this.getGeometryInfo().updateTextureBuffer(this.isUseVBOs());
		}
		
		
		if (MT4jSettings.getInstance().isOpenGlMode()){

			
			GLTextureSettings g = new GLTextureSettings(TEXTURE_TARGET.TEXTURE_2D, SHRINKAGE_FILTER.BilinearNoMipMaps, EXPANSION_FILTER.Bilinear, WRAP_MODE.REPEAT, WRAP_MODE.REPEAT); 
			GLTexture tex;
			if (pot){
				tex = new GLTexture(app, bgImage, g);
			}else{
				if (tiled) {
				g.target = TEXTURE_TARGET.RECTANGULAR;
				g.shrinkFilter = SHRINKAGE_FILTER.Trilinear; //Because NPOT texture with GL_REPEAT isnt supported -> gluBuild2Dmipmapds strechtes the texture to POT size

				tex = new GLTexture(app, bgImage, g);
				} else {
					g.target = TEXTURE_TARGET.RECTANGULAR;

					tex = new GLTexture(app, bgImage, g);
				}
			}
			this.setTexture(tex);
		}else{
			this.setTexture(bgImage);
		}
		

		
		
	}
	
	private float getXDistance(float x, Vertex v2) {
		float distance = x - v2.x;
		if (distance >= 0) return distance;
		else return -distance;
		
		
	}
	private float getYDistance(float y, Vertex v2) {
		float distance = y - v2.y;
		if (distance >= 0) return distance;
		else return -distance;
	}


}
