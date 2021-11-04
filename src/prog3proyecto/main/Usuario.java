package prog3proyecto.main;

import java.util.ArrayList;

public class Usuario {
	
	private String nombre;
	private ArrayList<String> tiempos = new ArrayList<String>();
	
	public Usuario(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
