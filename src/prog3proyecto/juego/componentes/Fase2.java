package prog3proyecto.juego.componentes;

import com.lndf.glengine.scene.Component;

import prog3proyecto.juego.EscenaPrincipal;

public class Fase2 extends Component {
	
	@Override
	public void addToScene() {
		EscenaPrincipal pepe = (EscenaPrincipal) this.getScene();
		pepe.getDatos().setFase(2);
	}
	
}
