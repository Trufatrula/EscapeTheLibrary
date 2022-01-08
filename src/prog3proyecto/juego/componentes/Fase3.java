package prog3proyecto.juego.componentes;

import com.lndf.glengine.scene.Component;

import prog3proyecto.juego.EscenaPrincipal;

public class Fase3 extends Component {
	
	@Override
	public void addToScene() {
		EscenaPrincipal pepe = (EscenaPrincipal) this.getScene();
		pepe.getDatos().setFase(3);
		pepe.setAmbientLight(0.004f);
		pepe.getJugador().getLuz().setRadius(10);
		pepe.getJugador().getLuz().setStrength(0.5f);
		pepe.getTerreno().movilizarPuerta();
	}
}