package prog3proyecto.juego;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.lndf.glengine.physics.PhysicalMaterial;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.components.Camera;
import com.lndf.glengine.scene.components.lighting.PointLight;
import com.lndf.glengine.scene.components.physics.CapsuleCharacterController;

import prog3proyecto.juego.componentes.Fase1;
import prog3proyecto.juego.componentes.MovimientoFisicas;
import prog3proyecto.juego.componentes.RotacionFisicas;

public class Jugador extends GameObject{
	
	private Camera camara;
	private GameObject objetoCamara;
	private GameObject objetoMano;
	private CapsuleCharacterController controller;
	
	public Jugador() {
		super("");
		PhysicalMaterial mat = new PhysicalMaterial(64, 32, 0);
		this.controller = new CapsuleCharacterController(mat, 0.25f, 0.25f);
		this.controller.setConstrainedClimbing(true);
		this.controller.setStepOffset(0.16f);
		MovimientoFisicas mov = new MovimientoFisicas(this.controller);
		RotacionFisicas rot = new RotacionFisicas();
		this.objetoMano = new GameObject();
		this.objetoMano.getTransform().setPosition(new Vector3f(0,0,-0.75f));
		this.addChild(this.objetoMano);
		this.camara = new Camera((float) Math.PI / 4, 1000);
		this.objetoCamara = new GameObject();
		this.objetoCamara.getTransform().setPosition(new Vector3f(0, 0.125f, 0));
		this.addChild(this.objetoCamara);
		this.objetoCamara.addComponent(this.camara);
		this.objetoCamara.addComponent(new PointLight(new Vector3f(1, 1, 1), 0f, 1f, 1f));
		this.addComponent(mov);
		this.addComponent(rot);
		this.addComponent(this.controller);
		this.addComponent(new Fase1());
		this.setYaw((float) Math.PI);
	}
	
	public Camera getCamara() {
		return camara;
	}

	public GameObject getObjetoCamara() {
		return objetoCamara;
	}
	
	public GameObject getObjetoMano() {
		return objetoMano;
	}

	public float getPitch() {
		return this.objetoCamara.getTransform().getRotation().getEulerAnglesXYZ(new Vector3f()).x;
	}
	
	public float getYaw() {
		return this.getTransform().getRotation().getEulerAnglesXYZ(new Vector3f()).y;
	}
	
	public void rotatePitch(float angle) {
		this.getObjetoCamara().getTransform().setRotation(this.objetoCamara.getTransform().getRotation().rotateX(angle));
	}
	
	public void rotateYaw(float angle) {
		this.getTransform().setRotation(this.getTransform().getRotation().rotateY(angle));
	}
	
	public void setPitch(float angle) {
		this.objetoCamara.getTransform().setRotation(new Quaternionf().rotateX(angle));
	}
	
	public void setYaw(float angle) {
		this.getTransform().setRotation(new Quaternionf().rotateY(angle));
	}
	
}
