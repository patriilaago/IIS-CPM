package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.text.DateFormat;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnButtons;
	private JPanel pnDateTime;
	private JPanel pnFlag;

	private JButton btSpanish;
	private JButton btEnglish;
	private JButton btFrench;
	private JButton btItalian;    

	private JLabel lbDate;
	private JLabel lbTime;
	private JLabel lbFlag;
	private JLabel lbCountry;      

	private JTextArea taText;

	// Resource bundle + locale
	private ResourceBundle texts;
	private Locale currentLocale;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(getPnButtons(), BorderLayout.NORTH);
		contentPane.add(getPnDateTime(), BorderLayout.SOUTH);
		contentPane.add(getPnFlag(), BorderLayout.WEST);
		contentPane.add(getTaText(), BorderLayout.CENTER);

		Locale osLocale = Locale.getDefault(Locale.Category.FORMAT);
		Locale initialLocale = mapToSupportedLocale(osLocale);
		localize(initialLocale);
	}

	private Locale mapToSupportedLocale(Locale osLocale) {
		String lang = osLocale.getLanguage();
		if ("es".equals(lang)) {
			return new Locale("es", "ES");
		} else if ("fr".equals(lang)) {
			return new Locale("fr", "FR");
		} else if ("it".equals(lang)) {
			return new Locale("it", "IT");
		} else {
			// default: English
			return new Locale("en", "GB");
		}
	}
	
	private void localize(Locale localization) {
	    currentLocale = localization;

	    // Load bundle
	    texts = ResourceBundle.getBundle("rcs.messages", localization);

	    // Window title
	    setTitle(texts.getString("window.title"));

	    String lang = localization.getLanguage();

	    if (lang.equals("it")) {
	        lbFlag.setIcon(null);
	        setIconImage(null);
	    } else {
	        String flagPicture = "/img/" + texts.getString("image.flag");
	        ImageIcon flagIcon = new ImageIcon(MainWindow.class.getResource(flagPicture));
	        lbFlag.setIcon(flagIcon);
	        setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource(flagPicture)));
	    }
	    
	    lbCountry.setText(texts.getString("label.country"));

	    // Date & time formatted in the selected locale
	    Date now = new Date();
	    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, localization);
	    DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.LONG, localization);
	    lbDate.setText(dateFormat.format(now));
	    lbTime.setText(timeFormat.format(now));

	    // Text area: main text + "Developed by ...
	    String mainText = texts.getString("text.main");
	    String developedBy = texts.getString("text.developedBy");
	    taText.setText(mainText + "\n\n" + developedBy);

	    // Internationalize buttons' tooltips 
	    btSpanish.setToolTipText(texts.getString("tooltip.spanish"));
	    btEnglish.setToolTipText(texts.getString("tooltip.english"));
	    btFrench.setToolTipText(texts.getString("tooltip.french"));
	    btItalian.setToolTipText(texts.getString("tooltip.italian"));
	}


	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			pnButtons.setBackground(Color.WHITE);
			FlowLayout fl_pnButtons = (FlowLayout) pnButtons.getLayout();
			fl_pnButtons.setAlignment(FlowLayout.RIGHT);
			pnButtons.add(getBtSpanish());
			pnButtons.add(getBtEnglish());
			pnButtons.add(getBtFrench());
			pnButtons.add(getBtItalian());
		}
		return pnButtons;
	}
	private JPanel getPnDateTime() {
		if (pnDateTime == null) {
			pnDateTime = new JPanel();
			pnDateTime.setLayout(new GridLayout(1, 2, 0, 0));
			pnDateTime.add(getLbDate());
			pnDateTime.add(getLbTime());
		}
		return pnDateTime;
	}
	private JPanel getPnFlag() {
		if (pnFlag == null) {
			pnFlag = new JPanel();
			pnFlag.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			pnFlag.setBackground(Color.WHITE);
			pnFlag.setLayout(new GridLayout(2, 1, 0, 0));
			pnFlag.add(getLbFlag());
			pnFlag.add(getLbCountry());
		}
		return pnFlag;
	}
	private JButton getBtSpanish() {
		if (btSpanish == null) {
			btSpanish = new JButton("ES");
			btSpanish.setMnemonic('e');
			btSpanish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					localize(new Locale("es", "ES"));
				}
			});
		}
		return btSpanish;
	}
	private JButton getBtEnglish() {
		if (btEnglish == null) {
			btEnglish = new JButton("EN");
			btEnglish.setMnemonic('n');
			btEnglish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					localize(new Locale("en", "GB"));
				}
			});
		}
		return btEnglish;
	}
	private JButton getBtFrench() {
		if (btFrench == null) {
			btFrench = new JButton("FR");
			btFrench.setMnemonic('f');
			btFrench.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					localize(new Locale("fr", "FR"));
				}
			});
		}
		return btFrench;
	}
	
	private JButton getBtItalian() {
		if (btItalian == null) {
			btItalian = new JButton("IT");
			btItalian.setMnemonic('i');
			btItalian.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					localize(new Locale("it", "IT"));
				}
			});
		}
		return btItalian;
	}
	
	private JLabel getLbDate() {
		if (lbDate == null) {
			lbDate = new JLabel("");
		}
		return lbDate;
	}
	private JLabel getLbTime() {
		if (lbTime == null) {
			lbTime = new JLabel("");
		}
		return lbTime;
	}
	private JLabel getLbFlag() {
		if (lbFlag == null) {
			lbFlag = new JLabel("");
			// Real icon is set in localize(), but we can give a default:
			lbFlag.setIcon(new ImageIcon(MainWindow.class.getResource("/img/spain.PNG")));
		}
		return lbFlag;
	}
	
	private JLabel getLbCountry() {
		if (lbCountry == null) {
			lbCountry = new JLabel("");
		}
		return lbCountry;
	}
	
	private JTextArea getTaText() {
		if (taText == null) {
			taText = new JTextArea();
			taText.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			taText.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 20));
			taText.setWrapStyleWord(true);
			taText.setLineWrap(true);
			taText.setEditable(false);
		}
		return taText;
	}
	
}
