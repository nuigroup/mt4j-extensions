package de.molokoid.extensions;



import de.molokoid.data.CSSStyle;


public interface CSSStylable {
	
	public void evaluateStyleSheets();
	public void setStyleSheet(CSSStyle sheet);
	public void applyStyleSheet();
	
}
