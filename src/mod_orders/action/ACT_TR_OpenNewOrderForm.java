package mod_orders.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import mod_billing.gui.GUI_DIALOG_CreateBill;
import mod_orders.gui.GUI_DIALOG_CreateOrderDialog;
import base.action.MyTreeAction;

public class ACT_TR_OpenNewOrderForm extends MyTreeAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5488052500193841359L;
	
	public ACT_TR_OpenNewOrderForm(){
		setName("Auftrag anlegen");
		putValue(AbstractAction.NAME, toString());
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//TODO: DB anlegen mit dynamik
		String[] opts = {"Angebot", "Rechnung", "Gutschrift", "Reklamation"};
		int input = JOptionPane.showOptionDialog(null,
				"Bitte den Auftragstyp auswählen",
				"Typ wählen",
				JOptionPane.OK_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				opts,
				opts[0]
		);
		
		switch(input){
		case 0: new GUI_DIALOG_CreateOrderDialog();break;
		case 1: new GUI_DIALOG_CreateBill(); break;
		case 2: /*TODO: GS_DIALOG;*/ break;
		case 3: /*TODO: Rekla Dialog*/break;
			default: /*nix tun*/break;
		}
		
		//
		
	}

}
