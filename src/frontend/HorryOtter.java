package frontend;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Cylinder;

import kinesthesis.LeapController;

public class HorryOtter extends SimpleApplication {

	private LeapController leapController;
	private WandController wandController;
	private SpellController spellController;
	private Node playerNode;


	@Override
	public void simpleInitApp() {
		cam.setLocation(rootNode.getWorldTranslation().add(new Vector3f(0,4,5)));
		cam.lookAt(new Vector3f(0,1.5f,0), new Vector3f(0,1,0));
		initializeLeap();
		initializePlayer();
		initializeWand();
		initializeSpells();

	}

	private void initializePlayer() {
		playerNode = new Node();
		rootNode.attachChild(playerNode);
	}

	private void initializeSpells() {
		Node spellNode = new Node();
		playerNode.attachChild(spellNode);
		spellController = new SpellController(spellNode, leapController, wandController);
		stateManager.attach(spellController);
	}

	private void initializeLeap() {
		leapController = new LeapController();
	}

	private void initializeWand() {
		Material unshaded = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		unshaded.setColor("Color", ColorRGBA.Brown);
		Cylinder wandShape = new Cylinder(100, 100, .2f, 1, true);
		Geometry wand = new Geometry("Wand", wandShape);
		wand.setMaterial(unshaded);
		playerNode.attachChild(wand);
		wand.setLocalTranslation(0, 0, 0);
		
		
		wandController = new WandController(wand, playerNode, cam, leapController);
	}
	
	@Override
	public void simpleUpdate(float tpf) {
		wandController.update();
	}


}
