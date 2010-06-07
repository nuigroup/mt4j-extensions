package de.molokoid.css;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.mt4j.util.MTColor;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.DocumentHandler;
import org.w3c.css.sac.InputSource;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.css.sac.SACMediaList;
import org.w3c.css.sac.SelectorList;

import de.molokoid.data.BorderStyle;
import de.molokoid.data.CSSStyle;
import de.molokoid.data.Selector;
import de.molokoid.data.SelectorType;

public class CSSHandler implements DocumentHandler{
	
	Logger logger = null;
	List<CSSStyle> styles = null;
	List<CSSStyle> activeStyles = new ArrayList<CSSStyle>();
	
	
	public CSSHandler(List<CSSStyle> styles) {
		logger = Logger.getLogger("MT4J Extensions");
		this.styles = styles;
	}
	
	
	@Override
	public void comment(String arg0) throws CSSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endDocument(InputSource arg0) throws CSSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endFontFace() throws CSSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endMedia(SACMediaList arg0) throws CSSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endPage(String arg0, String arg1) throws CSSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endSelector(SelectorList arg0) throws CSSException {
		// TODO Auto-generated method stub
		activeStyles.clear();
		logger.debug("Clearing activeStyles");
	}

	@Override
	public void ignorableAtRule(String arg0) throws CSSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void importStyle(String arg0, SACMediaList arg1, String arg2)
			throws CSSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void namespaceDeclaration(String arg0, String arg1)
			throws CSSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void property(String name, LexicalUnit value, boolean important)
			throws CSSException {
		try {
		logger.debug(name + " | " + value.getLexicalUnitType());
		parseValue(name, value);
		} catch (Exception e){
			//logger.debug("Exception: " + value.getStringValue());
			logger.debug(name + " | " + value.getLexicalUnitType());
			e.printStackTrace();
			
		}
	}
	
	private void parseValue(String name, LexicalUnit value) {
		try {
			cssproperties prop = cssproperties.UNKNOWN;
			try {
				prop  = cssproperties.valueOf(name.replace(" ", "").replace("-", "").toUpperCase());
			} catch (IllegalArgumentException iae) {
				
			}
		switch (prop) {
		case COLOR:
			MTColor color = handleColor(value);
			logger.debug("Color: " + color.toColorString());
			for (CSSStyle sty: activeStyles) sty.setColor(color);
			
			break;
		case BACKGROUNDCOLOR:
			color = handleColor(value);
			logger.debug("Background Color: " + color.toColorString());
			for (CSSStyle sty: activeStyles) sty.setBackgroundColor(color);
			
			break;
			
		case WIDTH:
			LexicalUnit parameter = value;
			for (CSSStyle sty: activeStyles) sty.setWidth(parseMeasuringUnit(parameter));
			logger.debug("Width: " + parseMeasuringUnit(parameter));
			break;
		case HEIGHT:
			parameter = value;
			for (CSSStyle sty: activeStyles) sty.setHeight(parseMeasuringUnit(parameter));
			logger.debug("Height: " + parseMeasuringUnit(parameter));
			break;
		case BORDER:
			
			break;
		case BORDERWIDTH:
			parameter = value;
			for (CSSStyle sty: activeStyles) sty.setBorderWidth(parseMeasuringUnit(parameter));
			logger.debug("Border Width: " + parseMeasuringUnit(parameter));
			break;
		case BORDERCOLOR:
			color = handleColor(value);
			logger.debug("Border Color: " + color.toColorString());
			for (CSSStyle sty: activeStyles) sty.setBorderColor(color);
			
			break;
		case BORDERSTYLE:
			BorderStyle style = parseBorderStyle(value);
			for (CSSStyle sty: activeStyles) sty.setBorderStyle(style);
			logger.debug("BorderStyle: " + style);
			break;
		case PADDING:
			parameter = value;
			for (CSSStyle sty: activeStyles) sty.setPaddingWidth(parseMeasuringUnit(parameter));
			logger.debug("Padding: " + parseMeasuringUnit(parameter));
			break;
		case FONTSIZE:
			parameter = value;
			for (CSSStyle sty: activeStyles) sty.setFontSize((int) parseMeasuringUnit(parameter));
			logger.debug("Font Size: " + parseMeasuringUnit(parameter));
			break;
		case VISIBILITY:
			boolean visible; 
			visible = parseBoolean(value);
			for (CSSStyle sty: activeStyles) sty.setVisibility(visible);
			logger.debug("VISIBILITY: " + visible);
			break;
			
		}}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private MTColor handleColor(LexicalUnit value){
		switch (value.getLexicalUnitType()) {
		case LexicalUnit.SAC_RGBCOLOR:
				try {
					logger.debug("Color by RGB: " + value.getFunctionName());
					//logger.debug("Color by RGB: " + value.getParameters());
					LexicalUnit parameters = value.getParameters();
					float red = parseMeasuringUnit(parameters);
					parameters = parameters.getNextLexicalUnit().getNextLexicalUnit();
					float green = parseMeasuringUnit(parameters);
					parameters = parameters.getNextLexicalUnit().getNextLexicalUnit();
					float blue = parseMeasuringUnit(parameters);
					//logger.debug("Color: " + red + "," + green + "," + blue);
					return new MTColor(red, green, blue, 255);
					
				} catch (Exception e) {e.printStackTrace();};
			break;
		case LexicalUnit.SAC_IDENT:
				logger.debug("Color by Identifier: " + value.getStringValue());
				if (value.getStringValue().equalsIgnoreCase("black")) return new MTColor(0,0,0,255);
				if (value.getStringValue().equalsIgnoreCase("white")) return new MTColor(255,255,255,255);
				if (value.getStringValue().equalsIgnoreCase("silver")) return new MTColor(192,192,192,255);
				if (value.getStringValue().equalsIgnoreCase("gray")) return new MTColor(128,128,128,255);
				if (value.getStringValue().equalsIgnoreCase("maroon")) return new MTColor(128,0,0,255);
				if (value.getStringValue().equalsIgnoreCase("red")) return new MTColor(255,0,0,255);
				if (value.getStringValue().equalsIgnoreCase("purple")) return new MTColor(128,0,128,255);
				if (value.getStringValue().equalsIgnoreCase("fuchsia")) return new MTColor(255,0,255,255);
				if (value.getStringValue().equalsIgnoreCase("green")) return new MTColor(0,128,0,255);
				if (value.getStringValue().equalsIgnoreCase("lime")) return new MTColor(0,255,0,255);
				if (value.getStringValue().equalsIgnoreCase("olive")) return new MTColor(128,128,0,255);
				if (value.getStringValue().equalsIgnoreCase("yellow")) return new MTColor(255,255,0,255);
				if (value.getStringValue().equalsIgnoreCase("navy")) return new MTColor(0,0,128,255);
				if (value.getStringValue().equalsIgnoreCase("blue")) return new MTColor(0,0,255,255);
				if (value.getStringValue().equalsIgnoreCase("teal")) return new MTColor(0,0,128,255);
				if (value.getStringValue().equalsIgnoreCase("aqua")) return new MTColor(0,255,255,255);
				
				
				
				
				
			break;
		}
		
		return new MTColor(0,0,0,0);
	}
	
	private boolean parseBoolean(LexicalUnit value) {
		try {
			return Boolean.parseBoolean(value.getStringValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	private BorderStyle parseBorderStyle(LexicalUnit value) {
		LexicalUnit parameter = value;
		if (parameter.getLexicalUnitType() == LexicalUnit.SAC_IDENT) {
			if (parameter.getStringValue().equalsIgnoreCase("dashed")) return BorderStyle.DASHED;
			if (parameter.getStringValue().equalsIgnoreCase("dotted")) return BorderStyle.DOTTED;
			if (parameter.getStringValue().equalsIgnoreCase("none")) return BorderStyle.NONE;
			if (parameter.getStringValue().equalsIgnoreCase("hidden")) return BorderStyle.HIDDEN;
		}
		
		return BorderStyle.SOLID;
	}
	
	private float parseNumber(LexicalUnit component) {
		if (component.getLexicalUnitType() == LexicalUnit.SAC_INTEGER) {
			return (float) component.getIntegerValue();
		}
		if (component.getLexicalUnitType() == LexicalUnit.SAC_REAL) {
			return (float) component.getFloatValue();
		}
		if (component.getLexicalUnitType() == LexicalUnit.SAC_PERCENTAGE) {
			return (float) component.getFloatValue();
		}
		
		return 0;
	}
	
	private float parseMeasuringUnit(LexicalUnit value) {
		float dpi = 100f;
		
		//TODO: Font Sizes
		float emtopx = 16f/72f * dpi;
		//float extopx;
		float inchtopx = dpi;
		float centtopx = (10f/254f) * dpi;
		float mmtopx = (1f/254f) * dpi;
		float pointtopx = (1f/72f) * dpi;
		float picatopx = (12f/72f) * dpi;
		float rv;
		try {
		switch (value.getLexicalUnitType()) {
		case LexicalUnit.SAC_CENTIMETER:
			//logger.debug("1 Float: " + value.getFloatValue());
			return value.getFloatValue() * centtopx;
		case LexicalUnit.SAC_INCH:
			//logger.debug("2 Float: " + value.getFloatValue());
			return value.getFloatValue() * inchtopx;
		case LexicalUnit.SAC_MILLIMETER:
			//logger.debug("3 Float: " + value.getFloatValue());
			return value.getFloatValue() * mmtopx;
		case LexicalUnit.SAC_POINT:
			//logger.debug("4 Float: " + value.getFloatValue());
			return value.getFloatValue() * pointtopx;
		case LexicalUnit.SAC_PICA:
			//logger.debug("5 Float: " + value.getFloatValue());
			return value.getFloatValue() * picatopx;
		case LexicalUnit.SAC_EM:
			//logger.debug("6 Float: " + value.getFloatValue());
			return (value.getFloatValue() * emtopx);
		case LexicalUnit.SAC_PIXEL:
			return value.getFloatValue();
		case LexicalUnit.SAC_INTEGER:
			return (float) value.getIntegerValue();
		case LexicalUnit.SAC_REAL:
		case LexicalUnit.SAC_PERCENTAGE:
			return (float) value.getFloatValue();
			
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("Unrecognized Measuring Unit: " + value.getLexicalUnitType());
		return 0;
	}
	
	
	@Override
	public void startDocument(InputSource arg0) throws CSSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startFontFace() throws CSSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startMedia(SACMediaList arg0) throws CSSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startPage(String arg0, String arg1) throws CSSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startSelector(SelectorList selectors) throws CSSException {
		
		for (int i=0; i < selectors.getLength(); i++) {
			styles.add(new CSSStyle(parseSelector(selectors.item(i))));
			activeStyles.add(styles.get(styles.size()-1));
			
		}
		
		
		
	}
	
	private Selector parseSelector(org.w3c.css.sac.Selector selector) {
		logger.debug(selector.toString());
		//Ignoring "F E" combinator
		//TODO: FE Combinator
		
		//Includes Child
		
		Selector newSelector = null;
		
		if (selector.toString().contains(">")) {
			String[] parts = selector.toString().split(">", 2);
			newSelector = processElement(parts[0]);
			newSelector.setChild(processElement(parts[1]));
			
			
		} else {
			//No Children (yet)
			newSelector = processElement(selector.toString());
			
			
		}
		
		
		
		
		logger.debug("Primary: " + newSelector.getPrimary() + "|" + newSelector.getPrimaryType().toString());
		if (newSelector.getSecondary() != null) logger.debug("Secondary: " + newSelector.getSecondary() + "|" + newSelector.getSecondaryType().toString());
		if (newSelector.getChild() != null) logger.debug("Child: " + newSelector.getChild().getPrimary() + "|" + newSelector.getChild().getPrimaryType().toString());
		return newSelector;
	}
	
	private Selector processElement(String in) {
		Selector newSelector = null;
		
		
		
		String work = String.copyValueOf(in.toCharArray());	
		
		if (work.contains("*") && !work.startsWith("*")) {
			work = work.substring(0, 1) + work.substring(1).replace("*", "");
		}
		
		if (work.startsWith("*") && !work.equals("*")) {
			work = work.substring(1);
		}
		work.replace(" ", "");
		boolean containsSharp = false;
		boolean containsDot = false;
		boolean firstCharacterDot = false;
		boolean firstCharacterSharp = false;
		
		if (work.contains("#")) containsSharp = true;
		if (work.contains(".")) containsDot = true;
		
		if (work.equals("*")) {
			newSelector = new Selector("*", SelectorType.UNIVERSAL);
			return newSelector;
		}
		
		if (work.startsWith(".")) {
			firstCharacterDot = true;
			containsDot = work.substring(1).contains(".");
			
		}
		
		if (work.startsWith("#")) {
			firstCharacterSharp = true;
			containsSharp = work.substring(1).contains("#");
		}
		
		
		if (!containsSharp && !containsDot) {
			newSelector = new Selector(work.replace(".", "").replace("#", ""), determineType(work));
		}
		
		if (containsSharp) {
			if (firstCharacterSharp) {
			String[] parts = work.substring(1).split("#",2);
			logger.debug("Just Split " + work.substring(1)  +  " by '#' into " + parts.length + " Parts");
			newSelector = new Selector(parts[0].replace(".", "").replace("#", ""), SelectorType.ID);
			newSelector.setSecondary(parts[1].replace(".", "").replace("#", ""));
			newSelector.setSecondaryType(SelectorType.ID);
			} else {
			String[] parts = work.split("#",2);
			logger.debug("Just Split " + work  +  " by '#' into " + parts.length + " Parts");
			newSelector = new Selector(parts[0].replace(".", "").replace("#", ""), determineType(parts[0]));
			newSelector.setSecondary(parts[1].replace(".", "").replace("#", ""));
			newSelector.setSecondaryType(SelectorType.ID);
			}
			return newSelector;
		}
		
		if (containsDot) {
			if (firstCharacterDot) {
				String[] parts = work.substring(1).split(".",2);
				logger.debug("Just Split " + work.substring(1)  +  " by '.' into " + parts.length + " Parts");
				newSelector = new Selector(parts[0], SelectorType.CLASS);
				newSelector.setSecondary(parts[1]);
				newSelector.setSecondaryType(SelectorType.CLASS);
			} else {
				String[] parts = work.split(".",2);
				logger.debug("Just Split " + work  +  " by '.' into " + parts.length + " Parts");
				if (parts.length == 0) {
					for (char c: work.toCharArray()) {
						logger.debug(c + " = " + (int)c);
					}
					
					logger.debug("Control Sample: " + "." + " = " + (int)".".charAt(0));
				}
				
				
				newSelector = new Selector(parts[0], determineType(parts[0]));
				newSelector.setSecondary(parts[1]);
				newSelector.setSecondaryType(SelectorType.CLASS);
			}
			return newSelector;		
		}
		
		
		
		return newSelector;
	}
	
	private SelectorType determineType (String in) {
		if (in.contains(".")) return SelectorType.CLASS;
		if (in.contains("#")) return SelectorType.ID;
		if (in.contains("*")) return SelectorType.UNIVERSAL;
			
		return SelectorType.TYPE;
	}
	
	private enum cssproperties {
		COLOR, BACKGROUNDCOLOR, BORDERCOLOR, BACKGROUNDIMAGE, BACKGROUNDPOSITION, BACKGROUNDREPEAT, BORDERSTYLE, FONTFAMILY, FONT, FONTSIZE, FONTSTYLE, WIDTH, HEIGHT, BORDERWIDTH, PADDING, VISIBILITY, ZINDEX, BORDER, UNKNOWN 
	}
	
}