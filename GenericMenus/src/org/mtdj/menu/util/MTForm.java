package org.mtdj.menu.util;

import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import processing.core.PApplet;

public abstract class MTForm extends MTRectangle{
	private short type = 0;
	public static short BOOLEAN = 1;
	public static short STRING = 2;
	public static short CUSTOM = 3;
	public static short UNDEFINED = 0;
	
	public MTForm(float x, float y, float width, float height, PApplet pApplet, short type) {
		super(x, y, width, height, pApplet);
		this.type = type;
	}
	
	public abstract boolean getBooleanValue();
	
	public abstract void setBooleanValue(boolean value);
	
	public abstract String getStringValue();
	
	public abstract float getNumericValue();
	
	public short getType() {
		return this.type;
	}
	protected void setType(short type) {
		this.type = type;
	}
	
	public class BooleanTapListener implements IGestureEventListener {

		@Override
		public boolean processGestureEvent(MTGestureEvent ge) {
			if (ge instanceof TapEvent) {
				TapEvent te = (TapEvent)ge;
				if (te.getTapID() == TapEvent.BUTTON_CLICKED) {
					setBooleanValue(!getBooleanValue());
				}
				
				
			}
			return false;
		}
		
	}
	
}
