package prog3proyecto.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import prog3proyecto.juego.Juego;

public class VentanaMain extends JFrame {

	private static final long serialVersionUID = 4636392744743705348L;
	
	private Thread hiloJuego = null;
	private DatosJugador datos;
	
	private JPanel panelPrincipal;
	private JPanel panelJuego;
	
	public VentanaMain() {
		panelPrincipal = crearPanelPrincipal();
		panelJuego = crearPanelJuego();
		
		add(panelPrincipal);
		
		this.setVisible(true);
		this.setSize(800, 600);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				terminarJuego();
			}
		});
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private JPanel crearPanelPrincipal() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel panelN = new JPanel();
		//panelN.setBackground(Color.green);
		JPanel panelW = new JPanel();
		//panelW.setBackground(Color.red);
		panelW.setLayout(new BorderLayout());
		JPanel panelWS = new JPanel();
		//panelWS.setBackground(Color.black);
		JPanel panelC = new JPanel();
		//panelC.setBackground(Color.blue);
		JPanel panelS = new JPanel();
		//panelS.setBackground(Color.yellow);
		
		String nombres[] = {"Kaladin", "Shallan", "Dalinar", "Adolin", "Sagaz", "Navani", "Szeth"};
		
		JButton botonJugar = new JButton("JUGAR");
		JButton botonOpciones = new JButton("OPCIONES");
		JButton botonCrearUsuario = new JButton("Nuevo");
		JButton botonBorrarUsuario = new JButton("Borrar");
		JLabel lnombre = new JLabel("Nombre de usuario: ");
		JTextField tnombre = new JTextField(15);
		JList<String> a = new JList<String>(nombres);
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)a.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);  
		
		panel.add(panelN, BorderLayout.NORTH);
		panel.add(panelW, BorderLayout.WEST);
		panel.add(panelC, BorderLayout.CENTER);
		panel.add(panelS, BorderLayout.SOUTH);
		panelW.add(panelWS, BorderLayout.SOUTH);
		panelN.add(lnombre);
		panelN.add(tnombre);
		panelS.add(botonJugar);
		panelS.add(botonOpciones);
		panelW.add(a, BorderLayout.CENTER);
		panelWS.add(botonCrearUsuario);
		panelWS.add(botonBorrarUsuario);
		
		botonJugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tnombre.getText().isEmpty()) {
				} else {
					startJuego(tnombre.getText());
				}
			}
		});
		
		botonOpciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				startOpciones();
			}
		});
		
		botonCrearUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				crearUsuario();
			}
		});
		
		botonBorrarUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				eliminarUsuario();
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
		JLabel controles = new JLabel("<html>Controles:<br>TODO</html>");
		controles.setVerticalAlignment(JLabel.BOTTOM);
		controles.setVerticalTextPosition(JLabel.BOTTOM);
		controles.setAlignmentY(BOTTOM_ALIGNMENT);
		controles.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0));
		
		//Crear los datos de juego
		datos = new DatosJugador(msgDatos);
		
		//Finalizar panel W
		panelW.add(msgDatos, BorderLayout.CENTER);
		panelW.add(controles, BorderLayout.SOUTH);
		
		//Finalizar panel
		panel.add(panelW);
		panel.add(new JButton("RightButtonTest"), BorderLayout.EAST);
		
		datos.actualizar();
		
		return panel;
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
	
	public void terminarJuego() {
		if (hiloJuego != null) {
			hiloJuego.interrupt();
		}
	}
	
	public void startOpciones()	{
		//TODO: hacer las opciones
	}
	
	public void crearUsuario() {
		//TODO: crear usuario
	}
	
	public void eliminarUsuario() {
		//TODO: borrar usuario
	}
	
	public static void main(String[] args) {
		new VentanaMain();
	}
	
}