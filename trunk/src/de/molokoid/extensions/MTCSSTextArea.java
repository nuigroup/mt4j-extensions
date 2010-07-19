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

import de.molokoid.css.CSSHelper;
import de.molokoid.data.CSSStyle;
import de.molokoid.data.CSSStyleHierarchy;
import de.molokoid.data.CSSStyleManager;
import org.mt4j.util.*;
import processing.core.PApplet;

public class MTCSSTextArea extends MTTextArea implements MTCSSStylable{

	CSSHelper cssh;
	
	public void applyStyleSheet() {
		cssh.applyStyleSheet();
	}
	
	public MTCSSTextArea(MTApplication mta, IFont font, CSSStyleManager csm) {
		super(mta, font);
		cssh = new CSSHelper(this, mta, csm);
		
	}
	
	public MTCSSTextArea(MTApplication mta, CSSStyleManager csm) {
		super(mta, csm.getDefaultFont(mta));
		cssh = new CSSHelper(this, mta, csm);
		
	}
	
	
	public MTCSSTextArea(MTApplication mta, IFont font, CSSStyleManager csm, CSSStyle style) {
		super(mta, font);
		cssh = new CSSHelper(this, mta, csm, style);
	}


}
