package de.molokoid.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.junit.Before;
import org.junit.Test;


import de.molokoid.css.CSSHandler;
import de.molokoid.css.parserConnector;
import de.molokoid.data.CSSStyle;
import de.molokoid.data.Selector;

public class CSSHandlerTest extends TestCase{

	Logger logger = Logger.getLogger("MT4J Extensions");
	SimpleLayout l = new SimpleLayout();
	ConsoleAppender ca = new ConsoleAppender(l);
	
		
	StartTestApp app = new StartTestApp();
	List<CSSStyle> styles = new ArrayList<CSSStyle>();
	CSSHandler cssh = new CSSHandler(app, styles);
	
	@Before
	public void setUp() {
		logger.addAppender(ca);
	}
	
	
	@Test 
	public void testProcessElement() {
		Selector test = cssh.processElement("P.c141");
		logger.debug(test);
		test = cssh.processElement("P#c141");
		logger.debug(test);
		test = cssh.processElement("#P.c141");
		logger.debug(test);
		test = cssh.processElement("#P c141");
		logger.debug(test);
		test = cssh.processElement("#P                                .c141");
		logger.debug(test);
		
		
	}

}
