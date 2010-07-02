package de.molokoid.extensions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mt4j.MTApplication;
import org.mt4j.components.visibleComponents.shapes.MTLine;
import org.mt4j.util.math.Vector3D;

import de.molokoid.data.CSSStyle;
import de.molokoid.data.CSSStyleHierarchy;
import de.molokoid.data.CSSStyleManager;

import processing.core.PApplet;

public class MTCSSLine extends MTLine implements CSSStylable{
	
	List<CSSStyle> privateStyleSheets = new ArrayList<CSSStyle>();
	List<CSSStyleHierarchy> sheets = new ArrayList<CSSStyleHierarchy>();
	CSSStyle virtualStyleSheet = null;
	CSSStyleManager cssStyleManager;
	MTApplication app;
	
	Vector3D v3d;
	public MTCSSLine(MTApplication mta, float x1, float y1, float x2, float y2, CSSStyleManager csm) {
		super(mta, x1, y1, x2, y2);
		this.app = mta;
		this.cssStyleManager = csm;
		applyStyleSheet();
	}
	
	public MTCSSLine(MTApplication mta, float x1, float y1, float x2, float y2, CSSStyleManager csm, CSSStyle style) {
		super(mta, x1, y1, x2, y2);
		this.app = mta;
		this.cssStyleManager = csm;
		this.privateStyleSheets.add(style);
		applyStyleSheet();
	}
	@Override
	public void applyStyleSheet() {
		evaluateStyleSheets();
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
	@Override
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
	@Override
	public void setStyleSheet(CSSStyle sheet) {
		this.privateStyleSheets.add(sheet);
		
	}
	
}
