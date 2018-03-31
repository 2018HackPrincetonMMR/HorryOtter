package frontend;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
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
		wand.rotate(.5f, 0, 0);
		playerNode.attachChild(wand);
		
		wandController = new WandController(wand, leapController);

	}
	
	@Override
	public void simpleUpdate(float tpf) {
		wandController.update();
	}


}
