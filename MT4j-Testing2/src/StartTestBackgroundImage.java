
import org.mt4j.MTApplication;

public class StartTestBackgroundImage extends MTApplication{

	public static void main(String[] args) {
		initialize();
	}
	
	
	@Override
	public void startUp() {
		// TODO Auto-generated method stub
		addScene(new TestBackgroundImage(this, "Integration  Test Scene"));
	}

}
