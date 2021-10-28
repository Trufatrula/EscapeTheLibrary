package prog3proyecto.main;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;

public class DatosJugador {
	
	private float pX = 0;
	private float pY = 0;
	private float pZ = 0;
	
	private float rX = 0;
	private float rY = 0;
	private float rZ = 0;
	
	private String usuario  = "";
	private double tiempoJuego = 0;
	private double tiempoEnPartida = 0;
	
	private int fase = 0;
	private double tiempoFase = 0;
	
	private VentanaMain vent;
	private JLabel dest;
	
	public DatosJugador(VentanaMain vent, JLabel dest) {
		this.vent = vent;
		this.dest = dest;
	}
	
	private static String doubleDeTiempoAString(double tiempo) {
		String s = String.format("%06d", (int)tiempo);   
		DateFormat format = new SimpleDateFormat("HHmmss");
		DateFormat formatOut = new SimpleDateFormat("HH:mm:ss");
		try {
			Date date = format.parse(s);
			return formatOut.format(date);
		} catch (ParseException e) {
			return "00:00:00";
		}
	}
	
	public void actualizar() {
		String strTiempoJuego = doubleDeTiempoAString(tiempoJuego);
		String strTiempoEnPartida = doubleDeTiempoAString(tiempoEnPartida);
		String strTiempoFase = doubleDeTiempoAString(tiempoFase);
		String msg = "Posición:\n" +
					 "   X: " + pX + "\n" +
					 "   Y: " + pY + "\n" +
					 "   Z: " + pZ + "\n\n" +
					 "Rotación:\n" +
					 "   X: " + rX + "\n" +
					 "   Y: " + rY + "\n" +
					 "   Z: " + rZ + "\n\n" +
					 "Usuario: " + usuario + "\n" +
					 "Tiempo total: " + strTiempoJuego + "\n" +
					 "Tiempo en partida: " + strTiempoEnPartida + "\n\n" +
					 "Fase: " + fase + "\n" + 
					 "Tiempo en la fase: " +  strTiempoFase;
		//TODO: actualizar la ventana
	}
	
	public void setPos(float x, float y, float z) {
		this.pX = x;
		this.pY = y;
		this.pZ = z;
	}
	
	public void setRotacion(float x, float y, float z) {
		this.rX = x;
		this.rY = y;
		this.rZ = z;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public void setTiempoJuego(double tiempo) {
		this.tiempoJuego = tiempo;
	}
	
	public void setTiempoEnPartida(double tiempo) {
		this.tiempoEnPartida = tiempo;
	}
	
	public void setTiempoFase(double tiempo) {
		this.tiempoFase = tiempo;
	}
	
	public void setFase(int fase) {
		this.fase = fase;
	}
	
	public void addTiempo(double tiempo) {
		setTiempoJuego(tiempoJuego + tiempo);
		setTiempoEnPartida(tiempoJuego + tiempo);
		setTiempoFase(tiempoFase + tiempo);
	}
	
}
