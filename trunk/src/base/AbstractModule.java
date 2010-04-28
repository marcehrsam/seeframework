package base;
import gui.MyPanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;

import javax.swing.JMenu;
import javax.swing.JPanel;

import tools.Debug;

public abstract class AbstractModule extends Observable implements IModule {
	
	private String user = "web1187";
	private String pass = "dechemax";
	private String path = "jdbc:mysql://marc-ehrsam.de:3306/usr_web1187_8";
	
	protected Connection connection = null;
	protected Statement stmt = null;
	
	public AbstractModule(){
		//konfiguration in neuem thread einlesen
		Thread tInit = new Thread(new InitModuleThread(this));
		tInit.start();
	}
	
	protected boolean connectToDB(){
		
		boolean ok = true;
		
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
			ok = false;
		}catch (Exception e){
			Debug.out("Allgemeiner Fehler im SQL_Writer.");
			e.printStackTrace();
			ok = false;
		}
		return ok;
	}
	
	public abstract boolean getDataFromServer();
	
	protected MyPanel contentScreen = null;
	
	public abstract JMenu getMenu();
	public abstract void createMenu();
	public abstract String getMenuName();
	public abstract boolean stopAllActions();
	public abstract JPanel getContentScreen();
	public abstract void setContentScreen(MyPanel screen);
	public abstract boolean readConfigFile();

}
