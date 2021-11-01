package prog3proyecto.juego;

import org.joml.Vector3f;

import com.lndf.glengine.asset.Asset;
import com.lndf.glengine.engine.DeltaTime;
import com.lndf.glengine.model.Model;
import com.lndf.glengine.scene.Component;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.Scene;
import com.lndf.glengine.scene.components.FPCamera;
import com.lndf.glengine.scene.components.lighting.DirectionalLight;

public class EscenaPrincipal extends Scene {
	
	private Model demoModel;
	private FPCamera camara;
	
	public EscenaPrincipal() {
		this.subscribeToUpdates();
		this.addUpdateRunnable(new ActualizarEscena());
		GameObject cameraObject = new GameObject();
		GameObject luzDemoObject = new GameObject();
		DirectionalLight luzDemo = new DirectionalLight();

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
		luzDemoObject.addComponent(new Component() {
			float r = 0f;
			float d = 0.8f;
			@Override
			public void update() {
				luzDemo.setColor(new Vector3f(r, 0, 0));
				r += d*DeltaTime.get();
				if(r>1 || r<0 ) {
					d = -d;
				}
				this.getGameObject().getTransform().getRotation().rotateX((float)(( Math.PI / 4)*DeltaTime.get()));
			}
		});  
	}
	
	public FPCamera getCamara() {
		return camara;
	}
	
}
