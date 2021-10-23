package prog3proyecto.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lndf.glengine.gl.DefaultMaterial;

import prog3proyecto.juego.Juego;

public class VentanaMain extends JFrame {

	private static final long serialVersionUID = 4636392744743705348L;
	
	private boolean juegoStarted = false;
	
	public VentanaMain() {
		JPanel panel = new JPanel();
		JButton botonJugar = new JButton("JUGAR");
		JButton botonOpciones = new JButton("OPCIONES");
		JLabel lnombre = new JLabel("Nombre de usuario: ");
		JTextField tnombre = new JTextField(15);
		
		panel.add(lnombre);
		panel.add(tnombre);
		panel.add(botonJugar);
		panel.add(botonOpciones);
		add(panel);
		
		botonJugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tnombre.getText().equals("")) {
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
		
		this.setVisible(true);
		this.setSize(800, 600);
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