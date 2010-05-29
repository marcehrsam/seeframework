package mod_customer.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class ACT_MI_KLICK_RemoveCustomer extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8063288158989597107L;
	
	public final String REMOVECUSTOMER = "Kunde löschen";
	
	public ACT_MI_KLICK_RemoveCustomer(){
		putValue(AbstractAction.NAME, REMOVECUSTOMER);
	}
	
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "keine Action zugewiesen!");

	}

}
