package prog3proyecto.juego;

import org.joml.Vector3f;

import com.lndf.glengine.engine.DeltaTime;
import com.lndf.glengine.engine.Engine;

import prog3proyecto.main.DatosJugador;

public class ActualizarEscena implements Runnable {
	
	private EscenaPrincipal escena;
	
	@Override
	public void run() {
		if (Thread.currentThread().isInterrupted()) {
			Engine.setClose(true);
		}
		//Se recogen los datos del juego y se guardan todo el rato
		Jugador jugador = (Jugador) escena.getJugador();
		DatosJugador datos = escena.getDatos();
		Vector3f posicion = jugador.getTransform().getWorldPosition();
		float pitch = jugador.getPitch();
		float yaw = jugador.getYaw();
		datos.setPos(posicion.x, posicion.y, posicion.z);
		datos.setRotacion(yaw, pitch);
		datos.addTiempo(DeltaTime.get());
		datos.actualizar();
	}
	
	public ActualizarEscena (EscenaPrincipal escena) {
		this.escena = escena;
	}
}
