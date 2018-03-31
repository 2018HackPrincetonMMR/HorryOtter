package kinesthesis;

import java.util.logging.Logger;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;

public class LeapListener extends Listener {

	private LeapController controller;

	private boolean keyTap= new false;
  public boolean getKeyTap() { return keyTap; }

	private boolean swipe = false;
  public boolean getSwipe() { return swipe; }

  private Vector angleOfTheDangle = new Vector();
  public Vector getAngle() { return angleOfTheDangle; }

	private float pitch = 0.0;
	private float yaw = 0.0;
	private float roll = 0.0;

	public float getRoll() {
		return roll;
	}

	public float getYaw() {
		return yaw;
	}
	public float getPitch() {
		return pitch;
	}

	public LeapListener(LeapController c) {
		this.controller = c;
	}

  public void onFrame(Controller controller) {
  	Frame frame = controller.frame();
    if (!frame.hands().isEmpty()) {
    	Screen screen = controller.calibratedScreens().get(0);
    	if (screen != null && screen.isValid()){
    		Hand hand = frame.hands().get(0);
    		if (hand.isValid() && (hand.fingers().count() == 1))  {
    			pitch = hand.Direction.Pitch;
					yaw = hand.Direction.Yaw;
					roll = hand.PalmNormal.Roll;

    		}
    	}
    }
    keyTap = false;
		swipe = false;
    GestureList gestures = frame.gestures();
    for (int i = 0; i < gestures.count(); i++) {
    	if(gestures.get(i).type()==Gesture.Type.TYPE_KEY_TAP){
    		keyTap = true;
				break;
      }
			if(gestures.get(i).type()==Gesture.Type.TYPE_SWIPE){
    		swipe = true;
				break;
      }
    }

  }
}
