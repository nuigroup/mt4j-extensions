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

	public CSSFont(MTColor color) {
		super();
		this.color = color;
	}

	public CSSFont(int fontsize) {
		super();
		this.fontsize = fontsize;
	}

	public CSSFont(String customType) {
		super();
		this.customType = customType;
		debugOutput();
	}

	public CSSFont(fontstyle style) {
		super();
		this.style = style;
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
		debugOutput();
	}

	public MTColor getColor() {
		return color;
	}

	public void setColor(MTColor color) {
		this.color = color;
	}

	public int getFontsize() {
		return fontsize;

	}

	public void setFontsize(int fontsize) {
		this.fontsize = fontsize;
		debugOutput();
	}

	public fontweight getWeight() {
		return weight;
	}

	public void setWeight(fontweight weight) {
		this.weight = weight;
		debugOutput();
	}

	public fontfamily getFamily() {
		return family;
	}

	public void setFamily(fontfamily family) {
		this.family = family;
		debugOutput();
	}

	public fontstyle getStyle() {
		return style;
	}

	public void setStyle(fontstyle style) {
		this.style = style;
		debugOutput();
	}

	public String getCustomType() {
		return customType;
	}

	public void setCustomType(String customType) {
		this.customType = customType;
		debugOutput();
	}

	

	private void debugOutput() {
		Logger logger = Logger.getLogger("MT4J Extensions");
		logger.debug("Font Family: " + family + ", Font Style: " + style
				+ ", Font Weight: " + weight + ", Font Size: " + fontsize
				+ ", Custom TTF Font: " + customType);
	}
}
