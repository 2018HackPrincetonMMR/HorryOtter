package frontend;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;

public class Application extends SimpleApplication {
	private WandController WAND_CONTROLLER;
		
	@Override
	public void simpleInitApp() {
		makeWand();
		
		
	}
	
	private void makeWand() {
		Material unshaded = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		unshaded.setColor("Color", ColorRGBA.Brown);
		
		Cylinder wandShape = new Cylinder(100,100,.2f,1, true);
		Geometry wand = new Geometry("Wand", wandShape);
		wand.setMaterial(unshaded);
		wand.rotate(.5f, 0, 0);
		rootNode.attachChild(wand);
		
		WAND_CONTROLLER = new WandController(wand);
		
	}
	
	public WandController getWandController() {
		return WAND_CONTROLLER;
	}
}
