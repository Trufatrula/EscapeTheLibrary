package prog3proyecto.juego.componentes;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.lndf.glengine.engine.DeltaTime;
import com.lndf.glengine.scene.Component;
import com.lndf.glengine.scene.components.physics.DynamicRigidBody;

public class ElevadorSubeYBaja extends Component{
	
	private Vector3f posicionAbajo;
	private Vector3f posicionArriba;
	private Quaternionf rot0 = new Quaternionf();
	
	private DynamicRigidBody rigid;
	
	private float direccion = 1f;
	
	public ElevadorSubeYBaja(DynamicRigidBody rigid) {
		this.rigid = rigid;
	}
	
	@Override
	public void addToGameObject() {
		this.posicionAbajo = this.getGameObject().getTransform().getWorldPosition();
		this.posicionArriba = this.posicionAbajo.add(new Vector3f(0, 7.5f, 0), new Vector3f());
	}
	
	@Override
	public void update() {
		Vector3f pos = this.getGameObject().getTransform().getWorldPosition();
		if (pos.y > this.posicionArriba.y || pos.y < this.posicionAbajo.y) {
			this.direccion = -this.direccion;
			if (pos.y > this.posicionArriba.y) {
				pos.y = this.posicionArriba.y;
			} else {
				pos.y = this.posicionAbajo.y;
			}
		}
		pos.y += this.direccion * (float) DeltaTime.get();
		this.rigid.setKinematicTarget(pos, rot0);
	}
}
