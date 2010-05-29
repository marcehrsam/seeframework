package mod_user.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import mod_user.gui.GU_Dialog_AddUser;

public class ACT_MI_KLICK_AddUser extends AbstractAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3345958528674121006L;
	
	public final String ADDUSER = "Benutzer hinzufügen";
	
	public ACT_MI_KLICK_AddUser(){
		putValue(AbstractAction.NAME, ADDUSER);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		@SuppressWarnings("unused")
		GU_Dialog_AddUser dialog = new GU_Dialog_AddUser();
	}

}
