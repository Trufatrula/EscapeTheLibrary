package prog3proyecto.juego;

import java.util.HashMap;

import org.joml.Vector3f;

import com.lndf.glengine.asset.Asset;
import com.lndf.glengine.gl.Mesh;
import com.lndf.glengine.model.Model;
import com.lndf.glengine.physics.PhysicalMaterial;
import com.lndf.glengine.physics.PhysicalTriangleMesh;
import com.lndf.glengine.scene.Component;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.components.MeshRenderer;
import com.lndf.glengine.scene.components.physics.TriangleMeshCollider;

public class Estanteria extends GameObject {
	
	private static Model modelo = null;
	private static PhysicalMaterial materialFisico;
	private static HashMap<String, PhysicalTriangleMesh> fisicas = new HashMap<>();
	
	public Estanteria(int x, int y) {
		this("", x, y);
	}

	public Estanteria(String nombre, int x, int y) {
		super(nombre);
		if (modelo == null) {
			modelo = new Model(new Asset("resource:/models/estanteria.fbx"));
		}
		GameObject t = modelo.createGameObject();
		t.getTransform().setScale(new Vector3f(2, 2, 2));
		this.addChild(t);
		if (materialFisico == null) materialFisico = new PhysicalMaterial(64, 32, 0.3f);
		crearFisicas(this, this.getName());
		float a = 1.57f * x - 8.725f;
		float b = 1.58f * y - 4.725f;
		this.getTransform().setPosition(new Vector3f(b*2,-0.2f,a*2));
	}
	
	private void crearFisicas(GameObject obj, String name) {
		for (Component comp : obj.getComponents(MeshRenderer.class)) {
			MeshRenderer renderer = (MeshRenderer) comp;
			String n = name + ":" + renderer.getName();
			PhysicalTriangleMesh TriangleMesh;
			if (Estanteria.fisicas.containsKey(n)) {
				TriangleMesh = Estanteria.fisicas.get(n);
			} else {
				Mesh mesh = renderer.getMesh();
				TriangleMesh = new PhysicalTriangleMesh(mesh);
				Estanteria.fisicas.put(n, TriangleMesh);
			}
			TriangleMeshCollider collider = new TriangleMeshCollider(materialFisico, TriangleMesh);
			obj.addComponent(collider);
		}
		for (GameObject child : obj.getChildren()) {
			crearFisicas(child, name + "/" + child.getName());
		}
	}
	
	public static void destruirCache() {
		for (PhysicalTriangleMesh mesh : Estanteria.fisicas.values()) {
			mesh.destroy();
		}
		Estanteria.fisicas.clear();
		Estanteria.materialFisico.destroy();
		Estanteria.materialFisico = null;
		Estanteria.modelo = null;
	}
	
}
