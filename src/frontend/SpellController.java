package frontend;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
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

	Geometry beam;

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

	public SpellController(Node spellNode, LeapController leapController, WandController wandController) {
		this.lastSpell = leapController.getLatestSpell();
		this.leapController = leapController;
		this.spellNode = spellNode;
		this.wandController = wandController;
		timeOfLastSpell = 0;
	}

	@Override
	public void update(float tpf) {
		long currentTime = System.currentTimeMillis();

		if (currentTime - timeOfLastSpell < DEBOUNSE_TIME)
			return;
		
		spellNode.detachAllChildren();
		

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
	}

	private void castLevitate() {
		// TODO Auto-generated method stub

	}
}
