package gui;

import java.awt.Dimension;

import javax.swing.JDialog;

import base.Framework;

import tools.TO_JFrame;

public class GU_ConfigDialog_App extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4027503098075803998L;
	
	public final String APPCONFIG = "Programmeinstellungen";

	public GU_ConfigDialog_App(){
		super();
		setTitle(APPCONFIG);
		setSize(new Dimension(300, 300));
		TO_JFrame.getInstance().centerJFrame(this);
		setVisible(true);
		
		//TODO: Einstellungen vornehmen
	}
	
	
}
