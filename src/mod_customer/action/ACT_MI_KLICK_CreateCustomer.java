package mod_customer.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class ACT_MI_KLICK_CreateCustomer extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5774967645894559215L;
	
	public final String NEWCUSTOMER = "Neuer Kunde";
	
	public ACT_MI_KLICK_CreateCustomer(){
		putValue(AbstractAction.NAME, NEWCUSTOMER);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null, "keine Action zugewiesen");

	}

}
