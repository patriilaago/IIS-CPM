package igu;

import java.awt.*;
import javax.swing.*;

import logica.JuegoOca;
import logica.Tablero;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelTablero;
	private JButton bt1;
	private JTextField txtPuntuacion;
	private JLabel etFoto;
	private JLabel lblPuntuacion;
	private JButton btDado;
	private JuegoOca oca;
	private JLabel lblDado;
	private JTextField txtDado;
	private JMenuBar menuBar;
	private JMenu mnNew;
	private JMenuItem mntmNewGame;
	private JMenu mnExit;
	private JMenu mnNewMenu_1;
	private JMenuItem mntmExit;
	
	public static void main(String[] args) {
		JuegoOca oca = new JuegoOca();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal(oca);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaPrincipal(JuegoOca oca) {
		this.oca = oca;
		setTitle("El Juego De La Oca");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/oca.png")));
		setSize(770, 324);		
		setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(java.awt.Color.white);
		contentPane.add(getPanelTablero());
		contentPane.add(getTxtPuntuacion());
		contentPane.add(getEtFoto());
		setContentPane(contentPane);
		contentPane.add(getLblPuntuacion());	
		contentPane.add(getBtDado());
		contentPane.add(getLblDado());
		contentPane.add(getTxtDado());
		setLocationRelativeTo(null);
		
		generarTablero();
	}

	private void generarTablero() {
		// TODO Auto-generated method stub
		getPanelTablero().removeAll();
		for(int i = 0; i<Tablero.DIM ; i++) {
			getPanelTablero().add(newButton());
		}
		representarEstadoJuego();
	}

	private JButton newButton() {
		// TODO Auto-generated method stub
		JButton button = new JButton();
		button.setBackground(new java.awt.Color(244, 164, 96));
		return button;
	}
	
	private void representarEstadoJuego(){
		Component[] components = getPanelTablero().getComponents();
		for(int i = 0 ; i < components.length ; i++) {
			JButton button = (JButton) components[i];
			paintButton(button,i);
			button.setEnabled(false);
		}
	}

	private void paintButton(JButton button, int i) {
		// TODO Auto-generated method stub
		if(i == oca.getPosicionFicha()) {
			button.setIcon(new ImageIcon("src/img/ficha.png"));
			button.setDisabledIcon(new ImageIcon("src/img/ficha.png"));
		}
		else if(i == Tablero.OCAINICIO) {
			button.setIcon(new ImageIcon("src/img/ocainicio.png"));
			button.setDisabledIcon(new ImageIcon("src/img/ocainicio.png"));
		}
		else if(i == Tablero.OCAFIN) {
			button.setIcon(new ImageIcon("src/img/ocafin.png"));
			button.setDisabledIcon(new ImageIcon("src/img/ocafin.png"));
		}
		else {
			button.setIcon(null);//PARA QUE SE VEA LA FICHA SOLO UNA VEZ
		}
	}

	private JLabel getEtFoto() {
	  if (etFoto == null) {
		etFoto = new JLabel();
		etFoto.setBounds(new Rectangle(0, 54, 215, 233));
		etFoto.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/oca.png")));
		etFoto.setText("");
		}
	  return etFoto;
	}
	
	
	private JPanel getPanelTablero() {
		if (panelTablero == null) {
			panelTablero = new JPanel();
			panelTablero.setBounds(new Rectangle(231, 163, 509, 72));
			panelTablero.setBackground(java.awt.Color.white);
			panelTablero.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return panelTablero;
	}


	private void habilitarTablero(boolean estado) {
		Component [] botones = getPanelTablero().getComponents();
		for (int i=0; i<botones.length;i++ )
		{
			  JButton boton = (JButton) botones[i];
		      boton.setEnabled(estado);
			}	
	   }
	
	
	private JTextField getTxtPuntuacion() {
		if (txtPuntuacion == null) {
			txtPuntuacion = new JTextField();
			txtPuntuacion.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 36));
			txtPuntuacion.setText("");
			txtPuntuacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			txtPuntuacion.setBounds(new Rectangle(476, 100, 113, 50));
			txtPuntuacion.setEditable(false);
		}
		return txtPuntuacion;
	}
	
	private JLabel getLblPuntuacion() {
		if (lblPuntuacion == null) {
			lblPuntuacion = new JLabel("Puntuacion:");
			lblPuntuacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPuntuacion.setBounds(476, 76, 82, 14);
		}
		return lblPuntuacion;
	}
	private JButton getBtDado() {
		if (btDado == null) {
			btDado = new JButton("");
			btDado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int dado = oca.lanzarDado();
					getTxtDado().setText(String.valueOf(dado));
					getTxtPuntuacion().setText(String.valueOf(oca.getPuntosJugador()));
					representarEstadoJuego();
					if(oca.isPartidaFinalizada()) {
						habilitarTablero(false);
						representarEstadoJuego();
						getBtDado().setEnabled(false);
						JOptionPane.showMessageDialog(rootPane, "YOU HAVE FINISHED THE GAME!! \n"+"Points obtained: "+String.valueOf(oca.getPuntosJugador()));
					}
				}
			});
			btDado.setBorderPainted(false);
			btDado.setBackground(Color.WHITE);
			btDado.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/dado.JPG")));
			btDado.setBounds(231, 65, 88, 85);
		}
		return btDado;
	}
	private JLabel getLblDado() {
		if (lblDado == null) {
			lblDado = new JLabel("Dado:");
			lblDado.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblDado.setBounds(329, 76, 82, 14);
		}
		return lblDado;
	}
	private JTextField getTxtDado() {
		if (txtDado == null) {
			txtDado = new JTextField();
			txtDado.setText("");
			txtDado.setHorizontalAlignment(SwingConstants.CENTER);
			txtDado.setFont(new Font("Dialog", Font.PLAIN, 36));
			txtDado.setEditable(false);
			txtDado.setBounds(new Rectangle(476, 100, 113, 50));
			txtDado.setBounds(329, 100, 113, 50);
		}
		return txtDado;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnNew());
			menuBar.add(getMnExit());
			menuBar.add(getMnNewMenu_1());
		}
		return menuBar;
	}
	private JMenu getMnNew() {
		if (mnNew == null) {
			mnNew = new JMenu("New");
			mnNew.setMnemonic('N');
			mnNew.add(getMntmNewGame());
		}
		return mnNew;
	}
	private JMenuItem getMntmNewGame() {
		if (mntmNewGame == null) {
			mntmNewGame = new JMenuItem("New Game");
			mntmNewGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					oca.inicializarJuego();
					getTxtDado().setText("0");
					getTxtPuntuacion().setText("0");
					representarEstadoJuego();
					getBtDado().setEnabled(true);
				}
			});
			mntmNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		}
		return mntmNewGame;
	}
	private JMenu getMnExit() {
		if (mnExit == null) {
			mnExit = new JMenu("Exit");
			mnExit.add(getMntmExit());
		}
		return mnExit;
	}
	
	private void checkExit() {
		// TODO Auto-generated method stub
		if(JOptionPane.showConfirmDialog(contentPane, "Are you sure you want to exit the game?") == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	private JMenu getMnNewMenu_1() {
		if (mnNewMenu_1 == null) {
			mnNewMenu_1 = new JMenu("New menu");
		}
		return mnNewMenu_1;
	}
	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit Game");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkExit();
				}
			});
			mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		}
		return mntmExit;
	}
} 
