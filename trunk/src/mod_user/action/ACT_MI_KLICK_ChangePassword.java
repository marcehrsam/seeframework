package mod_user.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class ACT_MI_KLICK_ChangePassword extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4185516289006641121L;
	
	public final String CHANGEPWD = "Eigenes Passwort ändern";
	
	public ACT_MI_KLICK_ChangePassword(){
		putValue(AbstractAction.NAME, CHANGEPWD);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
