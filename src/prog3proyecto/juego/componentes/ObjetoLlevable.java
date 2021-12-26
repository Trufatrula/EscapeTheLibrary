package prog3proyecto.juego.componentes;

import org.joml.Vector3f;

import com.lndf.glengine.engine.Input;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.components.physics.DynamicRigidBody;

public class ObjetoLlevable extends InteractConObjeto {

	private static ObjetoLlevable objetoLlevando;
	private static boolean interactActivado = true;
	
	private DynamicRigidBody rigid;
	private GameObject mano;
	private boolean llevando = false;
	private boolean puedeSoltar = true;
	private float velocidad = 3f;
	private float minDistancia = 0.015f;
	
	public ObjetoLlevable(GameObject jugador, DynamicRigidBody rigid, GameObject mano) {
		super(jugador);
		this.rigid = rigid;
		this.mano = mano;
	}
	
	@Override
	public void update() {
		super.update();
		if (!this.llevando) return;
		if (this.isPulsadoAntes() == false) this.puedeSoltar = true;
		Vector3f dir = mano.getTransform().getWorldPosition();
		dir.sub(this.getGameObject().getTransform().getWorldPosition());
		float dist = dir.length();
		if (dist > this.minDistancia) {
			dir.normalize().mul(this.velocidad);
			this.rigid.setLinearVelocity(dir);
		} else {
			this.rigid.setLinearVelocity(new Vector3f());
		}
		if (ObjetoLlevable.interactActivado && this.puedeSoltar && Input.getKey(this.getInteractKey())) {
			this.soltar();
		}
	}
	
	public void soltar() {
		if (this.llevando) {
			this.llevando = false;
			ObjetoLlevable.objetoLlevando = null;
			this.rigid.setLinearVelocity(new Vector3f());
			this.rigid.setGravityEnabled(true);
		}
	}
	
	@Override
	public void interactuar() {
		if (!this.llevando && ObjetoLlevable.objetoLlevando == null && ObjetoLlevable.interactActivado) {
			ObjetoLlevable.objetoLlevando = this;
			this.llevando = true;
			this.puedeSoltar = false;
			this.rigid.setGravityEnabled(false);
		}
	}

	public static boolean isInteractActivado() {
		return interactActivado;
	}

	public static void setInteractActivado(boolean interactActivado) {
		ObjetoLlevable.interactActivado = interactActivado;
	}

	public float getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(float velocidad) {
		this.velocidad = velocidad;
	}

	public float getMinDistancia() {
		return minDistancia;
	}

	public void setMinDistancia(float minDistancia) {
		this.minDistancia = minDistancia;
	}

	public static ObjetoLlevable getObjetoLlevando() {
		return objetoLlevando;
	}

	public DynamicRigidBody getRigid() {
		return rigid;
	}

	public boolean isLlevando() {
		return llevando;
	}
	
}
