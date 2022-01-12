package prog3proyecto.juego.componentes;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.lndf.glengine.scene.Component;

public class Respawn extends Component {
	
	private float alturaMinima;
	private Vector3f pos;
	private Quaternionf rot;
	
	public Respawn(float alturaMinima, Vector3f pos, Quaternionf rot) {
		this.alturaMinima = alturaMinima;
		this.pos = pos;
		this.rot = rot;
	}
	
	@Override
	public void update() {
		Vector3f pos = this.getGameObject().getTransform().getWorldPosition();
		if (pos.y < this.alturaMinima) {
			this.getGameObject().getTransform().setWorldPosition(this.pos);
			this.getGameObject().getTransform().setWorldRotation(rot);
		}
	}

	public float getAlturaMinima() {
		return alturaMinima;
	}

	public void setAlturaMinima(float alturaMinima) {
		this.alturaMinima = alturaMinima;
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public Quaternionf getRot() {
		return rot;
	}

	public void setRot(Quaternionf rot) {
		this.rot = rot;
	}
	
}
