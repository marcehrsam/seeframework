package mod_billing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class ACT_MI_KLICK_ChangeOrder extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -43516154780397883L;
	public final String CHANGEORDER = "Auftrag ändern";
	
	public ACT_MI_KLICK_ChangeOrder(){
		putValue(AbstractAction.NAME, CHANGEORDER);
	}
	
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "keine Action zugewiesen");
	}

}
