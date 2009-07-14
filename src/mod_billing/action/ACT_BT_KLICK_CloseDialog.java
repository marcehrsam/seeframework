package mod_billing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

public class ACT_BT_KLICK_CloseDialog extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6241031690438378558L;
	
	private JDialog dialog = null;
	
	public final String CLOSEDIALOG = "Abbrechen";
	
	public ACT_BT_KLICK_CloseDialog(JDialog dialog ){
		putValue(AbstractAction.NAME, CLOSEDIALOG);
		this.dialog = dialog;
	}

	public void actionPerformed(ActionEvent arg0) {
		if(dialog!=null)dialog.dispose();
	}

}
