package prog3proyecto.juego.componentes;

import static org.junit.Assert.*;

import org.joml.Vector3f;
import org.junit.Test;

import com.lndf.glengine.scene.GameObject;

public class InteractTest {
	
	@Test
	public void test() {
		GameObject jugador = new GameObject();
		GameObject objeto = new GameObject();
		
		Interact interact = new Interact(jugador) {
			@Override
			public void entrarInteract() {
			}
			@Override
			public void salirInteract() {
			}		
		};
		objeto.addComponent(interact);
		
		assertEquals(jugador, interact.getJugador());
		
		assertEquals(3f, interact.getDistancia(), 0);
		
		assertEquals(0.5236f, interact.getAngulo(), 0);
		
		objeto.getTransform().setPosition(new Vector3f(0, 0, -0.5f));
		
		interact.update();
		
		assertTrue(interact.estaInteractuando());
		
		objeto.getTransform().setPosition(new Vector3f(0, 0, 1));
		
		interact.update();
		
		assertFalse(interact.estaInteractuando());
	}

}
