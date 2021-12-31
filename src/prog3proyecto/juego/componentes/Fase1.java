package prog3proyecto.juego.componentes;

import java.util.Random;

import org.joml.Vector3f;
import org.joml.Vector4f;

import com.lndf.glengine.gl.DefaultMaterial;
import com.lndf.glengine.gl.Material;
import com.lndf.glengine.primitives.Cube;
import com.lndf.glengine.scene.Component;

import prog3proyecto.juego.EscenaPrincipal;
import prog3proyecto.juego.Laberinto;

public class Fase1 extends Component {
	
	private boolean fase1Iniciada = false;
	
	private Random random = new Random();
	private Material material = new DefaultMaterial(new Vector4f(1,0,0,1), new Vector4f(1,1,1,1), 1f);
	
	private void crearLibros() {
		EscenaPrincipal pepe = (EscenaPrincipal) this.getScene();
		Laberinto laberinto = pepe.getLaberinto();
		for (int i = 0; i < 3; i++) {
			int x = 0, y = 0;
			do {
				x = this.random.nextInt(14);
				y = this.random.nextInt(24);
			} while(!laberinto.getPared(x, y) && (
						laberinto.hayCamino(7, 0, x+1, y) ||
						laberinto.hayCamino(7, 0, x-1, y) ||
						laberinto.hayCamino(7, 0, x, y+1) ||
						laberinto.hayCamino(7, 0, x, y-1)
					));
			float a = 2 * (0.785f * x - 5.1f);
			float b = 2 * (0.79f * y - 9.1f);
			Cube cube = new Cube(this.material);
			cube.getTransform().setPosition(new Vector3f(a, 1, b));
			pepe.addObject(cube);
		}
	}
	
	@Override
	public void update() {
		if (!this.fase1Iniciada) {
			this.getGameObject().getTransform().setPosition(new Vector3f(0, 1, -18));
			this.fase1Iniciada = true;
		}
	}
	
	@Override
	public void addToScene() {
		EscenaPrincipal pepe = (EscenaPrincipal) this.getScene();
		pepe.crearLaberinto();
		this.crearLibros();
		
	}
	

	
	
	
}
