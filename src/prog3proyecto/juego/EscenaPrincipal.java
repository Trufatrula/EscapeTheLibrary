package prog3proyecto.juego;

import org.joml.Vector3f;
import org.joml.Vector4f;

import com.lndf.glengine.gl.DefaultMaterial;
import com.lndf.glengine.physics.PhysicalMaterial;
import com.lndf.glengine.primitives.Cube;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.Scene;
import com.lndf.glengine.scene.components.Camera;
import com.lndf.glengine.scene.components.physics.BoxCollider;
import com.lndf.glengine.scene.components.physics.DynamicRigidBody;

import prog3proyecto.juego.componentes.Movimiento;
import prog3proyecto.juego.componentes.Rotacion;
import prog3proyecto.main.DatosJugador;

public class EscenaPrincipal extends Scene {
	
	private TerrenoPrincipal terreno;
	private Camera camara;
	private GameObject jugador;
	private DatosJugador datos;
	
	public EscenaPrincipal(DatosJugador datos) {
		this.subscribeToUpdates();
		this.addUpdateRunnable(new ActualizarEscena(this));
		this.datos = datos;
		this.camara = new Camera((float) Math.PI / 4, 1000);
		this.terreno = new TerrenoPrincipal();
		this.addObject(this.terreno);
		
		///TEMPORAL
		this.jugador = new GameObject();
		this.jugador.addComponent(this.camara);
		this.jugador.addComponent(new Movimiento());
		this.jugador.addComponent(new Rotacion());
		this.addObject(this.jugador);
		this.setAmbientLight(1);
		DefaultMaterial m = new DefaultMaterial(new Vector4f(0,1,0,1), new Vector4f(0,0,0,1), 1);
		Cube c1 = new Cube(m);
		c1.addComponent(new BoxCollider(new PhysicalMaterial(0.5f,0.5f,0.5f)));
		c1.addComponent(new DynamicRigidBody());
		c1.getTransform().setPosition(new Vector3f(0, 200, -5));
		this.addObject(c1);
		Cube c2 = new Cube(m);
		c2.addComponent(new BoxCollider(new PhysicalMaterial(0.5f,0.5f,0.5f)));
		c2.addComponent(new DynamicRigidBody());
		c2.getTransform().setPosition(new Vector3f(0, 204, -5));
		this.addObject(c2);
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
