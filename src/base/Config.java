package base;

import java.util.HashMap;
import java.util.Map;

import mod_billing.MD_Billing;
import mod_customer.MD_Customer;
import mod_login.MD_Login;
import mod_orders.MD_Orders;
import mod_products.MD_ProductManager;
import mod_user.MD_User;

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
	private boolean addMDCustomer = false;
	private boolean addMDProductManager = false;
	private boolean addMDUser = true;
	private boolean addMDOrders = true;
	private boolean addMDState = true;
	
	//hier wird die komplette config gespeichert
	private Map<String,String> cfg = null;
	
	private Config(){
		cfg = new HashMap<String, String>();
	}
	
	public void Init(){
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
