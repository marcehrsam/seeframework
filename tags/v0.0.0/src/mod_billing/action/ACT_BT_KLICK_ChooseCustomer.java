package mod_billing.action;

import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import mod_customer.gui.GUI_DIALOG_ChooseCustomer;
import model_test.ICustomerHolder;

public class ACT_BT_KLICK_ChooseCustomer extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2607017263618682828L;
	
	public final String TITLE = "Kunde wählen";
	
	private ICustomerHolder holder = null;
	private Container parent = null;
	
	public ACT_BT_KLICK_ChooseCustomer(Container parent, ICustomerHolder holder){
		putValue(AbstractAction.NAME, TITLE);
		this.holder = holder;
		this.parent = parent;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		//TODO: Action übergeben
		GUI_DIALOG_ChooseCustomer dialog = new GUI_DIALOG_ChooseCustomer(parent, holder);

	}

}
