package frontend;

import com.jme3.math.Quaternion;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.leapmotion.leap.Vector;

import kinesthesis.LeapController;
import sphinx.SphinxController;

public class WandController {

	private Geometry wand;
	private Node playerNode;
	private LeapController leapController;
	private SphinxController sphinxController;
	private Quaternion wandRotation;

	public WandController(Geometry wand, Node playerNode, LeapController leapController, SphinxController sphinxController) {
		this.leapController = leapController;
		this.sphinxController = sphinxController;
		this.wand = wand;
		this.playerNode = playerNode;
		this.wandRotation = eulerToQuat(leapController.getEulerAngles());
	}

	public void update() {
		wandRotation = eulerToQuat(leapController.getEulerAngles());
		playerNode.setLocalRotation(wandRotation);

	}

	public Quaternion getWandAngle() {
		return wandRotation;
	}
	
	private Quaternion eulerToQuat(Vector euler) {
		Quaternion tmp = new Quaternion();
		return tmp.fromAngles(-euler.get(0), euler.get(1), euler.get(2));

	}

}
