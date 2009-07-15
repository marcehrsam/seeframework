package mod_customer.gui;

import gui.MyButton;
import gui.MyPanel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mod_billing.action.ACT_BT_KLICK_CloseDialog;
import mod_customer.AbstractCustomer;
import mod_customer.Customer;
import mod_customer.MD_Customer;
import model_test.ICustomerHolder;
import tools.TO_JFrame;
import base.action.AC_SetCustomer;

public class GUI_DIALOG_ChooseCustomer extends JDialog implements ListSelectionListener, ICustomerSource{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3041399392337792249L;
	
	public final Dimension SIZE = new Dimension(400, 400);
	public final String TITLE = "Kunde auswählen";
	
	private JList customerList = null;
	
	private AbstractCustomer kunde = null;
	private Container parent = null;
	private ICustomerHolder holder = null;
	
	private JPanel btPanel = null;
	private MyButton btOk = null;
	private MyButton btCancel = null;
	
	public GUI_DIALOG_ChooseCustomer(Container parent, ICustomerHolder holder){
		setSize(SIZE);
		setTitle(TITLE);
		setLayout(new BorderLayout());
		setModal(true);
		this.parent = parent;
		this.holder = holder;
		InitializeComponents();
		TO_JFrame.getInstance().centerJFrame(this);
		setVisible(true);
		
	}

	private void InitializeComponents() {
		customerList = new JList(MD_Customer.getInstance());
		getContentPane().add(customerList, BorderLayout.NORTH);
		customerList.addListSelectionListener(this);
		
		btPanel = new JPanel(new FlowLayout());
		
		if(holder!=null){
			btOk = new MyButton(new AC_SetCustomer(holder, this));
		}
		else{
			//Ohne Action
			btOk = new MyButton("--OK--"); 
		}
		btPanel.add(btOk);
		
		btCancel = new MyButton(new ACT_BT_KLICK_CloseDialog(this));
		btPanel.add(btCancel);
		
		getContentPane().add(btPanel, BorderLayout.SOUTH);
		
		
		
	}

	public void valueChanged(ListSelectionEvent e) {
		this.kunde = (AbstractCustomer)customerList.getSelectedValue();
		
	}

	public AbstractCustomer getCustomer() {
		return this.kunde;
	}

}
