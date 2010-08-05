package org.mtdj.menu.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mt4j.MTApplication;
import org.mt4j.components.StateChange;
import org.mt4j.components.StateChangeEvent;
import org.mt4j.components.StateChangeListener;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.components.visibleComponents.widgets.MTTextField;
import org.mt4j.components.visibleComponents.widgets.keyboard.MTKeyboard;
import org.mt4j.css.style.CSSFont;
import org.mt4j.css.util.CSSFontManager;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Tools3D;
import org.mt4j.util.math.Vector3D;
import org.mtdj.menu.util.MTTextInput.AcceptListener;

import processing.core.PGraphics;

public class MTSuggestionTextArea extends MTTextArea{
	
	List<String> availableValues;
	MTApplication app;
	HashMap<String, MTTextArea> textCache = new HashMap<String,MTTextArea>();
	MTTextArea suggestionBox;
	MTKeyboard keyboard;
	float width;
	List<String> currentSuggestions = new ArrayList<String>();
	
	public MTSuggestionTextArea(MTApplication app, float width, List<String> suggestions) {
		super(app);
		this.width = width;
		this.setWidthLocal(width);
		this.availableValues = suggestions;
		this.app = app;
		
		this.setGestureAllowance(TapProcessor.class, true);
		this.registerInputProcessor(new TapProcessor(app));
		this.addGestureListener(TapProcessor.class, new EditListener(this));
		
		suggestionBox = new MTTextArea(app, this.getFont());
		suggestionBox.setWidthLocal(width);
		suggestionBox.setCssForceDisable(true);
		suggestionBox.setNoFill(this.isNoFill());
		suggestionBox.setNoStroke(this.isNoStroke());
		suggestionBox.setFillColor(this.getFillColor());
		suggestionBox.setStrokeColor(new MTColor(this.getStrokeColor().getR(), this.getStrokeColor().getG(),this.getStrokeColor().getG(),this.getStrokeColor().getAlpha() * 0.75f));
		suggestionBox.setStrokeWeight(0.25f);
		suggestionBox.removeAllGestureEventListeners();
		
		suggestionBox.setGestureAllowance(TapProcessor.class, true);
		suggestionBox.registerInputProcessor(new TapProcessor(app));
		suggestionBox.addGestureListener(TapProcessor.class, new SuggestionBoxListener());
		
		this.addChild(suggestionBox);
		drawSuggestionBox();
	}
	
	int o = 0;
	int p = 0;
	@Override
	public void drawComponent(PGraphics g) {
		super.drawComponent(g);
		if (o++ > 30) {
			o = 0;
			drawSuggestionBox();
			
		}
		if (p++ > 10) {
			this.setWidthLocal(width);
		}
		
	}
	
	private void drawSuggestionBox() {
		
		String suggestions = "";
		int i = 0;
		List<String> strings = this.getRelevantStrings();
		if (strings.size() > 0) {
			suggestionBox.setVisible(true);
		currentSuggestions.clear();
		for (String s: strings) {
			if (i != 0 && i < 5) suggestions += "\n";
			
			if (i++ < 5) {
				suggestions += s;
				currentSuggestions.add(s);
			} else {
				break;
			}
			
		}
		suggestionBox.setText(suggestions);
		suggestionBox.setWidthLocal(width);
		suggestionBox.setPositionRelativeToParent(calcPos(this, suggestionBox, 0, this.getHeightXY(TransformSpace.LOCAL)));
		} else {
			suggestionBox.setVisible(false);
		}
		if (keyboard == null) suggestionBox.setVisible(false);
	}
	
	private Vector3D calcPos(MTRectangle rect, MTPolygon ta, float xo, float yo) {

		return new Vector3D((ta.getWidthXYVectLocal().length() / 2)
				+ rect.getVerticesLocal()[0].getX() + xo, (ta
				.getHeightXYVectLocal().length() / 2)
				+ rect.getVerticesLocal()[0].getY() + yo);
	}
	
	
	public List<String> getRelevantStrings() {
		List<String> newList = new ArrayList<String>();
		if (!availableValues.isEmpty()) {
		String currentText = this.getText();
			for (String s: availableValues) {
				if (currentText != "") {
					if (s.toUpperCase().contains(currentText.toUpperCase())) {
						newList.add(s.replaceAll("\n", " "));
					}
				} else {
					newList.add(s.replace("\n", ""));
				}
			}
		}
		return newList;
	}
	

	public class EditListener implements IGestureEventListener {

		MTTextArea ta;
		public EditListener(MTTextArea ta) {
			this.ta = ta;
		}
		
		@Override
		public boolean processGestureEvent(MTGestureEvent ge) {
			if (ge instanceof TapEvent) {
				TapEvent te = (TapEvent)ge;
				if (keyboard == null && te.getTapID() == TapEvent.BUTTON_CLICKED) {
					keyboard = new MTKeyboard(app);
					addChild(keyboard);
					keyboard.addTextInputListener(ta);
					keyboard.addStateChangeListener(StateChange.COMPONENT_DESTROYED, new KeyboardListener());
					keyboard.setPositionRelativeToParent(calcPos(ta, keyboard, 0, ta.getHeightXY(TransformSpace.LOCAL) + suggestionBox.getHeightXY(TransformSpace.LOCAL)));
				}
			}
			return false;
		}
		
	}
	public class KeyboardListener implements StateChangeListener {

		@Override
		public void stateChanged(StateChangeEvent evt) {
			if (evt.getState() == StateChange.COMPONENT_DESTROYED) {
				keyboard = null;
			}
			
		}
		
	}
	public class SuggestionBoxListener implements IGestureEventListener {

		@Override
		public boolean processGestureEvent(MTGestureEvent ge) {
			if (ge instanceof TapEvent) {
				TapEvent te = (TapEvent)ge;
				if (te.getTapID() == TapEvent.BUTTON_CLICKED) {
					Vector3D w = Tools3D.project(app, app.getCurrentScene().getSceneCam(), te.getLocationOnScreen());
					Vector3D x = suggestionBox.globalToLocal(w);
					float zero = suggestionBox.getVerticesLocal()[0].y;
					float heightPerLine = suggestionBox.getHeightXY(TransformSpace.LOCAL) / (float)(suggestionBox.getLineCount() + 1);
					int line = (int)((x.y - zero) / heightPerLine);
					System.out.println(line + " Line Count: " + suggestionBox.getLineCount() + " Height per Line: " + heightPerLine);
					if (currentSuggestions.size() > line) {
						setText(currentSuggestions.get(line));
					}
					
				}
			}
			return false;
		}
		
	}
}
