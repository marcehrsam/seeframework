package mod_user.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import mod_user.MD_User;
import mod_user.MyUser;
import mod_user.gui.GU_Dialog_AddUser;

public class ACT_BT_KLICK_AddUserOk extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3995005994497626182L;

	public final String OK = "Ok";
	
	private GU_Dialog_AddUser dialog = null;
	
	public ACT_BT_KLICK_AddUserOk(GU_Dialog_AddUser dialog){
		putValue(AbstractAction.NAME, OK);
		this.dialog = dialog;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(dialog!=null){
			String userName = dialog.getUserName();
			String password = dialog.getPassword();
			MyUser user = new MyUser(userName, password);
			MD_User.getInstance().addUser(user);
			dialog.dispose();
		}
	}

}
