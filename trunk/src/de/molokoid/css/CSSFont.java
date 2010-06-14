package de.molokoid.css;

import org.apache.log4j.Logger;

public class CSSFont {
	private fontfamily family = fontfamily.CUSTOM;
	private fontstyle style = fontstyle.NORMAL;
	private String customType = "";
	private fontweight weight = fontweight.NORMAL;


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
	protected enum fontfamily {
		SANS, SERIF, MONO, CUSTOM;
	}
	protected enum fontstyle {
		ITALIC, OBLIQUE, NORMAL;
	}
	protected enum fontweight {
		BOLD, LIGHT, NORMAL;
	}
	private void debugOutput() {
		Logger logger = Logger.getLogger("MT4J Extensions");
		logger.debug("Font Family: " + family + ", Font Style: " + style + ", Font Weight: " + weight + ", Custom TTF Font: " + customType);
	}
}
