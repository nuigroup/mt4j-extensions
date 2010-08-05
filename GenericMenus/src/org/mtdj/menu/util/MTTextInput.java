package org.mtdj.menu.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mt4j.MTApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.components.visibleComponents.widgets.MTList;
import org.mt4j.components.visibleComponents.widgets.MTListCell;
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
import processing.core.PGraphics;

public class MTTextInput extends MTForm{
	String accept = "Accept";
	String discard = "Discard";
	
	MTTextArea inputField;
	MTTextArea acceptButton;
	MTTextArea discardButton;
	MTKeyboard keyboard;
	MTTextArea ta;
	String lastText = "";
	MTApplication app;
	int fontsize;
	
	
	
	boolean suggestionsEnabled = false;
	List<String> availableValues = new ArrayList<String>();
	MTList list;
	HashMap<String, MTTextArea> textCache = new HashMap<String,MTTextArea>();
	List<MTTextArea> currentList;
	

	
	
	public MTTextInput(MTTextArea ta, int fontsize,
			MTApplication app) {
		super(0, 0, fontsize * 20, fontsize * 10, app, MTForm.BOOLEAN);
		this.ta = ta;
		this.app = app;
		this.fontsize = fontsize;
		
		
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
		
		inputField.setPositionRelativeToParent(calcPos(inputField, 10,10));
		acceptButton.setPositionRelativeToParent(calcPos(acceptButton,10, 10+ inputField.getHeightXY(TransformSpace.LOCAL) + 10));
		discardButton.setPositionRelativeToParent(calcPos(discardButton, 10 + acceptButton.getWidthXY(TransformSpace.LOCAL) + 10 ,10 + inputField.getHeightXY(TransformSpace.LOCAL) + 10));
			
		lastText = inputField.getText();
		
		adjustSize();
		keyboard.setPositionRelativeToParent(calcPos(keyboard, 0, 30 + inputField.getHeightXY(TransformSpace.LOCAL) + acceptButton.getHeightXY(TransformSpace.LOCAL)));
	}
	
	private void adjustSize() {
		Vertex[] v = this.getVerticesGlobal();
		this.setWidthLocal(Math.max(20 + this.inputField.getWidthXY(TransformSpace.LOCAL), 30 + this.acceptButton.getWidthXY(TransformSpace.LOCAL) + this.discardButton.getWidthXY(TransformSpace.LOCAL)));
		this.setHeightLocal(30 + this.inputField.getHeightXY(TransformSpace.LOCAL) + Math.max(acceptButton.getHeightXY(TransformSpace.LOCAL), discardButton.getHeightXY(TransformSpace.LOCAL)));
		
		
	}
	
	int o = 0;
	
	@Override
	public void drawComponent(PGraphics g) {
		super.drawComponent(g);
		if (o++ > 60) {
			o = 0;
			adjustSize();
		}
		
		
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
	
	public List<String> getRelevantStrings() {
		List<String> newList = new ArrayList<String>();
		if (!availableValues.isEmpty()) {
		String currentText = inputField.getText();
			for (String s: availableValues) {
				if (s.toUpperCase().contains(currentText.toUpperCase())) {
					newList.add(s);
				}
			}
		}
		return newList;
	}
	
	public List<MTTextArea> getFirstTextAreas() {
		List<MTTextArea> tas = new ArrayList<MTTextArea>();
		int i = 0;
		for (String s: getRelevantStrings()) {
			if (i++ < 5) {
				if (textCache.containsKey(s)) {
					tas.add(textCache.get(s));
				} else {
					MTTextArea ta;
					if (this.isCSSStyled() && this.getCssHelper().getVirtualStyleSheet().isModifiedCssfont()) {
						
						CSSFont fullSize = this.getCssHelper().getVirtualStyleSheet().getCssfont().clone(fontsize);
						CSSFontManager cfm = new CSSFontManager(app);
						IFont fullFont = cfm.selectFont(fullSize);
						ta = new MTTextArea(app, fullFont);
						
								
					} else {
						ta = new MTTextArea(app, FontManager.getInstance().createFont(app, "SansSerif.Bold", fontsize, MTColor.WHITE, MTColor.WHITE));
					}
					
					ta.setText(s);
					textCache.put(s, ta);
					tas.add(ta);
					
				}

			} else {
				break;
			}

		}

		return tas;
	}
}
