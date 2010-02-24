package mod_products.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import mod_products.IProductSource;

public class ACT_BT_KLICK_AddProductOK extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4977911653996959247L;
	
	public final String ADDPRODUCT = "Neues Produkt";

	private IProductSource source = null;
	
	public ACT_BT_KLICK_AddProductOK(IProductSource source) {
		putValue(NAME, ADDPRODUCT);
		this.source = source;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		

	}

}
