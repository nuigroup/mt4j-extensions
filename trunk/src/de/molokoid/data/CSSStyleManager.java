package de.molokoid.data;

import java.util.ArrayList;
import java.util.List;

import org.mt4j.components.MTComponent;
import org.mt4j.components.visibleComponents.shapes.AbstractShape;

public class CSSStyleManager {
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
			if (s.getStyle().getSelector().appliesTo(c)) {
				relevantStyles.add(s);
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
