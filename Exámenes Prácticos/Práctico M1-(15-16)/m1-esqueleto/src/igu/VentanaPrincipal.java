package igu;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

import logica.DonkeyKongGame;
import logica.Tablero;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelTablero;
	private JLabel lblDado;
	private JTextField txtDado;
	private JLabel lblPuntuacion;
	private JTextField txtPuntuacion;
	private JLabel lblDonkeyKong;
	private JLabel lblVioleta;
	private JButton btnDado;
	private DonkeyKongGame game;

	public static void main(String[] args) {
		DonkeyKongGame game = new DonkeyKongGame();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal(game);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void inicializar(){
		game.inicializar();
		getTxtDado().setText("0");
		getTxtPuntuacion().setText("0");
		getBtnDado().setEnabled(true);
		representarEstadoJuego();
	}

	public VentanaPrincipal(DonkeyKongGame game) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/DonkeyKong.png")));
		setTitle("Donkey Kong: The Game");
		this.game = game;
		setSize(906, 344);		
		setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(java.awt.Color.white);
		contentPane.add(getPanelTablero());
		contentPane.add(getTxtDado());
		setContentPane(contentPane);
		contentPane.add(getLblDado());	
		contentPane.add(getLblDonkeyKong());
		contentPane.add(getLblVioleta());
		contentPane.add(getTxtPuntuacion());
		contentPane.add(getLblPuntuacion());
		contentPane.add(getBtnDado());
		setLocationRelativeTo(null);
		generarTablero();
	}

	private void generarTablero() {
		getPanelTablero().removeAll();
		for (int i=0; i<Tablero.DIM;i++ )
		{
			  JButton boton = newButton(i);
		      panelTablero.add(boton);
		}
		representarEstadoJuego();
	}

	private void representarEstadoJuego() {
		Component[] componentes = getPanelTablero().getComponents();
		for (int i = 0 ; i< game.getTablero().getSize();i++) {
			JButton boton = (JButton) componentes[i];
			pintarBoton(boton,i);
		}
	}

	private void pintarBoton(JButton boton, int pos) {
		if (pos == game.getPosicionFicha()){
	    	  boton.setIcon(new ImageIcon("src/img/marioicon.png"));
	    	  boton.setDisabledIcon(new ImageIcon("src/img/marioicon.png"));	    	 
	      }
	      else if (pos == Tablero.ESCALERA_INICIO_INDEX||pos == Tablero.ESCALERA_FIN_INDEX) {
	    	  boton.setIcon(new ImageIcon("src/img/stairs.png"));
		      boton.setDisabledIcon(new ImageIcon("src/img/stairs.png"));
	      }
	      else
	    	 boton.setIcon(null);
	}

	private void modificarEstadoTablero(boolean estado) {
		Component[] componentes = getPanelTablero().getComponents();
		for (Component component : componentes) {
			JButton boton = (JButton) component;
			boton.setEnabled(estado);
		}
	}

	private JPanel getPanelTablero() {
		if (panelTablero == null) {
			panelTablero = new JPanel();
			panelTablero.setBounds(new Rectangle(95, 152, 625, 112));
			panelTablero.setBackground(java.awt.Color.white);
			panelTablero.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return panelTablero;
	}

	private JLabel getLblDado() {
		if (lblDado == null) {
			lblDado = new JLabel("Dado:");
			lblDado.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblDado.setBounds(250, 47, 60, 24);
		}
		return lblDado;
	}

	private JTextField getTxtDado() {
		if (txtDado == null) {
			txtDado = new JTextField();
			txtDado.setBounds(250, 81, 90, 39);
			txtDado.setHorizontalAlignment(SwingConstants.CENTER);
			txtDado.setEditable(false);
		}
		return txtDado;
	}

	private JLabel getLblPuntuacion() {
		if (lblPuntuacion == null) {
			lblPuntuacion = new JLabel("Puntuación:");
			lblPuntuacion.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblPuntuacion.setBounds(381, 47, 126, 24);
		}
		return lblPuntuacion;
	}

	private JTextField getTxtPuntuacion() {
		if (txtPuntuacion == null) {
			txtPuntuacion = new JTextField("0");
			txtPuntuacion.setBounds(381, 81, 100, 39);
			txtPuntuacion.setHorizontalAlignment(SwingConstants.RIGHT);
			txtPuntuacion.setEditable(false);
		}
		return txtPuntuacion;
	}

	private JLabel getLblDonkeyKong() {
		if (lblDonkeyKong == null) {
			lblDonkeyKong = new JLabel(new ImageIcon(VentanaPrincipal.class.getResource("/img/DonkeyKong.png")));
			lblDonkeyKong.setBounds(690, 152, 175, 130);
		}
		return lblDonkeyKong;
	}

	private JLabel getLblVioleta() {
		if (lblVioleta == null) {
			lblVioleta = new JLabel(new ImageIcon(VentanaPrincipal.class.getResource("/img/lady.gif")));
			lblVioleta.setBounds(811, 166, 74, 120);
		}
		return lblVioleta;
	}

	private JButton getBtnDado() {
		if (btnDado == null) {
			btnDado = new JButton("");
			btnDado.setBackground(Color.WHITE);
			btnDado.setMnemonic('D');
			btnDado.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/dado.JPG")));
			btnDado.setBounds(95, 28, 120, 95);
			btnDado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (game.isFinalizada()) {
						return;
					}
					int dado = game.tirar();
					txtDado.setText(String.valueOf(dado));
					txtPuntuacion.setText(String.valueOf(game.getPuntos()));
					representarEstadoJuego();
					if (game.isFinalizada()) {
						modificarEstadoTablero(false);
						JOptionPane.showMessageDialog(
								VentanaPrincipal.this,
								"¡Has llegado a la meta!\nPuntuación: " + game.getPuntos(),
								"Fin del juego",
								JOptionPane.INFORMATION_MESSAGE
						);
					}
				}
			});
		}
		return btnDado;
	}

	private final ActionListener accionBotones = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(VentanaPrincipal.this, "El movimiento se realiza con el dado.");
		}
	};
	private JMenuBar menuBar;
	private JMenu mnGame;
	private JMenuItem mntmNewGame;

	private JButton newButton(int pos) {
		JButton button = new JButton();
		button.setEnabled(false);
		button.addActionListener(accionBotones);
		button.setBackground(new java.awt.Color(244, 164, 96));
		return button;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnGame());
		}
		return menuBar;
	}
	private JMenu getMnGame() {
		if (mnGame == null) {
			mnGame = new JMenu("New");
			mnGame.setMnemonic('N');
			mnGame.add(getMntmNewGame());
		}
		return mnGame;
	}
	private JMenuItem getMntmNewGame() {
		if (mntmNewGame == null) {
			mntmNewGame = new JMenuItem("New");
			mntmNewGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializar();
				}
			});
			mntmNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
			mntmNewGame.setVerticalTextPosition(SwingConstants.BOTTOM);
		}
		return mntmNewGame;
	}
}
 
