package prog3proyecto.main;

import static org.junit.Assert.*;

import org.junit.Test;

import prog3proyecto.juego.Laberinto;

public class LaberintoTest {

	@Test
	public void test() {
		Laberinto laberinto = new Laberinto(30,20);
		
		assertEquals(20, laberinto.getAlto());
		
		assertEquals(30, laberinto.getAncho());
		
		laberinto.setPared(0, 0, true);
		
		assertTrue(laberinto.getPared(0, 0));
		
		laberinto.setPared(0, 0, false);
		
		assertFalse(laberinto.getPared(0, 0));
	}
}
