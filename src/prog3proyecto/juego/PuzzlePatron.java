package prog3proyecto.juego;

import java.util.Random;

import org.joml.Vector3f;

import com.lndf.glengine.gl.DefaultMaterial;

public class PuzzlePatron {

	private boolean[] bolasEstado = new boolean[9];
	private boolean[] cubosEstado = new boolean[9];
	private DefaultMaterial[] materialesBolas;
	private DefaultMaterial[] materialesCubos;
	private Random random = new Random();
	
	public PuzzlePatron(DefaultMaterial[] materialesBolas, DefaultMaterial[] materialesCubos) {
		this.materialesBolas = materialesBolas;
		this.materialesCubos = materialesCubos;
	}
	
	public void generarPatron() {
		int c = 0;
		
		while( c < 9 ) {
			cubosEstado[c] = random.nextBoolean();
		    c++;
		}
		
		for(boolean value: cubosEstado) {
			if (value) {
				c=-1;
			}
		}
		
		if(c==9) {
			cubosEstado[random.nextInt(10)] = true;
		}
		
		for (int i = 0; i < 9; i++) {
			if (cubosEstado[i]) {
				this.materialesCubos[i].setEmissiveColor(new Vector3f(0.8f,0,0));
			} 
		}
	}
	
	public void setBola(int i, boolean estado) {
		this.bolasEstado[i] = estado;
		if (estado) {
			this.materialesBolas[i].setEmissiveColor(new Vector3f(0.8f,0,0));
		} else {
			this.materialesBolas[i].setEmissiveColor(new Vector3f(0,0,0));
		}	
	}
	
	public boolean getBola(int i) {
		return bolasEstado[i];
	}
	
	public boolean comprobarPuzzle() {
		for(int i = 0; i < 9; i++) {
			if (bolasEstado[i] != cubosEstado[i]) {
				return false;
			}
		}
		return true;
	}
	
	
	
}
