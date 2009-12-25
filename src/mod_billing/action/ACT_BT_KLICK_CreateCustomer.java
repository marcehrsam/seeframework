package mod_billing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import mod_customer.gui.GUI_DIALOG_CreateCustomer;
import model_test.ICustomerHolder;

public class ACT_BT_KLICK_CreateCustomer extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3642268052174687441L;

	public final String TITLE = "Kunde anlegen";
	
	private ICustomerHolder holder;
	
	public ACT_BT_KLICK_CreateCustomer(ICustomerHolder holder){
		putValue(AbstractAction.NAME, TITLE);
		this.holder = holder;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		//hier schnittstelle zu anderen modul einbinden!!!
		@SuppressWarnings("unused")
		GUI_DIALOG_CreateCustomer dialog = new GUI_DIALOG_CreateCustomer(holder);
		
		
	}

}
