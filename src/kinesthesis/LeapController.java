package kinesthesis;

import com.leapmotion.leap.Controller;

import frontend.WandController;

public class LeapController {
	
	public LeapController(WandController wandController) {
        Controller controller = new Controller();
        LeapListener listener = new LeapListener();
        controller.addListener(listener);
	}

}
