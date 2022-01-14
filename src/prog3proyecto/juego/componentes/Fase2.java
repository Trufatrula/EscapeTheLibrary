package prog3proyecto.juego.componentes;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.lndf.glengine.gl.DefaultMaterial;
import com.lndf.glengine.scene.Component;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.components.MeshRenderer;

import prog3proyecto.juego.EscenaPrincipal;
import prog3proyecto.juego.PuzzlePatron;
import prog3proyecto.main.DatosJugador;

public class Fase2 extends Component {
	
	public static Logger logger = Logger.getLogger(DatosJugador.class.getName());
	
	private static PuzzlePatron puzzle;
	
	@Override
	public void addToScene() {
		logger.log(Level.INFO, "Inicio de Fase 2...");
		EscenaPrincipal pepe = (EscenaPrincipal) this.getScene();
		pepe.getDatos().setFase(2);
		pepe.getJugador().getRespawn().setPos(new Vector3f(0, 8, 17));
		pepe.getJugador().getRespawn().setRot(new Quaternionf());
		pepe.setAmbientLight(0);
		pepe.getJugador().getLuz().setRadius(3);
		pepe.getJugador().getLuz().setStrength(2);
		pepe.getTerreno().movilizarElevador();
		GameObject[] cubos = pepe.getTerreno().getCubos();
		GameObject[] bolas = pepe.getTerreno().getBolas();
		DefaultMaterial[] materialesBolas = new DefaultMaterial[9];
		DefaultMaterial[] materialesCubos = new DefaultMaterial[9];
		int c = 0;
		for(GameObject cubo: cubos) {
			MeshRenderer meshCubo = (MeshRenderer) cubo.getComponent(MeshRenderer.class);
			DefaultMaterial material = (DefaultMaterial) meshCubo.getMaterial();
			materialesCubos[c++] = material;
		}
		c = 0;
		for(GameObject bola: bolas) {
			MeshRenderer meshBola = (MeshRenderer) bola.getComponent(MeshRenderer.class);
			DefaultMaterial material = (DefaultMaterial) meshBola.getMaterial();
			materialesBolas[c++] = material;
		}
		puzzle = new PuzzlePatron(materialesBolas, materialesCubos, cubos, bolas);
		puzzle.generarPatron();
		logger.log(Level.INFO, "Inicio de Fase 2 completado");
	}
	
	@Override
	public void removeFromScene() {
		EscenaPrincipal pepe = (EscenaPrincipal) this.getScene();
		pepe.getJugador().getLuz().setRadius(10);
		pepe.getJugador().getLuz().setStrength(0.5f);
		pepe.setAmbientLight(0.004f);
	}
	
	public static PuzzlePatron getPuzzle() {
		return puzzle;
	}
	
}
