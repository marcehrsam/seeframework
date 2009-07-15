package mod_billing;

import gui.MyPanel;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import mod_billing.action.ACT_MI_KLICK_ChangeOrder;
import mod_billing.action.ACT_MI_KLICK_CreateOrder;
import mod_billing.action.ACT_MI_KLICK_NewBill;
import mod_billing.action.ACT_MI_KLICK_ShowOrders;
import base.AbstractModule;

/**
 * Module for Creating bills.
 * 
 * @author Marc_2
 *
 */
public class MD_Billing extends AbstractModule{

	private static MD_Billing instance = null;
	
	public final String NEWBILL = "Neue Rechnung anlegen";
	public final String BILL = "Rechnung";
	
	protected JMenu menu = null;
	
	private MD_Billing(){
		super();
	}
	
	public static MD_Billing getInstance(){
		if(instance==null)instance = new MD_Billing();
		return instance;
	}
	
	public JMenu getMenu() {
		if(menu==null)createMenu();		
		return menu;
	}
	
	public void createMenu(){
		menu = new JMenu(getMenuName());
		//über Actions realisieren
		JMenuItem miShowOrders = new JMenuItem(new ACT_MI_KLICK_ShowOrders());
		menu.add(miShowOrders);
		JMenuItem miCreateOrder = new JMenuItem(new ACT_MI_KLICK_CreateOrder());
		menu.add(miCreateOrder);
		JMenuItem miChangeOrder = new JMenuItem(new ACT_MI_KLICK_ChangeOrder());
		menu.add(miChangeOrder);
		//JMenuItem miAddMDSEBilling = new JMenuItem(new AddMDSEBillingToFW());
		//menu.add(miAddMDSEBilling);
		JMenuItem miNewBill = new JMenuItem(new ACT_MI_KLICK_NewBill());
		menu.add(miNewBill);
	}

	public String getMenuName(){
		return BILL;
	}

	@Override
	public boolean stopAllActions() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JPanel getContentScreen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setContentScreen(MyPanel screen) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean readConfigFile() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
