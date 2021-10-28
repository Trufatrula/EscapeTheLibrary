package prog3proyecto.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import prog3proyecto.juego.Juego;

public class VentanaMain extends JFrame {

	private static final long serialVersionUID = 4636392744743705348L;
	
	private boolean juegoStarted = false;
	
	public VentanaMain() {
		JPanel panelPrincipal = crearPanelPrincipal();
		JPanel panelJuego = crearPanelJuego();
		
		add(panelPrincipal);
		//remove(panelPrincipal);
		//add(panelJuego);
		
		this.setVisible(true);
		this.setSize(800, 600);
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
					startJuego();
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
				startOpciones();
			}
		});
		
		botonBorrarUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				startOpciones();
			}
		});
		
		return panel;
	}
	
	private JPanel crearPanelJuego() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		
		panel.add(new JButton("1"));
		panel.add(new JButton("2"));
		panel.add(new JButton("3"));
		
		return panel;
	}
	
	public void startJuego() {
		if (juegoStarted) return;
		juegoStarted = true;
		new Thread() {
			@Override
			public void run() {
				Juego.juego();
				juegoStarted = false;
			};
		}.start();
	}
	
	public void startOpciones()	{
		
	}
	
	public static void main(String[] args) {
		new VentanaMain();
	}
	
}