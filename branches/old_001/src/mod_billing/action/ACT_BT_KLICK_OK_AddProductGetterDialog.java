package mod_billing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import tools.Debug;

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
		if(dialog.getProduct() != null){
			int anz = -1;
			while(anz<1){
			String input = JOptionPane.showInputDialog(dialog, "Wieviele Artikel sollen hinzugefügt werden?");
				try{
					anz = Integer.parseInt(input);
				}catch(Exception e){
					Debug.out("Falsche Eingabe");
				}
			}
			
			
			target.addProduct(dialog.getProduct(), anz);
			dialog.dispose();
		}else{
			//TODO Fehlermeldung ausgeben
			Debug.out("null übergeben!!!");
			dialog.dispose();
		}
		
	}

}
