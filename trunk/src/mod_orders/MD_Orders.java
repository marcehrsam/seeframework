package mod_orders;

import gui.MyPanel;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.table.TableModel;

import mod_customer.Customer;
import mod_orders.gui.GU_MP_OrdersStartScreen;
import model_test.AbstractBeleg;
import base.AbstractModule;

public class MD_Orders extends AbstractModule{

	private static MD_Orders instance = null;
	
	public final String MENUNAME = "Auftrag";
	
	private JMenu menu = null;
	
	public boolean tableModelInitialisiert = false;
	
	//die aufträge werden nach status auf mehrere Listen verteilt
	
	//erledigte aufträge am besten nur in db archivieren und nicht in den ram laden
	private ArrayList<Order> erledigt = null;
	
	//ram
	private ArrayList<Order> fehlerhaft = null;
	
	//diese liste im ram halten 
	private ArrayList<AbstractBeleg> offen = null;
	
	//hier das Tablemodel als Objekt
	private TableModel tabMod;

	private MD_Orders(){
		//createTableModel();
		
		//listen initialisieren
		erledigt = new ArrayList<Order>();
		fehlerhaft = new ArrayList<Order>();
		offen = new ArrayList<AbstractBeleg>();
		
		initOffene();
		
	}
	
	private void initOffene() {
		// TODO Aufträge aus Datenbank lesen
		
		//TODO: nur zum Test
		
		Customer kunde1 = new Customer();
		
		Order o1 = new Order(kunde1);
		Order o2 = new Order(kunde1);
		o1.addBeleg(o2);
		offen.add(o1);
		
	}

	public void createTableModel() {
		tabMod = new MD_OrdersTableModel();
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
	
	public void setTabMod(TableModel tabMod) {
		this.tabMod = tabMod;
		setChanged();
		notifyObservers();
	}

	public ArrayList<AbstractBeleg> getAllBelege(int param){
		if(param==AbstractBeleg.OFFEN){
			return offen;
		}
		return null;
	}
	
	//TODO: nur für offene!!!
	public boolean addOrder(AbstractBeleg order){
		order.setID(getNextID());
		boolean ret = offen.add(order);
		//((MD_OrdersTableModel)tabMod).refreshModel();
		tabMod = new MD_OrdersTableModel();
		setChanged();
		notifyObservers();
		return ret;
	}
	
	public String getNextID(){
		return "keine ID";
	}
	
}
