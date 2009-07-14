package mod_user.action;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import mod_user.MD_User;
import mod_user.MyUser;
import mod_user.gui.GU_Dialog_AddUser;

public class ACT_MI_KLICK_AddUser extends AbstractAction{
	
	public final String ADDUSER = "Benutzer hinzufügen";
	
	public ACT_MI_KLICK_AddUser(){
		putValue(AbstractAction.NAME, ADDUSER);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		GU_Dialog_AddUser dialog = new GU_Dialog_AddUser();
	}

}
