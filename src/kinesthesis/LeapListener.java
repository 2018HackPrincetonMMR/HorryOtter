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

	private BooleanProperty keyTap= new SimpleBooleanProperty(false);
  public BooleanProperty keyTapProperty() { return keyTap; }

	private BooleanProperty swipe = new SimpleBooleanProperty(false);
  public BooleanProperty swipeProperty() { return swipe; }

  private Vector angleOfTheDangle = new Vector();
  public Vector getAngle() { return angleOfTheDangle; }

	public LeapListener(LeapController c) {
		this.controller = c;
	}

  public void onFrame(Controller controller) {
  	Frame frame = controller.frame();
    if (!frame.hands().empty()) {
    	Screen screen = controller.calibratedScreens().get(0);
    	if (screen != null && screen.isValid()){
    		Hand hand = frame.hands().get(0);
    		if (hand.isValid() && (hand.fingers().count() == 1))  {
    			Vector intersect = screen.intersect(hand.palmPosition(),hand.direction(), true);
    		}
    	}
    }
    keyTap.set(false);
		swipe.set(false);
    GestureList gestures = frame.gestures();
    for (int i = 0; i < gestures.count(); i++) {
    	if(gestures.get(i).type()==Gesture.Type.TYPE_KEY_TAP){
    		keyTap.set(true); break;
      }
			if(gestures.get(i).type()==Gesture.Type.TYPE_SWIPE){
    		swipe.set(true); break;
      }
    }

  }
}
