package mod_user.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class ACT_MI_KLICK_Privileges extends AbstractAction {

	public final String PRIVILEGES = "Benutzerrechte ändern";
	
	public ACT_MI_KLICK_Privileges(){
		putValue(AbstractAction.NAME, PRIVILEGES);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
