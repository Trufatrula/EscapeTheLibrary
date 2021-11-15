package prog3proyecto.juego;

import java.util.Random;

public class GenerarLaberinto {
	
	private int alto;
	private int ancho;
	private boolean[] laberinto;
	
	public GenerarLaberinto(int alto, int ancho) {
		this.alto = alto;
		this.ancho = ancho;
		laberinto = new boolean[alto*ancho];
	}
	
	public void crearLaberinto(int ancho, int alto) {
		if(alto < 2 || ancho < 2) {
			return;
		}
		
		setPared(new Random().nextInt(alto + 1), new Random().nextInt(ancho + 1));
		
	}
	
	public void setPared(int x, int y) {
		for(int i = 0; i == alto; i++) {
			//TODO
		}
		for(int i = 0; i == ancho; i++) {
			//TODO
		}
	}
	
//	public boolean getPared(int x, int y) {
//		
//	}
	
}
