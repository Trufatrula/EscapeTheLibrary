package prog3proyecto.juego;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.lndf.glengine.engine.Window;

import prog3proyecto.main.DatosJugador;

public class Juego {
	
	public static Logger logger = Logger.getLogger(Juego.class.getName());
	
	public static Window ventanaPrincipal;
	
	public static void juego(DatosJugador datos) {
		logger.log(Level.FINE, "Iniciando juego");
		ventanaPrincipal = Window.createWindow("Juego de Prog 3", 800, 600, true);
		EscenaPrincipal escena = new EscenaPrincipal(datos);
		ventanaPrincipal.addDrawable(escena.getCamara());
		ventanaPrincipal.mainLoop();
		escena.destroy();
		logger.log(Level.FINE, "Terminando juego");
		Window.terminate();
	}
}
