package mod_billing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import mod_billing.gui.GUI_DIALOG_ProductGetterDialog;
import mod_products.IProductGetter;

public class ACT_BT_KLICK_OK_AddProductGetterDialog extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3549464507139605186L;
	
	private IProductGetter target = null;
	private GUI_DIALOG_ProductGetterDialog dialog = null;
	
	public ACT_BT_KLICK_OK_AddProductGetterDialog(GUI_DIALOG_ProductGetterDialog dialog){
		this.target = dialog.getTarget();
		this.dialog = dialog;
		
		putValue(NAME, "Übernehmen");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		target.addProduct(dialog.getProduct(), dialog.getCount());
		dialog.dispose();
	}

}
