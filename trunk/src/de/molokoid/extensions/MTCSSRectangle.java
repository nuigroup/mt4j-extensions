package de.molokoid.extensions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mt4j.MTApplication;
import org.mt4j.components.MTComponent;
import org.mt4j.components.StateChange;
import org.mt4j.components.StateChangeEvent;
import org.mt4j.components.StateChangeListener;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;

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
		this.addStateChangeListener(StateChange.STYLE_CHANGED, new StateChangeListener() {
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
