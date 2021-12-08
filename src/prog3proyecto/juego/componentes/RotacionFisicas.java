package prog3proyecto.juego.componentes;

import java.awt.event.KeyEvent;

import org.joml.Vector3f;

import com.lndf.glengine.engine.DeltaTime;
import com.lndf.glengine.engine.Input;
import com.lndf.glengine.scene.Component;
import com.lndf.glengine.scene.GameObject;

import prog3proyecto.juego.Jugador;

public class RotacionFisicas extends Component {
	
private static final Vector3f UP = new Vector3f(0, 1, 0);
	
	private float rotationSpeed = 1f;
	
	private int camUpKey = KeyEvent.VK_I;
	private int camDownKey = KeyEvent.VK_K;
	private int camLeftKey = KeyEvent.VK_J;
	private int camRightKey = KeyEvent.VK_L;
	
	public float getRotationSpeed() {
		return rotationSpeed;
	}
	public void setRotationSpeed(float rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}
	public int getCamUpKey() {
		return camUpKey;
	}
	public void setCamUpKey(int camUpKey) {
		this.camUpKey = camUpKey;
	}
	public int getCamDownKey() {
		return camDownKey;
	}
	public void setCamDownKey(int camDownKey) {
		this.camDownKey = camDownKey;
	}
	public int getCamLeftKey() {
		return camLeftKey;
	}
	public void setCamLeftKey(int camLeftKey) {
		this.camLeftKey = camLeftKey;
	}
	public int getCamRightKey() {
		return camRightKey;
	}
	public void setCamRightKey(int camRightKey) {
		this.camRightKey = camRightKey;
	}
	public static Vector3f getUp() {
		return UP;
	}
	
	@Override
	public void update() {
		GameObject obj = this.getGameObject();
		Jugador jugador = (Jugador) obj;
		float rotAngle = (float) (rotationSpeed * DeltaTime.get());
		if (Input.getKey(this.camUpKey)) {
			jugador.rotatePitch(rotAngle);
		}
		if (Input.getKey(this.camDownKey)) {
			jugador.rotatePitch(-rotAngle);
		}
		if (Input.getKey(this.camLeftKey)) {
			jugador.rotateYaw(rotAngle);
		}
		if (Input.getKey(this.camRightKey)) {
			jugador.rotateYaw(-rotAngle);
		}
	}
	
}
