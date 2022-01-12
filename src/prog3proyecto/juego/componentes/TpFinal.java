package prog3proyecto.juego.componentes;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.lndf.glengine.scene.GameObject;

public class TpFinal extends InteractConObjeto {
	
	private Vector3f posicion;
	private Quaternionf rotacion;

	public TpFinal(GameObject jugador, Vector3f posicion, Quaternionf rotacion) {
		super(jugador);
		this.posicion = posicion;
		this.rotacion = rotacion;
	}
	
	@Override
	public void interactuar() {
		this.getJugador().getTransform().setWorldPosition(posicion);
		this.getJugador().getTransform().setWorldRotation(rotacion);
	}
	
	
}
