package prog3proyecto.juego.componentes;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.lndf.glengine.engine.DeltaTime;
import com.lndf.glengine.scene.GameObject;

public class TpFinal extends InteractConObjeto {
	
	private Vector3f posicion;
	private Quaternionf rotacion;

	public TpFinal(GameObject jugador, Vector3f posicion, Quaternionf rotacion) {
		super(jugador);
		this.posicion = posicion;
		this.rotacion = rotacion;
		this.setDistancia(3);
	}
	
	@Override
	public void interactuar() {
		this.getJugador().getTransform().setWorldPosition(posicion);
		this.getJugador().getTransform().setWorldRotation(rotacion);
		
		Fase1 fase1 = (Fase1) this.getJugador().getComponent(Fase1.class);
		Fase2 fase2 = (Fase2) this.getJugador().getComponent(Fase2.class);
		Fase3 fase3 = (Fase3) this.getJugador().getComponent(Fase3.class);
		
		if(fase1 != null) {
			this.getJugador().removeComponent(fase1);
		}
		
		if(fase2 != null) {
			this.getJugador().removeComponent(fase2);
		}
		
		if(fase3 != null) {
			this.getJugador().removeComponent(fase3);
		}
		
		this.getJugador().addComponent(new FaseFinal());
		
	}
	
	
	@Override
	public void update() {
		super.update();
		Quaternionf rot = this.getGameObject().getTransform().getRotation();
		rot.rotateY((float) ((Math.PI/5)*DeltaTime.get()));
		this.getGameObject().getTransform().setRotation(rot);
	}

	public Vector3f getPosicion() {
		return posicion;
	}

	public void setPosicion(Vector3f posicion) {
		this.posicion = posicion;
	}

	public Quaternionf getRotacion() {
		return rotacion;
	}

	public void setRotacion(Quaternionf rotacion) {
		this.rotacion = rotacion;
	}
	
	
}
