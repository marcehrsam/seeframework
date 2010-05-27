package base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mod_billing.MD_Billing;
import mod_customer.MD_Customer;
import mod_login.MD_Login;
import mod_orders.MD_Orders;
import mod_products.MD_ProductManager;
import mod_user.MD_User;
import tools.SQLTools;

public class Config implements ISqlSource {
	
	private static Config instance = null;
	
	public final String CONFIG_INI = "config.ini";
	public final String ERROR_LOG = "error.log";
	
	//für derby
	private final String DB_USER = "db_user";
	private final Object DB_PASS = "db_pass";
	private final Object DB_PATH = "db_path";
	
	//welche Module sollen geadded werden?
	private boolean addMDLogin = true;
	private boolean addMDBilling = true;
	private boolean addMDCustomer = true;
	private boolean addMDProductManager = true;
	private boolean addMDUser = true;
	private boolean addMDOrders = true;
	private boolean addMDState = true;
	
	//hier wird die komplette config gespeichert
	private Map<String,String> cfg = null;
	
	private Config(){
		cfg = new HashMap<String, String>();
	}
	
	public void Init(){
		
		//fügt module in Programm ein
		addModules();
		
		//prüft, ob für jedes Modul eine SQL-DB auf dem Server vorhanden ist.
		//falls eine Tabelle fehlt, so wird diese erstellt
		checkAndCreateSQLTables();
		
		
	}
	
	private void checkAndCreateSQLTables(){
		//Namen der Tabellen als ArrayList
		Set<String> tablesNeeded = new HashSet<String>();
		List<AbstractModule> reqModules = Framework.FW().getModules();
		Iterator<AbstractModule> it = reqModules.iterator();
		
		//fügt tabellennamen zur Liste der zu überprüfenden SQL-Einträge hinzu
		while(it.hasNext()){
			AbstractModule current = it.next();
			try{
				ArrayList<String> cur = current.getSQLTableStrings();
				if(cur!=null){
					tablesNeeded.addAll(cur);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
		//liste mit benötigten tabellen gefüllt
		
		//jetzt muss geprüft werden, ob die tabellen in der db (richtig) angelegt sind
		
		//sind tabellen angelegt?
		SQLTools tool = new SQLTools();
		ArrayList<String> tablesInDb = tool.getAllTablesinDB();
		tablesNeeded.removeAll(tablesInDb);
		
		//wenn tablesNeeded leer ist, dann sind alle benötigten Tabellen vorhanden (noch prüfen, ob korrekt angelegt)
		//sonst müssen tabellen erzeugt werden
		if(!tablesNeeded.isEmpty()){
			//fehlende tabellen anlegen
			tool.createMissingTablesInDB(tablesNeeded);
			
		}
		//alles ok, nur noch die tabellen prüfen
		
	}
	
	private void addModules(){
		if(addMDLogin){
			Framework.FW().addModule(MD_Login.getInstance(), true);
			}
			if(addMDBilling){
			Framework.FW().addModule(MD_Billing.getInstance());
			}
			if(addMDCustomer){
			Framework.FW().addModule(MD_Customer.getInstance());
			}
			if(addMDProductManager){
			Framework.FW().addModule(MD_ProductManager.getInstance());
			}
			if(addMDUser){
			Framework.FW().addModule(MD_User.getInstance());
			}
			if(addMDOrders){
			Framework.FW().addModule(MD_Orders.getInstance());
			}
			if(addMDState){
			Framework.FW().setState(StateMan.SM().getState(StateMan.INITOK));
			}
	}
	
	public static Config getInstance(){
		if(instance == null){
			instance = new Config();
		}
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see base.ISqlSource#getDbUser()
	 */
	public String getDbUser() {
		return cfg.get(DB_USER);
	}

	/* (non-Javadoc)
	 * @see base.ISqlSource#getDbPassword()
	 */
	public String getDbPassword() {
		return cfg.get(DB_PASS);
	}

	/* (non-Javadoc)
	 * @see base.ISqlSource#getDbPath()
	 */
	public String getDbPath() {
		return cfg.get(DB_PATH);
	}


}
