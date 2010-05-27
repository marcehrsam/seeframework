package tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MyDatabaseStructureFactory {
		
	public static final String S_USERS = "users";
	public static final String S_CUSTOMER = "customer";
	public static final String S_ORDERS = "orders";
	public static final String S_PRODUCTS = "products";
	public static final String S_BILLS = "bills";
		
	//Alle Tabellen als Struktur
	public static TreeMap<String,Map<String, String>> struc = null;
		
	//DB-Struktur
	private Map<String, String> customerTable = null;
	private Map<String, String> userTable = null;
	private Map<String, String> ordersTable = null;
	private Map<String, String> billsTable = null;
	private Map<String, String> productsTable = null;
		
	public MyDatabaseStructureFactory(){
		struc = new TreeMap<String, Map<String,String>>();
		
		customerTable = new TreeMap<String, String>();
		userTable = new TreeMap<String, String>();
		ordersTable = new TreeMap<String, String>();
		billsTable = new TreeMap<String, String>();
		productsTable = new TreeMap<String, String>();
		
		initCustomerStruct();
		initUserStruct();
		initOrdersTable();
		initBillsTable();
		initProductsStruct();
		
		struc.put(S_CUSTOMER, customerTable);
		struc.put(S_USERS,userTable);
		struc.put(S_ORDERS,ordersTable);
		struc.put(S_BILLS,billsTable);
		struc.put(S_PRODUCTS,productsTable);
	}
	
	private void initOrdersTable() {
		ordersTable.put("_pKey", "oid");
		ordersTable.put("_tName", S_ORDERS);
		ordersTable.put("_active", "BOOL");
		
		//TODO: Struktur festlegen
		ordersTable.put("oid", "INT UNSIGNED ZEROFILL NOT NULL");
		
	}

	private void initBillsTable() {

		billsTable.put("_pKey", "bid");
		billsTable.put("_tName", S_BILLS);
		billsTable.put("_active", "BOOL");
		
		//TODO: Struktur festlegen
		billsTable.put("bid", "INT UNSIGNED ZEROFILL NOT NULL");
		
	}

	private void initCustomerStruct(){
		
		//ben.def.
		customerTable.put("cid", "INT UNSIGNED ZEROFILL NOT NULL");
		customerTable.put("title", "TEXT");
		customerTable.put("name", "TEXT");
		customerTable.put("firstname", "TEXT");
		customerTable.put("street", "TEXT");
		customerTable.put("sno", "TEXT");
		customerTable.put("zip", "TEXT");
		customerTable.put("city", "TEXT");
		customerTable.put("phone", "TEXT");
		customerTable.put("mobile", "TEXT");
		customerTable.put("mail", "TEXT");
				
		//primärSchlüssel
		customerTable.put("_pKey", "cid");
		
		//tabellenName
		customerTable.put("_tName", S_CUSTOMER);
		
		//sonstige flags
		customerTable.put("_active", "BOOL");
	}
	private void initUserStruct(){
		
		userTable.put("_pKey", "uid");
		userTable.put("_tName", S_USERS);
		userTable.put("_active", "BOOL");
		
		userTable.put("uid", "INT UNSIGNED ZEROFILL NOT NULL");
		userTable.put("name", "TEXT");
		userTable.put("pass", "TEXT");
		userTable.put("rights", "TEXT");
	}
	private void initProductsStruct(){
		productsTable.put("_pKey", "pid");
		productsTable.put("_tName", S_PRODUCTS);
		productsTable.put("_active", "BOOL");
		
		productsTable.put("pid", "INT UNSIGNED ZEROFILL NOT NULL");
		productsTable.put("name", "TEXT");
		productsTable.put("desc", "TEXT");
		productsTable.put("scost", "DOUBLE");
		
	}
	
	//universelle Methode
	private static String createCreateTableStatement(Map<String, String> mapInfo){
		String statement = "";
		if(mapInfo == null){
			Debug.out("mapInfo ist null -> createCreateTableStatement");
			return "";
		}
		if(mapInfo.containsKey("_pKey") && mapInfo.containsKey("_tName") && mapInfo.containsKey("_active")){
			Debug.out("Enter loop: " + mapInfo.get("_tName"));
			//allg.
			statement = "CREATE TABLE `";
			String pKey = mapInfo.get("_pKey");
			String tName = mapInfo.get("_tName");
			String active = mapInfo.get("_active");
			
			statement += tName + "` ( ";
		
			String statement_end = ""; 
			statement_end += "`active` " + active + " , ";
			statement_end += "PRIMARY KEY ( `"+ pKey +"` )";
			statement_end += " )";
				
			//speziell
			Set<String> kSet = mapInfo.keySet();
			kSet.remove("_pKey");
			kSet.remove("_tName");
			kSet.remove("_active");
			Iterator<String> it = kSet.iterator(); 
			while(it.hasNext()){
				String cKey = it.next();
				String currentType = mapInfo.get(cKey);
				statement += "`" + cKey + "` " + currentType + " , ";
				it.remove();
			}
			statement += statement_end;	
		}
		return statement;
	}
	
	public String createCreateTableStatement(String tabName){
		//was ist, wenn tabName falsch ist?
		if(tabName==null)return "";
		return createCreateTableStatement(struc.get(tabName));
	}	
}
