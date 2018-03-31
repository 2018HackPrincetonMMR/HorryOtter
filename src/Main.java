import com.leapmotion.leap.Controller;

import frontend.Application;
import kinesthesis.LeapController;
import kinesthesis.LeapListener;

public class Main {

	public static void main(String[] args) {
		Application app = new Application();
		LeapController leap = new LeapController(app.getWandController());
		app.start();
    }


}