package de.molokoid.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.mt4j.components.MTComponent;
import org.mt4j.components.visibleComponents.shapes.AbstractShape;

public class CSSStyleManager {
	public CSSStyleManager(List<CSSStyle> styles) {
		for (CSSStyle s: styles) {
			this.styles.add(new CSSStyleHierarchy(s));
		}
	}
	
	List<CSSStyleHierarchy> styles = new ArrayList<CSSStyleHierarchy>();

	public List<CSSStyleHierarchy> getStyles() {
		return styles;
	}

	public void setStyles(List<CSSStyleHierarchy> styles) {
		this.styles = styles;
	}
	
	public void addStyle(CSSStyle style) {
		this.styles.add(new CSSStyleHierarchy(style));
	}
	public void addStyle(CSSStyle style, int priority) {
		this.styles.add(new CSSStyleHierarchy(style, priority));
	}
	public void removeStyle(CSSStyle style) {
		this.styles.remove(style);
	}
	
	public List<CSSStyleHierarchy> getRelevantStyles(MTComponent c) {
		List<CSSStyleHierarchy> relevantStyles = new ArrayList<CSSStyleHierarchy>();
		List<String> superClasses = getSuperclasses(c.getClass());
	
		
		for (CSSStyleHierarchy s: styles) {
			int temp = s.getStyle().getSelector().appliesTo(c);
			if (temp != 0)
			Logger.getLogger("MT4J Extensions").debug("Relevant Style? " + temp + " (" + s.getStyle().getSelector() + ")");
			if (temp != 0) {
				relevantStyles.add(new CSSStyleHierarchy(s, temp % 100, (short)(temp / 100)));
			}
			
			
		}
		return relevantStyles;
	}
	

	private List<String> getSuperclasses(Class c) {
		List<String> superclasses = new ArrayList<String>();
		superclasses.add(c.getSimpleName().toUpperCase().replace(" ", ""));
		while (c.getSuperclass() != null) {
			c = c.getSuperclass();
			superclasses.add(c.getSimpleName().toUpperCase().replace(" ", ""));
		}
		
		
		return superclasses;
	}
	
}
