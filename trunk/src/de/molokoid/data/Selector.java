package de.molokoid.data;

public class Selector {
	String primary = null;
	SelectorType primaryType = null;
	
	String secondary = null;
	SelectorType secondaryType = null;
	
	
	
	public Selector(String primary, SelectorType primaryType) {
		super();
		this.primary = primary;
		this.primaryType = primaryType;
	}

	boolean selectChild = false;

	Selector child = null;
	
	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

	public SelectorType getPrimaryType() {
		return primaryType;
	}

	public void setPrimaryType(SelectorType primaryType) {
		this.primaryType = primaryType;
	}

	public String getSecondary() {
		return secondary;
	}

	public void setSecondary(String secondary) {
		this.secondary = secondary;
	}

	public SelectorType getSecondaryType() {
		return secondaryType;
	}

	public void setSecondaryType(SelectorType secondaryType) {
		this.secondaryType = secondaryType;
	}

	public boolean isSelectChild() {
		return selectChild;
	}

	public void setSelectChild(boolean selectChild) {
		this.selectChild = selectChild;
	}

	public Selector getChild() {
		if (selectChild) return child;
		else return null;
	}

	public void setChild(Selector child) {
		if (child != null) {
		this.selectChild = true;
		this.child = child;
		} else {
			this.selectChild = false;
			this.child = null;
		}
	}
	
}
