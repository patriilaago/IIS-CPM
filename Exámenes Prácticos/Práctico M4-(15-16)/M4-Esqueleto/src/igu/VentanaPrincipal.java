package igu;

import java.awt.*;
import javax.swing.*;

import logica.PacmanGame;
import logica.Tablero;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelTablero;

	private JTextField txtPuntuacion;
	private JLabel lblPuntuacion;
	private JButton btDado;

	private PacmanGame pacman;
	private JTextField txtDado;
	private JLabel lblDado;
	
	public static void main(String[] args) {
		PacmanGame pacman = new PacmanGame();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal(pacman);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaPrincipal(PacmanGame pacman) {
		this.pacman = pacman;
		setSize(770, 324);		
		setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(java.awt.Color.white);
		contentPane.add(getPanelTablero());
		contentPane.add(getTxtPuntuacion());
		setContentPane(contentPane);
		contentPane.add(getLblPuntuacion());	
		contentPane.add(getBtDado());
		contentPane.add(getTxtDado());
		contentPane.add(getLblDado());
		
		generateBoard();
	}
	
	
	private JPanel getPanelTablero() {
		if (panelTablero == null) {
			panelTablero = new JPanel();
			panelTablero.setBounds(new Rectangle(86, 180, 604, 72));
			panelTablero.setBackground(java.awt.Color.white);
			panelTablero.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return panelTablero;
	}
	
	private JButton newButton(int pos) {
		JButton button = new JButton();
		button.setBackground(Color.RED);
		return button;
	}
	
	private void generateBoard() {
		getPanelTablero().removeAll();;
		for(int i = 0 ; i<Tablero.DIM ; i++) {
			JButton button = newButton(i);
			panelTablero.add(button);
		}
		representarEstadoJuego();
	}

	private void modificarEstadoTablero(boolean estado) { //HABILITAR SOLO LOS BOTONES
		Component [] botones = getPanelTablero().getComponents();
		for (int i=0; i<botones.length;i++ )
		{
			  JButton boton = (JButton) botones[i];
		      boton.setEnabled(estado);
		}	
	}
	
	private void representarEstadoJuego(){//CARGAR IMÁGENES Y VER PARTIDA FINALIZADA O NO
		Component [] botones = getPanelTablero().getComponents();
		for (int i=0; i<botones.length;i++ )
		{
			  JButton boton = (JButton) botones[i];
			  pintarBoton(boton,i);
		}
	}
	
	private void pintarBoton(JButton boton, int position) {
		// TODO Auto-generated method stub
		if(position == pacman.getPosicionFicha()) {
			boton.setIcon(new ImageIcon("src/img/pacmanicon.jpg"));
			boton.setDisabledIcon(new ImageIcon("src/img/pacmanicon.jpg"));
		}
		else if(position == Tablero.GHOST) {
			boton.setIcon(new ImageIcon("src/img/ghost.jpg"));
			boton.setDisabledIcon(new ImageIcon("src/img/ghost.jpg"));
		}
		else {
			boton.setIcon(null);
		}
	}

	private JTextField getTxtPuntuacion() {
		if (txtPuntuacion == null) {
			txtPuntuacion = new JTextField();
			txtPuntuacion.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 36));
			txtPuntuacion.setText("");
			txtPuntuacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			txtPuntuacion.setBounds(new Rectangle(209, 94, 138, 50));
			txtPuntuacion.setEditable(false);
		}
		return txtPuntuacion;
	}

	private JLabel getLblPuntuacion() {
		if (lblPuntuacion == null) {
			lblPuntuacion = new JLabel("Puntuacion:");
			lblPuntuacion.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblPuntuacion.setBounds(209, 70, 101, 14);
		}
		return lblPuntuacion;
	}
	private JButton getBtDado() {
		if (btDado == null) {
			btDado = new JButton("");
			btDado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(pacman.isPartidaFinalizada()) {
						return;
					}
					else {
						int dado = pacman.lanzarDado();
						txtDado.setText(String.valueOf(dado));
						txtPuntuacion.setText(String.valueOf(pacman.getPuntosJugador()));
						representarEstadoJuego();
						if(pacman.isGameOver()) {
							modificarEstadoTablero(false);
							JOptionPane.showMessageDialog(VentanaPrincipal.this, "GAME OVER");
						}
						
						if (pacman.isPartidaFinalizada()) {
							modificarEstadoTablero(false);
							JOptionPane.showMessageDialog(
									VentanaPrincipal.this,
									"¡Has llegado a la meta!\nPuntuación: " + pacman.getPuntosJugador(),
									"Fin del juego",
									JOptionPane.INFORMATION_MESSAGE
							);
						}
					}
				}
			});
			btDado.setBorderPainted(false);
			btDado.setBackground(Color.WHITE);
			btDado.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/dado.JPG")));
			btDado.setBounds(86, 70, 75, 85);
		}
		return btDado;
	}
	private JTextField getTxtDado() {
		if (txtDado == null) {
			txtDado = new JTextField();
			txtDado.setText("");
			txtDado.setHorizontalAlignment(SwingConstants.CENTER);
			txtDado.setFont(new Font("Dialog", Font.PLAIN, 36));
			txtDado.setEditable(false);
			txtDado.setBounds(new Rectangle(209, 94, 138, 50));
			txtDado.setBounds(381, 94, 138, 50);
		}
		return txtDado;
	}
	private JLabel getLblDado() {
		if (lblDado == null) {
			lblDado = new JLabel("Dado:");
			lblDado.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblDado.setBounds(381, 73, 101, 14);
		}
		return lblDado;
	}
} 
