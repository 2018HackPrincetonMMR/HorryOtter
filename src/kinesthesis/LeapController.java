package kinesthesis;

import com.jme3.math.Matrix3f;
import com.leapmotion.leap.Controller;

import frontend.Spell;
import frontend.Spell.SpellType;

public class LeapController {

	private LeapListener listener;
	private static Controller controller;

	private Vector wandAngle = new Vector();
	private Spell latestSpell = new Spell(SpellType.NULL);

	public LeapController() {
		listener = new LeapListener(this);
		controller = new Controller();
		controller.addListener(listener);
	}

	public Vector getWandAngle() {
		return wandAngle;
	}

	public Spell getLatestSpell() {
		return latestSpell;

	}

	public void onUpdate() {
		if (listener.getSwipe()) {
			latestSpell = new Spell(SpellType.SPARKS);
		}
		if (listener.getKeyTap()) {
			latestSpell = new Spell(SpellType.LEVITATE);
		}
		wandAngle = listener.getAngle();

	}

}
