package mod_billing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

import mod_billing.gui.GUI_DIALOG_ProductGetterDialog;
import mod_products.IProductGetter;

public class ACT_BT_KLICK_START_AddProductGetterDialog extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7734093844475080952L;

	private IProductGetter target = null;
	
	public ACT_BT_KLICK_START_AddProductGetterDialog(IProductGetter target){
		this.target = target;
		putValue(NAME, "+");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
		JDialog dialog = new GUI_DIALOG_ProductGetterDialog(this.target);
		
	}
	
	

}
