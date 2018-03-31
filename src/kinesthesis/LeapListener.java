package kinesthesis;

import java.util.logging.Logger;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Listener;

public class LeapListener extends Listener {
	 public void onConnect(Controller controller) {
	        System.out.println("Connected");
	    }

	    public void onFrame(Controller controller) {
	    	System.out.println("Frame arrived");
	    }
}
