package frontend;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Cylinder;

import kinesthesis.LeapController;

public class SpellController extends AbstractAppState {

	private HorryOtter app;

	private Node spellNode;
	private LeapController leapController;
	private WandController wandController;
	private SphinxController sphinxController;

	private Spell lastSpell;
	private long timeOfLastSpell;
	private final int DEBOUNCE_TIME = 1000;
	private final int LASTING_TIME = 300;
	private final int BUFFER_TIME = 750;

	private Geometry beam;
	private Node shootables;
	
	private boolean currentlyCastingLevitate = false;
	private Geometry hitObj;

	@Override
	public void initialize(AppStateManager stateManager, Application app) {
		super.initialize(stateManager, app);
		this.app = (HorryOtter) app;

		Material unshaded = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
		unshaded.setColor("Color", ColorRGBA.Yellow);
		Cylinder beamShape = new Cylinder(100, 100, .2f, 10, true);
		beam = new Geometry("Wand", beamShape);
		beam.setLocalTranslation(new Vector3f(0, 0, -10));
		beam.setMaterial(unshaded);
	}

	public SpellController(Node spellNode, LeapController leapController, WandController wandController, SphinxController sphinxController, Node shootables) {
		this.lastSpell = leapController.getLatestSpell();
		this.leapController = leapController;
		this.sphinxController = sphinxController;
		this.spellNode = spellNode;
		this.wandController = wandController;
		timeOfLastSpell = 0;
		this.shootables = shootables;
	}

	@Override
	public void update(float tpf) {
		long currentTime = System.currentTimeMillis();
		
		if (currentTime - timeOfLastSpell > LASTING_TIME) {
			currentlyCastingLevitate = false;
			spellNode.detachAllChildren();
		}
		else if (currentlyCastingLevitate && hitObj != null && beam != null) {
			hitObj.setLocalTranslation(new Vector3f(hitObj.getLocalTranslation().x, beam.getWorldTranslation().y, hitObj.getLocalTranslation().z));
		}

		if (currentTime - timeOfLastSpell < DEBOUNCE_TIME)
			return;

		Spell nextGestureSpell = leapController.getLatestSpell();
		Spell nextSpeechSpell = sphinxController.getLatestSpell();
		
		if (Math.abs(nextGestureSpell.getID() - nextSpeechSpell.getID()) >= BUFFER_TIME)
			return;

		switch (nextSpeechSpell.getType()) {
		case NULL:
			break;
		case LEVITATE:
			timeOfLastSpell = currentTime;
			castLevitate();
			break;
		case SPARKS:
			timeOfLastSpell = currentTime;
			castSpark();
			break;
		}
	}

	private void castSpark() {
		spellNode.attachChild(beam);
		CollisionResults results = new CollisionResults();
		Ray ray = new Ray(spellNode.getWorldTranslation(), beam.getWorldTranslation());
		shootables.collideWith(ray, results);
		CollisionResult res = results.getClosestCollision();
		if(res != null) {
			res.getGeometry().setLocalScale(res.getGeometry().getLocalScale().mult(.7f));
		}
		
	}

	private void castLevitate() {
		currentlyCastingLevitate = true;
		spellNode.attachChild(beam);
		CollisionResults results = new CollisionResults();
		Ray ray = new Ray(spellNode.getWorldTranslation(), beam.getWorldTranslation());
		shootables.collideWith(ray, results);
		CollisionResult res = results.getClosestCollision();
		if (res != null) {
			hitObj = res.getGeometry();
		}
	}
	
	private void castAvadaKedavara() {
		spellNode.attachChild(beam);
		CollisionResults results = new CollisionResults();
		Ray ray = new Ray(spellNode.getWorldTranslation(), beam.getWorldTranslation());
		shootables.collideWith(ray, results);
		CollisionResult res = results.getClosestCollision();
		if(res != null) {
			res.getGeometry().setLocalScale(res.getGeometry().getLocalScale().mult(0f));
		}
	}
	
	private void castLumos() {
		AmbientLight al = new AmbientLight();
		al.setColor(ColorRGBA.White.mult(1.3f));
		rootNode.addLight(al);
	}
	
	private void castExpectoPatronum() {
		
	}
	
	
}
