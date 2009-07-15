package mod_user.gui;

import gui.MyButton;
import gui.MyPanel;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import mod_user.MD_User;
import mod_user.action.ACT_MI_KLICK_AddUser;

public class GU_MP_UserStartScreen extends MyPanel implements Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8517024572496130718L;

	private JPanel content = null;
	
	public GU_MP_UserStartScreen(){
		setLayout(new BorderLayout());
		InitComponents();
		MD_User.getInstance().addObserver(this);
	}
	
	
	private void InitComponents() {

		initLeftBar();
		initContent();
		
	}


	private void initContent() {
		content = new GU_CurrentUserPanel();
		
	}


	private void initLeftBar() {
		//links leiste mit buttons
		JPanel leftBar = new JPanel();
		
		MyButton btAddUser = new MyButton(new ACT_MI_KLICK_AddUser());
		leftBar.add(btAddUser);
		
		MyButton btRemUser = new MyButton("Benutzer entfernen");
		leftBar.add(btRemUser);
		
		MyButton btPrivileges = new MyButton("Benutzerrechte ändern");
		leftBar.add(btPrivileges);
		
		this.add(leftBar, BorderLayout.WEST);
	}


	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
