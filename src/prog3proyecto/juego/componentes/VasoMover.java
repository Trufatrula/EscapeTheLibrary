package prog3proyecto.juego.componentes;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.lndf.glengine.engine.DeltaTime;
import com.lndf.glengine.scene.GameObject;
import com.lndf.glengine.scene.components.physics.DynamicRigidBody;

public class VasoMover extends InteractConObjeto {
	
	private int pos;
	private int id;
	private DynamicRigidBody rigid;
	private GameObject cono;
	private DynamicRigidBody conoRigid;
	
	private int[] moves;
	private double tiempoAnimacion = 0;
	private boolean arriba = false;
	private boolean haInteractuado = false;
	private double tiempoArriba = 0;
	
	private static Vector3f[] posiciones;
	private static Vector3f[] posicionesArriba;
	
	public VasoMover(GameObject jugador, int id, int pos, DynamicRigidBody rigid, GameObject cono, DynamicRigidBody conoRigid) {
		super(jugador);
		this.setDistancia(5);
		this.pos = pos;
		this.id = id;
		this.rigid = rigid;
		this.conoRigid = conoRigid;
		this.cono = cono;
	}
	
	@Override
	public void update() {
		super.update();
		Vector3f pos = this.getGameObject().getTransform().getWorldPosition();
		
		Quaternionf rot = this.getGameObject().getTransform().getWorldRotation();
		float posY = pos.y;
		float posIrY = 0;
		if (this.arriba) {
			posIrY = VasoMover.posicionesArriba[this.pos].y;
		} else {
			posIrY = VasoMover.posiciones[this.pos].y;
		}
		float dir = posIrY - posY;
		if (arriba && dir < 0) dir = 0;
		if (!arriba && dir > 0) dir = 0;
		if (dir != 0) {
			dir /= Math.abs(dir);
			dir *= (float) (DeltaTime.get() * 4);
			pos.y += dir;
		}
		if (this.moves != null) {
			int paso = (int) this.tiempoAnimacion - 1;
			float porcentaje = (float) (this.tiempoAnimacion % 1);
			this.tiempoAnimacion += DeltaTime.get();
			if (paso >= 0) {
				if (paso >= this.moves.length - 1) {
					this.pos = this.moves[this.moves.length - 1];
					this.moves = null;
				} else {
					Vector3f posAntes = posiciones[this.moves[paso]];
					Vector3f posDespues = posiciones[this.moves[paso + 1]];
					float x = (posDespues.x - posAntes.x) * porcentaje;
					float z = 3 * (float) Math.sin(porcentaje * Math.PI);
					if (x < 0) {
						z *= -1;
					} else if (x == 0) {
						z = 0;
					}
					z += posAntes.z;
					x += posAntes.x;
					pos.x = x;
					pos.z = z;
					if (this.cono != null) {
						Vector3f conoPos = this.cono.getTransform().getWorldPosition();
						Quaternionf conoRot = this.cono.getTransform().getWorldRotation();
						conoPos.x = x;
						conoPos.z = z;
						this.conoRigid.setKinematicTarget(conoPos, conoRot);
					}
				}
			}
		}
		if (arriba) {
			this.tiempoArriba -= DeltaTime.get();
			if (this.tiempoArriba <= 0) {
				arriba = false;
				if (this.cono != null) {
					if (this.haInteractuado) {
						Fase3.getPuzzleVasos().completado();
						this.cono.addComponent(new TpFinal(this.getJugador(), new Vector3f(10.849f, 404, -221.171f), new Quaternionf()));
					} else {
						Fase3.getPuzzleVasos().moverVasos();
					}
				}
				haInteractuado = false;
			}
			
		}
		this.rigid.setKinematicTarget(pos, rot);
	}
	
	@Override
	public void interactuar() {
		if (arriba && this.moves != null) return;
		this.haInteractuado = true;
		Fase3.getPuzzleVasos().subirVasos();
		if (this.cono != null) {
			Fase3.getPuzzleVasos().setTiemposCortos();
		}
	}

	public int getPos() {
		return pos;
	}

	public int getId() {
		return id;
	}
	
	public boolean isArriba() {
		return arriba;
	}

	public void setArriba(boolean arriba) {
		this.arriba = arriba;
	}

	public double getTiempoArriba() {
		return tiempoArriba;
	}

	public void setTiempoArriba(double tiempoArriba) {
		this.tiempoArriba = tiempoArriba;
	}

	public void animar(int[] moves) {
		this.moves = moves;
		this.tiempoAnimacion = 0;
	}

	public static void setPosiciones(Vector3f[] posiciones) {
		VasoMover.posiciones = posiciones;
		if (VasoMover.posicionesArriba == null) {
			VasoMover.posicionesArriba = new Vector3f[3];
		}
		for (int i = 0; i < 3; i++) {
			VasoMover.posicionesArriba[i] = posiciones[i].add(new Vector3f(0, 2, 0), new Vector3f());
		}
	}

}
