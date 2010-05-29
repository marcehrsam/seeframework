package mod_products.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class ACT_MI_KLICK_NewProduct extends AbstractAction {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1828782452295601240L;
	
	public final String NEWPRODUCT = "Neues Produkt anlegen";
	
	public ACT_MI_KLICK_NewProduct(){
		putValue(AbstractAction.NAME, NEWPRODUCT);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null, "keine Action zugewiesen!");

	}

}
