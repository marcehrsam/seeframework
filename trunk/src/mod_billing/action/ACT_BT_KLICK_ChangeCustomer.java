package mod_billing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import model_test.ICustomerHolder;

public class ACT_BT_KLICK_ChangeCustomer extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7627121334320997513L;

	public final String TITLE = "Kunde ändern";
	
	private ICustomerHolder holder;
	
	public ACT_BT_KLICK_ChangeCustomer(ICustomerHolder holder){
		putValue(AbstractAction.NAME, TITLE);
		this.holder = holder;
	}
	
	public void actionPerformed(ActionEvent e) {


	}

}
