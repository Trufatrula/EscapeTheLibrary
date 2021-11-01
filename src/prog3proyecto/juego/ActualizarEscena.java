package prog3proyecto.juego;

import com.lndf.glengine.engine.Window;

public class ActualizarEscena implements Runnable {

	@Override
	public void run() {
		if (Thread.currentThread().isInterrupted()) {
			Window.getWindow().setClose(true);
		}	
	}
}
