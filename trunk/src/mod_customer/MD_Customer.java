package mod_customer;

import gui.MyPanel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import mod_customer.action.ACT_MI_KLICK_CreateCustomer;
import mod_customer.action.ACT_MI_KLICK_EditCustomer;
import mod_customer.action.ACT_MI_KLICK_RemoveCustomer;
import mod_customer.gui.GUI_DIALOG_CustomerStartScreen;
import model_test.ICustomerHolder;
import tools.SQL_Writer;
import base.AbstractModule;

public class MD_Customer extends AbstractModule implements ICustomerHolder, ListModel{

	private static MD_Customer instance = null;
	
	public final String MENUNAME = "Kunde";
	
	private JMenu menu = null;
	
	private Collection<Customer> db = null;
	
	@SuppressWarnings("unused")
	private AbstractCustomer tempCustomer = null;
	
	private Collection<ListDataListener> listDataListenerList = null;
	
	private MD_Customer(){
		super();
		
		db = new HashSet<Customer>();
		listDataListenerList = new ArrayList<ListDataListener>();
		//TODO: bei der Initialisierung werden die kundendaten aus der mysql db eingelesen
		connectToDB();
		getDataFromServer();
		//TODO: InitDB();
	}
	
	private void InitDB() {
		String query = "SELECT * FROM `kundendaten` WHERE 1;";
		SQL_Writer sqlWriter = new SQL_Writer();
		//ResultSet rs = null;
		try {
			ResultSet rs = sqlWriter.getResult(query);
			while(rs.next()){
				Customer kunde = new Customer();		
				kunde.setKundenNummer(rs.getString(kunde.KDNRA));
				kunde.setAnrede(rs.getString(kunde.ANREDE));
				kunde.setName(rs.getString(kunde.NAME));
				kunde.setVorname(rs.getString(kunde.VORNAME));
				kunde.setStrasse(rs.getString(kunde.STRASSE));
				kunde.setNummer(rs.getString(kunde.NUMMER));
				kunde.setZusatzZeile(rs.getString(kunde.ORTSTEIL));
				kunde.setPlz(rs.getString(kunde.PLZ));
				kunde.setOrt(rs.getString(kunde.ORT));
				kunde.setMail(rs.getString(kunde.MAIL));
				kunde.setTel(rs.getString(kunde.TELEFON));
				kunde.setMobil(rs.getString(kunde.HANDY));
				
				addKunde(kunde);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static MD_Customer getInstance(){
		if (instance==null) instance = new MD_Customer();
		return instance;
	}
	
	public JMenu getMenu() {
		if(menu == null)createMenu();
		return menu;
	}

	@Override
	public void createMenu() {
		menu = new JMenu(getMenuName());
		JMenuItem miCreateCustomer = new JMenuItem(new ACT_MI_KLICK_CreateCustomer());
		menu.add(miCreateCustomer);
		JMenuItem miEditCustomer = new JMenuItem(new ACT_MI_KLICK_EditCustomer());
		menu.add(miEditCustomer);
		JMenuItem miRemoveCustomer = new JMenuItem(new ACT_MI_KLICK_RemoveCustomer());
		menu.add(miRemoveCustomer);
	}

	@Override
	public String getMenuName() {
		return MENUNAME;
	}
	
	//Methoden für Modul Kundenverwaltung
	
	public boolean addKunde(Customer kunde){
		boolean ret = db.add(kunde);
		if(ret){
			setChanged();
			notifyObservers();
			return true;
		}
		JOptionPane.showMessageDialog(null, "Fehler beim Einfügen!");
		return false;
	}

	public void setCustomer(AbstractCustomer customer) {
		tempCustomer = customer;
		setChanged();
		notifyObservers();
	}

	public void addListDataListener(ListDataListener l) {
		listDataListenerList.add(l);
		setChanged();
		notifyObservers();
	}

	public Object getElementAt(int index) {
		//TODO: hier Performance verbessern 
		return db.toArray()[index];
	}

	public int getSize() {
		return db.size();
	}

	public void removeListDataListener(ListDataListener l) {
		if(listDataListenerList!=null){
			if(listDataListenerList.contains(l))listDataListenerList.remove(l);
		}
	}

	@Override
	public boolean stopAllActions() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public JPanel getContentScreen() {
		return new GUI_DIALOG_CustomerStartScreen();
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
	
	public Collection<Customer> getAllCustomers(){
		return db;
	}

	@Override
	public boolean getDataFromServer() {
		
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM kunden;");
			while(rs.next()){
				Customer nCust = new Customer();
				nCust.setKundenNummer(rs.getString("kdnr"));
				nCust.setAnrede(rs.getString("anrede"));
				nCust.setName(rs.getString("name"));
				nCust.setVorname(rs.getString("vorname"));
				nCust.setStrasse(rs.getString("strasse"));
				nCust.setNummer(rs.getString("hnr"));
				nCust.setPlz(rs.getString("plz"));
				nCust.setOrt(rs.getString("ort"));
				nCust.setTel(rs.getString("telefon"));
				nCust.setMobil(rs.getString("handy"));
				nCust.setMail(rs.getString("mail"));
				db.add(nCust);
			}
			setChanged();
			notifyObservers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	

}
