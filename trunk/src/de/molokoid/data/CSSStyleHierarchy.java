package de.molokoid.data;

public class CSSStyleHierarchy implements Comparable{
	

	public CSSStyleHierarchy(CSSStyle style) {
		this.style = style;
		this.priority = 0;
		this.type = SelectorType.CUSTOM;
		
	}
	
	public CSSStyleHierarchy(CSSStyle style, SelectorType type) {
		this.style = style;
		this.priority = 0;
		this.type = type;
	}

	int priority;
	CSSStyle style;
	SelectorType type;
	
	public CSSStyleHierarchy(CSSStyle style, int priority) {
		this.style = style;
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}

	public CSSStyle getStyle() {
		return style;
	}

	public void setStyle(CSSStyle style) {
		this.style = style;
	}

	public SelectorType getType() {
		return type;
	}

	public void setType(SelectorType type) {
		this.type = type;
	}

	@Override
	public int compareTo(Object arg0) {
		if (arg0 instanceof CSSStyleHierarchy) {
			CSSStyleHierarchy cs = (CSSStyleHierarchy) arg0;
			if (getValue(cs.getType()) != getValue(this.getType())) {
				if (getValue(this.getType()) < getValue(cs.getType())) {
					return -1;
				} else if (getValue(this.getType()) > getValue(cs.getType())) {
					return 1;
				} else {
					return 0;
				}
			} else {
				if (this.getPriority() < cs.getPriority()) {
					return -1;
				} else if (this.getPriority() > cs.getPriority()) {
					return 1;
				} else {
					return 0;
				}
			}
			
			
		}
		return 0;
	}

	private int getValue(SelectorType type) {
		switch (type) {
		case UNIVERSAL:
			return 1;

		case CLASS:
			return 2;
			
		case TYPE:
			return 3;
			
		case ID:
			return 4;
		
		case CUSTOM:
			return 5;
			
		default:
			return 0;
	
		}

		
		
	}
	
	
}
