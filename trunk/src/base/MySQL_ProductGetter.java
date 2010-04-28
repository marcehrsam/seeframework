package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tools.Debug;

public class MySQL_ProductGetter {

	private String user = "";
	private String pass = "";
	private String path = "";
	
	private Connection connection = null;
	private Statement stmt = null;
	
	public MySQL_ProductGetter(){
		user = "web1187";
		pass = "dechemax";
		path = "jdbc:mysql://marc-ehrsam.de:3306/usr_web1187_8";
		initConnection();
	}
	
	private void initConnection(){
		
		//sql treiber laden
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Debug.out("SQL Treiber geladen.");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.exit(1);
		}	
		
		//verbindung zu db herstellen
		try {
			connection = DriverManager.getConnection(path, user, pass);
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Debug.out("Verbindung fehlgeschlagen!");
			e.printStackTrace();
		}catch (Exception e){
			Debug.out("Allgemeiner Fehler im SQL_Writer.");
			e.printStackTrace();
		}
		
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM produkte;");
			while(rs.next()){
				System.out.println(rs.getString("artnr"));
				System.out.println(rs.getString("bez"));
				System.out.println(rs.getString("text"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public void getDB(){
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MySQL_ProductGetter();

	}

}
