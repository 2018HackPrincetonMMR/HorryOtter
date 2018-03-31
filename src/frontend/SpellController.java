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

	private Spell lastSpell;
	private long timeOfLastSpell;
	private final int DEBOUNSE_TIME = 1000;
	private final int LASTING_TIME = 300;

	private Geometry beam;
	private Node shootables;

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

	public SpellController(Node spellNode, LeapController leapController, WandController wandController, Node shootables) {
		this.lastSpell = leapController.getLatestSpell();
		this.leapController = leapController;
		this.spellNode = spellNode;
		this.wandController = wandController;
		timeOfLastSpell = 0;
		this.shootables = shootables;
	}

	@Override
	public void update(float tpf) {
		long currentTime = System.currentTimeMillis();
		
		if (currentTime - timeOfLastSpell > LASTING_TIME)
			spellNode.detachAllChildren();

		if (currentTime - timeOfLastSpell < DEBOUNSE_TIME)
			return;

		Spell nextSpell = leapController.getLatestSpell();
		if (nextSpell.getID() == lastSpell.getID())
			return;

		switch (nextSpell.getType()) {
		case NULL:
			break;
		case LEVITATE:
			timeOfLastSpell = currentTime;

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
		// TODO Auto-generated method stub

	}
}
