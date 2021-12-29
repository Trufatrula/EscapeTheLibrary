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
import com.lndf.glengine.scene.components.physics.DynamicRigidBody;
import com.lndf.glengine.scene.components.physics.TriangleMeshCollider;

import prog3proyecto.juego.componentes.ElevadorSubeYBaja;

public class TerrenoPrincipal extends GameObject {
	
	private Model modelo = null;
	private GameObject elevador;
	private DynamicRigidBody elevadorRigid;
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
		GameObject t = modelo.createGameObject();
		t.getTransform().setScale(new Vector3f(2, 2, 2));
		this.addChild(t);
		this.elevador = t.search("Elevador");
		this.elevadorRigid = new DynamicRigidBody();
		this.elevadorRigid.setKinematic(true);
		this.elevador.addComponent(elevadorRigid);
		this.elevador.addComponent(new ElevadorSubeYBaja(elevadorRigid)); //TEMPORAL
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
		if (this.elevadorRigid != null) this.elevadorRigid.destroy();
		this.elevadorRigid = null;
		fisicas.clear();
		materialFisico.destroy();
	}
	
}
