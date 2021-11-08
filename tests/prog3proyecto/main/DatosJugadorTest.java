package prog3proyecto.main;

import static org.junit.Assert.*;

import org.junit.Test;

public class DatosJugadorTest {
	
	@Test
	public void test() {
		DatosJugador datos = new DatosJugador(null);
		//position
		datos.setPos(1, 2, 3);
		assertEquals(1f, datos.getpX(), 1e-15f);
		assertEquals(2f, datos.getpY(), 1e-15f);
		assertEquals(3f, datos.getpZ(), 1e-15f);
		//rotation
		datos.setRotacion(3, 2, 1);
		assertEquals(3f, datos.getrX(), 1e-15f);
		assertEquals(2f, datos.getrY(), 1e-15f);
		assertEquals(1f, datos.getrZ(), 1e-15f);
		//Usuario
		datos.setUsuario("test");
		assertTrue(datos.getUsuario().equals("test"));
		//Fases
		datos.setFase(3);
		assertEquals(3, datos.getFase());
		//Tiempos
		datos.addTiempo(5);
		assertEquals(5, datos.getTiempoJuego(), 1e-15);
		assertEquals(5, datos.getTiempoEnPartida(), 1e-15);
		assertEquals(5, datos.getTiempoFase(), 1e-15);
		datos.setFase(1);
		assertEquals(0, datos.getTiempoFase(), 1e-15);
		datos.setFase(3);
		assertEquals(5, datos.getTiempoFase(), 1e-15);
	}

}
