package mod_products.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class ACT_MI_KLICK_DeleteProduct extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1255625953441050611L;
	
	public final String REMOVEPRODUCT = "Produkt löschen";
	
	public ACT_MI_KLICK_DeleteProduct(){
		putValue(AbstractAction.NAME, REMOVEPRODUCT);
	}
	
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "keine Action zugewiesen!");

	}

}
