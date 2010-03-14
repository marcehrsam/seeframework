package model_test;

import mod_orders.MD_Orders;
import mod_orders.Order;
import search.AbstractSearch;

public class Search_Order_Default extends AbstractSearch {

	@Override
	public Object search() {
		return MD_Orders.getInstance().getAllBelege(Order.OFFEN);
	}

}
