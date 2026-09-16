package igu;

import java.awt.*;

import javax.swing.*;

import logica.SpaceInvaders;
import logica.Tablero;

import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelTablero;
	private JButton btDado;
	private JTextField txtDado;
	private JLabel lblDado;
	private JLabel lbLogo;
	private JTextField txtPuntuacion;
	private JLabel lblPuntuacion;
	private SpaceInvaders invaders;
	
	public static void main(String[] args) {
		SpaceInvaders invaders = new SpaceInvaders();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal(invaders);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaPrincipal(SpaceInvaders invaders ) {
		this.invaders = invaders;
		setSize(882, 372);
		setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.BLACK);
		contentPane.add(getPanelTablero());
		contentPane.add(getBtDado());
		contentPane.add(getTxtDado());
		setContentPane(contentPane);
		contentPane.add(getLblDado());
		contentPane.add(getLbLogo());
		contentPane.add(getTxtPuntuacion());
		contentPane.add(getLblPuntuacion());
		
		generarTablero();
	}
	
	
	private void generarTablero() {
		// TODO Auto-generated method stub
		getPanelTablero().removeAll();
		for(int i = 0 ; i <Tablero.DIM ; i++) {
			getPanelTablero().add(newButton());
		}
		representarEstadoJuego();
	}

	private JButton newButton() {
		// TODO Auto-generated method stub
		JButton button = new JButton();
		button.setBackground(Color.GREEN);
		return button;
	}

	private void representarEstadoJuego() {
		// TODO Auto-generated method stub
		Component [] botones = getPanelTablero().getComponents();
		for (int i=0; i<botones.length;i++ ){
			 JButton button = (JButton) botones[i];
			 paintButton(button, i);
		}
		habilitarTablero(false);
	}
	
	private void paintButton(JButton button, int i) {
		// TODO Auto-generated method stub
		if(i==invaders.getPosicionNave()) {
			button.setIcon(new ImageIcon("src/img/nave.png"));
			button.setDisabledIcon(new ImageIcon("src/img/nave.png"));
		}
		else if(i==invaders.getTablero().getFirstInvasor()||i==invaders.getTablero().getSecondInvasor()) {
			button.setIcon(new ImageIcon("src/img/invader.png"));
			button.setDisabledIcon(new ImageIcon("src/img/invader.png"));
		}
		else {
			button.setIcon(null);
		}
	}

	private JPanel getPanelTablero() {
		if (panelTablero == null) {
			panelTablero = new JPanel();
			panelTablero.setBorder(new LineBorder(Color.GREEN, 3));
			panelTablero.setBounds(new Rectangle(78, 175, 735, 101));
			panelTablero.setBackground(Color.GREEN);
			panelTablero.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return panelTablero;
	}


	private JButton getBtDado() {
		if (btDado == null) {
			btDado = new JButton();
			btDado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int dado = invaders.lanzarDado();
					getTxtDado().setText(String.valueOf(dado));
					getTxtPuntuacion().setText(String.valueOf(invaders.getPuntos()));
					representarEstadoJuego();
					if(invaders.isPartidaFinalizada()) {
						representarEstadoJuego();
						habilitarTablero(false);
						JOptionPane.showMessageDialog(rootPane, "THE END!! You have finsished the game!!\n"+"Points obtained: "+String.valueOf(invaders.getPuntos()));
					}
				}
			});
			btDado.setBorderPainted(false);
			btDado.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/dado.JPG")));
			btDado.setBounds(new Rectangle(448, 37, 92, 99));
		}
		return btDado;
	}

	private void habilitarTablero(boolean estado) {
		Component [] botones = getPanelTablero().getComponents();
		for (int i=0; i<botones.length;i++ ){
			 JButton button = (JButton) botones[i];
			 button.setEnabled(estado);
		}	
	}
	
	
	
	private JTextField getTxtDado() {
		if (txtDado == null) {
			txtDado = new JTextField();
			txtDado.setForeground(Color.GREEN);
			txtDado.setBackground(Color.BLACK);
			txtDado.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 36));
			txtDado.setText(" ");
			txtDado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			txtDado.setBounds(new Rectangle(580, 76, 98, 50));
		}
		return txtDado;
	}


	private JLabel getLblDado() {
		if (lblDado == null) {
			lblDado = new JLabel("Dado:");
			lblDado.setForeground(Color.GREEN);
			lblDado.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblDado.setBounds(580, 52, 102, 14);
		}
		return lblDado;
	}
	private JLabel getLbLogo() {
		if (lbLogo == null) {
			lbLogo = new JLabel("");
			lbLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.png")));
			lbLogo.setBounds(30, 35, 385, 101);
		}
		return lbLogo;
	}
	private JTextField getTxtPuntuacion() {
		if (txtPuntuacion == null) {
			txtPuntuacion = new JTextField();
			txtPuntuacion.setText(" ");
			txtPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
			txtPuntuacion.setForeground(Color.GREEN);
			txtPuntuacion.setFont(new Font("Dialog", Font.PLAIN, 36));
			txtPuntuacion.setBounds(new Rectangle(580, 76, 98, 50));
			txtPuntuacion.setBackground(Color.BLACK);
			txtPuntuacion.setBounds(711, 76, 98, 50);
		}
		return txtPuntuacion;
	}
	private JLabel getLblPuntuacion() {
		if (lblPuntuacion == null) {
			lblPuntuacion = new JLabel("Puntuacion:");
			lblPuntuacion.setForeground(Color.GREEN);
			lblPuntuacion.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblPuntuacion.setBounds(711, 52, 102, 14);
		}
		return lblPuntuacion;
	}
} 
