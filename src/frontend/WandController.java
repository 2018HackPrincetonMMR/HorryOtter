package frontend;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

import kinesthesis.LeapController;

public class WandController {
	
	private Geometry wand;
	private Node playerNode;
	private LeapController leapController;
	private Camera cam;
	
	private Matrix3f angle;
	
	public WandController(Geometry wand, Node playerNode, Camera cam, LeapController leapController) {
		this.leapController = leapController;
		this.wand = wand;
		this.angle = Matrix3f.IDENTITY;
		this.playerNode = playerNode;
		this.cam = cam;
	}
	
	public void update() {
		angle = leapController.getWandAngle();
		playerNode.setLocalRotation(angle);
		
	}
	
	public Matrix3f getWandAngle() {
		return angle;
	}

}
