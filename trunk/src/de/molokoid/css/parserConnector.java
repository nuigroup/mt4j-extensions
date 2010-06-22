package de.molokoid.css;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.batik.css.parser.Parser;
import org.mt4j.MTApplication;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.InputSource;

import de.molokoid.data.CSSStyle;

public class parserConnector {
	Parser pa = null;
	FileReader fileReader = null;
	CSSHandler cssh = null;
	
	
	public parserConnector(String source, MTApplication app) {
		
		List<CSSStyle> styles= new ArrayList<CSSStyle>();
		cssh = new CSSHandler(app, styles);
		
		pa = new Parser();
		try {
			fileReader = new FileReader(source);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pa.setDocumentHandler(cssh);
		try {
			pa.parseStyleSheet(new InputSource(fileReader));
		} catch (CSSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	public CSSHandler getCssh() {
		return cssh;
	}


	public void setCssh(CSSHandler cssh) {
		this.cssh = cssh;
	}
	
	
}
