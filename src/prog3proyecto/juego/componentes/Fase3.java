package prog3proyecto.juego.componentes;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.lndf.glengine.scene.Component;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.components.physics.DynamicRigidBody;

import prog3proyecto.juego.EscenaPrincipal;
import prog3proyecto.juego.PuzzleVasos;
import prog3proyecto.main.DatosJugador;

public class Fase3 extends Component {
	public static Logger logger = Logger.getLogger(DatosJugador.class.getName());
	
	private static PuzzleVasos puzzleVasos;
	private boolean iniciado = false;
	
	
	@Override
	public void update() {
		if (!iniciado) {
			Vector3f p = this.getGameObject().getTransform().getWorldPosition();
			if (p.y > 7 && p.z < -7 && !iniciado) {
				logger.log(Level.INFO, "Iniciando puzle...");
				EscenaPrincipal pepe = (EscenaPrincipal) this.getScene();
				iniciado = true;
				Vector3f[] posiciones = new Vector3f[3];
				VasoMover[] mover = new VasoMover[3];
				GameObject[] vasos = pepe.getTerreno().getVasos();
				DynamicRigidBody[] rigids = pepe.getTerreno().getVasosRigid();
				for (int i = 0; i < 3; i++) {
					GameObject cono = null;
					DynamicRigidBody rigid = null;
					if (i == 1) {
						cono = pepe.getTerreno().getConoVictoria();
						rigid = pepe.getTerreno().getConoVictoriaRigid();
					}
					mover[i] = new VasoMover(this.getGameObject(), i, i, rigids[i], cono, rigid);
					vasos[i].addComponent(mover[i]);
					posiciones[i] = vasos[i].getTransform().getWorldPosition();
				}
				puzzleVasos = new PuzzleVasos(mover);
				VasoMover.setPosiciones(posiciones);
				puzzleVasos.subirVasos();
				logger.log(Level.INFO, "Inicio de Fase 3 completado");
			}
		}
	}
	
	@Override
	public void addToScene() {
		logger.log(Level.INFO, "Inicio de Fase 3...");
		EscenaPrincipal pepe = (EscenaPrincipal) this.getScene();
		pepe.getDatos().setFase(3);
		pepe.getJugador().getRespawn().setPos(new Vector3f(0, 8, -3));
		pepe.getJugador().getRespawn().setRot(new Quaternionf());
		pepe.setAmbientLight(0.004f);
		pepe.getJugador().getLuz().setRadius(10);
		pepe.getJugador().getLuz().setStrength(0.5f);
		pepe.getTerreno().movilizarPuerta();
		logger.log(Level.INFO, "Todo preparado. Esperando para completar inicio de Fase 3.");
	}

	public static PuzzleVasos getPuzzleVasos() {
		return puzzleVasos;
	}
}