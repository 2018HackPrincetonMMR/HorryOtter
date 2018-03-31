import com.leapmotion.leap.Controller;

import frontend.Application;
import kinesthesis.LeapListener;

public class Main {

	public static void main(String[] args) {
		Application app = new Application();
		app.start();

        Controller controller = new Controller();
        LeapListener listener = new LeapListener();
        controller.addListener(listener);
    }


}