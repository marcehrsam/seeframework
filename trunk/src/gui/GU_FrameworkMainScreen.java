package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EtchedBorder;

import com.lowagie.text.pdf.FdfWriter;

import tools.TO_JFrame;
import base.AbstractModule;
import base.Framework;
import base.IModule;
import base.action.AC_ExitAction;

public class GU_FrameworkMainScreen extends JFrame implements Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;
	
	public final int WIDTH = 1024;
	public final int HEIGHT = 768;
	public final String TITLE = "SEE Framework Entwicklerversion v0.0";
	public final String FILE = "Datei";
	public final String CONFIG = "Einstellungen";
	
	private JMenuBar mainMenu = null;
	private JPanel content = null;
	private JPanel statusBar = null;
	
	private JMenu fileMenu = null;
	private JSeparator jsep = null;
	private JMenuItem miExit = null;
	
	private JLabel labMsg = null;
	
	
	public GU_FrameworkMainScreen(){
		
		//Initialisiere Framework
		Framework.FW().Init();
		
		setSize(new Dimension(WIDTH, HEIGHT));
		setTitle(TITLE);
		setLayout(new BorderLayout());
		TO_JFrame.getInstance().centerJFrame(this);
		initLayout();
		setVisible(true);
	}
	
	private void initLayout(){
		initMenu();
		initContent();
		initStatusBar();
	}
	
	private void initMenu(){
		mainMenu = new JMenuBar();
		
		//fest implementierte Bestandteile (modulverwaltung)
		initFileMenu();
		
		//Menüs für die Module hinzufügen
		for(IModule mod : Framework.FW().getModules()){
			if(mod.getMenu() != null){
				mainMenu.add(mod.getMenu());
			}
		}
		
		initConfigMenu();
		
		getContentPane().add(mainMenu, BorderLayout.NORTH);
	}
	
	private void initConfigMenu() {
		JMenu configMenu = new JMenu(CONFIG);
		
		JMenuItem miConfigApp = new JMenuItem(new ACT_MI_KLICK_ConfigApp());
		configMenu.add(miConfigApp);
		
		mainMenu.add(configMenu);
		
	}

	private void initFileMenu(){
		fileMenu = new JMenu(FILE);
		jsep = new JSeparator();
		miExit = new JMenuItem(new AC_ExitAction());
		
		
		fileMenu.add(jsep);	
		fileMenu.add(miExit);
		
		mainMenu.add(fileMenu);
	}
	
	private void initContent(){
		content = new MyPanel();
		AbstractModule mod = Framework.FW().getActiveModule();
		content = mod.getContentScreen();
		if(content!=null){
			getContentPane().add(content, BorderLayout.CENTER);
		}else{
			//Testbild anzeigen
			getContentPane().add(new GU_MP_BlankScreen(), BorderLayout.CENTER);
		}
		
	}
	
	private void initStatusBar(){
		statusBar = new MyPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		ImageIcon icon = new ImageIcon("logo.png");
		icon.setImage(icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		JLabel labIcon = new JLabel(icon);
		labIcon.setBorder(new EtchedBorder());
		statusBar.add(labIcon, gbc);
		
		MyPanel panTxt = new MyPanel(new BorderLayout());
		panTxt.setBorder(new EtchedBorder());
		labMsg = new JLabel("Alles in Ordnung");
		labMsg.setForeground(Color.GREEN);
		labMsg.setHorizontalAlignment(JLabel.CENTER);
		panTxt.add(labMsg, BorderLayout.CENTER);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 15;
		gbc.weighty = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		statusBar.add(panTxt, gbc);
		
		MyButton exitButton = new MyButton(new AC_ExitAction());
		gbc.weightx = 1;
		statusBar.add(exitButton, gbc);
		
		getContentPane().add(statusBar, BorderLayout.SOUTH);
	}

	private void updateMenu(){
		getContentPane().remove(mainMenu);
		mainMenu = new JMenuBar();
		initMenu();
		mainMenu.setVisible(false);
		mainMenu.setVisible(true);
	}
	
	private void updateState(){
		labMsg.setText(Framework.FW().getStatus().getStatusText());
		labMsg.setForeground(Framework.FW().getStatus().getStatusColor());
	}
	
	public void run(){
		//initialize framework
		Framework.FW().addObserver(this);
		//initiales update aller Observer
		update(Framework.FW(), null);
		setVisible(true);
	}
	
	public void update(Observable obs, Object o) {
		if(obs instanceof Framework){
			updateMenu();
			updateState();
			updateContent();
		}else if(obs instanceof IModule){
			
		}else{
			
		}	
	}

	private void updateContent() {
		getContentPane().remove(content);
		this.content = Framework.FW().getActiveModule().getContentScreen();
		if(this.content!=null){
			getContentPane().add(this.content, BorderLayout.CENTER);
		}
	}

}
