package de.molokoid.extensions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.molokoid.data.CSSStyle;
import de.molokoid.data.CSSStyleHierarchy;
import de.molokoid.data.CSSStyleManager;

import org.mt4j.MTApplication;
import org.mt4j.components.visibleComponents.shapes.MTEllipse;
import org.mt4j.util.math.Vector3D;


public class MTCSSEllipse extends MTEllipse implements CSSStylable{
	
	List<CSSStyle> privateStyleSheets = new ArrayList<CSSStyle>();
	List<CSSStyleHierarchy> sheets = new ArrayList<CSSStyleHierarchy>();
	CSSStyle virtualStyleSheet = null;
	CSSStyleManager cssStyleManager;
	MTApplication app;
	
	public MTCSSEllipse(MTApplication mta, Vector3D centerPoint, float radiusX,
			float radiusY, CSSStyleManager csm) {
		super(mta, centerPoint, radiusX, radiusY);

		this.cssStyleManager = csm;
		this.app = mta;
		applyStyleSheet();
		
	}
	
	public MTCSSEllipse(MTApplication mta, Vector3D centerPoint, float radiusX,
			float radiusY, CSSStyleManager csm, CSSStyle style) {
		super(mta, centerPoint, radiusX, radiusY);
		this.privateStyleSheets.add(style);
		this.cssStyleManager = csm;
		this.app = mta;
		applyStyleSheet();
		
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
		this.setHeightXYGlobal(virtualStyleSheet.getHeight());
		this.setWidthXYGlobal(virtualStyleSheet.getWidth());
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
