package prog3proyecto.juego;

import java.util.Random;

public class Laberinto {
	
	private int alto;
	private int ancho;
	private boolean[] laberinto;
	private Random random = new Random();
	
	public Laberinto(int ancho, int alto) {
		this.alto = alto;
		this.ancho = ancho;
		laberinto = new boolean[alto*ancho];
		this.generarLaberinto(0, 0, ancho - 1, alto - 1);
	}
	
	public void generarLaberinto(int xInicio, int yInicio, int xFinal, int yFinal) {
		if(xFinal - xInicio <= 1 || yFinal - yInicio <= 1) {
			return;
		}

		int xCentro = random.nextInt(xFinal - xInicio - 1) + xInicio + 1;
		int yCentro = random.nextInt(yFinal - yInicio - 1) + yInicio + 1;
		
		int xAgujero1 = random.nextInt(xCentro - xInicio) + xInicio;
		int xAgujero2 = random.nextInt(xFinal - xCentro) + xCentro + 1;
		
		int yAgujero1 = random.nextInt(yCentro - yInicio) + yInicio;
		int yAgujero2 = random.nextInt(yFinal - yCentro) + yCentro + 1;
		
		
		for (int i = xInicio; i <= xFinal; i++) {
			setPared(i, yCentro, true);
		}
		
		for (int i = yInicio; i <= yFinal; i++) {
			setPared(xCentro, i, true);
		}
		
		setPared(xAgujero1, yCentro, false);
		setPared(xAgujero2, yCentro, false);
		setPared(xCentro, yAgujero1, false);
		setPared(xCentro, yAgujero2, false);
		
		generarLaberinto(xInicio, yInicio, xCentro - 1, yCentro - 1);
		generarLaberinto(xCentro + 1, yInicio, xFinal, yCentro - 1);
		generarLaberinto(xInicio, yCentro + 1, xCentro - 1, yFinal);
		generarLaberinto(xCentro + 1, yCentro + 1, xFinal, yFinal);
		
	}
	
	public void setPared(int x, int y, boolean pared) {
		laberinto[ancho*y+x] = pared;
	}
	
	public boolean getPared(int x, int y) {
		return laberinto[ancho*y+x];
	}
	
	public boolean hayCamino(int inicioX, int inicioY, int finX, int finY) {
		return this.hayCamino(inicioX, inicioY, finX, finY, new boolean[alto][ancho]);
	}
	
	private boolean hayCamino(int inicioX, int inicioY, int finX, int finY, boolean[][] explorado) {
		if (inicioX == finX && inicioY == finY) return true;
		if (this.getPared(inicioX, inicioY) || explorado[inicioY][inicioX]) return false;
		explorado[inicioY][inicioX] = true;
		if (inicioX != 0) {
			if (this.hayCamino(inicioX - 1, inicioY, finX, finY, explorado)) {
				return true;
			}
		}
		if (inicioX != this.ancho - 1) {
			if (this.hayCamino(inicioX + 1, inicioY, finX, finY, explorado)) {
				return true;
			}
		}
		if (inicioY != 0) {
			if (this.hayCamino(inicioX, inicioY - 1, finX, finY, explorado)) {
				return true;
			}
		}
		if (inicioY != this.alto - 1) {
			if (this.hayCamino(inicioX, inicioY + 1, finX, finY, explorado)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		String a = "";
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				if (getPared(j, i)) {
					a += "##";
				} else {
					a += "  ";
				}
			}
			a += "\n";
		}
		return a;
	}

	public int getAlto() {
		return alto;
	}

	public int getAncho() {
		return ancho;
	}
	
//	public static void main(String[] args) {
//		Laberinto a = new Laberinto(24, 14);
//		System.out.println(a.toString());
//	}
	
}
