package kinesthesis;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Listener;

public class LeapListener extends Listener {
	private LeapController leapController;

	public LeapListener(LeapController leapController) {
		this.leapController = leapController;
	}

	@Override
	public void onInit(Controller controller) {
		System.out.println("Initialized");
	}

	@Override
	public void onConnect(Controller controller) {
		System.out.println("Connected");
	}

	@Override
	public void onDisconnect(Controller controller) {
		// Note: not dispatched when running in a debugger.
		System.out.println("Disconnected");
	}

	@Override
	public void onFrame(Controller controller) {
		System.out.println("I've been framed");
		leapController.onUpdate();



	}
}
