package prog3proyecto.juego.componentes;

import java.awt.event.KeyEvent;

import com.lndf.glengine.engine.Input;
import com.lndf.glengine.scene.GameObject;

public class InteractConObjeto extends Interact {
	
	private int interactKey = KeyEvent.VK_F;
	private boolean pulsadoAntes = false;
	private boolean enRango = false;
	
	public InteractConObjeto(GameObject jugador) {
		super(jugador);
		this.setDistancia(1.2f);
		this.setAngulo((float) Math.PI / 2f);
	}
	
	@Override
	public void update() {
		super.update();
		if (this.enRango) {
			if (Input.getKey(this.interactKey)) {
				if (!this.pulsadoAntes) {
					this.interactuar();
				}
				this.pulsadoAntes = true;
			} else {
				this.pulsadoAntes = false;
			}
		}
	}
	
	@Override
	public void entrarInteract() {
		this.enRango = true;
		this.pulsadoAntes = false;
	}
	
	@Override
	public void salirInteract() {
		this.enRango = false;
		this.pulsadoAntes = false;
	}
	
	
	public void interactuar() {}

	public int getInteractKey() {
		return interactKey;
	}

	public void setInteractKey(int interactKey) {
		this.interactKey = interactKey;
	}

	public boolean isPulsadoAntes() {
		return pulsadoAntes;
	}

}
