package prog3proyecto.juego.componentes;

import com.lndf.glengine.scene.GameObject;

public class Bola extends InteractConObjeto {

	private int i;
	
	public Bola(GameObject jugador, int i) {
		super(jugador);
		this.i = i;
	}
	
	@Override
	public void interactuar() {
		boolean estado = Fase2.getPuzzle().getBola(i);
		estado = !estado;
		Fase2.getPuzzle().setBola(i, estado);
	}
	
}
