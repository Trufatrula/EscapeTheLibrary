package prog3proyecto.juego;

import com.lndf.glengine.asset.Asset;
import com.lndf.glengine.model.Model;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.Scene;
import com.lndf.glengine.scene.components.FPCamera;
import com.lndf.glengine.scene.components.lighting.DirectionalLight;

public class EscenaPrincipal extends Scene {
	
	private Model demoModel;
	private FPCamera camara;
	
	public EscenaPrincipal() {
		GameObject cameraObject = new GameObject();
		GameObject luzDemoObject = new GameObject();
		DirectionalLight luzDemo = new DirectionalLight();
		this.subscribeToUpdates();
		luzDemoObject.getTransform().getRotation().rotateX(-(float) Math.PI / 4 );
		luzDemoObject.getTransform().getRotation().rotateY((float) Math.PI / 4 );

		demoModel = new Model(new Asset("resource:/models/demomodel/demo.obj"));
		camara = new FPCamera((float) Math.PI / 4, 100);
		cameraObject.addComponent(camara);
		luzDemoObject.addComponent(luzDemo);
		this.addObject(demoModel.createGameObject());
		this.addObject(cameraObject);
		this.addObject(luzDemoObject);
		this.setAmbientLight(0.1f);
	}
	
	public FPCamera getCamara() {
		return camara;
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
	}
	
}
