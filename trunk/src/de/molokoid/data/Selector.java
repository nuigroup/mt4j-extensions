package de.molokoid.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.mt4j.components.MTCanvas;
import org.mt4j.components.MTComponent;

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
	
	public boolean appliesTo(MTComponent c) {
		List<String> superclasses = getSuperclasses(c.getClass());
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
					if (c.getCSSID() != "" && primary.equalsIgnoreCase(c.getCSSID().replace(" ", ""))) {
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
						return true && containsParent(c,1);
					}
					break;
				case CLASS:
					for (String s: superclasses) {
						if (secondary.equalsIgnoreCase(s.replace(" ", ""))) {
							return true && containsParent(c,1);
						}
					}
						
					break;
				case ID:
					if (c.getCSSID() != "" && secondary.equalsIgnoreCase(c.getCSSID().replace(" ", ""))) {
						return true && containsParent(c,1);
					}
					break;
				case UNIVERSAL:
					return true && containsParent(c,1);
				default:
					break;
				}
			} else {
				//return child.appliesTo(c);
				if (secondary == null && child.secondary == null) {
					switch (child.primaryType) {
					case TYPE:
						String type = superclasses.get(0);
						if (child.primary.equalsIgnoreCase(type.replace(" ", ""))) {
							return true && containsParent(c,1);
						}
						break;
					case CLASS:
						for (String s: superclasses) {
							if (child.primary.equalsIgnoreCase(s.replace(" ", ""))) {
								return true && containsParent(c,1);
							}
						}
							
						break;
					case ID:
						if (c.getCSSID() != "" && child.primary.equalsIgnoreCase(c.getCSSID().replace(" ", ""))) {
							return true && containsParent(c,1);
						}
						break;
					case UNIVERSAL:
						return true && containsParent(c,1);
					default:
						break;
					}
				} else if (secondary == null) {
					switch (child.secondaryType) {
					case TYPE:
						String type = superclasses.get(0);
						if (child.secondary.equalsIgnoreCase(type.replace(" ", ""))) {
							return true && containsParent(c,4);
						}
						break;
					case CLASS:
						for (String s: superclasses) {
							if (child.secondary.equalsIgnoreCase(s.replace(" ", ""))) {
								return true && containsParent(c,4);
							}
						}
							
						break;
					case ID:
						if (c.getCSSID() != "" && child.secondary.equalsIgnoreCase(c.getCSSID().replace(" ", ""))) {
							return true && containsParent(c,4);
						}
						break;
					case UNIVERSAL:
						return true && containsParent(c,4);
					default:
						break;
					}
				} else if (child.secondary == null) {
					switch (child.primaryType) {
					case TYPE:
						String type = superclasses.get(0);
						if (child.primary.equalsIgnoreCase(type.replace(" ", ""))) {
							return true && containsParent(c,2);
						}
						break;
					case CLASS:
						for (String s: superclasses) {
							if (child.primary.equalsIgnoreCase(s.replace(" ", ""))) {
								return true && containsParent(c,2);
							}
						}
							
						break;
					case ID:
						if (c.getCSSID() != "" && child.primary.equalsIgnoreCase(c.getCSSID().replace(" ", ""))) {
							return true && containsParent(c,2);
						}
						break;
					case UNIVERSAL:
						return true && containsParent(c,2);
					default:
						break;
					}
				} else {
					switch (child.secondaryType) {
					case TYPE:
						String type = superclasses.get(0);
						if (child.secondary.equalsIgnoreCase(type.replace(" ", ""))) {
							return true && containsParent(c,3);
						}
						break;
					case CLASS:
						for (String s: superclasses) {
							if (child.secondary.equalsIgnoreCase(s.replace(" ", ""))) {
								return true && containsParent(c,3);
							}
						}
							
						break;
					case ID:
						if (c.getCSSID() != "" && child.secondary.equalsIgnoreCase(c.getCSSID().replace(" ", ""))) {
							return true && containsParent(c,3);
						}
						break;
					case UNIVERSAL:
						return true && containsParent(c,3);
					default:
						break;
					}
				}
	
				
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return false;
	}
	
	private boolean containsParent(MTComponent c, int level) {
		//Level:
		//1: Parent Match
		//2: Grandparent Parent > Match
		//3: Grandgrandparent Grandparent > Parent Match
		//4: Grandparent > Parent Match
		
		try {
			if (c.getParent() != null) {
				switch (level) {
				case 1: 
					return searchLevelsOne(c);		
				
				case 2: 	
					return 	searchLevelsTwo(c);
				case 3:
					return 	searchLevelsThree(c);
				case 4:
					return 	searchLevelsFour(c);
				
				}
				
			}
		} catch (Exception e) {
			System.out.println("Selector not found - not enough levels?");
		}
		
		
		return false;
	}
	
	private boolean searchLevelsOne(MTComponent c) {
		//Search all above levels for instance of Selector
		int numberOfLevels = numberOfLevels(c);
		boolean found = false;
		try {
			if (numberOfLevels > 1) {
				for (int i = 2; i <= numberOfLevels; i++) {
					found = found || isMatch(primaryType, primary, getComponentAtLevel(c,i));				
				}
				
				
			}
		} catch (Exception e) {

		}
			
		return found;
	}
	
	private boolean searchLevelsTwo(MTComponent c) {
		//Search all upper levels for Grandparent, Level 1 = Match, Level 2 = Parent (Parent must be directly over child)
		int numberOfLevels = numberOfLevels(c);
		boolean found = false;
		try {
			if (numberOfLevels > 2) {
				isMatch(secondaryType, secondary, getComponentAtLevel(c,2));
				for (int i = 3; i <= numberOfLevels; i++) {
					found = found || isMatch(primaryType, primary, getComponentAtLevel(c,i));				
				}


			}
		} catch (Exception e) {

		}

		return found;
	}

	private boolean searchLevelsThree(MTComponent c) {
		int numberOfLevels = numberOfLevels(c);
		boolean found = false;

		try {
			if (numberOfLevels > 3) {
				for (int i=3; i<=numberOfLevels-1; i++) {
					for (int j=i+1; j <= numberOfLevels; j++) {
						found = found ||
						(isMatch(primaryType, primary, getComponentAtLevel(c,j)) &&
								isMatch(secondaryType, secondary, getComponentAtLevel(c, i)) &&
								isMatch(child.getPrimaryType(), child.getPrimary(), getComponentAtLevel(c,i-1)));
					}
				}
			}
		} catch (Exception e) {

		}

		return found;

	}
	private boolean searchLevelsFour (MTComponent c) {
		//return 	isMatch(primaryType, primary, c.getParent().getParent()) &&
		//isMatch(child.getPrimaryType(), child.getPrimary(), c.getParent());
		int numberOfLevels = numberOfLevels(c);
		boolean found = false;
		
		if (numberOfLevels > 2) {
			for (int i=3; i <= numberOfLevels; i++) {
				found = found ||
				(isMatch(primaryType, primary, getComponentAtLevel(c,i)) && 
						isMatch(child.getPrimaryType(), child.getPrimary(), getComponentAtLevel(c,i-1)));
				
			}
			
			
		}
		
		
		return found;
	}
	
	private MTComponent getComponentAtLevel(MTComponent c,int level) {
		
		try {
			if (level <= 1) {
				return c;
			} else {
				return getComponentAtLevel(c.getParent(), level-1);
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	
	private int numberOfLevels(MTComponent c) {
		try {
			if (c instanceof MTCanvas || c == null) {
				return 0;
			} else {
				return numberOfLevels(c.getParent()) + 1;
			}
		} catch (Exception e) {
			//TODO: Is this right?	
			return 0;
		}
	}
	
	private boolean isMatch(SelectorType type, String selector, MTComponent c) {
		try {
			if (c != null && c instanceof MTComponent) {
			List<String> superclasses = getSuperclasses(c.getClass());
			switch (type) {
			case TYPE:
				if (superclasses.get(0).replace(" ", "").equalsIgnoreCase(selector)) return true; 
				break;
			case CLASS:
				for (String s: superclasses) {
					if (selector.equalsIgnoreCase(s.replace(" ", ""))) return true;
				}
				break;
			case ID: 
				if (c.getCSSID() != "" && c.getCSSID().replace(" ", "").equalsIgnoreCase(selector)) return true;
				break;
			default:
				System.out.println("WTF? Unknown Type " + type + " with Selector " + selector);
			
			}
			}
		} catch (Exception e) {
			
			System.out.println("Someting went wrong with finding " + selector + " in " + c.getClass().getSimpleName());
		}
		return false;
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
