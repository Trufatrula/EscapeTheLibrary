package prog3proyecto.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.lndf.glengine.gl.DefaultMaterial;

import prog3proyecto.juego.Juego;

public class VentanaMain extends JFrame {

	private static final long serialVersionUID = 4636392744743705348L;
	
	private boolean juegoStarted = false;
	
	public VentanaMain() {
		JButton botonJugar = new JButton("JUGAR");
		
		add(botonJugar);
		
		botonJugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				startJuego();
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
	
	public static void main(String[] args) {
		new VentanaMain();
	}
	
}