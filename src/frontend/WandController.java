package frontend;
import com.jme3.math.Matrix3f;
import com.jme3.scene.Geometry;
import com.leapmotion.leap.Vector;

import kinesthesis.LeapController;

public class WandController {
	
	private Geometry wand;
	private LeapController leapController;
	
	private Vector angle;
	
	
	public WandController(Geometry wand, LeapController leapController) {
		this.leapController = leapController;
		this.wand = wand;
		this.angle = leapController.getWandAngle();
	}
	
	public void update() {
		angle = leapController.getWandAngle();
		wand.setLocalRotation(angle);
	}
	
	public Vector getWandAngle() {
		return angle;
	}

}
