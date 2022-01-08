package prog3proyecto.juego.componentes;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.lndf.glengine.engine.DeltaTime;
import com.lndf.glengine.scene.Component;
import com.lndf.glengine.scene.components.physics.DynamicRigidBody;

public class PuertaSoloSube extends Component {
	
	private Vector3f posicionAbajo;
	private Vector3f posicionArriba;
	
	private DynamicRigidBody rigid;
	
	private float direccion = 1f;
	
	public PuertaSoloSube(DynamicRigidBody rigid) {
		this.rigid = rigid;
	}
	
	@Override
	public void addToGameObject() {
		this.posicionAbajo = this.getGameObject().getTransform().getWorldPosition();
		this.posicionArriba = this.posicionAbajo.add(new Vector3f(0, 2, 0), new Vector3f());
	}
	
	@Override
	public void update() {
		Vector3f pos = this.getGameObject().getTransform().getWorldPosition();
		Quaternionf rotacion = this.getGameObject().getTransform().getWorldRotation();
		if (pos.y > this.posicionArriba.y) {
			this.getGameObject().removeComponent(this);
			return;
		}
		pos.y += this.direccion * (float) DeltaTime.get();
		this.rigid.setKinematicTarget(pos, rotacion);
	}
}

