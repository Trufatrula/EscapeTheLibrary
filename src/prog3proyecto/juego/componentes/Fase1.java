package prog3proyecto.juego.componentes;

import java.util.Random;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.lndf.glengine.scene.Component;

import prog3proyecto.juego.EscenaPrincipal;
import prog3proyecto.juego.Laberinto;
import prog3proyecto.juego.Libro;

public class Fase1 extends Component {
	
	private boolean fase1Iniciada = false;
	
	private Random random = new Random();
	
	private void crearLibros() {
		EscenaPrincipal pepe = (EscenaPrincipal) this.getScene();
		Laberinto laberinto = pepe.getLaberinto();
		for (int i = 0; i < 3; i++) {
			int x = 0, y = 0;
			do {
				x = this.random.nextInt(14);
				y = this.random.nextInt(24);
			} while(!laberinto.getPared(x, y) && (
						laberinto.hayCamino(7, 0, x+1, y) ||
						laberinto.hayCamino(7, 0, x-1, y) ||
						laberinto.hayCamino(7, 0, x, y+1) ||
						laberinto.hayCamino(7, 0, x, y-1)
					));
			Libro libro = new Libro(x, y, pepe.getJugador());
			pepe.addObject(libro);
		}
	}
	
	@Override
	public void update() {
		if (!this.fase1Iniciada) {
			this.getGameObject().getTransform().setPosition(new Vector3f(0, 1, -18));
			this.fase1Iniciada = true;
		}
	}
	
	@Override
	public void addToScene() {
		EscenaPrincipal pepe = (EscenaPrincipal) this.getScene();
		pepe.crearLaberinto();
		pepe.getJugador().getRespawn().setPos(new Vector3f(0, 1, -18));
		pepe.getJugador().getRespawn().setRot(new Quaternionf().rotateY((float) Math.PI));
		this.crearLibros();
		pepe.getDatos().setFase(1);
	}
	
}
