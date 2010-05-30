package de.molokoid.data;

import java.util.ArrayList;
import java.util.List;

import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.util.MTColor;

import processing.core.PImage;

public class CSSStyle {
	
	List<Selector> selectors = new ArrayList<Selector>();	
	Selector selector;
	//Colours
	
	MTColor backgroundColor;
	MTColor color; //Text Color
	MTColor borderColor;
	
	//Background Image
	
	PImage backgroundImage;
	float[] backgroundPosition;
	boolean backgroundRepeat;
	
	//Border
	BorderStyle borderStyle;
	IFont font;
	
	//Sizes
	float width;
	float height;
	float depth;
	
	float xpos;
	float ypos;
	float zpos;
	
	float borderWidth;
	float paddingWidth; // Graphics and Text only
	
	int fontSize;
	
	//General Properties
	boolean visibility;
	float zIndex;
	
	
	
	
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
	
	
}
