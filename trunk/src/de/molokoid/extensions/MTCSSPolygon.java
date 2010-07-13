package de.molokoid.extensions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mt4j.MTApplication;
import org.mt4j.components.MTComponent;
import org.mt4j.components.StateChange;
import org.mt4j.components.StateChangeEvent;
import org.mt4j.components.StateChangeListener;
import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.util.math.Vertex;

import de.molokoid.data.CSSStyle;
import de.molokoid.data.CSSStyleHierarchy;
import de.molokoid.data.CSSStyleManager;

import processing.core.PApplet;

public class MTCSSPolygon extends MTPolygon implements CSSStylable{

	public MTCSSPolygon(MTApplication mta, Vertex[] vertices, CSSStyleManager csm) {
		super(mta, vertices);
		this.app = mta;
		this.cssStyleManager = csm;
		//applyStyleSheet();
		addListeners();
	}
	
	public MTCSSPolygon(MTApplication mta, Vertex[] vertices, CSSStyle style, CSSStyleManager csm) {
		super(mta, vertices);
		this.app = mta;
		this.cssStyleManager = csm;
		this.privateStyleSheets.add(style);
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
	CSSStyle virtualStyleSheet = null;
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
		
		this.setFillColor(virtualStyleSheet.getBackgroundColor());
		this.setStrokeColor(virtualStyleSheet.getBorderColor());
		this.setStrokeWeight(virtualStyleSheet.getBorderWidth());
		this.setVisible(virtualStyleSheet.isVisibility());
		
		if (virtualStyleSheet.getBorderStylePattern() >= 0) {
			this.setLineStipple(virtualStyleSheet.getBorderStylePattern());
		} else {
			this.setNoStroke(true);
		}
		for (MTComponent c: this.getChildren()) {
			if (c instanceof CSSStylable) {
				CSSStylable s = (CSSStylable)c;
				s.applyStyleSheet();
			}
		}
		
	}
}
