package mod_products.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class ACT_MI_KLICK_EditProduct extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2348653449564991932L;
	
	public final String EDITPRODUCT = "Produkt ändern";
	
	public ACT_MI_KLICK_EditProduct(){
		putValue(AbstractAction.NAME, EDITPRODUCT);
	}
	
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "keine Action zugewiesen!");

	}

}
