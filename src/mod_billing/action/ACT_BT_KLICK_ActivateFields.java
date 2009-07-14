package mod_billing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import mod_billing.gui.GUI_DIALOG_CreateBill;

public class ACT_BT_KLICK_ActivateFields extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6328869094917533104L;
	
	private GUI_DIALOG_CreateBill dialog = null;
	
	public final String ACTIVATE = "Rechnungsdaten ändern";
	
	public ACT_BT_KLICK_ActivateFields(GUI_DIALOG_CreateBill dialog){
		putValue(AbstractAction.NAME, ACTIVATE);
		this.dialog = dialog;
	}

	public void actionPerformed(ActionEvent arg0) {
		dialog.enableCustomerFields();
	}

}
