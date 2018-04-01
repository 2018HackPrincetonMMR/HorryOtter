package frontend;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;

import kinesthesis.LeapController;
import sphinx.SphinxController;

public class HorryOtter extends SimpleApplication {

	private LeapController leapController;
	private WandController wandController;
	private SpellController spellController;
	private SphinxController sphinxController;
	private Node playerNode;
	private Node shootables;

	@Override
	public void simpleInitApp() {
		cam.setLocation(rootNode.getWorldTranslation().add(new Vector3f(0, 0, 5)));
		cam.lookAt(new Vector3f(0, 0, -10), new Vector3f(0,-1,0));
		initializeLeap();
		initializeSphinx();
		initializeEnvironment();
		initializePlayer();
		initializeWand();
		initializeSpells();

	}

	private void initializeEnvironment() {
		shootables = new Node();
		Box b = new Box(1, 1, 1);
		Geometry geom = new Geometry("Box1", b);
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.Blue);
		geom.setMaterial(mat);
		shootables.attachChild(geom);
		geom.setLocalTranslation(-4, 0, -8);
		
		Box b2 = new Box(1, 1, 1);
		Geometry geom2 = new Geometry("Box2", b2);
		Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat2.setColor("Color", ColorRGBA.Red);
		geom2.setMaterial(mat2);
		shootables.attachChild(geom2);
		geom2.setLocalTranslation(4
				, 0, -8);
		
		
		rootNode.attachChild(shootables);

	}

	private void initializePlayer() {
		playerNode = new Node();
		rootNode.attachChild(playerNode);
	}

	private void initializeSpells() {
		Node spellNode = new Node();
		playerNode.attachChild(spellNode);
		spellController = new SpellController(spellNode, leapController, wandController, sphinxController, shootables);
		stateManager.attach(spellController);
		stateManager.attach(sphinxController);
	}

	private void initializeLeap() {
		leapController = new LeapController();
	}
	
	private void initializeSphinx() {
		sphinxController = new SphinxController();
	}

	private void initializeWand() {
		Material unshaded = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		unshaded.setColor("Color", ColorRGBA.Brown);
		Cylinder wandShape = new Cylinder(100, 100, .2f, 1, true);
		Geometry wand = new Geometry("Wand", wandShape);
		wand.setMaterial(unshaded);
		playerNode.attachChild(wand);
		wand.setLocalTranslation(0, 0, 0);

		wandController = new WandController(wand, playerNode, leapController, sphinxController);
	}

	@Override
	public void simpleUpdate(float tpf) {
		wandController.update();
	}

}
