package prog3proyecto.juego;

import org.joml.Vector3f;
import com.lndf.glengine.asset.Asset;
import com.lndf.glengine.model.Model;
import com.lndf.glengine.physics.PhysicalMaterial;
import com.lndf.glengine.scene.GameObject;

public class Libro extends GameObject{

	private static Model modelo = null;
	private static PhysicalMaterial materialFisico;
	
	public Libro(int x, int y) {
		this("", x, y);
	}

	public Libro(String nombre, int x, int y) {
		super(nombre);
		if (modelo == null) {
			modelo = new Model(new Asset("resource:/models/libroFase1.fbx"));
		}
		GameObject t = modelo.createGameObject();
		t.getTransform().setScale(new Vector3f(1, 2, 1));
		this.addChild(t);
		if (materialFisico == null) materialFisico = new PhysicalMaterial(64, 32, 0.3f);
		float a = 2 * (0.785f * x - 5.1f);
		float b = 2 * (0.79f * y - 9.1f);
		this.getTransform().setPosition(new Vector3f(a,0.75f,b));
	}	
	
	public static void destruirCache() {
		Libro.materialFisico.destroy();
		Libro.materialFisico = null;
		Libro.modelo = null;
	}
}
