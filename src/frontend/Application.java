package frontend;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Cylinder;

import kinesthesis.LeapController;

public class Application extends SimpleApplication {

	private LeapController leapController;
	private Geometry wand;

	@Override
	public void simpleInitApp() {
		initializeLeap();
		makeWand();

	}

	@Override
	public void simpleUpdate(float tpf) {
		wand.setLocalRotation(leapController.getWandAngle());
		System.out.println(leapController.getLatestSpell());
	}

	private void initializeLeap() {
		leapController = new LeapController();
	}

	private void makeWand() {
		Material unshaded = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		unshaded.setColor("Color", ColorRGBA.Brown);

		Cylinder wandShape = new Cylinder(100, 100, .2f, 1, true);
		wand = new Geometry("Wand", wandShape);
		wand.setMaterial(unshaded);
		wand.rotate(.5f, 0, 0);
		rootNode.attachChild(wand);

	}

}
