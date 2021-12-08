package prog3proyecto.juego.componentes;

import java.awt.event.KeyEvent;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import com.lndf.glengine.engine.DeltaTime;
import com.lndf.glengine.engine.Input;
import com.lndf.glengine.scene.Component;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.Transform;
import com.lndf.glengine.scene.components.physics.DynamicRigidBody;

public class MovimientoFisicas extends Component {
	
	private DynamicRigidBody rigidBody;
	
	private float speed = 1000f;
	
	private int forwardKey = KeyEvent.VK_W;
	private int backWardsKey = KeyEvent.VK_S;
	private int leftKey = KeyEvent.VK_A;
	private int rightKey = KeyEvent.VK_D;
	private int upKey = KeyEvent.VK_SPACE;
	private int downKey = GLFW.GLFW_KEY_LEFT_SHIFT;
	
	public MovimientoFisicas(DynamicRigidBody rigidBody) {
		this.rigidBody = rigidBody;
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getForwardKey() {
		return forwardKey;
	}

	public void setForwardKey(int forwardKey) {
		this.forwardKey = forwardKey;
	}

	public int getBackWardsKey() {
		return backWardsKey;
	}

	public void setBackWardsKey(int backWardsKey) {
		this.backWardsKey = backWardsKey;
	}

	public int getLeftKey() {
		return leftKey;
	}

	public void setLeftKey(int leftKey) {
		this.leftKey = leftKey;
	}

	public int getRightKey() {
		return rightKey;
	}

	public void setRightKey(int rightKey) {
		this.rightKey = rightKey;
	}

	public int getUpKey() {
		return upKey;
	}

	public void setUpKey(int upKey) {
		this.upKey = upKey;
	}

	public int getDownKey() {
		return downKey;
	}

	public void setDownKey(int downKey) {
		this.downKey = downKey;
	}

	@Override
	public void update() {
		GameObject obj = this.getGameObject();
		Transform t = obj.getTransform();
		float step = (float) (speed * DeltaTime.get());
		Vector3f v = new Vector3f();
		if(Input.getKey(this.forwardKey)) {
			v.add(t.getFront());
		}
		if(Input.getKey(this.backWardsKey)) {
			v.add(t.getBack());
		}
		if(Input.getKey(this.rightKey)) {
			v.add(t.getRight());
		}
		if(Input.getKey(this.leftKey)) {
			v.add(t.getLeft());
		}
		if (v.length() == 0) return;
		v.normalize().mul(step);
		this.rigidBody.setLinearVelocity(v);
//		if(Input.getKey(this.downKey)) {
//			pos.add(0, -step, 0);
//		}
//		if(Input.getKey(this.upKey)) {
//			pos.add(0, step, 0);
//		}
	}
	
}
