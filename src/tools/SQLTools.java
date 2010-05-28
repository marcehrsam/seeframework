package tools;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

public class SQLTools {

	//verweis zu xml-datei mit config machen -> konstanten sind hier ungünstig (db-name wechselt)
	//path, user, pass aus xml lesen
	
	//Pfad zur Konfigurationsdatei
	private static String FILE = "data/db.see";
	
	//XML Parameter
	private static String DBUSER = "DbUser";
	private static String DBPASS = "DbPass";
	private static String DBPATH = "DbPath";
	
	private String path;
	private String user;
	private String pass;
	
	private Connection connection = null;
	private Statement stmt = null;
	
	public SQLTools(){
		//pfad, name, passwort lesen
			readXMLFile();
	}
	
	//pfad, name, passwort lesen und lokal speichern
	private boolean readXMLFile(){
		
		SAXBuilder sxbuild = new SAXBuilder();
		InputSource source = new InputSource(FILE);
		Document doc;
		Element root = null;
		try {
			doc = sxbuild.build(source);
			//Wurzel ermitteln
			root = doc.getRootElement();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
		//werte setzen (auslesen der datei)
			user = root.getChild(DBUSER).getValue(); 
			pass = root.getChild(DBPASS).getValue();
			path = root.getChild(DBPATH).getValue();
			return true;
		}catch(NullPointerException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean connectToDB(){
		
		boolean ok = true;
		
		//sql treiber laden
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			ok = false;
			System.exit(1);
		}	
		
		//verbindung zu db herstellen
		try {
			connection = DriverManager.getConnection(path, user, pass);
			stmt = connection.createStatement();
			Debug.out("Verbindung zur Datenbank erfolgreich hergestellt.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Debug.out("Verbindung fehlgeschlagen!");
			e.printStackTrace();
			ok = false;
		}catch (Exception e){
			Debug.out("Allgemeiner Fehler im SQL_Writer.");
			e.printStackTrace();
			ok = false;
		}
		return ok;
	}
	
	public boolean disconnectFromDB(){
		try {
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}catch(NullPointerException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void exec(String query){
		try {
			Debug.out(query);
			stmt.execute(query);
			Debug.out("--->erfolgreich ausgeführt");
		} catch (SQLException e) {
			Debug.out("!!!--->fehlerhaft");
			System.err.println(e.getMessage());
		}
	}

	public ResultSet getResultSet(String query){
		ResultSet rs = null;
		try {
			Debug.out(query);
			rs = stmt.executeQuery(query);
			Debug.out("--->erfolgreich ausgeführt");
			return rs;
		} catch (SQLException e) {
			Debug.out("!!!--->fehlerhaft");
			Debug.out(e.getMessage());
			return null;
		}
	}
	
	public ArrayList<String> getAllTablesinDB(){
		ArrayList<String> tablesInDB = new ArrayList<String>();
		connectToDB();
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("SHOW TABLES");
			try{	
				while(rs.next()){
					tablesInDB.add(rs.getString(1));
				}
			}catch (NullPointerException e) {
				Debug.out("ResultSet==null in SQLTools getAllTablesinDB");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnectFromDB();
		return tablesInDB;
	}

	public boolean createMissingTablesInDB(Set<String> tablesNeeded){
		connectToDB();
		for(String tabName:tablesNeeded){	
			//Tabelle mit Struktur generieren
			MyDatabaseStructureFactory fac = new MyDatabaseStructureFactory();
			exec(fac.createCreateTableStatement(tabName));
		}
		disconnectFromDB();
		return true;
	}
	
	//vergleicht alle Tabellen
	public boolean validateStructure(){
		boolean allOk = true;
		Iterator<String> it = getAllTablesNeeded().iterator();
		while(it.hasNext()){
			String akt = it.next();
			boolean bVal = validateStructure(akt);
			if(bVal == false)allOk = false;
		}
		return allOk;
	}
	
	//einzelne Tabelle abgleichen
	public boolean validateStructure(String tabName){
		connectToDB();
		MyDatabaseStructureFactory fac = new MyDatabaseStructureFactory();
		
		Map<String, String> tab_current = new TreeMap<String, String>();
		Map<String, String> tab_template = fac.getStruc().get(tabName);
		
		//aktuelle tab einlesen
		ResultSet rs = getResultSet(fac.createValidationStatement(tabName));
		if(rs!=null){
			
		
			try {
				while(rs.next()){
					//name
					String name = rs.getString(1);
					//typ
					String type = rs.getString(2);
					tab_current.put(name, type);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
			//fehler speichern
			Map<String, String> tab_inconsistent = new TreeMap<String, String>();
			
			Set<String> template_set = tab_template.keySet();
			Iterator<String> it = template_set.iterator();
					
			while(it.hasNext()){
				String c_string = it.next();
				if(c_string.equals("_active"))c_string="active";
				if((c_string.equals("_tName"))||(c_string.equals("_pKey")))continue;
				
				//parameter in beiden maps abgleichen
				
				String s_current = tab_current.get(c_string);
				String s_template = tab_template.get(c_string);
				
				if(s_current!=null){
					//fehler speichern
					if(!(s_current.toUpperCase()).equals((s_template.toUpperCase()))){
						tab_inconsistent.put(c_string, tab_current.get(c_string));
						Debug.out(c_string + " ist fehlerhaft.");
					}else {
						Debug.out(c_string + " ist OK.");
					}
				}else{
					//es wurde eine spalte gelöscht
					Debug.out("Spalte " + c_string + " wurde gelöscht.");
					System.err.println("Spalte " + c_string + " wurde gelöscht.");
					//was muss regeneriert werden?
					tab_inconsistent.put(c_string, s_template);
				}
				
			}
			disconnectFromDB();
			if(tab_inconsistent.isEmpty()){
				//keine Fehler vorhanden
				Debug.out("Tabelle " + tabName + " ist OK.");
				return true;
			}else {
				Debug.out("Tabelle " + tabName + " ist fehlerhaft.");
				//fehler angeben
				return false;
			}
		}else{
			Debug.out("Tabelle " + tabName + " ist nicht vorhanden.");
			return false;
		}
	}
	
	public ArrayList<String> getAllTablesNeeded(){
		MyDatabaseStructureFactory fac = new MyDatabaseStructureFactory();
		Iterator<String> it = fac.getStruc().keySet().iterator();
		ArrayList<String> ret = new ArrayList<String>();
		while(it.hasNext()){
			ret.add(it.next());
		}
		return ret;
	}
	
}
