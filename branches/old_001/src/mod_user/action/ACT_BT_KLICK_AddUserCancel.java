package mod_user.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import mod_user.gui.GU_Dialog_AddUser;

import base.Framework;
import base.StateMan;

public class ACT_BT_KLICK_AddUserCancel extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3458533134113808072L;

	private GU_Dialog_AddUser dialog = null;
	public final String CANCEL = "Abbrechen";
	
	public ACT_BT_KLICK_AddUserCancel(GU_Dialog_AddUser dialog){
		putValue(AbstractAction.NAME, CANCEL);
		this.dialog = dialog;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(dialog!=null){
			dialog.dispose();
		}
		Framework.FW().setState(StateMan.SM().getState(StateMan.ACTION_CANCELED));
	}

}
