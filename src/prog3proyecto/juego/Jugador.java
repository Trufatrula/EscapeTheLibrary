package prog3proyecto.juego;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.lndf.glengine.physics.PhysicalMaterial;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.components.Camera;
import com.lndf.glengine.scene.components.physics.CapsuleCollider;
import com.lndf.glengine.scene.components.physics.DynamicRigidBody;

import prog3proyecto.juego.componentes.MovimientoFisicas;
import prog3proyecto.juego.componentes.RotacionFisicas;

public class Jugador extends GameObject{
	
	private Camera camara;
	private GameObject objetoCamara;
	private DynamicRigidBody rigidBody;
	
	public Jugador() {
		super("");
		PhysicalMaterial material = new PhysicalMaterial(128, 64, 0);
		this.rigidBody = new DynamicRigidBody();
		MovimientoFisicas mov = new MovimientoFisicas(this.rigidBody);
		RotacionFisicas rot = new RotacionFisicas();
		rigidBody.setXRotationLock(true);
		rigidBody.setYRotationLock(true);
		rigidBody.setZRotationLock(true);
		CapsuleCollider colisionador = new CapsuleCollider(material, 0.25f, 0.25f);
		colisionador.setRotation(new Quaternionf().rotateZ(((float) Math.PI / 2)));
		this.camara = new Camera((float) Math.PI / 4, 1000);
		this.objetoCamara = new GameObject();
		this.objetoCamara.getTransform().setPosition(new Vector3f(0, 0.125f, 0));
		this.addChild(this.objetoCamara);
		this.objetoCamara.addComponent(this.camara);
		this.addComponent(rigidBody);
		this.addComponent(colisionador);
		this.addComponent(colisionador);
		this.addComponent(mov);
		this.addComponent(rot);
	}
	
	public Camera getCamara() {
		return camara;
	}

	public GameObject getObjetoCamara() {
		return objetoCamara;
	}

	public DynamicRigidBody getRigidBody() {
		return rigidBody;
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
