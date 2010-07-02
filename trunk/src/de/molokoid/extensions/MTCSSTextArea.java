package de.molokoid.extensions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mt4j.MTApplication;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;

import de.molokoid.data.CSSStyle;
import de.molokoid.data.CSSStyleHierarchy;
import de.molokoid.data.CSSStyleManager;

import processing.core.PApplet;

public class MTCSSTextArea extends MTTextArea implements CSSStylable{

	public MTCSSTextArea(MTApplication mta, IFont font, CSSStyleManager csm) {
		super(mta, font);
		this.cssStyleManager = csm;
		this.app = mta;
		applyStyleSheet();
	}
	
	public MTCSSTextArea(MTApplication mta, IFont font, CSSStyleManager csm, CSSStyle style) {
		super(mta, font);
		this.cssStyleManager = csm;
		this.app = mta;
		this.privateStyleSheets.add(style);
		applyStyleSheet();
	}
	
	List<CSSStyle> privateStyleSheets = new ArrayList<CSSStyle>();
	List<CSSStyleHierarchy> sheets = new ArrayList<CSSStyleHierarchy>();
	CSSStyle virtualStyleSheet = null;
	CSSStyleManager cssStyleManager;
	MTApplication app;
	
	
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
		this.setSizeLocal(virtualStyleSheet.getWidth(), virtualStyleSheet.getHeight());
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
}
