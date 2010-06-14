package de.molokoid.test;

import org.mt4j.MTApplication;



public class StartTestApp extends MTApplication{
	public static void main(String[] args) {
		initialize();
	}
	
	public StartTestApp() {
		initialize();
	}
	
	@Override
	public void startUp() {
		addScene(new TestApp(this, "Test Scene"));
		
	}

}
