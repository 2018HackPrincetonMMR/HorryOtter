package kinesthesis;

import com.jme3.math.Matrix3f;
import com.leapmotion.leap.Controller;

import frontend.Spell;
import frontend.Spell.SpellType;

public class LeapController {

	private LeapListener listener;
	private static Controller controller;

	private Matrix3f wandAngle = Matrix3f.IDENTITY;
	private Spell latestSpell = new Spell(SpellType.NULL);

	public LeapController() {
		listener = new LeapListener(this);
		controller = new Controller();
		controller.addListener(listener);
	}

	public Matrix3f getWandAngle() {
		return wandAngle;
	}
	
	public Spell getLatestSpell() {
		return latestSpell;
		
	}

	public void onUpdate() {
		latestSpell = new Spell(Math.round(Math.random()) == 0 ? SpellType.LEVITATE : SpellType.SPARKS);
		wandAngle = new Matrix3f((float) Math.random(), (float) Math.random(), (float) Math.random(),
				(float) Math.random(), (float) Math.random(), (float) Math.random(), (float) Math.random(),
				(float) Math.random(), (float) Math.random());

	}

}
