package prog3proyecto.main;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class BaseDatosTest {
	
	@Test
	public void crearUsuario() {
		BaseDatos.abrirConexion(true);
		ArrayList<Usuario> usuariosAntes = BaseDatos.getUsuarios();
		BaseDatos.meterUsuario(new Usuario("a", 1, 2, 3, 4, 5));
		ArrayList<Usuario> usuariosDespues = BaseDatos.getUsuarios();
		assertEquals(usuariosAntes.size() + 1, usuariosDespues.size());
		Usuario u = usuariosDespues.get(0);
		assertEquals("a", u.getNombre());
		assertEquals(1, u.getTiempo1());
		assertEquals(2, u.getTiempo2());
		assertEquals(3, u.getTiempo3());
		assertEquals(4, u.getTiempoTotal());
		assertEquals(5, u.getPartidasJugadas());
		BaseDatos.cerrarConexion();
	}
	
	@Test
	public void editarTest() {
		BaseDatos.abrirConexion(true);
		Usuario u = new Usuario("a", 1, 2, 3, 4, 5);
		BaseDatos.meterUsuario(u);
		u.setTiempo1(100);
		BaseDatos.modificarUsuario(u);
		Usuario u2 = BaseDatos.getUsuarios().get(0);
		assertEquals(u.getTiempo1(), u2.getTiempo1());
		BaseDatos.cerrarConexion();
	}
	
	@Test
	public void borrarUsuario() {
		BaseDatos.abrirConexion(true);
		Usuario u = new Usuario("a", 1, 2, 3, 4, 5);
		BaseDatos.meterUsuario(u);
		BaseDatos.eliminarUsuario(u);
		assertEquals(0, BaseDatos.getUsuarios().size());
		BaseDatos.cerrarConexion();
	}
	
}
