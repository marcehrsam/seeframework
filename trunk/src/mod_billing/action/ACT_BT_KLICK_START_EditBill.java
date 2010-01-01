package mod_billing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import mod_billing.gui.GUI_DIALOG_EditBill;
import model_test.Rechnung;

public class ACT_BT_KLICK_START_EditBill extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1472680785877313529L;
	
	private Rechnung rechnung = null;
	
	public ACT_BT_KLICK_START_EditBill(Rechnung rechnung){
		this.rechnung = rechnung;
		putValue(NAME, "Ändern");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		@SuppressWarnings("unused")
		GUI_DIALOG_EditBill dialog = new GUI_DIALOG_EditBill(rechnung);
	}

}
