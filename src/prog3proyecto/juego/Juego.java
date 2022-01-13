package prog3proyecto.juego;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.lndf.glengine.engine.Engine;
import com.lndf.glengine.gl.DefaultMaterial;

import prog3proyecto.main.DatosJugador;

public class Juego {
	
	public static Logger logger = Logger.getLogger(Juego.class.getName());
	
	public static EscenaPrincipal escena;
	
	public static void juego(DatosJugador datos) {
		logger.log(Level.INFO, "Iniciando juego");
		Engine.createWindow("Escape the library", 800, 600, true);
		escena = new EscenaPrincipal(datos);
		Engine.addDrawable(escena.getCamara());
		DefaultMaterial.disableAO = true;
		logger.log(Level.INFO, "Todo listo para entrar en el main loop. Entrando...");
		Engine.mainLoop();
		logger.log(Level.INFO, "Salido del main loop. Destruir todo y terminar ahora.");
		escena.destroy();
		escena = null;
		Engine.terminate();
		logger.log(Level.INFO, "Juego terminado");
	}
}
