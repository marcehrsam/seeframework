package mod_orders.gui;

import mod_orders.Order;
import model_test.AbstractBeleg;

public interface IOrderHolder {
	
	public AbstractBeleg getOrder();
	public void closeDialog();

}
