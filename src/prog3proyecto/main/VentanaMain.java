package prog3proyecto.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.joml.Vector3f;

import com.lndf.glengine.engine.Engine;

import prog3proyecto.juego.EscenaPrincipal;
import prog3proyecto.juego.Juego;
import prog3proyecto.juego.Jugador;
import prog3proyecto.juego.componentes.Fase1;
import prog3proyecto.juego.componentes.Fase2;
import prog3proyecto.juego.componentes.Fase3;

public class VentanaMain extends JFrame {
	
	private static final long serialVersionUID = 4636392744743705348L;
	
	public static Logger logger = Logger.getLogger(EscenaPrincipal.class.getName());
	
	private Thread hiloJuego = null;
	private DatosJugador datos;
	
	private JPanel panelPrincipal;
	private JPanel panelJuego;
	
	private DefaultTableModel mUsuarios;
	private JTable tUsuarios;
	
	private ArrayList<Usuario> listaUsuarios;
	

	public VentanaMain() {
		
		tUsuarios = new JTable() {
			private static final long serialVersionUID = -3985044683211177377L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tUsuarios.getTableHeader().setReorderingAllowed(false);
		tUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		panelPrincipal = crearPanelPrincipal();
		panelJuego = crearPanelJuego();
		
		add(panelPrincipal);
		panelPrincipal.add(new JScrollPane(tUsuarios), BorderLayout.CENTER );
		
		this.setVisible(true);
		this.setSize(800, 600);
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				if (new File("usuarios.db").exists()) {
					BaseDatos.abrirConexion(false);
				} else {
					BaseDatos.abrirConexion(true); 
				}
				verUsuarios();
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				terminarJuego();
			}
		});
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	//Creación de los paneles y sus elementos
	private JPanel crearPanelPrincipal() {

		logger.log(Level.FINE, "Creando panel");
		JPanel panel = new JPanel();		
		panel.setLayout(new BorderLayout());
		JPanel panelN = new JPanel();
		//panelN.setBackground(Color.green);
		JPanel panelC = new JPanel();
		//panelC.setBackground(Color.blue);
		JPanel panelS = new JPanel();
		//panelS.setBackground(Color.yellow);
		
		//Crear elementos
		JButton botonJugar = new JButton("Jugar");
		JButton botonOpciones = new JButton("Opciones");
		JButton botonCrearUsuario = new JButton("Crear usuario");
		JButton botonBorrarUsuario = new JButton("Borrar usuario");
		JLabel lnombre = new JLabel("Nombre de usuario: ");
		JTextField tnombre = new JTextField(15);
		
		//Añadir elementos a paneles
		panel.add(panelN, BorderLayout.NORTH);
		panel.add(panelC, BorderLayout.CENTER);
		panel.add(panelS, BorderLayout.SOUTH);
		panelN.add(lnombre);
		panelN.add(tnombre);
		panelS.add(botonJugar);
		panelS.add(botonOpciones);

		panelS.add(botonCrearUsuario);
		panelS.add(botonBorrarUsuario);
		
		botonJugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int sel = tUsuarios.getSelectedRow();
				if(sel == -1) {
					logger.log(Level.FINE, "No hay usuario selecionado");
					return;
				}
				startJuego(listaUsuarios.get(sel).getNombre());
			}
		});
			
		botonCrearUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				crearUsuario(tnombre.getText());
				tnombre.setText("");
			}
		});
		
		botonBorrarUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int sel = tUsuarios.getSelectedRow();
				if(sel == -1) {
					logger.log(Level.FINE, "No hay usuario selecionado");
					return;
				}
				eliminarUsuario(listaUsuarios.get(sel));
				verUsuarios();
			}
		});
		
		return panel;
	}
	
	private JPanel crearPanelJuego() {
		//Crear paneles
		JPanel panel = new JPanel();
		JPanel panelW = new JPanel();
		JPanel panelE = new JPanel();
		
		//Configurar paneles
		panel.setLayout(new BorderLayout());
		panelW.setLayout(new BorderLayout());
		
		//Configurar panel de datos
		JLabel msgDatos = new JLabel();
		msgDatos.setVerticalAlignment(JLabel.TOP);
		msgDatos.setVerticalTextPosition(JLabel.TOP);
		msgDatos.setAlignmentY(TOP_ALIGNMENT);
		msgDatos.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
		
		//Configurar panel de controles
		JLabel controles = new JLabel("<html>Controles:<br>W/A/S/D: Moverse<br>I/J/K/L: mover cámara<br>Espacio: Saltar<br>F: Interactuar con objeto</html>");
		controles.setVerticalAlignment(JLabel.BOTTOM);
		controles.setVerticalTextPosition(JLabel.BOTTOM);
		controles.setAlignmentY(BOTTOM_ALIGNMENT);
		controles.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0));
		
		//Crear los datos de juego
		datos = new DatosJugador(msgDatos);
		
		//Configurar panel de cheats (panel E)
		JPanel panelCoords = new JPanel();
		JPanel panelRotacion = new JPanel();
		JTextField xTextField = new JTextField(10);
		JTextField yTextField = new JTextField(10);
		JTextField zTextField = new JTextField(10);
		JTextField pitchTextField = new JTextField(10);
		JTextField yawTextField = new JTextField(10);
		JButton botonTP = new JButton("Teletransportarse");
		JButton botonRotar = new JButton("Rotar cámara");
		JButton botonGenLaberinto = new JButton("Regenerar laberinto");
		JButton botonFase1 = new JButton("     Ir a Fase1   ");
		JButton botonFase2 = new JButton("Ir a Fase2");
		JButton botonFase3 = new JButton("Ir a Fase3");
		panelE.setLayout(new BoxLayout(panelE, BoxLayout.PAGE_AXIS));
		panelE.setAlignmentY(JPanel.TOP_ALIGNMENT);
		panelE.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));
		panelCoords.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));
		panelRotacion.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));
		
		//Crear inputs de cordenadas
		panelCoords.add(new JLabel("X: "));
		panelCoords.add(xTextField);
		panelCoords.add(new JLabel("Y: "));
		panelCoords.add(yTextField);
		panelCoords.add(new JLabel("Z: "));
		panelCoords.add(zTextField);
		
		panelRotacion.add(new JLabel("Pitch: "));
		panelRotacion.add(pitchTextField);
		panelRotacion.add(new JLabel("Yaw: "));
		panelRotacion.add(yawTextField);
		
		//Botones de TP y rotaciónº
		botonTP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setPosicion(xTextField.getText(), yTextField.getText(), zTextField.getText());
				xTextField.setText("");
				yTextField.setText("");
				zTextField.setText("");
			}
		});
		botonRotar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setRotacion(pitchTextField.getText(), yawTextField.getText());
				yawTextField.setText("");
				pitchTextField.setText("");
			}
		});
		botonGenLaberinto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Engine.addEndOfLoopRunnable(new Runnable() {
					@Override
					public void run() {
						Juego.escena.crearLaberinto();
					}
				});
			}
		});
		botonFase1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setPosicion("0", "1", "-18");
				setFase(1);
			}
		});
		botonFase2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setPosicion("0", "8", "17");
				setFase(2);
			}
		});
		botonFase3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setPosicion("0", "8", "-3");
				setFase(3);
			}
		});
		
		//Finalizar panel W
		panelW.add(msgDatos, BorderLayout.CENTER);
		panelW.add(controles, BorderLayout.SOUTH);
		
		//Finalizar panel E
		panelE.add(botonTP);
		panelE.add(panelCoords);
		panelE.add(botonRotar);
		panelE.add(panelRotacion);
		panelE.add(botonGenLaberinto);
		panelE.add(botonFase1);
		panelE.add(botonFase2);
		panelE.add(botonFase3);
			
		//Finalizar panel
		panel.add(panelW);
		panel.add(panelE, BorderLayout.EAST);
		
		datos.actualizar();
		
		return panel;
	}
	
	public void setPosicion(String x, String y, String z) {
		if (Juego.escena != null) {
			final float fx, fy, fz;
			try {
				fx = Float.parseFloat(x);
				fy = Float.parseFloat(y);
				fz = Float.parseFloat(z);
			} catch (Exception e) {
				logger.log(Level.WARNING, "Error interpretando coordenadas");
				return;
			}
			Engine.addEndOfLoopRunnable(new Runnable() {
				@Override
				public void run() {
					Juego.escena.getJugador().getTransform().setPosition(new Vector3f(fx, fy, fz));
				}
			});
		}
	}
	
	public void setRotacion(String p, String y) {
		if (Juego.escena != null) {
			final float fp, fy;
			try {
				fp = Float.parseFloat(p);
				fy = Float.parseFloat(y);
			} catch (Exception e) {
				logger.log(Level.WARNING, "Error interpretando coordenadas");
				return;
			}
			Engine.addEndOfLoopRunnable(new Runnable() {
				@Override
				public void run() {
					Jugador jugador = (Jugador) Juego.escena.getJugador();
					jugador.setPitch(fp);
					jugador.setYaw(fy);
				}
			});
		}
	}
	
	public void startJuego(String nombre) {
		if (hiloJuego != null) return;
		remove(panelPrincipal);
		add(panelJuego);
		revalidate();
		repaint();
		hiloJuego = new Thread() {
			@Override
			public void run() {
				datos.reset();
				datos.setUsuario(nombre);
				datos.actualizar();
				Juego.juego(datos);
				hiloJuego = null;
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						remove(panelJuego);
						add(panelPrincipal);
						revalidate();
						repaint();
					}
				});
			};
		};
		hiloJuego.start();
	}
	
	//Interrumpe el hilo del juego
	public void terminarJuego() {
		if (hiloJuego != null) {
			hiloJuego.interrupt();
		}
	}
	
	public void setFase(int i) {
		
		Engine.addEndOfLoopRunnable(new Runnable() {
			@Override
			public void run() {
				Fase1 fase1 = (Fase1) Juego.escena.getJugador().getComponent(Fase1.class);
				Fase2 fase2 = (Fase2) Juego.escena.getJugador().getComponent(Fase2.class);
				Fase3 fase3 = (Fase3) Juego.escena.getJugador().getComponent(Fase3.class);
				
				if(fase1 != null) {
					Juego.escena.getJugador().removeComponent(fase1);
				}
				
				if(fase2 != null) {
					Juego.escena.getJugador().removeComponent(fase2);
				}
				
				if(fase3 != null) {
					Juego.escena.getJugador().removeComponent(fase3);
				}
				
				switch(i) {
				case 1:
					Juego.escena.getJugador().addComponent(new Fase1());
					break;
				case 2:
					Juego.escena.getJugador().addComponent(new Fase2());
					break;
				case 3:
					Juego.escena.getJugador().addComponent(new Fase3());
					break;
				}
			}
		});
	}
	
	public void crearUsuario(String nombre) {
		try {
			if(nombre.isEmpty()) {
			} else {
				BaseDatos.meterUsuario(new Usuario(nombre, 0, 0, 0, 0, 0));
				verUsuarios();
			}
			logger.log(Level.FINE, "Usuario creado");
		} catch(Exception e) {
			logger.log(Level.WARNING, "Usuario no creado");
		}
	}
	
	public void eliminarUsuario(Usuario usuario) {
		
		try {
			BaseDatos.eliminarUsuario(usuario);
			logger.log(Level.FINE, "Usuario eliminado");
		} catch (Exception e) {
			logger.log(Level.WARNING, "Usuario no eliminado");
		}
	}
	
	private void cargarUsuarios() {
		listaUsuarios = BaseDatos.getUsuarios();
	}
	
	private void verUsuarios() {
		Vector<String> vectorColumnas = new Vector<String>( Arrays.asList( "Nombre", "Laberinto", "Fuego Puzzle", "Balance Centrico", "TiempoTotal", "Partidas Jugadas" ) );
		mUsuarios = new DefaultTableModel(
			new Vector<Vector<Object>>(),
			vectorColumnas
		);
		
		cargarUsuarios();
		for (Usuario u : listaUsuarios) {
			String tiempo1Str = DatosJugador.doubleDeTiempoAString(u.getTiempo1());
			String tiempo2Str = DatosJugador.doubleDeTiempoAString(u.getTiempo2());
			String tiempo3Str = DatosJugador.doubleDeTiempoAString(u.getTiempo3());
			String tiempoTotalStr = DatosJugador.doubleDeTiempoAString(u.getTiempoTotal());
			
			mUsuarios.addRow( new Object[] { u.getNombre(), tiempo1Str, tiempo2Str, tiempo3Str, tiempoTotalStr, u.getPartidasJugadas() } );
		}
		tUsuarios.setModel( mUsuarios );
		tUsuarios.getColumnModel().getColumn(0).setMinWidth(210);
		tUsuarios.getColumnModel().getColumn(0).setMaxWidth(30);
		tUsuarios.getColumnModel().getColumn(1).setMinWidth(110);
		tUsuarios.getColumnModel().getColumn(1).setMaxWidth(20);
		tUsuarios.getColumnModel().getColumn(2).setMinWidth(110);
		tUsuarios.getColumnModel().getColumn(2).setMaxWidth(20);
		tUsuarios.getColumnModel().getColumn(3).setMinWidth(110);
		tUsuarios.getColumnModel().getColumn(3).setMaxWidth(20);
		tUsuarios.getColumnModel().getColumn(4).setMinWidth(110);
		tUsuarios.getColumnModel().getColumn(4).setMaxWidth(20);
		tUsuarios.getColumnModel().getColumn(5).setMinWidth(110);
		tUsuarios.getColumnModel().getColumn(5).setMaxWidth(20);		
	}
	
	public static void main(String[] args) {
		new VentanaMain();
	}
	
}