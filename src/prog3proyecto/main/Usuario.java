package prog3proyecto.main;

//import java.util.ArrayList;

public class Usuario {

	private String nombre;
	private long tiempo1;
	private long tiempo2;
	private long tiempo3;
	private long tiempoTotal;
	private int partidasJugadas;
	
    //private ArrayList<String> tiempos = new ArrayList<String>();
	
	public Usuario(String nombre, long tiempo1, long tiempo2, long tiempo3, long tiempoTotal, int partidasJugadas) {

		this.nombre = nombre;
		this.tiempo1 = tiempo1;
		this.tiempo2 = tiempo2;
		this.tiempo3 = tiempo3;
		this.tiempoTotal = tiempoTotal;
		this.partidasJugadas = partidasJugadas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getTiempo1() {
		return tiempo1;
	}

	public void setTiempo1(long tiempo1) {
		this.tiempo1 = tiempo1;
	}

	public long getTiempo2() {
		return tiempo2;
	}

	public void setTiempo2(long tiempo2) {
		this.tiempo2 = tiempo2;
	}

	public long getTiempo3() {
		return tiempo3;
	}

	public void setTiempo3(long tiempo3) {
		this.tiempo3 = tiempo3;
	}
	
	public long getTiempoTotal() {
		return tiempoTotal;
	}

	public void setTiempoTotal(long tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}
	
	public int getPartidasJugadas() {
		return partidasJugadas;
	}

	public void setPartidasJugadas(int partidasJugadas) {
		this.partidasJugadas = partidasJugadas;
	}

	@Override
	public String toString() {
		return nombre + "\t" + tiempo1 + "\t" + tiempo2 + "\t" + tiempo3;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			return nombre == ((Usuario)obj).nombre;
		} else {
			return false;
		}
	}
}
