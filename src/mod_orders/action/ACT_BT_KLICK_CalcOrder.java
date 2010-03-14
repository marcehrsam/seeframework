package mod_orders.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import mod_billing.MD_Billing;
import mod_customer.Customer;
import mod_orders.Order;
import mod_orders.gui.IOrderHolder;
import model_test.AbstractBeleg;
import model_test.Rechnung;

public class ACT_BT_KLICK_CalcOrder extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6137907150676480145L;
	
	private IOrderHolder holder = null;
	
	public ACT_BT_KLICK_CalcOrder(IOrderHolder holder){
		this.holder = holder;
		putValue(NAME, "Rechnung anlegen");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
//		if(holder.getOrder() instanceof AbstractBeleg){
//			AbstractBeleg order = (AbstractBeleg)holder.getOrder();
//			Rechnung rechnung = new Rechnung(order);
//			//TODO: setzen
//			rechnung.setKunde(new Customer());
//			MD_Billing.getInstance().addRechnung(rechnung);
//		}
		Order o = new Order(new Customer());
		Rechnung re = new Rechnung(o);
		MD_Billing.getInstance().addRechnung(re);

	}

}
