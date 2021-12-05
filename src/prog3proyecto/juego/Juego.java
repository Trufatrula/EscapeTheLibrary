package prog3proyecto.juego;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.lndf.glengine.engine.Engine;

import prog3proyecto.main.DatosJugador;

public class Juego {
	
	public static Logger logger = Logger.getLogger(Juego.class.getName());
	
	public static EscenaPrincipal escena;
	
	public static void juego(DatosJugador datos) {
		logger.log(Level.FINE, "Iniciando juego");
		Engine.createWindow("Juego de Prog 3", 800, 600, true);
		escena = new EscenaPrincipal(datos);
		Engine.addDrawable(escena.getCamara());
		Engine.mainLoop();
		escena.destroy();
		escena = null;
		logger.log(Level.FINE, "Terminando juego");
		Engine.terminate();
	}
}
