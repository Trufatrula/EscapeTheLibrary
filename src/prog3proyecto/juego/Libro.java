package prog3proyecto.juego;

import org.joml.Vector3f;

import com.lndf.glengine.asset.Asset;
import com.lndf.glengine.model.Model;
import com.lndf.glengine.physics.PhysicalMaterial;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.components.physics.BoxCharacterController;
import com.lndf.glengine.scene.components.physics.BoxCollider;
import com.lndf.glengine.scene.components.physics.DynamicRigidBody;

import prog3proyecto.juego.componentes.ObjetoLlevable;

public class Libro extends GameObject{

	private static Model modelo = null;
	private static PhysicalMaterial materialFisico;
	private BoxCharacterController controller;
	private ObjetoLlevable llevable;
	
	public Libro(int x, int y, EscenaPrincipal escena) {
		this("", x, y, escena);
	}

	public Libro(String nombre, int x, int y, EscenaPrincipal escena) {
		super(nombre);
		if (modelo == null) {
			modelo = new Model(new Asset("resource:/models/libroFase1.fbx"));
		}
		GameObject t = modelo.createGameObject();
		t.getTransform().setScale(new Vector3f(1, 2, 1));
		this.addChild(t);
		if (materialFisico == null) materialFisico = new PhysicalMaterial(64, 32, 0.3f);
		controller = new BoxCharacterController(materialFisico, 0.129f, 0.02325f, 0.0945f);
		controller.setStepOffset(0);
		controller.setContactOffset(0.001f);
		this.addComponent(controller);
		llevable = new ObjetoLlevable(escena.getJugador(), controller, escena.getJugador().getObjetoMano());
		this.addComponent(llevable);
		float a = 2 * (0.785f * x - 5.1f);
		float b = 2 * (0.79f * y - 9.1f);
		this.getTransform().setPosition(new Vector3f(a,0.75f,b));
		this.getTransform().setScale(new Vector3f(0.5f));
	}
	
	public static void destruirCache() {
		if (Libro.materialFisico != null) Libro.materialFisico.destroy();
		Libro.materialFisico = null;
		Libro.modelo = null;
	}
}
