package mod_customer.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class ACT_MI_KLICK_EditCustomer extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7666758929302763231L;
	
	public final String EDITCUSTOMER = "Kunde ändern";
	
	public ACT_MI_KLICK_EditCustomer(){
		putValue(AbstractAction.NAME, EDITCUSTOMER);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null, "keine Action zugewiesen");

	}

}
