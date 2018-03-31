package frontend;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
<<<<<<< HEAD
import com.jme3.scene.Node;
=======
import com.leapmotion.leap.Vector;
>>>>>>> 840f62925f29c5c5c33b063154bb15cd8fe3d24f

import kinesthesis.LeapController;

public class WandController {
	
	private Geometry wand;
	private Node playerNode;
	private LeapController leapController;
	private Camera cam;
	
	private Vector angle;
	
	
	public WandController(Geometry wand, Node playerNode, Camera cam, LeapController leapController) {
		this.leapController = leapController;
		this.wand = wand;
<<<<<<< HEAD
		this.angle = Matrix3f.IDENTITY;
		this.playerNode = playerNode;
		this.cam = cam;
=======
		this.angle = leapController.getWandAngle();
>>>>>>> 840f62925f29c5c5c33b063154bb15cd8fe3d24f
	}
	
	public void update() {
		angle = leapController.getWandAngle();
		playerNode.setLocalRotation(angle);
		
	}
	
	public Vector getWandAngle() {
		return angle;
	}

}
