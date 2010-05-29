package mod_billing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class ACT_MI_KLICK_ShowOrders extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7543480244183520835L;
	
	public final String SHOWORDERS = "Auftrag anzeigen";
	
	public ACT_MI_KLICK_ShowOrders(){
		putValue(AbstractAction.NAME, SHOWORDERS);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null, "keine Action zugewiesen");

	}

}
