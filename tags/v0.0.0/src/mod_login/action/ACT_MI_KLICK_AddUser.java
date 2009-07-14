package mod_login.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import mod_login.MD_Login;

public class ACT_MI_KLICK_AddUser extends AbstractAction {

	public ACT_MI_KLICK_AddUser(){
		putValue(AbstractAction.NAME, MD_Login.getInstance().NEWUSER);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Keine Action zugewiesen!");
	}

}
