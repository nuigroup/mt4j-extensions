package de.molokoid.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.util.MTColor;


import processing.core.PImage;

public class CSSStyle {
	
	String uri = "";
	
	List<Selector> selectors = new ArrayList<Selector>();	
	Selector selector = null;
	//Colours
	
	MTColor backgroundColor = new MTColor(0,0,0,0);
	MTColor color= new MTColor(255,255,255,255);
	MTColor borderColor = new MTColor(255,255,255,255);
	
	//Background Image
	
	PImage backgroundImage = null;
	float[] backgroundPosition = null;
	boolean backgroundRepeat = false;;
	
	//Border
	BorderStyle borderStyle;
	IFont font;
	CSSFont cssfont;
	
	//Sizes
	float width = 0;
	boolean widthPercentage = false;
	float height = 0;
	boolean heightPercentage = false;
	float depth = 0;
	
	float xpos = 0;
	float ypos = 0;
	float zpos = 0;
	
	float borderWidth = 0;
	float paddingWidth = 0; // Graphics and Text only
	
	int fontSize = 16;
	
	//General Properties
	boolean visibility = true;
	float zIndex = 0;
	
	public CSSStyle() {
		this.selector = new Selector("Universal", SelectorType.UNIVERSAL);
	}
	
	public CSSStyle(Selector selector) {
		super();
		this.selector = selector;
	}
	

	public Selector getSelector() {
		return selector;
	}

	public void setSelector(Selector selector) {
		this.selector = selector;
	}

	public CSSFont getCssfont() {
		return cssfont;
	}

	public void setCssfont(CSSFont cssfont) {
		this.cssfont = cssfont;
	}

	public List<Selector> getSelectors() {
		return selectors;
	}
	public void setSelectors(List<Selector> selectors) {
		this.selectors = selectors;
	}
	public MTColor getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(MTColor backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public MTColor getColor() {
		return color;
	}
	public void setColor(MTColor color) {
		this.color = color;
	}
	public MTColor getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(MTColor borderColor) {
		this.borderColor = borderColor;
	}
	public PImage getBackgroundImage() {
		return backgroundImage;
	}
	public void setBackgroundImage(PImage backgroundImage) {
		this.backgroundImage = backgroundImage;
	}
	public float[] getBackgroundPosition() {
		return backgroundPosition;
	}
	public void setBackgroundPosition(float[] backgroundPosition) {
		this.backgroundPosition = backgroundPosition;
	}
	public boolean isBackgroundRepeat() {
		return backgroundRepeat;
	}
	public void setBackgroundRepeat(boolean backgroundRepeat) {
		this.backgroundRepeat = backgroundRepeat;
	}
	public BorderStyle getBorderStyle() {
		return borderStyle;
	}
	public void setBorderStyle(BorderStyle borderStyle) {
		this.borderStyle = borderStyle;
	}
	public IFont getFont() {
		return font;
	}
	public void setFont(IFont font) {
		this.font = font;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getDepth() {
		return depth;
	}
	public void setDepth(float depth) {
		this.depth = depth;
	}
	public float getXpos() {
		return xpos;
	}
	public void setXpos(float xpos) {
		this.xpos = xpos;
	}
	public float getYpos() {
		return ypos;
	}
	public void setYpos(float ypos) {
		this.ypos = ypos;
	}
	public float getZpos() {
		return zpos;
	}
	public void setZpos(float zpos) {
		this.zpos = zpos;
	}
	public float getBorderWidth() {
		return borderWidth;
	}
	public void setBorderWidth(float borderWidth) {
		this.borderWidth = borderWidth;
	}
	public float getPaddingWidth() {
		return paddingWidth;
	}
	public void setPaddingWidth(float paddingWidth) {
		this.paddingWidth = paddingWidth;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public boolean isVisibility() {
		return visibility;
	}
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
	public float getzIndex() {
		return zIndex;
	}
	public void setzIndex(float zIndex) {
		this.zIndex = zIndex;
	}

	public boolean isWidthPercentage() {
		return widthPercentage;
	}

	public void setWidthPercentage(boolean widthPercentage) {
		this.widthPercentage = widthPercentage;
	}

	public boolean isHeightPercentage() {
		return heightPercentage;
	}

	public void setHeightPercentage(boolean heightPercentage) {
		this.heightPercentage = heightPercentage;
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((backgroundColor == null) ? 0 : backgroundColor.hashCode());
		result = prime * result + Arrays.hashCode(backgroundPosition);
		result = prime * result + (backgroundRepeat ? 1231 : 1237);
		result = prime * result
				+ ((borderColor == null) ? 0 : borderColor.hashCode());
		result = prime * result
				+ ((borderStyle == null) ? 0 : borderStyle.hashCode());
		result = prime * result + Float.floatToIntBits(borderWidth);
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((cssfont == null) ? 0 : cssfont.hashCode());
		result = prime * result + Float.floatToIntBits(depth);
		result = prime * result + ((font == null) ? 0 : font.hashCode());
		result = prime * result + fontSize;
		result = prime * result + Float.floatToIntBits(height);
		result = prime * result + (heightPercentage ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(paddingWidth);
		result = prime * result
				+ ((selector == null) ? 0 : selector.hashCode());
		result = prime * result
				+ ((selectors == null) ? 0 : selectors.hashCode());
		result = prime * result + (visibility ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(width);
		result = prime * result + (widthPercentage ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(xpos);
		result = prime * result + Float.floatToIntBits(ypos);
		result = prime * result + Float.floatToIntBits(zIndex);
		result = prime * result + Float.floatToIntBits(zpos);
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
		CSSStyle other = (CSSStyle) obj;
		if (backgroundColor == null) {
			if (other.backgroundColor != null)
				return false;
		} else if (!backgroundColor.equals(other.backgroundColor))
			return false;
		if (!Arrays.equals(backgroundPosition, other.backgroundPosition))
			return false;
		if (backgroundRepeat != other.backgroundRepeat)
			return false;
		if (borderColor == null) {
			if (other.borderColor != null)
				return false;
		} else if (!borderColor.equals(other.borderColor))
			return false;
		if (borderStyle == null) {
			if (other.borderStyle != null)
				return false;
		} else if (!borderStyle.equals(other.borderStyle))
			return false;
		if (Float.floatToIntBits(borderWidth) != Float
				.floatToIntBits(other.borderWidth))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (cssfont == null) {
			if (other.cssfont != null)
				return false;
		} else if (!cssfont.equals(other.cssfont))
			return false;
		if (Float.floatToIntBits(depth) != Float.floatToIntBits(other.depth))
			return false;
		if (font == null) {
			if (other.font != null)
				return false;
		} else if (!font.equals(other.font))
			return false;
		if (fontSize != other.fontSize)
			return false;
		if (Float.floatToIntBits(height) != Float.floatToIntBits(other.height))
			return false;
		if (heightPercentage != other.heightPercentage)
			return false;
		if (Float.floatToIntBits(paddingWidth) != Float
				.floatToIntBits(other.paddingWidth))
			return false;
		if (selector == null) {
			if (other.selector != null)
				return false;
		} else if (!selector.equals(other.selector))
			return false;
		if (selectors == null) {
			if (other.selectors != null)
				return false;
		} else if (!selectors.equals(other.selectors))
			return false;
		if (visibility != other.visibility)
			return false;
		if (Float.floatToIntBits(width) != Float.floatToIntBits(other.width))
			return false;
		if (widthPercentage != other.widthPercentage)
			return false;
		if (Float.floatToIntBits(xpos) != Float.floatToIntBits(other.xpos))
			return false;
		if (Float.floatToIntBits(ypos) != Float.floatToIntBits(other.ypos))
			return false;
		if (Float.floatToIntBits(zIndex) != Float.floatToIntBits(other.zIndex))
			return false;
		if (Float.floatToIntBits(zpos) != Float.floatToIntBits(other.zpos))
			return false;
		return true;
	}
	



	
	
}
