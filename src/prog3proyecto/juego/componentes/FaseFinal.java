package prog3proyecto.juego.componentes;

import org.joml.Vector3f;

import com.lndf.glengine.engine.DeltaTime;
import com.lndf.glengine.engine.Engine;
import com.lndf.glengine.scene.Component;
import com.lndf.glengine.scene.components.Camera;

import prog3proyecto.juego.EscenaPrincipal;
import prog3proyecto.juego.Jugador;

public class FaseFinal extends Component {
	
	private float alturaMinima = 200;
	private float alturaMaxima = 400;
	
	@Override
	public void update() {
		Vector3f pos = this.getGameObject().getTransform().getWorldPosition();
		if (pos.y < alturaMaxima && pos.y > alturaMinima) {
			Jugador jugador = (Jugador) this.getGameObject();
			Camera cam = jugador.getCamara();
			float fov = cam.getFOV();
			if (fov >= Math.PI) {
				EscenaPrincipal pepe = (EscenaPrincipal) this.getScene();
				pepe.getDatos().setGuardarDatos(true);
				Engine.setClose(true);
			} else {
				fov += (Math.PI/4) * DeltaTime.get();
				cam.setFOV(fov);
			}
		}
	}

	@Override
	public void addToScene() {
		EscenaPrincipal pepe = (EscenaPrincipal) this.getScene();
		pepe.getDatos().setFase(4);
	}

}
