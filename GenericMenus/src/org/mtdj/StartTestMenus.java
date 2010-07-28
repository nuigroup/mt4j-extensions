package org.mtdj;

import org.mt4j.MTApplication;


public class StartTestMenus extends MTApplication {
	public static void main(String[] args) {
		initialize();
	}
	
	
	@Override
	public void startUp() {
		// TODO Auto-generated method stub
		addScene(new TestMenus(this, "Integration  Test Scene"));
	}
}
