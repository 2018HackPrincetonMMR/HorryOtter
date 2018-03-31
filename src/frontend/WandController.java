package frontend;
import com.jme3.math.Matrix3f;
import com.jme3.scene.Geometry;

import kinesthesis.LeapController;

public class WandController {
	
	private Geometry wand;
	private LeapController leapController;
	
	private Matrix3f angle;
	
	public WandController(Geometry wand, LeapController leapController) {
		this.leapController = leapController;
		this.wand = wand;
		this.angle = Matrix3f.IDENTITY;
	}
	
	public void update() {
		angle = leapController.getWandAngle();
		wand.setLocalRotation(angle);
	}
	
	public Matrix3f getWandAngle() {
		return angle;
	}

}
