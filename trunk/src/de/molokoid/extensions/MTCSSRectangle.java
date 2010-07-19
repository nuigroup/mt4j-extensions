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
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Tools3D;
import org.mt4j.util.math.Vector3D;
import org.mt4j.util.math.Vertex;
import org.mt4j.util.opengl.GLTexture;
import org.mt4j.util.opengl.GLTextureSettings;
import org.mt4j.util.opengl.GLTexture.EXPANSION_FILTER;
import org.mt4j.util.opengl.GLTexture.SHRINKAGE_FILTER;
import org.mt4j.util.opengl.GLTexture.TEXTURE_TARGET;
import org.mt4j.util.opengl.GLTexture.WRAP_MODE;

import processing.core.PImage;

import de.molokoid.css.CSSHelper;
import de.molokoid.css.CSSParserConnection;
import de.molokoid.data.CSSStyle;
import de.molokoid.data.CSSStyleHierarchy;
import de.molokoid.data.CSSStyleManager;


public class MTCSSRectangle extends MTRectangle implements MTCSSStylable{
	CSSHelper cssh;
	public MTCSSRectangle(float x, float y,	float width, float height, MTApplication mta, CSSStyleManager csm) {
		super(x, y, width, height, mta);
		cssh = new CSSHelper(this, mta, csm);
		

	}
	
	public void applyStyleSheet() {
		cssh.applyStyleSheet();
	}
	
	public MTCSSRectangle(CSSStyle style, float x, float y,	MTApplication mta, CSSStyleManager csm) {
		super(x, y, style.getWidth(), style.getHeight(), mta);
		cssh = new CSSHelper(this, mta, csm);
		cssh.getPrivateStyleSheets().add(style);

	}

}
