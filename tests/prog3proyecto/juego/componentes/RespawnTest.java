package prog3proyecto.juego.componentes;

import static org.junit.Assert.*;

import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.junit.Test;

public class RespawnTest {
	
	@Test
	public void test() {
		Respawn respawn = new Respawn(-10, new Vector3f(0, 1, -18), new Quaternionf());
		
		assertEquals(new Vector3f(0, 1, -18), respawn.getPos());
		
		assertEquals(new Quaternionf(), respawn.getRot());

		assertEquals(-10, respawn.getAlturaMinima(), 0);
		
		respawn.setAlturaMinima(-20);
		
		assertEquals(-20, respawn.getAlturaMinima(), 0);
		
		respawn.setPos(new Vector3f(10, 2, -100));
		
		assertEquals(new Vector3f(10, 2, -100), respawn.getPos());
		
	}

}
