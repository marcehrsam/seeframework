package base.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

import mod_customer.AbstractCustomer;
import mod_customer.Customer;
import mod_customer.gui.ICustomerSource;
import model_test.ICustomerHolder;

public class AC_SetCustomer extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5540144416757499482L;

	public final String SETCUSTOMER = "OK";
	
	private ICustomerHolder holder = null;
	private ICustomerSource source = null;
	
	public AC_SetCustomer(ICustomerHolder holder, ICustomerSource source){
		putValue(AbstractAction.NAME, SETCUSTOMER);
		this.holder = holder;
		this.source = source;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(holder!=null)holder.setCustomer(source.getCustomer());
		AbstractCustomer c = source.getCustomer();
		
		((JDialog)source).dispose();
	}
	

}
