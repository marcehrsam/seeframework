package test.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import base.Config;

import tools.Debug;

public class ProdukteSQLReader {

	private String user;
	private String pass;
	private String path;
	private Connection con;
	private Statement stmt;
	
	public ProdukteSQLReader(){
		user = Config.getInstance().getDbUser();
		pass = Config.getInstance().getDbPassword();
		path = Config.getInstance().getDbPath();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Debug.out("SQL Treiber geladen.");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.exit(1);
		}
		
		try {
			con = DriverManager.getConnection(path, user, pass);
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("Verbindung fehlgeschlagen!");
			e.printStackTrace();
		}
	
		//getProduct("SE-0002");
		
		
	}
	
	public void getProduct(String artNr){
		//startet sql abfrage und liefert die produktbeschreibung
		try {
			ResultSet rs = stmt.executeQuery("SELECT `Bezeichnung` FROM `produkte` WHERE `ArtNr` = '" + artNr + "'");

			while(rs.next()){
				System.out.println(rs.getString("Bezeichnung"));
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ProdukteSQLReader reader = new ProdukteSQLReader();		

	}

}
