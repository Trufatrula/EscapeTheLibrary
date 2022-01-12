package prog3proyecto.juego;

import java.util.Random;

import com.lndf.glengine.scene.GameObject;

import prog3proyecto.juego.componentes.VasoMover;

public class PuzzleVasos {
	
	private VasoMover[] vasos;
	
	private Random random = new Random();
	
	public PuzzleVasos(VasoMover[] vasos) {
		this.vasos = vasos;
	}
	
	public void moverVasos() {
		int countMoves = random.nextInt(5) + 10;
		int[][] movesVasos = new int[3][countMoves];
		movesVasos[0][0] = this.vasos[0].getPos();
		movesVasos[1][0] = this.vasos[1].getPos();
		movesVasos[2][0] = this.vasos[2].getPos();
		for (int i = 1; i < countMoves; i++) {
			int c1 = random.nextInt(3);
			int c2;
			do {
				c2 = random.nextInt(3);
			} while (c1 == c2);
			movesVasos[0][i] = movesVasos[0][i - 1];
			movesVasos[1][i] = movesVasos[1][i - 1];
			movesVasos[2][i] = movesVasos[2][i - 1];
			int tmp = movesVasos[c2][i];
			movesVasos[c2][i] = movesVasos[c1][i];
			movesVasos[c1][i] = tmp;
		}
		for (int i = 0; i < 3; i++) {
			this.vasos[i].animar(movesVasos[i]);
		}
	}
	
	public void subirVasos() {
		for (VasoMover vaso : this.vasos) {
			vaso.setArriba(true);
			vaso.setTiempoArriba(3.5);
		}
	}
	
	public void completado() {
		for (VasoMover vaso : this.vasos) {
			GameObject obj = vaso.getGameObject();
			obj.removeComponent(vaso);
		}
	}
}
