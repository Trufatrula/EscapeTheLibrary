package prog3proyecto.juego.componentes;

import org.joml.Vector3f;

import com.lndf.glengine.gl.DefaultMaterial;
import com.lndf.glengine.scene.Component;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.components.MeshRenderer;

import prog3proyecto.juego.EscenaPrincipal;
import prog3proyecto.juego.PuzzlePatron;

public class Fase2 extends Component {
	
	private static PuzzlePatron puzzle;
	
	@Override
	public void addToScene() {
		EscenaPrincipal pepe = (EscenaPrincipal) this.getScene();
		pepe.getDatos().setFase(2);
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
	}
	
	public static PuzzlePatron getPuzzle() {
		return puzzle;
	}
	
}
