package prog3proyecto.juego;

import org.joml.Vector3f;

import com.lndf.glengine.asset.Asset;
import com.lndf.glengine.engine.DeltaTime;
import com.lndf.glengine.model.Model;
import com.lndf.glengine.scene.Component;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.Scene;
import com.lndf.glengine.scene.components.Camera;
import com.lndf.glengine.scene.components.lighting.DirectionalLight;

import prog3proyecto.juego.componentes.Movimiento;
import prog3proyecto.juego.componentes.Rotacion;
import prog3proyecto.main.DatosJugador;

public class EscenaPrincipal extends Scene {
	
	private Model demoModel;
	private Camera camara;
	private GameObject jugador;
	private DatosJugador datos;
	private Movimiento movimiento;
	private Rotacion rotacion;
	
	public EscenaPrincipal(DatosJugador datos) {
		this.subscribeToUpdates();
		this.addUpdateRunnable(new ActualizarEscena(this));
		this.datos = datos;
		GameObject luzDemoObject = new GameObject();
		DirectionalLight luzDemo = new DirectionalLight();
		jugador = new GameObject();
		demoModel = new Model(new Asset("resource:/models/demomodel/demo.obj"));
		camara = new Camera((float) Math.PI / 4, 100);
		movimiento = new Movimiento();
		rotacion = new Rotacion();
		jugador.addComponent(camara);
		jugador.addComponent(movimiento);
		jugador.addComponent(rotacion);
		luzDemoObject.addComponent(luzDemo);
		this.addObject(demoModel.createGameObject());
		this.addObject(jugador);
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
	
	public Camera getCamara() {
		return camara;
	}
	
	public GameObject getJugador() {
		return jugador;
	}
	
	public DatosJugador getDatos() {
		return datos;
	}
}
