package mod_user.gui;

import gui.MyButton;
import gui.MyPanel;
import gui.MyTextField;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import mod_user.action.ACT_BT_KLICK_AddUserCancel;
import mod_user.action.ACT_BT_KLICK_AddUserOk;

import tools.TO_JFrame;

public class GU_Dialog_AddUser extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5577294178168434476L;
	
	public final String NAME = "Name:";
	public final String PASS = "Passwort:";
	public final String TITLE = "Benutzer hinzufügen";
	
	private MyTextField txtName;
	private JPasswordField jpfPass;
	
	public GU_Dialog_AddUser(){
		setSize(new Dimension(500, 500));
		setLayout(new GridBagLayout());
		setTitle(TITLE);
		
		InitDialog();
		
		TO_JFrame.getInstance().centerJFrame(this);
		setVisible(true);
	}

	private void InitDialog() {
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		MyPanel namePanel = new MyPanel();
		JLabel labName = new JLabel(NAME);
		namePanel.add(labName);
		txtName = new MyTextField(30);
		namePanel.add(txtName);
		gbc.gridy = 0;
		getContentPane().add(namePanel, gbc);
		
		MyPanel passPanel = new MyPanel();
		JLabel labPass = new JLabel(PASS);
		passPanel.add(labPass);
		jpfPass = new JPasswordField(30);
		passPanel.add(jpfPass);
		gbc.gridy++;
		getContentPane().add(passPanel, gbc);
		
		
		MyPanel btPanel = new MyPanel();
		MyButton btOk = new MyButton(new ACT_BT_KLICK_AddUserOk(this));
		MyButton btCancel = new MyButton(new ACT_BT_KLICK_AddUserCancel(this));
		btPanel.add(btOk);
		btPanel.add(btCancel);
		gbc.gridy++;
		getContentPane().add(btPanel, gbc);
		
	}
	
	public String getUserName(){
		return txtName.getText();
	}

	public String getPassword(){
		return jpfPass.getPassword().toString();
	}
}
