package prog3proyecto.main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		JButton botonJugar = new JButton("JUGAR");
		JButton botonOpciones = new JButton("OPCIONES");
		JLabel lnombre = new JLabel("Nombre de usuario: ");
		JTextField tnombre = new JTextField(15);
		
		panel.add(lnombre);
		panel.add(tnombre);
		panel.add(botonJugar);
		panel.add(botonOpciones);;
		
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