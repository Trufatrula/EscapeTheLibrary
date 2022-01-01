package prog3proyecto.juego;

import java.util.ArrayList;

import org.joml.Vector3f;

import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.Scene;
import com.lndf.glengine.scene.components.Camera;

import prog3proyecto.main.DatosJugador;

public class EscenaPrincipal extends Scene {
	
	private Laberinto berinto;
	private TerrenoPrincipal terreno;
	private Camera camara;
	private Jugador jugador;
	private DatosJugador datos;
	private ArrayList<Estanteria> estanterias = new ArrayList<>();
	
	public EscenaPrincipal(DatosJugador datos) {
		this.subscribeToUpdates();
		this.addUpdateRunnable(new ActualizarEscena(this));
		this.datos = datos;
		this.terreno = new TerrenoPrincipal();
		this.addObject(this.terreno);
		this.jugador = new Jugador();
		this.camara = this.jugador.getCamara();
		this.addObject(this.jugador);
		this.jugador.getTransform().setPosition(new Vector3f(0, 1.5f, 0));
		this.setAmbientLight(0.1f);
	}
	
	public Camera getCamara() {
		return camara;
	}
	
	public GameObject getJugador() {
		return jugador;
	}
	
	public DatosJugador getDatos() {
		return datos;
	}
	
	public void crearLaberinto() {
		for(Estanteria estanteria : estanterias) {
			this.removeObject(estanteria);
		}
		estanterias.clear();
		do {
			berinto = new Laberinto(14, 24);
			berinto.setPared(6, 0, false);
			berinto.setPared(7, 0, false);
			berinto.setPared(8, 0, false);
			berinto.setPared(6, 23, false);
			berinto.setPared(7, 23, false);
			berinto.setPared(8, 23, false);
		} while(!berinto.hayCamino(7, 0, 7, 23));
		for(int i = 0; i < 14; i++) {
			for(int j = 0; j < 24; j++) {
				if(berinto.getPared(i,j)) {
					Estanteria estanteria = new Estanteria(i, j);
					estanterias.add(estanteria);
					this.addObject(estanteria);
				}
			}
		}
	}
	
	public Laberinto getLaberinto() {
		return berinto;
	}

	@Override
	public void destroy() {
		Estanteria.destruirCache();
		Libro.destruirCache();
		super.destroy();
	}
}
