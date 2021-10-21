package prog3proyecto.juego;

import com.lndf.glengine.engine.Window;

public class Juego {
	
	public static Window ventanaPrincipal;
	
	public static void juego() {
		ventanaPrincipal = Window.createWindow("Juego de Prog 3", 800, 600, true);
		EscenaPrincipal escena = new EscenaPrincipal();
		ventanaPrincipal.addDrawable(escena.getCamara());
		ventanaPrincipal.mainLoop();
		Window.terminate();
	}
	
	public static void main(String args[]) {
		juego();
	}
	
}
