package prog3proyecto.juego.componentes;

import org.joml.Vector3f;

import com.lndf.glengine.engine.DeltaTime;
import com.lndf.glengine.engine.Input;
import com.lndf.glengine.physics.CharacterCollisionData;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.components.physics.CharacterController;

public class ObjetoLlevable extends InteractConObjeto {

	private static ObjetoLlevable objetoLlevando;
	private static boolean interactActivado = true;
	
	private Vector3f velocidad = new Vector3f();
	private Vector3f tmpV = new Vector3f();
	private boolean ground;
	
	private CharacterController controller;
	private GameObject mano;
	private boolean llevando = false;
	private boolean puedeSoltar = true;
	
	private float maxDistancia = 3f;
	private float maxMovimiento = 5f;
	
	public ObjetoLlevable(GameObject jugador, CharacterController controller, GameObject mano) {
		super(jugador);
		this.controller = controller;
		this.mano = mano;
	}
	
	@Override
	public void update() {
		super.update();
		if (!this.llevando) {
			if (ground) {
				this.velocidad.set(0);
			} else {
				this.velocidad.add(this.getScene().getGravity().mul((float) DeltaTime.get()));
			}
			this.velocidad.mul((float) DeltaTime.get(), tmpV);
			CharacterCollisionData data = this.controller.move(tmpV, 0.001f);
			this.ground = data.collidesDown();
			return;
		}
		if (this.isPulsadoAntes() == false) this.puedeSoltar = true;
		this.getGameObject().getTransform().setWorldRotation(this.mano.getTransform().getWorldRotation());
		this.mano.getTransform().getWorldPosition().sub(this.getGameObject().getTransform().getWorldPosition(), tmpV);
		float s = this.maxMovimiento * (float) DeltaTime.get();
		if (tmpV.length() > s) {
			tmpV.normalize().mul(s);
		}
		this.controller.move(tmpV, 0.001f);
		if ((ObjetoLlevable.interactActivado && this.puedeSoltar && Input.getKey(this.getInteractKey())) || this.tmpV.length() > this.maxDistancia) {
			this.soltar();
		}
	}
	
	public void soltar() {
		if (this.llevando) {
			this.llevando = false;
			ObjetoLlevable.objetoLlevando = null;
			this.velocidad.set(0);
			this.ground = false;
		}
	}
	
	@Override
	public void interactuar() {
		if (!this.llevando && ObjetoLlevable.objetoLlevando == null && ObjetoLlevable.interactActivado) {
			ObjetoLlevable.objetoLlevando = this;
			this.llevando = true;
			this.puedeSoltar = false;
		}
	}

	public static boolean isInteractActivado() {
		return interactActivado;
	}

	public static void setInteractActivado(boolean interactActivado) {
		ObjetoLlevable.interactActivado = interactActivado;
	}

	public float getMaxDistancia() {
		return maxDistancia;
	}

	public void setMaxDistancia(float maxDistancia) {
		this.maxDistancia = maxDistancia;
	}

	public Vector3f getVelocidad() {
		return velocidad;
	}

	public boolean isGround() {
		return ground;
	}

	public CharacterController getController() {
		return controller;
	}

	public GameObject getMano() {
		return mano;
	}

	public boolean isPuedeSoltar() {
		return puedeSoltar;
	}

	public float getMaxMovimiento() {
		return maxMovimiento;
	}

	public void setMaxMovimiento(float maxMovimiento) {
		this.maxMovimiento = maxMovimiento;
	}

	public boolean isLlevando() {
		return llevando;
	}
	
}
