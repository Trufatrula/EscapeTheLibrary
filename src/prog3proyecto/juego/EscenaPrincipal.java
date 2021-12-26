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

import prog3proyecto.main.DatosJugador;

public class EscenaPrincipal extends Scene {
	
	private TerrenoPrincipal terreno;
	private Camera camara;
	private Jugador jugador;
	private DatosJugador datos;
	
	public EscenaPrincipal(DatosJugador datos) {
		DefaultMaterial mat = new DefaultMaterial(new Vector4f(1,0,0,1), new Vector4f(1,1,1,1), 1);
		DynamicRigidBody rigid = new DynamicRigidBody();
		BoxCollider col = new BoxCollider(new PhysicalMaterial(1,1,1));
		Cube c = new Cube(mat);
		this.subscribeToUpdates();
		this.addUpdateRunnable(new ActualizarEscena(this));
		this.datos = datos;
		this.terreno = new TerrenoPrincipal();
		this.addObject(this.terreno);
		this.jugador = new Jugador();
		this.camara = this.jugador.getCamara();
		this.addObject(this.jugador);
		this.jugador.getTransform().setPosition(new Vector3f(0, 1.5f, 0));
		this.setAmbientLight(0.1f);
		c.getTransform().setPosition(new Vector3f(1,1,1));
		c.getTransform().setScale(new Vector3f(0.1f,0.1f,0.1f));
		c.addComponent(col);
		c.addComponent(rigid);
		this.addObject(c);
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
