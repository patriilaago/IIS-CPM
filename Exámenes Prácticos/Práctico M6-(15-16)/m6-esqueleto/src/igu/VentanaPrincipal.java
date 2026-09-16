package igu;

import java.awt.*;
import javax.swing.*;

import logica.BartoRace;
import logica.Tablero;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelTablero;
	private JTextField txtDado;
	private JLabel lblPuntuacion;
	private JButton btDado;
	private JLabel label;
	private JTextField txtPuntuacion;
	private JLabel lblDado;
	private JLabel lblBarto;
	private JMenuBar menuBar;
	private BartoRace bartoRace;
	private AccionDado aD = null;
	
	public static void main(String[] args) {
		BartoRace bartoRace = new BartoRace();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal(bartoRace);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaPrincipal(BartoRace bartoRace) {
		this.aD = new AccionDado();
		this.bartoRace = bartoRace;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/SimpsonsElBarto.jpg")));
		setTitle("Barto's Race");
		setSize(795, 458);		
		setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(java.awt.Color.white);
		contentPane.add(getPanelTablero());
		contentPane.add(getTxtDado());
		setContentPane(contentPane);
		contentPane.add(getLblPuntuacion());	
		contentPane.add(getBtDado());
		contentPane.add(getLabel());
		contentPane.add(getTxtPuntuacion());
		contentPane.add(getLblDado());
		contentPane.add(getLblBarto());
		setLocationRelativeTo(null);
		
		generarTablero();
	}
	
	
	private void generarTablero() {
		// TODO Auto-generated method stub
		getPanelTablero().removeAll();
		for(int i = 0 ; i<Tablero.DIM ; i++) {
			getPanelTablero().add(newButton());
		}
		representarEstadoTablero();
	}

	private JButton newButton() {
		// TODO Auto-generated method stub
		JButton button = new JButton();
		button.setBackground(new java.awt.Color(244, 164, 96));
		return button;
	}
	
	private void representarEstadoTablero() {
		// TODO Auto-generated method stub
		Component[] components = getPanelTablero().getComponents();
		for(int i = 0 ; i<Tablero.DIM ; i++) {
			JButton button = (JButton) components[i];
			paintButton(button,i);
		}
		habilitarTablero(false);
	}

	private void paintButton(JButton button, int i) {
		// TODO Auto-generated method stub
		if(i == bartoRace.getPosicionJugador()) {
			if(bartoRace.tieneSkate()) {
				button.setIcon(new ImageIcon("src/img/conskate.jpg"));
				button.setDisabledIcon(new ImageIcon("src/img/conskate.jpg"));
			}
			else {
				button.setIcon(new ImageIcon("src/img/bart.png"));
				button.setDisabledIcon(new ImageIcon("src/img/bart.png"));
			}
		}
		else if(i == bartoRace.getTablero().getSKATE()) {
			button.setIcon(new ImageIcon("src/img/skate.jpg"));
			button.setDisabledIcon(new ImageIcon("src/img/skate.jpg"));
		}
		else {
			button.setIcon(null);
		}
	}

	private JPanel getPanelTablero() {
		if (panelTablero == null) {
			panelTablero = new JPanel();
			panelTablero.setBounds(new Rectangle(33, 219, 712, 160));
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
	
	private JTextField getTxtDado() {
		if (txtDado == null) {
			txtDado = new JTextField();
			txtDado.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 36));
			txtDado.setText("");
			txtDado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			txtDado.setBounds(new Rectangle(204, 121, 133, 68));
			txtDado.setEditable(false);
		}
		return txtDado;
	}
		
	private JLabel getLblPuntuacion() {
		if (lblPuntuacion == null) {
			lblPuntuacion = new JLabel("Puntuacion:");
			lblPuntuacion.setFont(new Font("Tahoma", Font.BOLD, 17));
			lblPuntuacion.setBounds(380, 94, 105, 17);
		}
		return lblPuntuacion;
	}
	private JButton getBtDado() {
		if (btDado == null) {
			btDado = new JButton("");
			btDado.addActionListener(aD);
			btDado.setBackground(Color.WHITE);
			btDado.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/dado.JPG")));
			btDado.setBounds(44, 70, 133, 119);
		}
		return btDado;
	}
	
	class AccionDado implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int dado = bartoRace.lanzarDado();
			getTxtDado().setText(String.valueOf(dado));
			getTxtPuntuacion().setText(String.valueOf(bartoRace.getPuntosJugador()));
			representarEstadoTablero();
			
			if(bartoRace.isFinalizada()) {
				habilitarTablero(false);
				JOptionPane.showMessageDialog(rootPane, "Congratulations!!! You reached the end!!\n"+"Points gained: "+String.valueOf(bartoRace.getPuntosJugador()));
				getBtDado().setEnabled(false);
			}
		}
		
	}

	
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/SimpsonsElBarto.jpg")));
			label.setBounds(552, 36, 205, 173);
		}
		return label;
	}
	private JTextField getTxtPuntuacion() {
		if (txtPuntuacion == null) {
			txtPuntuacion = new JTextField();
			txtPuntuacion.setText("");
			txtPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
			txtPuntuacion.setFont(new Font("Dialog", Font.PLAIN, 36));
			txtPuntuacion.setEditable(false);
			txtPuntuacion.setBounds(new Rectangle(204, 121, 133, 68));
			txtPuntuacion.setBounds(380, 121, 133, 68);
		}
		return txtPuntuacion;
	}
	private JLabel getLblDado() {
		if (lblDado == null) {
			lblDado = new JLabel("Puntuacion:");
			lblDado.setFont(new Font("Tahoma", Font.BOLD, 17));
			lblDado.setBounds(204, 94, 105, 17);
		}
		return lblDado;
	}
	private JLabel getLblBarto() {
		if (lblBarto == null) {
			lblBarto = new JLabel("Barto's Race");
			lblBarto.setFont(new Font("Segoe UI Emoji", Font.BOLD, 38));
			lblBarto.setBounds(270, 36, 243, 39);
		}
		return lblBarto;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
		}
		return menuBar;
	}
} 
