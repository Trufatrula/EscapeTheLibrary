package prog3proyecto.juego;

import java.util.ArrayList;

import com.lndf.glengine.asset.Asset;
import com.lndf.glengine.gl.Mesh;
import com.lndf.glengine.model.Model;
import com.lndf.glengine.physics.PhysicalTriangleMesh;
import com.lndf.glengine.physics.PhysicalMaterial;
import com.lndf.glengine.scene.Component;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.components.MeshRenderer;
import com.lndf.glengine.scene.components.physics.TriangleMeshCollider;

public class TerrenoPrincipal extends GameObject {
	
	private Model modelo = null;
	private PhysicalMaterial materialFisico;
	private ArrayList<PhysicalTriangleMesh> fisicas = new ArrayList<>();
	
	public TerrenoPrincipal() {
		this("");
	}
	
	public TerrenoPrincipal(String nombre) {
		super(nombre);
		if (modelo == null) {
			modelo = new Model(new Asset("resource:/models/terreno.fbx"));
		}
		this.addChild(modelo.createGameObject());
		materialFisico = new PhysicalMaterial(64, 32, 0.3f);
		crearFisicas(this);
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
