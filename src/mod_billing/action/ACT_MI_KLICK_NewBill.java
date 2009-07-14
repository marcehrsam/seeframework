package mod_billing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import mod_billing.gui.GUI_DIALOG_CreateBill;

public class ACT_MI_KLICK_NewBill extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6060103133639208690L;

	public final String TITLE = "Neue Rechnung anlegen";
	
	public ACT_MI_KLICK_NewBill(){
		putValue(AbstractAction.NAME , TITLE);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		GUI_DIALOG_CreateBill dialog = new GUI_DIALOG_CreateBill();
		//dialog.setVisible(true);
		
	}

}
