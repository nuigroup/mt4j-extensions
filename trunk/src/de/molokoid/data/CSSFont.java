package de.molokoid.data;

import org.apache.log4j.Logger;
import org.mt4j.util.MTColor;

public class CSSFont {
	private fontfamily family = fontfamily.CUSTOM;
	private fontstyle style = fontstyle.NORMAL;
	private String customType = "";
	private fontweight weight = fontweight.NORMAL;
	private int fontsize = 16;
	private MTColor color = new MTColor(255, 255, 255, 255);
	private boolean modified = false;

	public CSSFont(MTColor color) {
		super();
		this.color = color;
	}
	public CSSFont() {
		super();
		this.family = fontfamily.DEFAULT;
	}
	
	public CSSFont(int fontsize) {
		super();
		this.fontsize = fontsize;
		this.modified = true;
	}

	public CSSFont(String customType) {
		super();
		this.customType = customType;
		this.modified = true;
		debugOutput();
	}

	public CSSFont(fontstyle style) {
		super();
		this.style = style;
		this.modified = true;
		debugOutput();
	}

	public CSSFont(fontfamily family) {
		super();
		this.family = family;
		debugOutput();
	}

	public CSSFont(fontweight weight) {
		super();
		this.weight = weight;
		this.modified = true;
		debugOutput();
	}

	public MTColor getColor() {
		return color;
	}

	public void setColor(MTColor color) {
		this.color = color;
		this.modified = true;
	}

	public int getFontsize() {
		return fontsize;

	}

	public void setFontsize(int fontsize) {
		this.fontsize = fontsize;
		this.modified = true;
		debugOutput();
	}

	public fontweight getWeight() {
		return weight;
	}

	public void setWeight(fontweight weight) {
		this.weight = weight;
		this.modified = true;
		debugOutput();
	}

	public fontfamily getFamily() {
		return family;
	}

	public void setFamily(fontfamily family) {
		this.family = family;
		this.modified = true;
		debugOutput();
	}

	public fontstyle getStyle() {
		return style;
	}

	public void setStyle(fontstyle style) {
		this.style = style;
		this.modified = true;
		debugOutput();
	}

	public String getCustomType() {
		return customType;
	}

	public void setCustomType(String customType) {
		this.customType = customType;
		this.modified = true;
		debugOutput();
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}
	public boolean isModified() {
		return modified;
	}
	private void debugOutput() {
		/*Logger logger = Logger.getLogger("MT4J Extensions");
		logger.debug("Font Family: " + family + ", Font Style: " + style
				+ ", Font Weight: " + weight + ", Font Size: " + fontsize
				+ ", Custom TTF Font: " + customType);*/
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result
				+ ((customType == null) ? 0 : customType.hashCode());
		result = prime * result + ((family == null) ? 0 : family.hashCode());
		result = prime * result + fontsize;
		result = prime * result + ((style == null) ? 0 : style.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		CSSFont other = (CSSFont) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (customType == null) {
			if (other.customType != null)
				return false;
		} else if (!customType.equals(other.customType))
			return false;
		if (family == null) {
			if (other.family != null)
				return false;
		} else if (!family.equals(other.family))
			return false;
		if (fontsize != other.fontsize)
			return false;
		if (style == null) {
			if (other.style != null)
				return false;
		} else if (!style.equals(other.style))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}
	
}
