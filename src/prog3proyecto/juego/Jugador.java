package prog3proyecto.juego;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.lndf.glengine.physics.PhysicalMaterial;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.components.Camera;
import com.lndf.glengine.scene.components.physics.CapsuleCharacterController;

import prog3proyecto.juego.componentes.MovimientoFisicas;
import prog3proyecto.juego.componentes.RotacionFisicas;

public class Jugador extends GameObject{
	
	private Camera camara;
	private GameObject objetoCamara;
	private CapsuleCharacterController controller;
	
	public Jugador() {
		super("");
		PhysicalMaterial mat = new PhysicalMaterial(64, 32, 0);
		this.controller = new CapsuleCharacterController(mat, 0.25f, 0.25f);
		MovimientoFisicas mov = new MovimientoFisicas(this.controller);
		RotacionFisicas rot = new RotacionFisicas();
		this.camara = new Camera((float) Math.PI / 4, 1000);
		this.objetoCamara = new GameObject();
		this.objetoCamara.getTransform().setPosition(new Vector3f(0, 0.125f, 0));
		this.addChild(this.objetoCamara);
		this.objetoCamara.addComponent(this.camara);
		this.addComponent(mov);
		this.addComponent(rot);
		this.addComponent(this.controller);
	}
	
	public Camera getCamara() {
		return camara;
	}

	public GameObject getObjetoCamara() {
		return objetoCamara;
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
