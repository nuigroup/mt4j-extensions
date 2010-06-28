package de.molokoid.extensions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mt4j.MTApplication;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;

import de.molokoid.css.parserConnector;
import de.molokoid.data.CSSStyle;
import de.molokoid.data.CSSStyleHierarchy;

import processing.core.PApplet;

public class MTCSSRectangle extends MTRectangle {
	
	List<CSSStyle> privateStyleSheets = new ArrayList<CSSStyle>();
	List<CSSStyleHierarchy> sheets = new ArrayList<CSSStyleHierarchy>();
	CSSStyle virtualStyleSheet = null;
	
	public MTCSSRectangle(CSSStyle style, float x, float y,	MTApplication mta) {
		super(x, y, style.getWidth(), style.getHeight(), mta);
		this.privateStyleSheets.add(style);
		applyStylesheet();
	}
	
	public MTCSSRectangle(String uri, float x, float y, MTApplication mta) {
		super(x,y,0,0, mta);
		parserConnector pc = new parserConnector(uri, mta);
		privateStyleSheets = pc.getCssh().getStyles();
		applyStylesheet();
		
		
		
	}
	
	
	


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
	public void evaluateStylesheets() {
		virtualStyleSheet = new CSSStyle();
		Collections.sort(sheets);
		for (CSSStyleHierarchy h: sheets) {
			addStyleSheet(h.getStyle());
		}
		for (CSSStyle s: privateStyleSheets) {
			addStyleSheet(s);
		}

	}
	
	private void addStyleSheet(CSSStyle sheet) {
		
	}
	
	public void applyStylesheet() {
		evaluateStylesheets();
		this.setSizeLocal(virtualStyleSheet.getWidth(), virtualStyleSheet.getHeight());
		this.setFillColor(virtualStyleSheet.getBackgroundColor());
		this.setStrokeColor(virtualStyleSheet.getBorderColor());
		this.setStrokeWeight(virtualStyleSheet.getBorderWidth());
		this.setVisible(virtualStyleSheet.isVisibility());
	}

}
