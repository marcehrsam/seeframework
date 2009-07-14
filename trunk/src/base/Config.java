package base;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import mod_billing.MD_Billing;
import mod_customer.MD_Customer;
import mod_login.MD_Login;
import mod_orders.MD_Orders;
import mod_products.MD_ProductManager;
import mod_user.MD_User;

import tools.Debug;

public class Config implements ISqlSource {
	
	

	private static Config instance = null;
	
	public final String CONFIG_INI = "config.ini";
	public final String ERROR_LOG = "error.log";
	
	//strings für config.ini
	private final String DROPLINE = "Zeile übersprungen";
	private final String DROPNODE = "Knoten übersprungen";
	private final String DB_USER = "db_user";
	private final Object DB_PASS = "db_pass";
	private final Object DB_PATH = "db_path";
	
	private Map<String,String> cfg = null;
	
	private Config(){
		
		cfg = new HashMap<String, String>();
		
		
	}
	
	public void Init(){
		Framework.FW().addModule(MD_Login.getInstance(), true);
		Framework.FW().addModule(MD_User.getInstance());
		Framework.FW().setState(StateMan.SM().getState(StateMan.INITOK));
	}
	
	/*
	public void Init(){
		//liest konfigurationsdatei ein
		//basisconfig -> config.ini
		try {
			readConfigIni();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//TODO: hier ist ein Variationspunkt (Marketing!!!)
		//Module hinzufügen
		Framework.FW().addModule(MD_Login.getInstance(), true);
		//Framework.FW().addModule(MD_Billing.getInstance());
		//Framework.FW().addModule(MD_Customer.getInstance());
		//Framework.FW().addModule(MD_ProductManager.getInstance());
		//Framework.FW().addModule(MD_User.getInstance());
		//Framework.FW().addModule(MD_Orders.getInstance());
		
		Framework.FW().setState(StateMan.SM().getState(StateMan.INITOK));
		
	}
	*/
	
	public static Config getInstance(){
		if(instance == null){
			instance = new Config();
		}
		return instance;
	}
	
	private void readConfigIni() throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(CONFIG_INI));
		
		String input = "";
		while( (input = reader.readLine()) != null ){

			//leerzeichen in zeile entfernen
			input = input.trim();
			
			//leerzeilen überspringen
			if(input.length()==0){
				Debug.out(DROPLINE);
				continue;
			}
			
			//ist die zeile ein knoten?
			if( input.contains("[") && input.contains("]") ){
				//verwerfe knoten (knoten nur zur Übersichtlichkeit)
				Debug.out(DROPNODE);
				continue;
			}
			
			
			
			String temp = input;
			String fieldName = temp.substring(0, temp.indexOf('=')-1).trim();
			String value = input.substring(input.indexOf('=')+1).replace(';', ' ').trim();
			cfg.put(fieldName, value);
			Debug.out("\"" + fieldName + " = " + value + "\""+ " importiert.");
			continue;	
			
		}
		
		
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
