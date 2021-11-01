package prog3proyecto.juego;

import com.lndf.glengine.engine.Window;

import prog3proyecto.main.DatosJugador;

public class Juego {
	
	public static Window ventanaPrincipal;
	
	public static void juego(DatosJugador datos) {
		ventanaPrincipal = Window.createWindow("Juego de Prog 3", 800, 600, true);
		EscenaPrincipal escena = new EscenaPrincipal(datos);
		ventanaPrincipal.addDrawable(escena.getCamara());
		ventanaPrincipal.mainLoop();
		Window.terminate();
	}
}
