package guitesting;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import mod_billing.MD_Billing;

import base.Framework;

public class AddMDSEBillingToFW extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6837020846212050206L;

	public AddMDSEBillingToFW(){
		putValue(AbstractAction.NAME, "MDSEBilling hinzufügen.");
	}
	
	public void actionPerformed(ActionEvent evt) {
		Framework.FW().addModule(MD_Billing.getInstance());
	}

}
