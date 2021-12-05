package prog3proyecto.juego;

import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector4f;

import com.lndf.glengine.asset.Asset;
import com.lndf.glengine.engine.DeltaTime;
import com.lndf.glengine.gl.DefaultMaterial;
import com.lndf.glengine.model.Model;
import com.lndf.glengine.primitives.Cube;
import com.lndf.glengine.scene.Component;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.Scene;
import com.lndf.glengine.scene.components.Camera;
import com.lndf.glengine.scene.components.lighting.DirectionalLight;

import prog3proyecto.juego.componentes.Interact;
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
		demoModel = new Model(new Asset("resource:/models/demomodel/emeritofecartus.fbx"));
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
				Quaternionf r = this.getGameObject().getTransform().getRotation().rotateX((float)(( Math.PI / 4)*DeltaTime.get()));
				this.getGameObject().getTransform().setRotation(r);
			}
		});  
		
		DefaultMaterial plomo = new DefaultMaterial(new Vector4f(0.4f,0.6f,1f,1), new Vector4f(1,1,1,1), 30);
		Cube cubito = new Cube(plomo);
		cubito.getTransform().setPosition(new Vector3f(15,0,0));
		this.addObject(cubito);
		cubito.addComponent(new Interact(jugador) {

			
			@Override
			public void entrarInteract() {
				System.out.println("ESTAMOS ENTRANDO");
				
			}

			@Override
			public void salirInteract() {
				System.out.println("ESTAMOS SALIENDO");
				
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
