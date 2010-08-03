package org.mtdj.menu.util;

import org.mt4j.components.visibleComponents.shapes.MTEllipse;
import org.mt4j.css.style.CSSStyle;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;
import org.mtdj.menu.util.MTForm.BooleanTapListener;

import processing.core.PApplet;

public class MTOptionBox extends MTForm implements BooleanForm {
	boolean booleanValue = false;
	MTColor backgroundColor;
	MTColor strokeColor;
	MTEllipse optionBox;
	OptionGroup group;
	
	public MTOptionBox(float size,
			PApplet app, OptionGroup group) {
		super(0, 0, size, size, app, MTForm.BOOLEAN);
		group.addOptionBox(this);
		this.setCssForceDisable(true);
		this.setNoStroke(true);
		this.setNoFill(true);
		this.group = group;
		
		optionBox = new MTEllipse(app, new Vector3D(size/2f,size/2f), size/2f, size/2f);
		optionBox.setCssForceDisable(true);
		this.addChild(optionBox);
		if (this.getCssHelper() != null) {
			CSSStyle vss = this.getCssHelper().virtualStyleSheet;
			if (vss.isModifiedBorderColor()) optionBox.setStrokeColor(vss.getBorderColor());
			else optionBox.setStrokeColor(MTColor.WHITE);
			
			if (vss.isModifiedBackgroundColor()) optionBox.setFillColor(vss.getBackgroundColor());
			else optionBox.setFillColor(MTColor.YELLOW);
			
			if (vss.isModifiedBorderWidth()) optionBox.setStrokeWeight(vss.getBorderWidth());
			else optionBox.setStrokeWeight(2f);
			
			
		} else {
			optionBox.setStrokeColor(MTColor.WHITE);
			optionBox.setFillColor(MTColor.YELLOW);
			optionBox.setStrokeWeight(2f);
		}
		
		optionBox.setPickable(false);
		optionBox.setNoFill(true);
		
		this.setGestureAllowance(TapProcessor.class, true);
		this.registerInputProcessor(new TapProcessor(app));
		this.addGestureListener(TapProcessor.class, new BooleanTapListener());
		
	}

	@Override
	public boolean getBooleanValue() {
		return booleanValue;
	}

	@Override
	public String getStringValue() {
		return String.valueOf(this.getBooleanValue());
	}

	@Override
	public float getNumericValue() {
		if (this.getBooleanValue() == true) return 1;
		else return 0;
	}

	@Override
	public void setBooleanValue(boolean value) {
		this.booleanValue = value;
		if (this.booleanValue == true) {
			optionBox.setNoFill(false);
			if (group != null)
			group.setEnabled(this);
		} else {
			optionBox.setNoFill(true);
		}
		
	}
	
	public void disable() {
		this.booleanValue = false;
		optionBox.setNoFill(true);
	}

}
