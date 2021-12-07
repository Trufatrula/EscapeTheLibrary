package prog3proyecto.main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * Clase para guardar los datos que se comarten entre la ventana de swing y el juego
 * 
 */
public class DatosJugador {
	
	public static Logger logger = Logger.getLogger(DatosJugador.class.getName());
	
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
	
	private HashMap<Integer, Double> fases = new HashMap<Integer, Double>();
	
	private JLabel dest;
	
	public DatosJugador(JLabel dest) {
		this.dest = dest;
	}
	
	/**
	 * Devolver la hora POSIX en un string HH:mm:ss
	 */
	public static String doubleDeTiempoAString(double tiempo) {
		String s = String.format("%06d", (long)tiempo);   
		DateFormat format = new SimpleDateFormat("HHmmss");
		DateFormat formatOut = new SimpleDateFormat("HH:mm:ss");
		try {
			Date date = format.parse(s);
			return formatOut.format(date);
		} catch (ParseException e) {
			return "00:00:00";
		}
	}
	
	/**
	 * Enviar las actualizaciónes a swing
	 */
	public void actualizar() {
		String strTiempoJuego = doubleDeTiempoAString(tiempoJuego);
		String strTiempoEnPartida = doubleDeTiempoAString(tiempoEnPartida);
		String strTiempoFase = doubleDeTiempoAString(this.getTiempoFase());
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
		this.fases.put(this.fase, tiempo);
	}
	
	public void setFase(int fase) {
		this.fase = fase;
		logger.log(Level.FINE, "Cambiando a fase " + fase);
	}
	
	public double getTiempoFase() {
		if (this.fases.containsKey(this.fase)) return this.fases.get(this.fase);
		return 0;
	}
	
	public HashMap<Integer, Double> getFases() {
		return fases;
	}

	public void setFases(HashMap<Integer, Double> fases) {
		this.fases = fases;
	}

	public float getpX() {
		return pX;
	}

	public float getpY() {
		return pY;
	}

	public float getpZ() {
		return pZ;
	}

	public float getrX() {
		return rX;
	}

	public float getrY() {
		return rY;
	}

	public float getrZ() {
		return rZ;
	}

	public String getUsuario() {
		return usuario;
	}

	public double getTiempoJuego() {
		return tiempoJuego;
	}

	public double getTiempoEnPartida() {
		return tiempoEnPartida;
	}

	public int getFase() {
		return fase;
	}

	/**
	 * Añadir tiempo a todos los tiempos
	 */
	public void addTiempo(double tiempo) {
		setTiempoJuego(tiempoJuego + tiempo);
		setTiempoEnPartida(tiempoEnPartida + tiempo);
		setTiempoFase(this.getTiempoFase() + tiempo);
	}
	
	/**
	 * Resetear todo a 0
	 */
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
