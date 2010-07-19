package de.molokoid.extensions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.molokoid.css.CSSHelper;
import de.molokoid.data.CSSStyle;
import de.molokoid.data.CSSStyleHierarchy;
import de.molokoid.data.CSSStyleManager;

import org.mt4j.MTApplication;
import org.mt4j.components.MTComponent;
import org.mt4j.components.StateChange;
import org.mt4j.components.StateChangeEvent;
import org.mt4j.components.StateChangeListener;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTEllipse;
import org.mt4j.util.math.Vector3D;


public class MTCSSEllipse extends MTEllipse implements MTCSSStylable{
	
	CSSHelper cssh;
	
	public void applyStyleSheet() {
		cssh.applyStyleSheet();
	}
	
	public MTCSSEllipse(MTApplication mta, Vector3D centerPoint, float radiusX,
			float radiusY, CSSStyleManager csm) {
		super(mta, centerPoint, radiusX, radiusY);

		cssh = new CSSHelper(this, mta, csm);
		
	}
	
	public MTCSSEllipse(MTApplication mta, Vector3D centerPoint, float radiusX,
			float radiusY, CSSStyleManager csm, CSSStyle style) {
		super(mta, centerPoint, radiusX, radiusY);
		
		cssh = new CSSHelper(this, mta, csm, style);
		
	}
	


}
