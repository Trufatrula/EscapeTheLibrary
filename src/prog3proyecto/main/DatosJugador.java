package prog3proyecto.main;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

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
	
	private JLabel dest;
	
	public DatosJugador(JLabel dest) {
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
		String msg = "<html>Posición X/Y/Z:" +
					 " " + pX + " /" +
					 " " + pY + " / " +
					 " " + pZ + "<br>" +
					 "Rotación X/Y/Z:" +
					 " " + rX + " /" +
					 " " + rY + " /" +
					 " " + rZ + "<br><br>" +
					 "Usuario: " + usuario + "<br>" +
					 "Tiempo total: " + strTiempoJuego + "<br>" +
					 "Tiempo en partida: " + strTiempoEnPartida + "<br><br>" +
					 "Fase: " + fase + "<br>" + 
					 "Tiempo en la fase: " +  strTiempoFase + "</html>";
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dest.setText(msg);
			}
		});
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
		setTiempoEnPartida(tiempoEnPartida + tiempo);
		setTiempoFase(tiempoFase + tiempo);
	}
	
	public void reset() {
		setPos(0, 0, 0);
		setRotacion(0, 0, 0);
		setUsuario("");
		setTiempoJuego(0);
		setTiempoEnPartida(0);
		setTiempoFase(0);
		setFase(0);
	}
	
}
