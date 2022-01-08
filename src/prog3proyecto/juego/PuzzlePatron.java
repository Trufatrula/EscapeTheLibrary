package prog3proyecto.juego;

import java.util.Random;

import org.joml.Vector3f;

import com.lndf.glengine.gl.DefaultMaterial;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.components.lighting.PointLight;

public class PuzzlePatron {

	private boolean[] bolasEstado = new boolean[9];
	private boolean[] cubosEstado = new boolean[9];
	private DefaultMaterial[] materialesBolas;
	private DefaultMaterial[] materialesCubos;
	private Random random = new Random();
	
	private GameObject[] cubos;
	private GameObject[] bolas;
	private PointLight[] lucesBolas = new PointLight[9];
	
	public PuzzlePatron(DefaultMaterial[] materialesBolas, DefaultMaterial[] materialesCubos, GameObject[] cubos, GameObject[] bolas) {
		this.materialesBolas = materialesBolas;
		this.materialesCubos = materialesCubos;
		this.cubos = cubos;
		this.bolas = bolas;
		for (int i = 0; i < 9; i++) {
			this.lucesBolas[i] = new PointLight(new Vector3f(1, 0, 0), 4, 2.3f);
		}
	}
	
	public void generarPatron() {
		int c = 0;
		
		while( c < 9 ) {
			cubosEstado[c] = random.nextBoolean();
		    c++;
		}
		
		for(boolean value: cubosEstado) {
			if (value) {
				c=-1;
			}
		}
		
		if(c==9) {
			cubosEstado[random.nextInt(10)] = true;
		}
		for (int i = 0; i < 9; i++) {
			if (cubosEstado[i]) {
				this.materialesCubos[i].setEmissiveColor(new Vector3f(0.8f,0,0));
				GameObject luzObjeto = new GameObject();
				luzObjeto.getTransform().setPosition(new Vector3f(0, 0, 1f));
				cubos[i].addChild(luzObjeto);
				luzObjeto.addComponent(new PointLight(new Vector3f(1, 0, 0), 2, 2.3f));
			} 
		}
	}
	
	public void setBola(int i, boolean estado) {
		this.bolasEstado[i] = estado;
		if (estado) {
			this.materialesBolas[i].setEmissiveColor(new Vector3f(0.8f,0,0));
			this.bolas[i].addComponent(this.lucesBolas[i]);
		} else {
			this.materialesBolas[i].setEmissiveColor(new Vector3f(0,0,0));
			this.bolas[i].removeComponent(this.lucesBolas[i]);
		}	
	}
	
	public boolean getBola(int i) {
		return bolasEstado[i];
	}
	
	public boolean comprobarPuzzle() {
		for(int i = 0; i < 9; i++) {
			if (bolasEstado[i] != cubosEstado[i]) {
				return false;
			}
		}
		return true;
	}
	
	
	
}
