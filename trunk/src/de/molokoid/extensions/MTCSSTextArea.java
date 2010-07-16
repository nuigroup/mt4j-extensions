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
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;

import de.molokoid.data.CSSStyle;
import de.molokoid.data.CSSStyleHierarchy;
import de.molokoid.data.CSSStyleManager;
import org.mt4j.util.*;
import processing.core.PApplet;

public class MTCSSTextArea extends MTTextArea implements CSSStylable{

	
	
	public MTCSSTextArea(MTApplication mta, IFont font, CSSStyleManager csm) {
		super(mta, font);
		this.cssStyleManager = csm;
		this.app = mta;
		//applyStyleSheet();
		addListeners();
		
	}
	
	public MTCSSTextArea(MTApplication mta, CSSStyleManager csm) {
		super(mta, csm.getDefaultFont(mta));
		this.cssStyleManager = csm;
		this.app = mta;
		//applyStyleSheet();
		addListeners();
		
	}
	
	
	public MTCSSTextArea(MTApplication mta, IFont font, CSSStyleManager csm, CSSStyle style) {
		super(mta, font);
		this.cssStyleManager = csm;
		this.app = mta;
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
					this.setWidthXYRelativeToParent(virtualStyleSheet.getWidth() / 100f * this.getParent().getBounds().getWidthXY(TransformSpace.RELATIVE_TO_PARENT));
				
				if (virtualStyleSheet.getHeight() > 0)
					this.setHeightXYRelativeToParent(virtualStyleSheet.getHeight() / 100f * this.getParent().getBounds().getHeightXY(TransformSpace.RELATIVE_TO_PARENT));
				

			}
		} else  if (virtualStyleSheet.isWidthPercentage()) {
			if (virtualStyleSheet.getWidth() > 0) 
				this.setWidthXYRelativeToParent(virtualStyleSheet.getWidth() / 100f * this.getParent().getBounds().getWidthXY(TransformSpace.RELATIVE_TO_PARENT));
			
			if (virtualStyleSheet.getHeight() > 0)
				this.setHeightXYRelativeToParent(virtualStyleSheet.getHeight());
		} else if (virtualStyleSheet.isHeightPercentage()) {
			if (virtualStyleSheet.getWidth() > 0) 
				this.setWidthXYRelativeToParent(virtualStyleSheet.getWidth());
			
			if (virtualStyleSheet.getHeight() > 0)
				this.setHeightXYRelativeToParent(virtualStyleSheet.getHeight() / 100f * this.getParent().getBounds().getHeightXY(TransformSpace.RELATIVE_TO_PARENT));

		} else {
			if (virtualStyleSheet.getWidth() > 0) 
				this.setWidthXYRelativeToParent(virtualStyleSheet.getWidth());
			
			if (virtualStyleSheet.getHeight() > 0)
				this.setHeightXYRelativeToParent(virtualStyleSheet.getHeight());
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
		if (!virtualStyleSheet.getFont().equals(cssStyleManager.getDefaultFont(app))) {
			this.setFont(virtualStyleSheet.getFont());
		}

	}
}
