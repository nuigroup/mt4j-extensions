package de.molokoid.css;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.mt4j.MTApplication;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.util.MTColor;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.DocumentHandler;
import org.w3c.css.sac.InputSource;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.css.sac.SACMediaList;
import org.w3c.css.sac.SelectorList;

import de.molokoid.data.BorderStyle;
import de.molokoid.data.CSSFont;
import de.molokoid.data.CSSStyle;
import de.molokoid.data.Selector;
import de.molokoid.data.SelectorType;
import de.molokoid.data.fontfamily;
import de.molokoid.data.fontstyle;
import de.molokoid.data.fontweight;


public class CSSHandler implements DocumentHandler{
	
	Logger logger = null;
	List<CSSStyle> styles = null;
	List<CSSStyle> activeStyles = new ArrayList<CSSStyle>();
	CSSFont currentFont = null;
	MTApplication app = null;
	float defaultFontSize = 16f;
	List<Short> numericalValues = new ArrayList<Short>( Arrays.asList(LexicalUnit.SAC_CENTIMETER,LexicalUnit.SAC_INCH,LexicalUnit.SAC_MILLIMETER,LexicalUnit.SAC_POINT,LexicalUnit.SAC_PICA,LexicalUnit.SAC_EM, LexicalUnit.SAC_PIXEL,LexicalUnit.SAC_INTEGER,LexicalUnit.SAC_REAL,LexicalUnit.SAC_PERCENTAGE));
	List<Short> stringValues = new ArrayList<Short>( Arrays.asList(LexicalUnit.SAC_IDENT, LexicalUnit.SAC_STRING_VALUE));

	
	
	
	public CSSHandler(MTApplication app, List<CSSStyle> styles) {
		logger = Logger.getLogger("MT4J Extensions");
		this.styles = styles;
		this.app = app;
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
		for (CSSStyle s: activeStyles) {
			s.setFont(selectFont(currentFont));
		}
		currentFont = new CSSFont();
		activeStyles.clear();
		
	}

	private IFont selectFont(CSSFont currentFont2) {

		if (currentFont2 != null) {
		try {
		switch (currentFont2.getFamily()) {
		
		case SERIF:
			switch (currentFont2.getStyle()) {
			case ITALIC:
			case OBLIQUE:
				if (currentFont2.getWeight() == fontweight.BOLD) {
					return getFont("dejavu/DejaVuSerif-BoldItalic.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == fontweight.LIGHT) {
					return getFont("dejavu/DejaVuSerif-Italic.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == fontweight.NORMAL) {
					return getFont("dejavu/DejaVuSerif-Italic.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
			
				break;
			case NORMAL:
			default:
				if (currentFont2.getWeight() == fontweight.BOLD) {
					return getFont("dejavu/DejaVuSerif-Bold.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == fontweight.LIGHT) {
					return getFont("dejavu/DejaVuSerif.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == fontweight.NORMAL) {
					return getFont("dejavu/DejaVuSerif.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				break;
			}
			break;
		case MONO:
			switch (currentFont2.getStyle()) {
			case ITALIC:
			case OBLIQUE:
				if (currentFont2.getWeight() == fontweight.BOLD) {
					return getFont("dejavu/DejaVuSansMono-BoldOblique.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == fontweight.LIGHT) {
					return getFont("dejavu/DejaVuSansMono-Oblique.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == fontweight.NORMAL) {
					return getFont("dejavu/DejaVuSansMono-Oblique.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
			
				break;
			case NORMAL:
			default:
				if (currentFont2.getWeight() == fontweight.BOLD) {
					return getFont("dejavu/DejaVuSansMono-Bold.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == fontweight.LIGHT) {
					return getFont("dejavu/DejaVuSansMono.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == fontweight.NORMAL) {
					return getFont("dejavu/DejaVuSansMono.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				break;
			}
			break;
		case CUSTOM:
			return getFont(currentFont2.getCustomType(), currentFont2.getFontsize(), currentFont2.getColor());
			
		case DEFAULT:
		case SANS:
		default:
			switch (currentFont2.getStyle()) {
			case ITALIC:
			case OBLIQUE:
				if (currentFont2.getWeight() == fontweight.BOLD) {
					return getFont("dejavu/DejaVuSans-BoldOblique.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == fontweight.LIGHT) {
					return getFont("dejavu/DejaVuSans-Oblique.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == fontweight.NORMAL) {
					return getFont("dejavu/DejaVuSans-Oblique.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
			
				break;
			case NORMAL:
			default:
				if (currentFont2.getWeight() == fontweight.BOLD) {
					return getFont("dejavu/DejaVuSans-Bold.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == fontweight.LIGHT) {
					return getFont("dejavu/DejaVuSans-ExtraLight.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				if (currentFont2.getWeight() == fontweight.NORMAL) {
					return getFont("dejavu/DejaVuSans.ttf", currentFont2.getFontsize(), currentFont2.getColor());
				}
				break;
			}
			break;
		
		}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		}
		

		if (currentFont2 == null) {
			currentFont2 = new CSSFont(16);
		}
		
		 return FontManager.getInstance().createFont(app,
				"dejavu/DejaVuSans.ttf", currentFont2.getFontsize(), // Font size
				currentFont2.getColor(), // Font fill color
				currentFont2.getColor()); 
	}

	private IFont getFont(String font, int size, MTColor color) {
		try {
			IFont returnFont = FontManager.getInstance().createFont(app,
		
				font, size, // Font size
				color, // Font fill color
				color);
			if (returnFont == null) throw new Exception();
			return returnFont;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FontManager.getInstance().createFont(app,
				"dejavu/DejaVuSans.ttf", size, // Font size
				color, // Font fill color
				color); 
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
		parseValue(name, value);
		} catch (Exception e){
			e.printStackTrace();
			
		}
	}
	
	private void parseValue(String name, LexicalUnit value) {
		try {
			cssproperties prop = cssproperties.UNKNOWN;
			try {
				prop  = cssproperties.valueOf(name.replace(" ", "").replace("-", "").toUpperCase());
			} catch (IllegalArgumentException iae) {
				iae.printStackTrace();
			}
		switch (prop) {
		case BACKGROUNDCOLOR:
			MTColor color = handleColor(value);
			for (CSSStyle sty: activeStyles) sty.setBackgroundColor(color);
			
			break;
		case COLOR:
			color = handleColor(value);
			//for (CSSStyle sty: activeStyles) sty.setBackgroundColor(color);
			if (currentFont == null) currentFont = new CSSFont(color);
			else currentFont.setColor(color);
			break;
			
		case WIDTH:
			LexicalUnit parameter = value;
			//TODO: Relative Größe
			for (CSSStyle sty: activeStyles) {
				sty.setWidth(parseMeasuringUnit(parameter,100));
				if (parameter.getLexicalUnitType() == LexicalUnit.SAC_PERCENTAGE) sty.setWidthPercentage(true);
			}
			break;
		case HEIGHT:
			parameter = value;
			//TODO: Relative Größe
			for (CSSStyle sty: activeStyles) {
				sty.setHeight(parseMeasuringUnit(parameter, 100));
				if (parameter.getLexicalUnitType() == LexicalUnit.SAC_PERCENTAGE) sty.setHeightPercentage(true);
			}
			break;
		case BORDER:
			
			break;
		case BORDERWIDTH:
			parameter = value;
			for (CSSStyle sty: activeStyles) sty.setBorderWidth(parseMeasuringUnit(parameter,1));
			break;
		case BORDERCOLOR:
			color = handleColor(value);
			for (CSSStyle sty: activeStyles) sty.setBorderColor(color);
			
			break;
		case BORDERSTYLE:
			BorderStyle style = parseBorderStyle(value);
			for (CSSStyle sty: activeStyles) sty.setBorderStyle(style);
			break;
		case PADDING:
			parameter = value;
			for (CSSStyle sty: activeStyles) sty.setPaddingWidth(parseMeasuringUnit(parameter,1));
			break;
		case FONTSIZE:
			parameter = value;
			//Have to convert to pt
			if (numericalValues.contains(parameter.getLexicalUnitType())) {
			if (currentFont == null) currentFont = new CSSFont((int) (parseMeasuringUnit(parameter,defaultFontSize) * (72f/100f)));
			else currentFont.setFontsize((int) (parseMeasuringUnit(parameter,defaultFontSize)* (72f/100f)));
			} else if (stringValues.contains(parameter.getLexicalUnitType())){
				if (currentFont == null) currentFont = new CSSFont(handleFontSizeString(parameter));
				else currentFont.setFontsize(handleFontSizeString(parameter));
			}
			break;
		case VISIBILITY:
			boolean visible; 
			visible = parseBoolean(value);
			for (CSSStyle sty: activeStyles) sty.setVisibility(visible);
			break;
			
		case FONTFAMILY:
			handleFontFamily(value);
			break;
		case FONT:
			break;
		case FONTSTYLE:
			handleFontStyle(value);
			break;
		case FONTWEIGHT:
			handleFontWeight(value);
			break;
		case UNKNOWN:
		default:
			logger.error("Unknown Identifier: " + name.replace(" ", "").replace("-", "").toUpperCase());
			break;
		
			
		}}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int handleFontSizeString(LexicalUnit parameter) {
		
		if (parameter.getStringValue().toUpperCase().contains("SMALLER")) return 8;
		if (parameter.getStringValue().toUpperCase().contains("BIGGER")) return 30;
		
		if (parameter.getStringValue().toUpperCase().contains("SMALL")) return 12;
		if (parameter.getStringValue().toUpperCase().contains("BIG")) return 24;
		
		return 16;
	}


	private void handleFontWeight(LexicalUnit value) {
		fontweight weight = fontweight.NORMAL;
		if (currentFont == null) currentFont = new CSSFont(weight);
		switch (value.getLexicalUnitType()) {
		case LexicalUnit.SAC_IDENT:
		case LexicalUnit.SAC_STRING_VALUE:
			if (value.getStringValue().toUpperCase().contains("BOLD")) {currentFont.setWeight(fontweight.BOLD); break;}
			if (value.getStringValue().toUpperCase().contains("LIGHT")) {currentFont.setWeight(fontweight.LIGHT); break;}
			currentFont.setWeight(fontweight.NORMAL);
			break;
		case LexicalUnit.SAC_INTEGER:
			if (value.getIntegerValue() < 400) {currentFont.setWeight(fontweight.LIGHT); break;}
			if (value.getIntegerValue() > 600) {currentFont.setWeight(fontweight.BOLD); break;}
			currentFont.setWeight(fontweight.NORMAL); 
			break;
		default: break;
		}
		
	}


	private void handleFontFamily(LexicalUnit value) {
		fontfamily family = fontfamily.CUSTOM;
		if (currentFont == null) currentFont = new CSSFont(family);
		if (value.getLexicalUnitType() == LexicalUnit.SAC_IDENT || value.getLexicalUnitType() == LexicalUnit.SAC_STRING_VALUE) {
			
			if (value.getStringValue().toUpperCase().contains("TTF")) {
				currentFont.setFamily(fontfamily.CUSTOM); 
				currentFont.setCustomType(value.getStringValue()); 
				return;}
			if (value.getStringValue().toUpperCase().contains("MONO")) {currentFont.setFamily(fontfamily.MONO); return;}
			if (value.getStringValue().toUpperCase().contains("SANS")) {currentFont.setFamily(fontfamily.SANS); return;}
			if (value.getStringValue().toUpperCase().contains("SERIF")) {currentFont.setFamily(fontfamily.SERIF); return;}

		} else {
			logger.debug(value.getLexicalUnitType());
		}
		
	}
	private void handleFontStyle(LexicalUnit value) {
		
		fontstyle style = fontstyle.NORMAL;
		if (currentFont == null) currentFont = new CSSFont(style);
		if (value.getLexicalUnitType() == LexicalUnit.SAC_IDENT || value.getLexicalUnitType() == LexicalUnit.SAC_STRING_VALUE) {
			if (value.getStringValue().toUpperCase().contains("ITALIC")) {currentFont.setStyle(fontstyle.ITALIC); return;}
			if (value.getStringValue().toUpperCase().contains("OBLIQUE")) {currentFont.setStyle(fontstyle.OBLIQUE); return;}
		}
	}
	
	private MTColor handleColor(LexicalUnit value){
		switch (value.getLexicalUnitType()) {
		case LexicalUnit.SAC_RGBCOLOR:
				try {

					LexicalUnit parameters = value.getParameters();
					float red = parseMeasuringUnit(parameters, 255);
					parameters = parameters.getNextLexicalUnit().getNextLexicalUnit();
					float green = parseMeasuringUnit(parameters, 255);
					parameters = parameters.getNextLexicalUnit().getNextLexicalUnit();
					float blue = parseMeasuringUnit(parameters, 255);

					return new MTColor(red, green, blue, 255);
					
				} catch (Exception e) {e.printStackTrace();};
			break;
		case LexicalUnit.SAC_IDENT:
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
	
	private float parseMeasuringUnit(LexicalUnit value, float referenceValue) {
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
			return value.getFloatValue() * centtopx;
		case LexicalUnit.SAC_INCH:
			return value.getFloatValue() * inchtopx;
		case LexicalUnit.SAC_MILLIMETER:
			return value.getFloatValue() * mmtopx;
		case LexicalUnit.SAC_POINT:
			return value.getFloatValue() * pointtopx;
		case LexicalUnit.SAC_PICA:
			return value.getFloatValue() * picatopx;
		case LexicalUnit.SAC_EM:
			return (value.getFloatValue() * emtopx);
		case LexicalUnit.SAC_PIXEL:
			return value.getFloatValue();
		case LexicalUnit.SAC_INTEGER:
			return (float) value.getIntegerValue();
		case LexicalUnit.SAC_REAL:
		case LexicalUnit.SAC_PERCENTAGE:
			return (float) value.getFloatValue() / 100 *  referenceValue;
			
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.error("Unrecognized Measuring Unit: " + value.getLexicalUnitType());
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
		logger.debug(newSelector);
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
			newSelector = new Selector(parts[0].replace(".", "").replace("#", ""), SelectorType.ID);
			newSelector.setSecondary(parts[1].replace(".", "").replace("#", ""));
			newSelector.setSecondaryType(SelectorType.ID);
			} else {
			String[] parts = work.split("#",2);
			newSelector = new Selector(parts[0].replace(".", "").replace("#", ""), determineType(parts[0]));
			newSelector.setSecondary(parts[1].replace(".", "").replace("#", ""));
			newSelector.setSecondaryType(SelectorType.ID);
			}
			return newSelector;
		}
		
		if (containsDot) {
			if (firstCharacterDot) {
				String[] parts = work.substring(1).split(".",2);
				newSelector = new Selector(parts[0], SelectorType.CLASS);
				newSelector.setSecondary(parts[1]);
				newSelector.setSecondaryType(SelectorType.CLASS);
			} else {
				String[] parts = work.split(".",2);
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
	
	public List<CSSStyle> getStyles() {
		return styles;
	}
	
	private SelectorType determineType (String in) {
		if (in.contains(".")) return SelectorType.CLASS;
		if (in.contains("#")) return SelectorType.ID;
		if (in.contains("*")) return SelectorType.UNIVERSAL;
			
		return SelectorType.TYPE;
	}
	
	private enum cssproperties {
		COLOR, BACKGROUNDCOLOR, BORDERCOLOR, BACKGROUNDIMAGE, BACKGROUNDPOSITION, BACKGROUNDREPEAT, BORDERSTYLE, FONTFAMILY, FONT, FONTSIZE, FONTSTYLE, FONTWEIGHT, WIDTH, HEIGHT, BORDERWIDTH, PADDING, VISIBILITY, ZINDEX, BORDER, UNKNOWN 
	}
	
}
