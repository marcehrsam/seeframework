package tools;

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
	
	public static final String S_ACTIVE = "active";
	
	//SQL-Datentypen für mySQL
	public static final String D_ID = "int(10) unsigned zerofill";
	public static final String D_TEXT = "TEXT";
	public static final String D_BOOL = "tinyint(1)";
	public static final String D_DOUBLE = "DOUBLE";
		
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
		ordersTable.put(S_ACTIVE, D_BOOL);
		
		//TODO: Struktur festlegen
		ordersTable.put("oid", D_ID);
		
	}

	private void initBillsTable() {

		billsTable.put("_pKey", "bid");
		billsTable.put("_tName", S_BILLS);
		billsTable.put(S_ACTIVE, D_BOOL);
		
		//TODO: Struktur festlegen
		billsTable.put("bid", D_ID);
		
	}

	private void initCustomerStruct(){
		
		//ben.def.
		customerTable.put("cid", D_ID);
		customerTable.put("title", D_TEXT);
		customerTable.put("name", D_TEXT);
		customerTable.put("firstname", D_TEXT);
		customerTable.put("street", D_TEXT);
		customerTable.put("sno", D_TEXT);
		customerTable.put("zip", D_TEXT);
		customerTable.put("city", D_TEXT);
		customerTable.put("phone", D_TEXT);
		customerTable.put("mobile", D_TEXT);
		customerTable.put("mail", D_TEXT);
				
		//primärSchlüssel
		customerTable.put("_pKey", "cid");
		
		//tabellenName
		customerTable.put("_tName", S_CUSTOMER);
		
		//sonstige flags
		customerTable.put(S_ACTIVE, D_BOOL);
	}
	private void initUserStruct(){
		
		userTable.put("_pKey", "uid");
		userTable.put("_tName", S_USERS);
		userTable.put(S_ACTIVE, D_BOOL);
		
		userTable.put("uid", D_ID);
		userTable.put("name", D_TEXT);
		userTable.put("pass", D_TEXT);
		userTable.put("rights", D_TEXT);
	}
	private void initProductsStruct(){
		productsTable.put("_pKey", "pid");
		productsTable.put("_tName", S_PRODUCTS);
		productsTable.put(S_ACTIVE, D_BOOL);
		
		productsTable.put("pid", D_ID);
		productsTable.put("name", D_TEXT);
		productsTable.put("desc", D_TEXT);
		productsTable.put("scost", D_DOUBLE);
		
	}
	
	//universelle Methode
	private static String createCreateTableStatement(Map<String, String> mapInfo){
		String statement = "";
		if(mapInfo == null){
			Debug.out("mapInfo ist null -> createCreateTableStatement");
			return "";
		}
		if(mapInfo.containsKey("_pKey") && mapInfo.containsKey("_tName") && mapInfo.containsKey(S_ACTIVE)){
			Debug.out("Enter loop: " + mapInfo.get("_tName"));
			//allg.
			statement = "CREATE TABLE `";
			String pKey = mapInfo.get("_pKey");
			String tName = mapInfo.get("_tName");
			String active = mapInfo.get(S_ACTIVE);
			
			statement += tName + "` ( ";
		
			String statement_end = ""; 
			statement_end += "`" + S_ACTIVE +"` " + active + " , ";
			statement_end += "PRIMARY KEY ( `"+ pKey +"` )";
			statement_end += " )";
				
			//speziell
			Set<String> kSet = mapInfo.keySet();
			kSet.remove("_pKey");
			kSet.remove("_tName");
			kSet.remove(S_ACTIVE);
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

	public String createValidationStatement(String tabName){
		return createValidationStatement(struc.get(tabName)); 
	}
	
	private String createValidationStatement(Map<String,String> mapInfo){
		return "describe " + mapInfo.get("_tName");
	}

	protected TreeMap<String, Map<String, String>> getStruc() {
		return struc;
	}
}
