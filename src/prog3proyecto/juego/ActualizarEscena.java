package prog3proyecto.juego;

import org.joml.Vector3f;

import com.lndf.glengine.engine.DeltaTime;
import com.lndf.glengine.engine.Engine;
import com.lndf.glengine.scene.GameObject;

import prog3proyecto.main.DatosJugador;

public class ActualizarEscena implements Runnable {
	
	private EscenaPrincipal escena;
	
	@Override
	public void run() {
		if (Thread.currentThread().isInterrupted()) {
			Engine.setClose(true);
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
	}
	
	public ActualizarEscena (EscenaPrincipal escena) {
		this.escena = escena;
	}
}
