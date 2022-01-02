package prog3proyecto.juego.componentes;

import org.joml.Vector3f;

import com.lndf.glengine.scene.Component;
import com.lndf.glengine.scene.GameObject;

//Clase que permite interactuar con objetos si estas mirando hacia ellos y a una distancia determinada

public abstract class Interact extends Component {
	
	private GameObject jugador;
	private float distancia = 3f;
	private float angulo = 0.5236f; //30 grados en radianes
	private boolean interactuando = false;
	
	public Interact(GameObject jugador) {
		this.jugador = jugador;
	}

	public GameObject getJugador() {
		return jugador;
	}

	public void setJugador(GameObject jugador) {
		this.jugador = jugador;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}
	
	public float getAngulo() {
		return angulo;
	}

	public void setAngulo(float angulo) {
		this.angulo = angulo;
	}

	public boolean estaInteractuando() {
		return interactuando;
	}
	
	@Override
	public void update() {
		Vector3f rotacionJugador = jugador.getTransform().getWorldFront().normalize();
		Vector3f posicion = this.getGameObject().getTransform().getWorldPosition();
		Vector3f direccion = posicion.sub(jugador.getTransform().getWorldPosition());
		float distanciaActual = direccion.length();
		direccion.normalize();
		float anguloActual = (float) Math.acos(direccion.dot(rotacionJugador));
		boolean interactuandoAhora = false;
		if (distanciaActual < distancia && anguloActual < angulo) {
			interactuandoAhora = true;
		}
		if (interactuandoAhora != interactuando) {
			interactuando = interactuandoAhora;
			if (interactuando) {
				entrarInteract();
			} else {
				salirInteract();
			}
		}
	}
	
	public abstract void entrarInteract();
	public abstract void salirInteract();
	
}
