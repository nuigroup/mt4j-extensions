package org.mtdj.menu.util;

import org.mt4j.components.visibleComponents.widgets.menus.MTHexagonMenu.TapListener;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;

import processing.core.PApplet;

public class MTCheckbox extends MTForm implements BooleanForm{

	boolean booleanValue = false;;	
	MTColor backgroundColor;
	MTColor strokeColor;
	
	public MTCheckbox(float size, PApplet pApplet) {
		super(0, 0, size, size, pApplet, MTForm.BOOLEAN);
		if (this.isCSSStyled() && this.getCssHelper().getVirtualStyleSheet().isModifiedBackgroundColor()) {
			this.backgroundColor = this.getCssHelper().getVirtualStyleSheet().getBackgroundColor();
			if (this.getCssHelper().getVirtualStyleSheet().isModifiedBorderColor()) strokeColor = this.getCssHelper().getVirtualStyleSheet().getBorderColor();
			else strokeColor = MTColor.WHITE;
		} else {
			this.backgroundColor = MTColor.YELLOW;
			this.strokeColor = MTColor.WHITE;
		}
		this.setFillColor(backgroundColor);
		this.setNoFill(true);
		this.setNoStroke(false);
		this.setStrokeWeight(2f);
		
		this.setGestureAllowance(TapProcessor.class, true);
		this.registerInputProcessor(new TapProcessor(pApplet));
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
			this.setNoFill(false);
		} else {
			this.setNoFill(true);
		}
		
	}


}
