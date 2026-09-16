package igu;

import java.awt.*;
import javax.swing.*;

import logica.Tablero;
import logica.OsoMiel;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelTablero;
	private JButton btnDado;
	private JTextField txtPuntuacion;
	private JLabel etFoto;
	private JLabel lblPuntuacion;
	private JTextField txtDado;
	private JLabel lblDado;
	private OsoMiel osoMiel;
	
	public static void main(String[] args) {
		OsoMiel osoMiel = new OsoMiel();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal(osoMiel);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaPrincipal(OsoMiel osoMiel) {
		this.osoMiel = osoMiel;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/oso.jpg")));
		setTitle("El Oso Y La Miel");
		setSize(770, 324);
		setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(java.awt.Color.white);
		contentPane.add(getPanelTablero());
		contentPane.add(getBtnDado());
		contentPane.add(getTxtPuntuacion());
		contentPane.add(getEtFoto());
		setContentPane(contentPane);
		contentPane.add(getLblPuntuacion());
		contentPane.add(getTxtDado());
		contentPane.add(getLblDado());
		setLocationRelativeTo(null);
		
		generarTablero();
	}

	private void generarTablero() {
		// TODO Auto-generated method stub
		getPanelTablero().removeAll();
		for(int i = 0 ; i < Tablero.DIM; i++) {
			getPanelTablero().add(newButton());
		}
		representarEstadoJuego();
	}

	private JButton newButton() {
		// TODO Auto-generated method stub
		JButton button = new JButton();
		return button;
	}

	private void representarEstadoJuego() {
		// TODO Auto-generated method stub
		Component [] botones = getPanelTablero().getComponents();
		for(int i = 0 ; i<Tablero.DIM ; i++) {
			JButton button = (JButton) botones[i];
			paintButton(button, i);
		}
		habilitarTablero(false);
	}

	private void paintButton(JButton button, int position) {
		// TODO Auto-generated method stub
		if(position == osoMiel.getPosicionJugador()) {
			button.setIcon(new ImageIcon("src/img/oso.jpg"));
			button.setDisabledIcon(new ImageIcon("src/img/oso.jpg"));
		}
		else if(position == Tablero.getCOLMENA()) {
			button.setIcon(new ImageIcon("src/img/enjambre.jpg"));
			button.setDisabledIcon(new ImageIcon("src/img/enjambre.jpg"));
		}
		else if(position == Tablero.DIM-1) {
			button.setIcon(new ImageIcon("src/img/miel.jpg"));
			button.setDisabledIcon(new ImageIcon("src/img/miel.jpg"));
		}
		else {
			button.setIcon(null);
		}
	}

	private JLabel getEtFoto() {
	  if (etFoto == null) {
		etFoto = new JLabel();
		etFoto.setBounds(new Rectangle(10, 10, 214, 151));
		etFoto.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.jpg")));
		etFoto.setText("");
		}
	  return etFoto;
	}
	
	
	private JPanel getPanelTablero() {
		if (panelTablero == null) {
			panelTablero = new JPanel();
			panelTablero.setBorder(new LineBorder(Color.ORANGE, 3));
			panelTablero.setBounds(new Rectangle(42, 175, 667, 102));
			panelTablero.setBackground(java.awt.Color.white);
			panelTablero.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return panelTablero;
	}


	private JButton getBtnDado() {
		if (btnDado == null) {
			btnDado = new JButton();
			btnDado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int dado = osoMiel.lanzarDado();
					getTxtDado().setText(String.valueOf(dado));
					getTxtPuntuacion().setText(String.valueOf(osoMiel.getPuntosJugador()));
					representarEstadoJuego();
					
					if(osoMiel.isFinalizada()) {
						habilitarTablero(false);
						getBtnDado().setEnabled(false);
						JOptionPane.showMessageDialog(rootPane, "YOU REACHED THE END!!\n"+"Points earned: "+String.valueOf(osoMiel.getPuntosJugador()+" points"));
					}
				}
			});
			btnDado.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/dado.JPG")));
			btnDado.setBorderPainted(false);
			btnDado.setContentAreaFilled(false);
			btnDado.setBounds(new Rectangle(229, 57, 89, 80));
			btnDado.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 18));
		}
		return btnDado;
	}

	private void habilitarTablero(boolean estado) {
		Component [] botones = getPanelTablero().getComponents();
		for(int i = 0 ; i<Tablero.DIM ; i++) {
			JButton button = (JButton) botones[i];
			button.setEnabled(estado);
		}	
	}
			
	private JTextField getTxtPuntuacion() {
		if (txtPuntuacion == null) {
			txtPuntuacion = new JTextField();
			txtPuntuacion.setEditable(false);
			txtPuntuacion.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 36));
			txtPuntuacion.setText(" ");
			txtPuntuacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			txtPuntuacion.setBounds(new Rectangle(484, 91, 109, 50));
		}
		return txtPuntuacion;
	}
	
	private JLabel getLblPuntuacion() {
		if (lblPuntuacion == null) {
			lblPuntuacion = new JLabel("Puntuacion:");
			lblPuntuacion.setFont(new Font("Tahoma", Font.BOLD, 17));
			lblPuntuacion.setBounds(483, 57, 110, 24);
		}
		return lblPuntuacion;
	}
	private JTextField getTxtDado() {
		if (txtDado == null) {
			txtDado = new JTextField();
			txtDado.setEditable(false);
			txtDado.setText(" ");
			txtDado.setHorizontalAlignment(SwingConstants.CENTER);
			txtDado.setFont(new Font("Dialog", Font.PLAIN, 36));
			txtDado.setBounds(new Rectangle(484, 91, 81, 50));
			txtDado.setBounds(344, 91, 81, 50);
		}
		return txtDado;
	}
	private JLabel getLblDado() {
		if (lblDado == null) {
			lblDado = new JLabel("Dado:");
			lblDado.setFont(new Font("Tahoma", Font.BOLD, 17));
			lblDado.setBounds(343, 57, 82, 14);
		}
		return lblDado;
	}
} 
