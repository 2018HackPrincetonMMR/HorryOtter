package kinesthesis;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Vector;

import frontend.Spell;
import frontend.Spell.GestureSpell;
import frontend.Spell.SpellType;

public class LeapController {

	private LeapListener listener;
	private static Controller controller;

	private Vector eulerAngle = new Vector();
	private Spell latestSpell = new Spell.GestureSpell(SpellType.NULL);

	public LeapController() {
	}
	public void start() {
		
		listener = new LeapListener(this);
		controller = new Controller();
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP,true);
		controller.enableGesture(Gesture.Type.TYPE_SWIPE,true);

		controller.addListener(listener);
	}

	public Vector getEulerAngles() {
		return eulerAngle;
	}

	public Spell getLatestSpell() {
		return latestSpell;

	}

	public void onUpdate() {
		if (listener.getSwipe()) {
			latestSpell = new Spell.GestureSpell(SpellType.LEVITATE);
		}
		else if (listener.getKeyTap()) {
			latestSpell = new Spell.GestureSpell(SpellType.SPARKS);
		}
		else {
		}
		eulerAngle = listener.getAngle();

	}
	public void setLatestSpell(GestureSpell gestureSpell) {
		latestSpell = gestureSpell;
		
	}

}
