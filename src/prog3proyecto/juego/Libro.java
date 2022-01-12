package prog3proyecto.juego;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.lndf.glengine.asset.Asset;
import com.lndf.glengine.model.Model;
import com.lndf.glengine.physics.PhysicalMaterial;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.components.physics.BoxCharacterController;

import prog3proyecto.juego.componentes.ObjetoLlevable;
import prog3proyecto.juego.componentes.Respawn;

public class Libro extends GameObject{

	private static Model modelo = null;
	private static PhysicalMaterial materialFisico;
	private BoxCharacterController controller;
	private ObjetoLlevable llevable;
	
	public Libro(int x, int y, Jugador jugador) {
		this("", x, y, jugador);
	}

	public Libro(String nombre, int x, int y, Jugador jugador) {
		this(nombre, new Vector3f(2 * (0.785f * x - 5.1f), 0.75f, 2 * (0.79f * y - 9.1f)), jugador);
	}
	
	public Libro(Vector3f pos, Jugador jugador) {
		this("", pos, jugador);
	}
	
	public Libro(String nombre, Vector3f pos, Jugador jugador) {
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
		llevable = new ObjetoLlevable(jugador, controller, jugador.getObjetoMano());
		this.addComponent(llevable);
		this.addComponent(new Respawn(0, pos, new Quaternionf()));
		this.getTransform().setPosition(pos);
		this.getTransform().setScale(new Vector3f(0.5f));
	}
	
	public static void destruirCache() {
		if (Libro.materialFisico != null) Libro.materialFisico.destroy();
		Libro.materialFisico = null;
		Libro.modelo = null;
	}
}
