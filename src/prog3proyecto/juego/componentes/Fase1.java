package prog3proyecto.juego.componentes;

import com.lndf.glengine.scene.Component;

import prog3proyecto.juego.EscenaPrincipal;
import prog3proyecto.juego.Juego;
import prog3proyecto.juego.Laberinto;

public class Fase1 extends Component {

	@Override
	public void update() {
		
	}
	
	@Override
	public void addToScene() {
		EscenaPrincipal pepe = (EscenaPrincipal) this.getScene();
		pepe.crearLaberinto();
	}
	

	
	
	
}
