package mod_customer.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import mod_customer.gui.GUI_DIALOG_CreateCustomer;

public class ACT_BT_KLICK_CANCEL_CreateCustomer extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 80431585577044659L;

	private GUI_DIALOG_CreateCustomer dialog;
	
	public final String CANCEL = "Abbrechen";
	
	public ACT_BT_KLICK_CANCEL_CreateCustomer(GUI_DIALOG_CreateCustomer dialog){
		putValue(AbstractAction.NAME, CANCEL);
		this.dialog = dialog;
	}
	
	public void actionPerformed(ActionEvent e) {
		dialog.dispose();
	}

}
