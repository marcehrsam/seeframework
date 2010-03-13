package mod_orders;

import gui.MyPanel;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.table.TableModel;

import mod_orders.gui.GU_MP_OrdersStartScreen;
import base.AbstractModule;

public class MD_Orders extends AbstractModule{

	private static MD_Orders instance = null;
	
	public final String MENUNAME = "Auftrag";
	
	private JMenu menu = null;
	
	//hier das Tablemodel als Objekt
	private TableModel tabMod;
	
	private MD_Orders(){
		createTableModel();
	}
	
	private void createTableModel() {
		tabMod = new MD_OrdersTableModel(this);
		
	}

	public static MD_Orders getInstance(){
		if(instance == null)instance = new MD_Orders();
		return instance;
	}
	
	@Override
	public void createMenu() {
		menu = new JMenu(MENUNAME);
		
		JMenuItem miNewOrder = new JMenuItem("Neu");
		menu.add(miNewOrder);
		
		JMenuItem miShowOrder = new JMenuItem("Anzeigen");
		menu.add(miShowOrder);
		
		JMenuItem miEditOrder = new JMenuItem("Ändern");
		menu.add(miEditOrder);
		
	}

	@Override
	public JPanel getContentScreen() {
		//return new Selektion();
		/*//TODO:*/ return new GU_MP_OrdersStartScreen();
	}

	@Override
	public JMenu getMenu() {
		if(menu==null)createMenu();
		return menu;
	}

	@Override
	public String getMenuName() {
		return MENUNAME;
	}

	@Override
	public boolean stopAllActions() {
		// TODO Auto-generated method stub
		return true;
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
	
	public TableModel getTableModel(){
		return tabMod;
	}

}
