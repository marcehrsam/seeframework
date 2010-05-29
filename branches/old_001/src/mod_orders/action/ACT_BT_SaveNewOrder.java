package mod_orders.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import mod_orders.MD_Orders;
import mod_orders.Order;
import mod_orders.gui.IOrderHolder;

public class ACT_BT_SaveNewOrder extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3285906613293456129L;
	
	private Order order;
	private IOrderHolder holder = null;

	public ACT_BT_SaveNewOrder(IOrderHolder holder){
		this.holder = holder;
		putValue(NAME, "Speichern");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean ok = MD_Orders.getInstance().addOrder(holder.getOrder());
		if(ok){
			//speichern erfolgreich
			holder.closeDialog();
		}else{
			//fehler -> fenster offen lassen + fehlermeldung
			JOptionPane.showMessageDialog(null, "Fehler beim Speichern!", "Fehler", JOptionPane.ERROR_MESSAGE );
		}
		
	}

}
