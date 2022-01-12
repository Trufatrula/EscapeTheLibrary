package prog3proyecto.juego;

import java.util.ArrayList;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.lndf.glengine.asset.Asset;
import com.lndf.glengine.gl.DefaultMaterial;
import com.lndf.glengine.gl.Mesh;
import com.lndf.glengine.model.Model;
import com.lndf.glengine.physics.PhysicalMaterial;
import com.lndf.glengine.physics.PhysicalTriangleMesh;
import com.lndf.glengine.scene.Component;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.components.MeshRenderer;
import com.lndf.glengine.scene.components.lighting.PointLight;
import com.lndf.glengine.scene.components.physics.DynamicRigidBody;
import com.lndf.glengine.scene.components.physics.TriangleMeshCollider;

import prog3proyecto.juego.componentes.Bola;
import prog3proyecto.juego.componentes.ElevadorSubeYBaja;
import prog3proyecto.juego.componentes.InteractuarMesa;
import prog3proyecto.juego.componentes.PuertaSoloSube;
import prog3proyecto.juego.componentes.TpFinal;

public class TerrenoPrincipal extends GameObject {
	
	private Model modelo = null;
	private GameObject elevador;
	private GameObject puerta;
	private GameObject mesa;
	private GameObject[] bolas = new GameObject[9];
	private GameObject[] cubos = new GameObject[9];
	private GameObject[] vasos = new GameObject[3];
	private DynamicRigidBody[] vasosRigid = new DynamicRigidBody[3];
	private GameObject conoVictoria;
	private DynamicRigidBody conoVictoriaRigid;
	private GameObject posarLibro1;
	private GameObject posarLibro2;
	private GameObject posarLibro3;
	private DynamicRigidBody elevadorRigid;
	private DynamicRigidBody puertaRigid;
	private PhysicalMaterial materialFisico;
	private ArrayList<PhysicalTriangleMesh> fisicas = new ArrayList<>();
	
	public TerrenoPrincipal(EscenaPrincipal escena) {
		this(escena, "");
	}
	
	public TerrenoPrincipal(EscenaPrincipal escena, String nombre) {
		super(nombre);
		Jugador jugador = escena.getJugador();
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
		this.puerta = t.search("Puerto");
		this.puertaRigid= new DynamicRigidBody();
		this.puertaRigid.setKinematic(true);
		this.puerta.addComponent(puertaRigid);
		this.mesa = t.search("Mesa");
		this.posarLibro1 = t.search("PosarLibro1");
		this.posarLibro2 = t.search("PosarLibro2");
		this.posarLibro3 = t.search("PosarLibro3");
		DefaultMaterial posarLibro1Material = materialDesdeObjeto(posarLibro1);
		DefaultMaterial posarLibro2Material = materialDesdeObjeto(posarLibro2);
		DefaultMaterial posarLibro3Material = materialDesdeObjeto(posarLibro3);
		posarLibro1Material.setAlbedoColor(new Vector3f(0.6f, 0.2f, 0.7f));
		posarLibro1Material.setMetalness(0.5f);
		posarLibro1Material.setRoughness(0.3f);
		posarLibro2Material.setAlbedoColor(new Vector3f(0.6f, 0.2f, 0.7f));
		posarLibro2Material.setMetalness(0.5f);
		posarLibro2Material.setRoughness(0.3f);
		posarLibro3Material.setAlbedoColor(new Vector3f(0.6f, 0.2f, 0.7f));
		posarLibro3Material.setMetalness(0.5f);
		posarLibro3Material.setRoughness(0.3f);
		for(int i = 0; i < 9; i++) {
			bolas[i] = t.search("Bola"+i);
			DefaultMaterial b = materialDesdeObjeto(bolas[i]);
			b.setAlbedoColor(new Vector3f(0.54f, 0.24f, 0.24f));
			b.setMetalness(0.6f);
			b.setRoughness(0.32f);
			bolas[i].addComponent(new Bola(jugador, i));
			cubos[i] = t.search("Cubo"+i);
			DefaultMaterial c = materialDesdeObjeto(cubos[i]);
			c.setAlbedoColor(new Vector3f(0.54f, 0.24f, 0.24f));
			c.setMetalness(0.6f);
			c.setRoughness(0.32f);
		}
		GameObject bolaFin1 = t.search("BolaFin1");
		GameObject bolaFin2 = t.search("BolaFin2");
		DefaultMaterial bolaFin1Material = materialDesdeObjeto(bolaFin1);
		DefaultMaterial bolaFin2Material = materialDesdeObjeto(bolaFin2);
		bolaFin1.addComponent(new PointLight(new Vector3f(1, 0, 0), 4, 2.3f));
		bolaFin2.addComponent(new PointLight(new Vector3f(1, 0, 0), 4, 2.3f));
		bolaFin1Material.setAlbedoColor(new Vector3f(0.54f, 0.24f, 0.24f));
		bolaFin1Material.setEmissiveColor(new Vector3f(0.8f,0,0));
		bolaFin1Material.setMetalness(0.6f);
		bolaFin1Material.setRoughness(0.32f);
		bolaFin2Material.setAlbedoColor(new Vector3f(0.54f, 0.24f, 0.24f));
		bolaFin2Material.setEmissiveColor(new Vector3f(0.8f,0,0));
		bolaFin2Material.setMetalness(0.6f);
		bolaFin2Material.setRoughness(0.32f);
		Libro libroFinal = new Libro(new Vector3f(14f, 404f, -233f), jugador);
		escena.addObject(libroFinal);
		for (int i = 0; i < 3; i++) {
			vasos[i] = t.search("Vaso"+i);
			DynamicRigidBody r = new DynamicRigidBody();
			r.setKinematic(true);
			vasos[i].addComponent(r);
			vasosRigid[i] = r;
		}
		this.conoVictoria = t.search("ConoVictoria");
		this.conoVictoriaRigid = new DynamicRigidBody();
		this.conoVictoriaRigid.setKinematic(true);
		this.conoVictoria.addComponent(conoVictoriaRigid);
		DefaultMaterial conoVictoriaMaterial = materialDesdeObjeto(this.conoVictoria);
		conoVictoriaMaterial.setAlbedoColor(new Vector3f(0.6f, 0.5f, 0.13f));
		conoVictoriaMaterial.setMetalness(0.74f);
		conoVictoriaMaterial.setRoughness(0.37f);
		GameObject sixpiker = t.search("Sixpiker");
		DefaultMaterial sixpikerMaterial = materialDesdeObjeto(sixpiker);
		sixpikerMaterial.setAlbedoColor(new Vector3f(0.6f, 0.5f, 0.13f));
		sixpikerMaterial.setMetalness(0.74f);
		sixpikerMaterial.setRoughness(0.37f);
		sixpiker.addComponent(new TpFinal(jugador, new Vector3f(10.849f, 404, -221.171f), new Quaternionf()));
		this.mesa.addComponent(new InteractuarMesa(jugador, posarLibro1, posarLibro2, posarLibro3));
		materialFisico = new PhysicalMaterial(64, 32, 0.3f);
		crearFisicas(this);
	}
	
	public static DefaultMaterial materialDesdeObjeto(GameObject obj) {
		return (DefaultMaterial) ((MeshRenderer) obj.getComponent(MeshRenderer.class)).getMaterial();
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
	
	public GameObject getPuerta() {
		return puerta;
	}
	
	public void movilizarPuerta() {
		this.puerta.addComponent(new PuertaSoloSube(this.puertaRigid));
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
		if (this.materialFisico != null) materialFisico.destroy();
		this.materialFisico = null;
		if (this.puertaRigid != null) this.puertaRigid.destroy();
		this.puertaRigid = null;
		for (int i = 0; i < 3; i++) {
			if (this.vasosRigid[i] != null) {
				this.vasosRigid[i].destroy();
				this.vasosRigid[i] = null;
			}
		}
		fisicas.clear();
	}

	public GameObject[] getBolas() {
		return bolas;
	}

	public GameObject[] getCubos() {
		return cubos;
	}
	
	public GameObject[] getVasos() {
		return vasos;
	}

	public DynamicRigidBody[] getVasosRigid() {
		return vasosRigid;
	}

	public GameObject getConoVictoria() {
		return conoVictoria;
	}

	public DynamicRigidBody getConoVictoriaRigid() {
		return conoVictoriaRigid;
	}

}
