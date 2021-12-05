package prog3proyecto.juego.componentes;

import java.awt.event.KeyEvent;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import com.lndf.glengine.engine.DeltaTime;
import com.lndf.glengine.engine.Input;
import com.lndf.glengine.scene.Component;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.Transform;

public class Movimiento extends Component {
	
	private float speed = 1f;
	
	private int forwardKey = KeyEvent.VK_W;
	private int backWardsKey = KeyEvent.VK_S;
	private int leftKey = KeyEvent.VK_A;
	private int rightKey = KeyEvent.VK_D;
	private int upKey = KeyEvent.VK_SPACE;
	private int downKey = GLFW.GLFW_KEY_LEFT_SHIFT;
	
	
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
		Vector3f pos = t.getPosition();
		Vector3f mover = new Vector3f();
		float step = (float) (speed * DeltaTime.get());
		
		if(Input.getKey(this.forwardKey)) {
			mover.add(t.getFront().mul(step));
		}
		if(Input.getKey(this.backWardsKey)) {
			mover.add(t.getBack().mul(step));
		}
		if(Input.getKey(this.rightKey)) {
			mover.add(t.getRight().mul(step));
		}
		if(Input.getKey(this.leftKey)) {
			mover.add(t.getLeft().mul(step));
		}
		if(Input.getKey(this.downKey)) {
			pos.add(0, -step, 0);
		}
		if(Input.getKey(this.upKey)) {
			pos.add(0, step, 0);
		}
		
		float lenCorrect = mover.length();
		float y = mover.y;
		mover.y = 0;
		float len = mover.length();
		if(len != 0) {
			mover.mul(lenCorrect / len); 
		} else {
			t.getDown().mul(y, mover);
		}
		pos.add(mover);
		t.setPosition(pos);
	}
	
	
	
	

}
