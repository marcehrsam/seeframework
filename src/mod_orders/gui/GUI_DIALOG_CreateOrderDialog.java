package mod_orders.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import mod_billing.action.ACT_BT_KLICK_CloseDialog;
import mod_customer.Customer;
import mod_orders.Order;
import mod_orders.action.ACT_BT_SaveNewOrder;

import tools.TO_JFrame;

public class GUI_DIALOG_CreateOrderDialog extends JDialog implements IOrderHolder{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6829234352640045508L;

	private JPanel northPanel;
	private JPanel centerPanel;
	private JPanel southPanel;
	
	private JButton saveBtn;
	private JButton cancelButton;
	
	private Order newOrder = null;
	
	public GUI_DIALOG_CreateOrderDialog(){
		newOrder = new Order();
		//TODO: kunde wählen
		Customer cust1 = new Customer();
		newOrder.setKunde(cust1);
		InitLayout();
		
	}

	private void InitLayout() {
		setSize(new Dimension(700, 700));
		TO_JFrame.getInstance().centerJFrame(this);
		setTitle("Angebot anlegen");
		setLayout(new BorderLayout());
		
		southPanel = new JPanel();
		saveBtn = new JButton(new ACT_BT_SaveNewOrder(this));
		cancelButton = new JButton(new ACT_BT_KLICK_CloseDialog(this));
		
		southPanel.add(saveBtn);
		southPanel.add(cancelButton);
		this.add(southPanel, BorderLayout.SOUTH);
		
		setAlwaysOnTop(true);
		setVisible(true);
	}

	@Override
	public Order getOrder() {
		return newOrder;
	}

	@Override
	public void closeDialog() {
		this.dispose();
	}
	
}
