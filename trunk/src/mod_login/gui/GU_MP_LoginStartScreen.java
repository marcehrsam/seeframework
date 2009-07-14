package mod_login.gui;

import gui.MyButton;
import gui.MyImagePanel;
import gui.MyPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.border.EtchedBorder;

import mod_login.MD_Login;
import mod_login.action.ACT_BT_KLICK_Logon;
import mod_user.MD_User;
import mod_user.User;
import base.Framework;
import base.MyState;
import base.StateMan;
import base.action.AC_ExitAction;

public class GU_MP_LoginStartScreen extends MyPanel implements Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2445577404599510108L;
	private MyButton btLogon = null;
	private MyButton btExit = null;
	private JComboBox jcbUser = null;
	private JPasswordField pwdField = null;
	
	public GU_MP_LoginStartScreen(){
		InitLayout();
		MD_User.getInstance().addObserver(this);
	}

	private void InitLayout() {
		setBorder(new EtchedBorder());
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridy = 0;
		ImageIcon img = new ImageIcon("logo.png");
		img.setImage(img.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
		
		MyImagePanel panImg = new MyImagePanel(img.getImage());
		panImg.setLayout(new GridBagLayout());
		panImg.setPreferredSize(new Dimension(300, 300));

		
		GridBagConstraints constr = new GridBagConstraints();
		constr.gridy = 0;
		
		MyPanel doublePanel = new MyPanel(new GridBagLayout());
		doublePanel.setBorder(new EtchedBorder());
		
		MyPanel userPanel = new MyPanel();
		//userPanel.setBorder(new EtchedBorder());
		JLabel labUser = new JLabel(" Username:");
		userPanel.add(labUser);
		jcbUser = new JComboBox(MD_User.getInstance());
		userPanel.add(jcbUser);
		doublePanel.add(userPanel, constr);
		
		MyPanel passwordPanel = new MyPanel();
		//passwordPanel.setBorder(new EtchedBorder());
		constr.gridy += 1;
		JLabel pwdLabel = new JLabel("Passwort:");
		passwordPanel.add(pwdLabel);
		pwdField = new JPasswordField(10);
		passwordPanel.add(pwdField);
		doublePanel.add(passwordPanel, constr);
		
		constr.insets = new Insets(180, 0, 0, 0);
		panImg.add(doublePanel, constr);
		
		this.add(panImg, gbc);
		
		btLogon = new MyButton(new ACT_BT_KLICK_Logon(this));
		btExit = new MyButton(new AC_ExitAction());
		
		MyPanel pan = new MyPanel();
		pan.add(btLogon);
		pan.add(btExit);
		
		gbc.gridy++;
		this.add(pan, gbc);
		
	}
	
	@Override
	public boolean checkInput() {
		if(jcbUser.getSelectedItem()==null){
			return false;
		}
		User user = (User)jcbUser.getSelectedItem();
		String pass = new String(pwdField.getPassword());
		//return MD_Login.getInstance().comparePasswords(user, pass);
		//TODO: Passwortabfrage aktivieren
		return true;
	}

	public void update(Observable o, Object arg) {
		if(jcbUser !=null){
			jcbUser.updateUI(); 
		}
	}
	

}
