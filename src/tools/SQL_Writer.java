package tools;

import java.sql.*;

import base.Config;

//als singleton implementiert
public class SQL_Writer {
	
	private String user;
	//TODO pass und user entfernen
	private String pass;
	private String path;
	private Connection con;
	private Statement stmt;
	
	public SQL_Writer(){
		user = Config.getInstance().getDbUser();
		pass = Config.getInstance().getDbPassword();
		path = Config.getInstance().getDbPath();
		
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
			con = DriverManager.getConnection(path, user, pass);
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Debug.out("Verbindung fehlgeschlagen!");
			e.printStackTrace();
		}catch (Exception e){
			Debug.out("Allgemeiner Fehler im SQL_Writer.");
			e.printStackTrace();
		}
		
		
		
	}
	
	public ResultSet getResult(String query) throws SQLException{
		ResultSet result = stmt.executeQuery(query);
		return result;
	}
	
	public Statement getStmt(){
		return stmt;
	}

}
