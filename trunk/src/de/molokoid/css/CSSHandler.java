package de.molokoid.css;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.DocumentHandler;
import org.w3c.css.sac.InputSource;
import org.w3c.css.sac.LexicalUnit;
import org.w3c.css.sac.SACMediaList;
import org.w3c.css.sac.SelectorList;

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
	public void property(String arg0, LexicalUnit arg1, boolean arg2)
			throws CSSException {
		// TODO Auto-generated method stub
		
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
	
	
	
}
