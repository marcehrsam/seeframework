package tools;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;

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
			e.printStackTrace();
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
	
	
}
