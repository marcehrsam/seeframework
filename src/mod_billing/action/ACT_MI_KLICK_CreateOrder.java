package mod_billing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class ACT_MI_KLICK_CreateOrder extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8334349595482742403L;
	
	public final String CREATEORDER = "Neuer Auftrag";

	public ACT_MI_KLICK_CreateOrder(){
		putValue(AbstractAction.NAME, CREATEORDER);
	}
	
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "keine Action zugewiesen");

	}

}
