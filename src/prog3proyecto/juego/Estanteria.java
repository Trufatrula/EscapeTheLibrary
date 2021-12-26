package prog3proyecto.juego;

import java.util.ArrayList;
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
	
	private Model modelo = null;
	private PhysicalMaterial materialFisico;
	private ArrayList<PhysicalTriangleMesh> fisicas = new ArrayList<>();
	
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
		materialFisico = new PhysicalMaterial(64, 32, 0.3f);
		crearFisicas(this);
		float a = 1.57f * x - 5.5f;
		float b = 1.58f * y - 9.5f;
		this.getTransform().setPosition(new Vector3f(b*2,0.5f,a*2));
	}
	
	private void crearFisicas(GameObject obj) {
		for (Component comp : obj.getComponents(MeshRenderer.class)) {
			MeshRenderer renderer = (MeshRenderer) comp;
			Mesh mesh = renderer.getMesh();
			PhysicalTriangleMesh TriangleMesh = new PhysicalTriangleMesh(mesh);
			TriangleMeshCollider collider = new TriangleMeshCollider(materialFisico, TriangleMesh);
			obj.addComponent(collider);
		}
		for (GameObject child : obj.getChildren()) {
			crearFisicas(child);
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
		for (PhysicalTriangleMesh mesh : fisicas) {
			mesh.destroy();
		}
		fisicas.clear();
		materialFisico.destroy();
	}
	
	
	
}
