package prog3proyecto.juego.componentes;

import static org.junit.Assert.*;

import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.junit.Test;


public class TpFinalTest {
	
	@Test
	public void test() {
		TpFinal tpFinal = new TpFinal(null, new Vector3f(7, 7, 7), new Quaternionf());
		
		assertEquals(new Vector3f(7, 7, 7), tpFinal.getPosicion());
		
		assertEquals(new Quaternionf(), tpFinal.getRotacion());
		
		tpFinal.setPosicion(new Vector3f(2, 0, -40));
		
		assertEquals(new Vector3f(2, 0, -40), tpFinal.getPosicion());
		
		tpFinal.setRotacion(new Quaternionf(2, 2, 2, 2));
		
		assertEquals(new Quaternionf(2, 2, 2, 2), tpFinal.getRotacion());
	}
	
}
