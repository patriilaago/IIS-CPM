package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JToolBar toolBar;
	private JButton btnNew;
	private JButton btnSave;
	private JButton btnOpen;
	private JButton btnPrint;
	private JButton btnEnglish;
	private JButton btnSpanish;
	private JButton btnFrench;
	private JPanel pnTexts;
	private JScrollPane spOriginal;
	private JScrollPane spTranslated;
	private JPanel stateBar;
	private JLabel etDocOriginal;
	private JLabel etIdOriginal;
	private JLabel etDocTraducido;
	private JLabel etIdTraducido;
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu translateMenu;
	private JMenu toolsMenu;
	private JMenu helpMenu;
	private JMenuItem saveAsItem;
	private JMenuItem newItem;
	private JMenuItem exitItem;
	private JTextArea taOriginal;
	private JTextArea taTranslated;
	private JToggleButton tglDigitsOnly;//Toggle to enable/disable digit-only input
	private boolean documentChanged = false;// Flag to track if the document has been modified

	public MainWindow() {
		setTitle("Text Translations Editor");
		setSize(981, 586);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setJMenuBar(getMyMenuBar());
		contentPane = new JPanel(new BorderLayout());
		contentPane.add(getToolBar(), BorderLayout.NORTH);
		contentPane.add(getStateBar(), BorderLayout.SOUTH);
		contentPane.add(getPnTexts(), BorderLayout.CENTER);
		setContentPane(contentPane);

		//Event wiring
		wireWindowOpenFocus();
		wireTextAreaFocusColors(getTaOriginal(), getTaTranslated());
		addHoverBorder(getBtnNew(), getBtnOpen(), getBtnSave(), getBtnPrint(), getBtnEnglish(), getBtnSpanish(), getBtnFrench());
		wireDigitsOnlyFilter(getTaOriginal(), getTaTranslated());
		wireSaveActions();
		wireConfirmOnClose();
		wireMenuActions();
	}
	
	//Menu bar and menu items
	private JMenuBar getMyMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.setBackground(new java.awt.Color(208,204,204));
			menuBar.setBorder(BorderFactory.createEmptyBorder());
			menuBar.add(getFileMenu());
			menuBar.add(getEditMenu());
			menuBar.add(getTranslateMenu());
			menuBar.add(getToolsMenu());
			menuBar.add(getHelpMenu());
		}
		return menuBar;
	}

	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu("File");
			fileMenu.setMnemonic('F');
			fileMenu.add(getNewItem());
			fileMenu.add(new JSeparator());
			fileMenu.add(getSaveAsItem());
			fileMenu.add(new JSeparator());
			fileMenu.add(getExitItem());
		}
		return fileMenu;
	}

	private JMenu getEditMenu() {
		if (editMenu == null) {
			editMenu = new JMenu("Edit");
			editMenu.setMnemonic('E');
		}
		return editMenu;
	}

	private JMenu getTranslateMenu() {
		if (translateMenu == null) {
			translateMenu = new JMenu("Translate");
			translateMenu.setMnemonic('T');
		}
		return translateMenu;
	}

	private JMenu getToolsMenu() {
		if (toolsMenu == null) {
			toolsMenu = new JMenu("Tools");
			toolsMenu.setMnemonic('L');
		}
		return toolsMenu;
	}

	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu("Help");
			helpMenu.setMnemonic('H');
		}
		return helpMenu;
	}

	private JMenuItem getSaveAsItem() {
		if (saveAsItem == null) {
			saveAsItem = new JMenuItem("Save as...");
			saveAsItem.setMnemonic('S');
		}
		return saveAsItem;
	}

	private JMenuItem getNewItem() {
		if (newItem == null) {
			newItem = new JMenuItem("New");
			newItem.setMnemonic('N');
		}
		return newItem;
	}

	private JMenuItem getExitItem() {
		if (exitItem == null) {
			exitItem = new JMenuItem("Exit");
			exitItem.setMnemonic('X');
		}
		return exitItem;
	}

	//Toolbar
	private JToolBar getToolBar() {
		if (toolBar == null) {
			toolBar = new JToolBar();
			toolBar.setBackground(new java.awt.Color(208,204,204));
			toolBar.add(getBtnNew());
			toolBar.add(getBtnOpen());
			toolBar.add(getBtnSave());
			toolBar.add(getBtnPrint());
			toolBar.add(getBtnEnglish());
			toolBar.add(getBtnSpanish());
			toolBar.add(getBtnFrench());
			toolBar.addSeparator();
			toolBar.add(getTglDigitsOnly()); // NUEVO: toggle "Digits only"
		}
		return toolBar;
	}

	private JButton makeToolButton(String path) {
		JButton b = new JButton(new ImageIcon(MainWindow.class.getResource(path)));
		b.setBorderPainted(false);
		b.setContentAreaFilled(false);
		b.setFocusPainted(false);
		b.setPreferredSize(new Dimension(24,24));
		b.setMaximumSize(new Dimension(24,24));
		b.setMinimumSize(new Dimension(24,24));
		b.setMargin(new Insets(0,0,0,0));
		return b;
	}

	private JButton getBtnNew(){
		if (btnNew == null) {
			btnNew = makeToolButton("/img/New.png"); 
		}
		return btnNew; 
	}
	private JButton getBtnSave(){
		if (btnSave == null) {
			btnSave = makeToolButton("/img/Save.png"); 
		}
		return btnSave; 
	}
	private JButton getBtnOpen(){
		if (btnOpen == null) {
			btnOpen = makeToolButton("/img/Open.png"); 
		}
		return btnOpen; 
	}
	private JButton getBtnPrint(){
		if (btnPrint== null) {
			btnPrint= makeToolButton("/img/Print.png");
		}
		return btnPrint;
	}

	// Language buttons (EN / ES / FR)
	private JButton makeLangButton(String text) {
		JButton b = new JButton(text);
	    b.setFont(new Font("Dialog", Font.PLAIN, 11));
	    b.setBorderPainted(false);
	    b.setContentAreaFilled(false);
	    b.setFocusPainted(false);
	    b.setPreferredSize(new Dimension(24, 24));
	    b.setMaximumSize(new Dimension(24, 24));
	    b.setMinimumSize(new Dimension(24, 24));
	    b.setMargin(new Insets(0, 0, 0, 0));
	    
	    b.addMouseListener(new java.awt.event.MouseAdapter() {
	        @Override public void mouseEntered(java.awt.event.MouseEvent e) {
	            b.setForeground(Color.BLUE);
	        }
	        @Override public void mouseExited(java.awt.event.MouseEvent e) {
	            b.setForeground(Color.BLACK);
	        }
	    });
	    
	    return b;
	}

	private JButton getBtnEnglish(){ 
		if (btnEnglish==null) { 
			btnEnglish=makeLangButton("EN"); 
		}
		return btnEnglish; 
	}
	private JButton getBtnSpanish(){ 
		if (btnSpanish==null) {
			btnSpanish=makeLangButton("ES");
		}
		return btnSpanish; 
	}
	private JButton getBtnFrench() { 
		if (btnFrench ==null) {
			btnFrench =makeLangButton("FR"); 
		}
		return btnFrench; 
	}

	//Text panels
	private JPanel getPnTexts() {
		if (pnTexts == null) {
			pnTexts = new JPanel(new GridLayout(1,2,5,5));
			pnTexts.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			spOriginal = new JScrollPane(getTaOriginal());
			spOriginal.setBorder(new TitledBorder("Original"));
			pnTexts.add(spOriginal);
			spTranslated = new JScrollPane(getTaTranslated());
			spTranslated.setBorder(new TitledBorder("Translated"));
			pnTexts.add(spTranslated);
		}
		return pnTexts;
	}

	private JTextArea getTaOriginal() {
		if (taOriginal == null) {
			taOriginal = new JTextArea();
			taOriginal.setWrapStyleWord(true);
			taOriginal.setLineWrap(true);
			taOriginal.setBackground(Color.LIGHT_GRAY);
			taOriginal.setBorder(BorderFactory.createEtchedBorder());
		}
		return taOriginal;
	}

	private JTextArea getTaTranslated() {
		if (taTranslated == null) {
			taTranslated = new JTextArea();
			taTranslated.setWrapStyleWord(true);
			taTranslated.setLineWrap(true);
			taTranslated.setBackground(Color.LIGHT_GRAY);
			taTranslated.setBorder(BorderFactory.createEtchedBorder());
		}
		return taTranslated;
	}

	//Status bar
	private JPanel getStateBar() {
		if (stateBar == null) {
			stateBar = new JPanel(new GridLayout(1,4,5,5));
			stateBar.add(getEtDocOriginal());
			stateBar.add(getEtIdOriginal());
			stateBar.add(getEtDocTraducido());
			stateBar.add(getEtIdTraducido());
		}
		return stateBar;
	}

	private JLabel getEtDocOriginal() {
		if (etDocOriginal == null){
			etDocOriginal = new JLabel("Original Document");
			etDocOriginal.setFont(new Font("Dialog", Font.PLAIN, 12));
			etDocOriginal.setBorder(BorderFactory.createEtchedBorder());
		}
		return etDocOriginal;
	}
	private JLabel getEtIdOriginal() {
		if (etIdOriginal == null){
			etIdOriginal = new JLabel("Spanish");
			etIdOriginal.setFont(new Font("Dialog", Font.PLAIN, 12));
			etIdOriginal.setBorder(BorderFactory.createEtchedBorder());
		}
		return etIdOriginal;
	}
	private JLabel getEtDocTraducido() {
		if (etDocTraducido == null){
			etDocTraducido = new JLabel("Translated Document");
			etDocTraducido.setFont(new Font("Dialog", Font.PLAIN, 12));
			etDocTraducido.setBorder(BorderFactory.createEtchedBorder());
		}
		return etDocTraducido;
	}
	private JLabel getEtIdTraducido() {
		if (etIdTraducido == null){
			etIdTraducido = new JLabel("English");
			etIdTraducido.setFont(new Font("Dialog", Font.PLAIN, 12));
			etIdTraducido.setBorder(BorderFactory.createEtchedBorder());
		}
		return etIdTraducido;
	}

	//Toggle button for digits only
	private JToggleButton getTglDigitsOnly() {
		if (tglDigitsOnly == null) {
			tglDigitsOnly = new JToggleButton("Digits only");
			tglDigitsOnly.setFocusable(false);
		}
		return tglDigitsOnly;
	}

	//EVENT HANDLING
	//Change background color when text areas gain/lose focus
	private void wireTextAreaFocusColors(JTextArea... areas) {
		FocusAdapter focus = new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				e.getComponent().setBackground(Color.WHITE);
			}
			public void focusLost(FocusEvent e) {
				e.getComponent().setBackground(Color.LIGHT_GRAY);
			}
		};
		for (JTextArea a : areas) a.addFocusListener(focus);
	}

	//Focus on the Original text area when the window opens
	private void wireWindowOpenFocus() {
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				getTaOriginal().grabFocus();
			}
		});
	}

	//Show border when mouse hovers over toolbar buttons
	private void addHoverBorder(AbstractButton... buttons) {
		MouseAdapter hover = new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				((AbstractButton)e.getComponent()).setBorderPainted(true);
			}
			public void mouseExited(MouseEvent e) {
				((AbstractButton)e.getComponent()).setBorderPainted(false);
			}
		};
		for (AbstractButton b : buttons) b.addMouseListener(hover);
	}

	//Allow only digits when toggle is active
	private void wireDigitsOnlyFilter(JTextArea... areas) {
		KeyAdapter keyfilter = new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (getTglDigitsOnly().isSelected()) {
					char key = e.getKeyChar();
					if (!Character.isDigit(key) && !Character.isISOControl(key)) {
						e.consume();
					}
				}
				documentChanged = true;
			}
		};
		for (JTextArea a : areas) a.addKeyListener(keyfilter);
	}

	//Save dialog and display file name in the status bar
	private void doSave() {
		String name = JOptionPane.showInputDialog(this, "Enter document name:", "Save", JOptionPane.PLAIN_MESSAGE);
		if (name != null && !name.trim().isEmpty()) {
			getEtIdOriginal().setText(name.trim());
			documentChanged = false;
		}
	}

	private void wireSaveActions() {
		getBtnSave().addActionListener(e -> doSave());
		getSaveAsItem().addActionListener(e -> doSave());
	}

	//Confirm exit when closing window
	private void wireConfirmOnClose() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (documentChanged) {
					int opt = JOptionPane.showConfirmDialog(
						MainWindow.this,
						"Do you want to save changes before exiting?",
						"Exit Confirmation",
						JOptionPane.YES_NO_CANCEL_OPTION);
					if (opt == JOptionPane.CANCEL_OPTION || opt == JOptionPane.CLOSED_OPTION) return;
					if (opt == JOptionPane.YES_OPTION) doSave();
				}
				dispose();
				System.exit(0);
			}
		});
	}

	//// Menu actions (New, Exit, etc.)
	private void wireMenuActions() {
		getExitItem().addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
		getNewItem().addActionListener(e -> {
			getTaOriginal().setText("");
			getTaTranslated().setText("");
			getEtIdOriginal().setText("Spanish");
			documentChanged = false;
			getTaOriginal().grabFocus();
		});
	}
}
