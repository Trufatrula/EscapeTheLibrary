package prog3proyecto.juego.componentes;

import com.lndf.glengine.scene.GameObject;

import prog3proyecto.juego.EscenaPrincipal;

public class Bola extends InteractConObjeto {

	private int i;
	
	public Bola(GameObject jugador, int i) {
		super(jugador);
		this.setDistancia(2.5f);
		this.i = i;
	}
	
	@Override
	public void interactuar() {
		boolean estado = Fase2.getPuzzle().getBola(i);
		estado = !estado;
		Fase2.getPuzzle().setBola(i, estado);
		if (Fase2.getPuzzle().comprobarPuzzle()) {
			EscenaPrincipal pepe = (EscenaPrincipal) this.getScene();
			
			for(GameObject bola : pepe.getTerreno().getBolas()) {
				bola.removeComponent(bola.getComponent(Bola.class));				
			}
			pepe.getJugador().removeComponent(pepe.getJugador().getComponent(Fase2.class));
			pepe.getJugador().addComponent(new Fase3());
		}
	}
}
