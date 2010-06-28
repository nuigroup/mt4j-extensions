package de.molokoid.data;

import java.util.List;

import org.apache.log4j.Logger;

public class Selector {
	String primary = null;
	SelectorType primaryType = null;
	
	String secondary = null;
	SelectorType secondaryType = null;
	
	
	
	public Selector(String primary, SelectorType primaryType) {
		super();
		this.primary = check(primary);
		this.primaryType = primaryType;
	}

	boolean selectChild = false;

	Selector child = null;
	
	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = check(primary);
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
		this.secondary = check(secondary);
	}

	public void setSecondary(String secondary, SelectorType type) {
		this.secondary = check(secondary);
		this.secondaryType = type;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((child == null) ? 0 : child.hashCode());
		result = prime * result + ((primary == null) ? 0 : primary.hashCode());
		result = prime * result
				+ ((primaryType == null) ? 0 : primaryType.hashCode());
		result = prime * result
				+ ((secondary == null) ? 0 : secondary.hashCode());
		result = prime * result
				+ ((secondaryType == null) ? 0 : secondaryType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Selector other = (Selector) obj;
		if (child == null) {
			if (other.child != null)
				return false;
		} else if (!child.equals(other.child))
			return false;
		if (primary == null) {
			if (other.primary != null)
				return false;
		} else if (!primary.equalsIgnoreCase(other.primary))
			return false;
		if (primaryType == null) {
			if (other.primaryType != null)
				return false;
		} else if (!primaryType.equals(other.primaryType))
			return false;
		if (secondary == null) {
			if (other.secondary != null)
				return false;
		} else if (!secondary.equalsIgnoreCase(other.secondary))
			return false;
		if (secondaryType == null) {
			if (other.secondaryType != null)
				return false;
		} else if (!secondaryType.equals(other.secondaryType))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		String childString = "";
		if (child != null) childString = " Child: " + child.toString();
		if (primary != null) {
			if (secondary != null) {
				return "Primary Selector: " + primary +
				" (" + primaryType + "), Secondary Selector: " + secondary + " (" + secondaryType + ")" + childString;
			} else {
				return "Primary Selector: " + primary +
				" (" + primaryType + ")" + childString;
			}
		}
		return "No Selector";
	}
	private String check(String input) {
		return input.replace(" ", "").replace(".", "").replace("#", "");
	}
	
	public boolean appliesTo(List<String> superclasses, String ID) {
		
		try {
			if (secondary == null && child == null) {
				switch (primaryType) {
				case TYPE:
					String type = superclasses.get(0);
					if (primary.equalsIgnoreCase(type.replace(" ", ""))) {
						return true;
					}
					break;
				case CLASS:
					for (String s: superclasses) {
						if (primary.equalsIgnoreCase(s.replace(" ", ""))) {
							return true;
						}
					}
						
					break;
				case ID:
					if (ID != "" && primary.equalsIgnoreCase(ID.replace(" ", ""))) {
						return true;
					}
					break;
				case UNIVERSAL:
					return true;
				default:
					break;
				}
					
			} else if (child == null) {
				switch (secondaryType) {
				case TYPE:
					String type = superclasses.get(0);
					if (secondary.equalsIgnoreCase(type.replace(" ", ""))) {
						return true;
					}
					break;
				case CLASS:
					for (String s: superclasses) {
						if (secondary.equalsIgnoreCase(s.replace(" ", ""))) {
							return true;
						}
					}
						
					break;
				case ID:
					if (ID != "" && secondary.equalsIgnoreCase(ID.replace(" ", ""))) {
						return true;
					}
					break;

				default:
					break;
				}
			} else {
				return child.appliesTo(superclasses, ID);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return false;
	}
	
}
