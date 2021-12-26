package prog3proyecto.juego.componentes;

import org.joml.Vector3f;

import com.lndf.glengine.scene.Component;

import prog3proyecto.juego.EscenaPrincipal;
import prog3proyecto.juego.Juego;
import prog3proyecto.juego.Laberinto;

public class Fase1 extends Component {
	
	private boolean fase1Iniciada = false;
	
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
	}
	

	
	
	
}
