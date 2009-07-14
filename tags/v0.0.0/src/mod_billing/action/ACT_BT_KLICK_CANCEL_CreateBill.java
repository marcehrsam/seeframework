package mod_billing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

public class ACT_BT_KLICK_CANCEL_CreateBill extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1192405034047372015L;

	private JDialog parent;
	
	public final String VERWERFEN = "Verwerfen";
	
	public ACT_BT_KLICK_CANCEL_CreateBill(JDialog parent){
		putValue(AbstractAction.NAME, VERWERFEN);
		this.parent = parent;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(this.parent!=null)parent.dispose();
	}

}
