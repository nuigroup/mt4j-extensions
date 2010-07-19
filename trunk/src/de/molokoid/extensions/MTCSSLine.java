package de.molokoid.extensions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mt4j.MTApplication;
import org.mt4j.components.MTComponent;
import org.mt4j.components.StateChange;
import org.mt4j.components.StateChangeEvent;
import org.mt4j.components.StateChangeListener;
import org.mt4j.components.visibleComponents.shapes.MTLine;
import org.mt4j.util.math.Vector3D;

import de.molokoid.css.CSSHelper;
import de.molokoid.data.CSSStyle;
import de.molokoid.data.CSSStyleHierarchy;
import de.molokoid.data.CSSStyleManager;

import processing.core.PApplet;

public class MTCSSLine extends MTLine implements MTCSSStylable{
	
	CSSHelper cssh;
	
	public void applyStyleSheet() {
		cssh.applyStyleSheet();
	}
	public MTCSSLine(MTApplication mta, float x1, float y1, float x2, float y2, CSSStyleManager csm) {
		super(mta, x1, y1, x2, y2);
		cssh = new CSSHelper(this, mta, csm);
	}
	
	public MTCSSLine(MTApplication mta, float x1, float y1, float x2, float y2, CSSStyleManager csm, CSSStyle style) {
		super(mta, x1, y1, x2, y2);
		cssh = new CSSHelper(this, mta, csm, style);;
	}
	

	
}
