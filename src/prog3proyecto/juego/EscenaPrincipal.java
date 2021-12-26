package prog3proyecto.juego;

import java.util.ArrayList;

import org.joml.Vector3f;

import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.Scene;
import com.lndf.glengine.scene.components.Camera;

import prog3proyecto.main.DatosJugador;

public class EscenaPrincipal extends Scene {
	
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

		Laberinto berinto = new Laberinto(12, 7);
		
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 7; j++) {
				if(berinto.getPared(i,j)) {
					Estanteria estanteria = new Estanteria(i, j);
					estanterias.add(estanteria);
					this.addObject(estanteria);
				}
			}
		}
	}
}
