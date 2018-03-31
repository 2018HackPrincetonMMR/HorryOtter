package frontend;

import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class WandController {
	
	private Spatial wand;
	
	public WandController(Spatial wand) {
		this.wand = wand;
	}
	
	public void setAngle(Matrix3f angle) {
		wand.setLocalRotation(angle);
	}
	
	public void translate(Vector3f offset) {
		wand.move(offset);
	}

	
}
