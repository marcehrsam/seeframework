package mod_customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import javax.swing.JOptionPane;

import tools.SQL_Writer;

public abstract class AbstractCustomer extends Observable{

	private Map<String, String> kunde = null;
	
	//Keys
	public final String KDNRA = "KdNrA";
	public final String KDNRB = "KdNrB";
	public final String ANREDE = "Anrede";
	public final String NAME = "Name";
	public final String VORNAME = "Vorname";
	public final String STRASSE = "Strasse";
	public final String NUMMER = "Nummer";
	public final String ORTSTEIL = "Ortsteil";
	public final String PLZ = "PLZ";
	public final String ORT = "Ort";
	public final String TELEFON = "Telefon";
	public final String HANDY = "Handy";
	public final String MAIL = "Mail";
	
	public AbstractCustomer(){
		kunde = new HashMap<String, String>();
		
		//Keys sind die DB-Spalten!!!
		//Initialisierung
		//KdNrB -> max abfragen und damit die Kundennummer erstellen
		try {
			String query = "SELECT max(`" + KDNRB + "`) AS `MAX` FROM `se_framework_db1`.`kundendaten`;";
			SQL_Writer writer = new SQL_Writer();
			ResultSet rs = writer.getResult(query);
			String str = "SQLERR";
			if(rs!=null){
				rs.next();
				int max = rs.getInt("MAX");
				str = "" + (max+1);
			}
			
			//Kundennummer zusammensetzen aus KD + xx (Jahr) + xxxxxx (Max + 0en)
			final String sFillStrWithWantLen = "0000";
			int len = str.length();
			if( len < sFillStrWithWantLen.length() )
			  str = (sFillStrWithWantLen + str).substring(len);
			str = "KD09" + str;
			
			kunde.put(KDNRA, str);
			
			//JOptionPane.showMessageDialog(null, str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		kunde.put(ANREDE, "");
		kunde.put(NAME, "Name");
		kunde.put(VORNAME, "Vorname");
		kunde.put(STRASSE, "");
		kunde.put(NUMMER, "");
		kunde.put(ORTSTEIL, "");
		kunde.put(PLZ, "");
		kunde.put(ORT, "");
		kunde.put(TELEFON, "");
		kunde.put(HANDY, "");
		kunde.put(MAIL, "");
	}	
	
	public String getTel() {
		return kunde.get(TELEFON);
	}
	public void setTel(String tel) {
		kunde.put(TELEFON, tel);
		setChanged();
		notifyObservers();
	}
	public String getMobil() {
		return kunde.get(HANDY);
	}
	public void setMobil(String mobil) {
		kunde.put(HANDY, mobil);
		setChanged();
		notifyObservers();
	}
	public String getMail() {
		return kunde.get(MAIL);
	}
	public void setMail(String mail) {
		kunde.put(MAIL, mail);
		setChanged();
		notifyObservers();
	}
	public String getKundenNummer() {
		return kunde.get(KDNRA);
	}
	public void setKundenNummer(String kundenNummer) {
		kunde.put(KDNRA, kundenNummer);
		setChanged();
		notifyObservers();
	}
	public String getAnrede() {
		return kunde.get(ANREDE);
	}
	public void setAnrede(String anrede) {
		kunde.put(ANREDE, anrede);
		setChanged();
		notifyObservers();
	}
	public String getName() {
		return kunde.get(NAME);
	}
	public void setName(String name) {
		kunde.put(NAME, name);
		setChanged();
		notifyObservers();
	}
	public String getVorname() {
		return kunde.get(VORNAME);
	}
	public void setVorname(String vorname) {
		kunde.put(VORNAME, vorname);
		setChanged();
		notifyObservers();
	}
	public String getStrasse() {
		return kunde.get(STRASSE);
	}
	public void setStrasse(String strasse) {
		kunde.put(STRASSE, strasse);
		setChanged();
		notifyObservers();
	}
	public String getNummer() {
		return kunde.get(NUMMER);
	}
	public void setNummer(String nummer) {
		kunde.put(NUMMER, nummer);
		setChanged();
		notifyObservers();
	}
	public String getZusatzZeile() {
		return kunde.get(ORTSTEIL);
	}
	public void setZusatzZeile(String zusatzZeile) {
		kunde.put(ORTSTEIL, zusatzZeile);
		setChanged();
		notifyObservers();
	}
	public String getPlz() {
		return kunde.get(PLZ);
	}
	public void setPlz(String plz) {
		kunde.put(PLZ, plz);
		setChanged();
		notifyObservers();
	}
	public String getOrt() {
		return kunde.get(ORT);
	}
	public void setOrt(String ort) {
		kunde.put(ORT, ort);
		setChanged();
		notifyObservers();
	}	
	
	public void writeToDB() throws SQLException{
		
		Set<String> keys = kunde.keySet();
		String query = "INSERT INTO `se_framework_db1`.`kundendaten` ( ";
		String header = "";
		String values = "";
		Iterator<String> it = keys.iterator();
		boolean firstLoop = true;
		while(it.hasNext()){
			if(!firstLoop){
				header += ", ";
				values += ", ";
			}
			firstLoop = false;
			String current = it.next();
			header += "`" + current + "`";
			String value = kunde.get(current);
			if((value.equals(""))||(value==null)){
				values += "NULL";
			}else{
				values += "'" + value + "'";
			}
			 
		}
		query += header + ") VALUES (" + values + ");";
		JOptionPane.showMessageDialog(null, query);
		
		SQL_Writer writer = new SQL_Writer();
		writer.getStmt().execute(query);
	}
	
	public String toString(){
		return kunde.get(KDNRA) + " " + kunde.get(NAME) + ", " + kunde.get(VORNAME); 
	}
	
}
