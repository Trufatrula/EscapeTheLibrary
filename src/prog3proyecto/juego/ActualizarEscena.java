package prog3proyecto.juego;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.joml.Vector3f;

import com.lndf.glengine.engine.DeltaTime;
import com.lndf.glengine.engine.Window;
import com.lndf.glengine.scene.GameObject;
import prog3proyecto.main.DatosJugador;

public class ActualizarEscena implements Runnable {

	public static Logger logger = Logger.getLogger(ActualizarEscena.class.getName());
	
	private EscenaPrincipal escena;
	
	@Override
	public void run() {
		if (Thread.currentThread().isInterrupted()) {
			Window.getWindow().setClose(true);
		}
		//Se recogen los datos del juego y se guardan todo el rato
		GameObject jugador = escena.getJugador();
		DatosJugador datos = escena.getDatos();
		Vector3f posicion = jugador.getTransform().getWorldPosition();
		Vector3f rotacion = jugador.getTransform().getWorldRotation().getEulerAnglesXYZ(new Vector3f());
		datos.setPos(posicion.x, posicion.y, posicion.z);
		datos.setRotacion(rotacion.x, rotacion.y, rotacion.z);
		datos.addTiempo(DeltaTime.get());
		datos.actualizar();
		recursivo(10);
		logger.log(Level.FINEST, "Actualizado los datos en la ventana con DeltaTime " + DeltaTime.get());
	}
	
	public ActualizarEscena (EscenaPrincipal escena) {
		this.escena = escena;
	}
}
