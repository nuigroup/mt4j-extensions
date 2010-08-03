package org.mtdj.menu.util;

import org.mt4j.MTApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.components.visibleComponents.widgets.keyboard.MTKeyboard;
import org.mt4j.css.style.CSSFont;
import org.mt4j.css.util.CSSFontManager;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;
import org.mt4j.util.math.Vertex;
import org.mtdj.menu.util.MTForm.BooleanTapListener;

import processing.core.PApplet;

public class MTTextInput extends MTForm{
	String accept = "Accept";
	String discard = "Discard";
	
	MTTextArea inputField;
	MTTextArea acceptButton;
	MTTextArea discardButton;
	MTKeyboard keyboard;
	MTTextArea ta;
	
	public MTTextInput(MTTextArea ta, int fontsize,
			MTApplication app) {
		super(0, 0, fontsize * 20, fontsize * 10, app, MTForm.BOOLEAN);
		this.ta = ta;
		
		
		
		if (this.isCSSStyled() && this.getCssHelper().getVirtualStyleSheet().isModifiedCssfont()) {
			CSSFont fullSize = this.getCssHelper().getVirtualStyleSheet().getCssfont().clone(fontsize);
			CSSFont minorSize = this.getCssHelper().getVirtualStyleSheet().getCssfont().clone((int)((float)fontsize*0.8));
			CSSFontManager cfm = new CSSFontManager(app);
			IFont fullFont = cfm.selectFont(fullSize);
			IFont minorFont = cfm.selectFont(minorSize);
			inputField = new MTTextArea(app, fullFont);
			inputField.setText(ta.getText());
			acceptButton = new MTTextArea(app, minorFont);
			acceptButton.setText(accept);
			discardButton = new MTTextArea(app, minorFont);
			discardButton.setText(discard);

			
		} else {
		
		inputField = new MTTextArea(app, FontManager.getInstance().createFont(app, "SansSerif.Bold", fontsize, MTColor.WHITE, MTColor.WHITE));
		
		inputField.setText(ta.getText());
		acceptButton = new MTTextArea(app, FontManager.getInstance().createFont(app, "SansSerif.Bold", (int)((float)fontsize * 0.8), MTColor.WHITE, MTColor.WHITE));
		acceptButton.setText(accept);
		discardButton = new MTTextArea(app, FontManager.getInstance().createFont(app, "SansSerif.Bold", (int)((float)fontsize * 0.8), MTColor.WHITE, MTColor.WHITE));
		discardButton.setText(discard);
		}
		
		keyboard = new MTKeyboard(app);
		keyboard.addTextInputListener(inputField);
		this.addChild(keyboard);
		
		discardButton.removeAllGestureEventListeners(DragProcessor.class);
		inputField.removeAllGestureEventListeners(DragProcessor.class);
		acceptButton.removeAllGestureEventListeners(DragProcessor.class);
		
		
		inputField.setNoStroke(true);
		discardButton.setNoFill(true);
		acceptButton.setNoFill(true);
		inputField.setNoFill(true);
		acceptButton.setStrokeColor(this.getCssHelper().getVirtualStyleSheet().getBorderColor());
		discardButton.setStrokeColor(this.getCssHelper().getVirtualStyleSheet().getBorderColor());
		
		inputField.setCssForceDisable(true);
		acceptButton.setCssForceDisable(true);
		discardButton.setCssForceDisable(true);
		
		
		acceptButton.setGestureAllowance(TapProcessor.class, true);
		acceptButton.registerInputProcessor(new TapProcessor(app));
		acceptButton.addGestureListener(TapProcessor.class, new AcceptListener());
		
		discardButton.setGestureAllowance(TapProcessor.class, true);
		discardButton.registerInputProcessor(new TapProcessor(app));
		discardButton.addGestureListener(TapProcessor.class, new DiscardListener());
		
		this.addChild(inputField);
		this.addChild(acceptButton);
		this.addChild(discardButton);
		
		inputField.setPositionRelativeToParent(calcPos(inputField, 5,5));
		acceptButton.setPositionRelativeToParent(calcPos(acceptButton,5, 5+ inputField.getHeightXY(TransformSpace.LOCAL) + 5));
		discardButton.setPositionRelativeToParent(calcPos(discardButton, 5 + acceptButton.getWidthXY(TransformSpace.LOCAL) + 5 ,5 + inputField.getHeightXY(TransformSpace.LOCAL) + 5));
		
		
		
		adjustSize();
		keyboard.setPositionRelativeToParent(calcPos(keyboard, 0, 15 + inputField.getHeightXY(TransformSpace.LOCAL) + acceptButton.getHeightXY(TransformSpace.LOCAL)));
	}
	
	private void adjustSize() {
		Vertex[] v = this.getVerticesGlobal();
		this.setWidthLocal(Math.max(10 + this.inputField.getWidthXY(TransformSpace.LOCAL), 15 + this.acceptButton.getWidthXY(TransformSpace.LOCAL) + this.discardButton.getWidthXY(TransformSpace.LOCAL)));
		this.setHeightLocal(15 + this.inputField.getHeightXY(TransformSpace.LOCAL) + Math.max(acceptButton.getHeightXY(TransformSpace.LOCAL), discardButton.getHeightXY(TransformSpace.LOCAL)));
		
		
	}

	
	
	private Vector3D calcPos(MTPolygon ta, float xo, float yo) {

		return new Vector3D((ta.getWidthXYVectLocal().length() / 2)
				+ this.getVerticesLocal()[0].getX() + xo, (ta
				.getHeightXYVectLocal().length() / 2)
				+ this.getVerticesLocal()[0].getY() + yo);
	}
	
	
	@Override
	public boolean getBooleanValue() {
		if (this.inputField.getText() != "") return true;
		return false;
	}

	@Override
	public void setBooleanValue(boolean value) {

	}

	@Override
	public String getStringValue() {
		return this.inputField.getText();
	}

	@Override
	public float getNumericValue() {
		return this.inputField.getText().length();
	}
	
	public class AcceptListener implements IGestureEventListener {

		@Override
		public boolean processGestureEvent(MTGestureEvent ge) {
			if (ge instanceof TapEvent) {
				TapEvent te = (TapEvent)ge;
				if (te.getTapID() == TapEvent.BUTTON_CLICKED) {
					if (ta != null && inputField != null)
					ta.setText(inputField.getText());
					destroy();
				}
			}
			return false;
		}
		
	}
	
	public class DiscardListener implements IGestureEventListener {

		@Override
		public boolean processGestureEvent(MTGestureEvent ge) {
			if (ge instanceof TapEvent) {
				TapEvent te = (TapEvent)ge;
				if (te.getTapID() == TapEvent.BUTTON_CLICKED) {
					destroy();
				}
			}
			return false;
		}
		
	}

}
