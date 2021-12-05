package prog3proyecto.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDatos {
	private static Connection conexion;
	private static Statement statement;
	private static Logger logger = Logger.getLogger(BaseDatos.class.getName());
	
	public static void abrirConexion(boolean crear) {
		
		try {
			Class.forName("org.sqlite.JDBC"); 
			conexion = DriverManager.getConnection("jdbc:sqlite:usuarios.db");
			if(crear) {
				Statement statement = conexion.createStatement();
				String sent = "DROP TABLE IF EXISTS usuario";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE usuario (nombre varchar(30) PRIMARY KEY, tiempo1 bigint, tiempo2 bigint, tiempo3 bigint, tiempoTotal bigint, partidasJugadas int;";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
			}
		} catch(Exception e) {
			logger.log( Level.SEVERE, "No se puede abrir", e );
		}
	}	
	
	public static void cerrarConexion() {
		try {
			conexion.close();
		} catch(Exception e) {
			logger.log( Level.SEVERE, "No se puede cerrar", e );
		}
	}	
		
	public static void meterUsuario( Usuario usuario ) {
		String sent = "insert into usuario (nombre, tiempo1, tiempo2, tiempo3, tiempoTotal, partidasJugadas) values ('" + usuario.getNombre() + "'," + usuario.getTiempo1() + "," + usuario.getTiempo2() + "," + usuario.getTiempo3() + "," + usuario.getTiempoTotal() + "," + usuario.getPartidasJugadas() + ");";
		logger.log( Level.INFO, "Statement: " + sent );
		try {
			statement.executeUpdate(sent);
		} catch (SQLException e) {
			logger.log( Level.WARNING, "No se puede meter", e );
		}
	}
	
	public static void eliminarUsuario( Usuario usuario ) {
		String sent = "delete from usuario where nombre='"+ usuario.getNombre()  +"';";
		logger.log( Level.INFO, "Statement: " + sent );
		try {
			statement.executeUpdate(sent);
		} catch (SQLException e) {
			logger.log( Level.WARNING, "No se puede eliminar", e );
		}
	}
	
	public static void modificarUsuario( Usuario usuario ) {
		String sent = "update usuario set tiempo1=" + usuario.getTiempo1() + ", tiempo2=" + usuario.getTiempo2() + ", tiempo3=" + usuario.getTiempo3() + ", tiempoTotal=" + usuario.getTiempoTotal() + ", partidasJugadas=" + usuario.getPartidasJugadas() + " where nombre='" + usuario.getNombre() + "';";
		logger.log( Level.INFO, "Statement: " + sent );
		try {
			statement.executeUpdate(sent);
		} catch (SQLException e) {
			logger.log( Level.WARNING, "No se puede modificar", e );
		}
	}
	
	public static ArrayList<Usuario> getUsuarios( Usuario usuario ) {
		String sent = "select * from usuario;";
		logger.log( Level.INFO, "Statement: " + sent );
		try {
			ResultSet rs = statement.executeQuery( sent );
			ArrayList<Usuario> listaU = new ArrayList<Usuario>();
			while(rs.next()) {
				String nombre = rs.getString("nombre");
				long tiempo1 = rs.getLong("tiempo1");
				long tiempo2 = rs.getLong("tiempo3");
				long tiempo3 = rs.getLong("tiempo3");
				long tiempoTotal = rs.getLong("tiempoTotal");
				int partidasJugadas = rs.getInt("partidasJugadas");
				listaU.add(new Usuario(nombre, tiempo1, tiempo2, tiempo3, tiempoTotal, partidasJugadas));
			}
			return listaU;
		} catch (SQLException e) {
			logger.log( Level.WARNING, "No se puede conseguir", e );
			return null;
		}
	}
}
