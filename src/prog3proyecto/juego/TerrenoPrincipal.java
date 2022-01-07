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

import prog3proyecto.juego.componentes.Bola;
import prog3proyecto.juego.componentes.ElevadorSubeYBaja;
import prog3proyecto.juego.componentes.InteractuarMesa;

public class TerrenoPrincipal extends GameObject {
	
	private Model modelo = null;
	private GameObject elevador;
	private GameObject mesa;
	private GameObject[] bolas = new GameObject[9];
	private GameObject[] cubos = new GameObject[9];
	private GameObject posarLibro1;
	private GameObject posarLibro2;
	private GameObject posarLibro3;
	private DynamicRigidBody elevadorRigid;
	private PhysicalMaterial materialFisico;
	private ArrayList<PhysicalTriangleMesh> fisicas = new ArrayList<>();
	
	public TerrenoPrincipal(Jugador jugador) {
		this(jugador, "");
	}
	
	public TerrenoPrincipal(Jugador jugador, String nombre) {
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
		this.mesa = t.search("Mesa");
		this.posarLibro1 = t.search("PosarLibro1");
		this.posarLibro2 = t.search("PosarLibro2");
		this.posarLibro3 = t.search("PosarLibro3");
		for(int i = 0; i < 9; i++) {
			bolas[i] = t.search("Bola"+i);
			bolas[i].addComponent(new Bola(jugador, i));
			cubos[i] = t.search("Cubo"+i);
		}
		
		this.mesa.addComponent(new InteractuarMesa(jugador, posarLibro1, posarLibro2, posarLibro3));
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
	
	public GameObject getElevador() {
		return elevador;
	}
	
	public void movilizarElevador() {
		this.elevador.addComponent(new ElevadorSubeYBaja(this.elevadorRigid));
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

	public GameObject[] getBolas() {
		return bolas;
	}

	public GameObject[] getCubos() {
		return cubos;
	}

}
