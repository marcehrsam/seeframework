package model_test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;

import mod_customer.AbstractCustomer;
import mod_customer.Customer;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Rechnung1 extends Rechnung {
		
	private List<TableModelListener> tableModelListenerList = null;
		
	private String rechnungsNummer = "abab";
	private String referenz = "";
	private String anrede = "";
	private String nachname = "";
	private String vorname = "";
	private String strasse = "";
	private String nummer = "";
	private String zusatzzeile = "";
	private String plz = "";
	private String ort = "";
	private String rechnungsdatum = "";
	private String auftragsdatum = "";
	private String kundennummer = "";
	
	public Rechnung1(){
		super();
		
	}
	
	public Rechnung1(AbstractBeleg order) {
		super(order);
	}

	protected void InitData(){
//		rechnungsNummer = ""; beim speichern
		referenz = ""; //std = ohne
		if(getKunde()!=null){
			Customer kd = getKunde();
			kundennummer = kd.getKundenNummer();
			anrede = kd.getAnrede();
			nachname = kd.getName();
			vorname = kd.getVorname();
			strasse = kd.getStrasse();
			nummer = kd.getNummer();
			zusatzzeile = kd.getZusatzZeile();
			plz = kd.getPlz();
			ort = kd.getOrt();
		}
		
		//rechnungsdatum = ""; beim speichern
		//TODO: datum
		auftragsdatum = new Date().toGMTString();
	}
	
	
	public void generatePdf() throws DocumentException, MalformedURLException, IOException{
		
		//test, ob rechnung leer ist
		if(positionen.size()==0){
			JOptionPane.showMessageDialog(null, "Bitte fügen Sie zuerst eine Position ein!", "Fehler", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//initialisiere header
		Document doc = new Document(PageSize.A4);
		PdfWriter pdfw = PdfWriter.getInstance(doc, new FileOutputStream("belege_pdf/" + rechnungsNummer + "_" + nachname + ".pdf"));
		doc.open();
		PdfContentByte cb = pdfw.getDirectContent();
		
		//Schrift festlegen
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
		
		//zähle seiten und initialisiere schleife
		int anzGes = calcPages();
		
		for(int i=1; i<(anzGes + 1); i++){
			
			createBanner(doc, cb);
			createEmpfänger(cb);
			createRechteSeiteHeader(cb, i, anzGes);
			
			//rechnungsheader
			cb.beginText();
			cb.setTextMatrix(250, 550);
			cb.setFontAndSize(bf, 14);
			cb.showText("Rechnung");
			cb.endText();
			
			//createTable(cb, i, anzGes);
			createTable(cb, i, anzGes);	
			
			//nur letzte seite
			if(!(i<anzGes)){
				createStampSignature(cb, bf, doc);
			}
			createFooter(cb, bf);
			if(i<anzGes){
				doc.newPage();
			}
		}		
		doc.close();
	}

	protected int calcPages() {
		//TODO
		return 1;
	}

	protected void createTable(PdfContentByte cb, int i, int anzGes) {

	BaseFont bf = null;
	try {
		bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//Tabellenkopf
	
	cb.beginText();
	
	cb.setFontAndSize(bf, 8);
	cb.setTextMatrix(25, 530);
	cb.showText("Position");
	
	cb.setFontAndSize(bf, 8);
	cb.setTextMatrix(80, 530);
	cb.showText("Artikel");
	
	cb.setFontAndSize(bf, 8);
	cb.setTextMatrix(160, 530);
	cb.showText("Bezeichnung");
	
	cb.setFontAndSize(bf, 8);
	cb.setTextMatrix(350, 530);
	cb.showText("Anzahl");
	
	cb.setFontAndSize(bf, 8);
	cb.setTextMatrix(450, 530);
	cb.showText("Einzelpreis");
	
	cb.setFontAndSize(bf, 8);
	cb.setTextMatrix(520, 530);
	cb.showText("Gesamtpreis");
	
	cb.endText();
		
	cb.beginText();
	cb.setFontAndSize(bf, 8);
	cb.setTextMatrix(20, 520);
	cb.showText("______________________________________________________________" +
			    "_______________________________________________________________");
	
	cb.endText();
	
	cb.setFontAndSize(bf, 8);
	
	//Iteration über die Positionen
	
	int lastY = 500; //das immer -20 für nächste zeile bis ca. 200
	int deltaY = 15; //zeilenabstand
	
	Iterator<Position> itPos = positionen.iterator(); 
	
	while(itPos.hasNext()){
		
		int currentY = lastY;
		Position pos = itPos.next();
		
		//spalten durchgehen
		
		//positionsnummer
		int posNr = -1;
		if(positionen instanceof ArrayList){
			posNr = ((ArrayList<Position>)positionen).indexOf(pos)+1;
		}
		cb.beginText();
		cb.setTextMatrix(25, currentY);
		cb.showText("" + posNr);
		cb.endText();
		
		//Artikel
		cb.beginText();
		cb.setTextMatrix(80, currentY);
		cb.showText("" + pos.getArtNr());
		cb.endText();
		
		//Anzahl
		cb.beginText();
		cb.setTextMatrix(350, currentY);
		cb.showText("" + pos.getAnzahl());
		cb.endText();
				
		//Einzelpreis
		cb.beginText();
		cb.setTextMatrix(450, currentY);
		cb.showText("" + pos.getEinzelpreis());
		cb.endText();
		
		//Gesamtpreis
		cb.beginText();
		cb.setTextMatrix(520, currentY);
		cb.showText("" + (pos.getAnzahl()*pos.getEinzelpreis()));
		cb.endText();
		
		
		//Beschreibung
		String rawText = pos.getProdukt().getBezeichnung();
		int charsPerLine = 100;
		int start = -charsPerLine;
		int end = 0;
		boolean bEnd = false;
		String currentTextLine = "";
		//Schleife start
		while(!bEnd){
			start += charsPerLine;
			end += charsPerLine;
			if(end >= rawText.length()-1){
				end = (rawText.length());
				bEnd = true;
			}
			StringBuffer buff = new StringBuffer(rawText);
			currentTextLine = buff.substring(start, end);
			
			cb.beginText();
			cb.setTextMatrix(160, currentY);
			cb.showText("" + currentTextLine);
			cb.endText();
			currentY -= deltaY;
		}
		lastY = currentY;	
	}
		
	}

	public String getRechnungsNummer() {
		return rechnungsNummer;
	}

	public void setRechnungsNummer(String rechnungsNummer) {
		this.rechnungsNummer = rechnungsNummer;
		setChanged();
		notifyObservers();
	}

	public String getKundenNummer() {
		return kundennummer;
	}

	public void setKundenNummer(String kundenNummer) {
		this.kundennummer = kundenNummer;
		setChanged();
		notifyObservers();
	}

	public String getAnrede() {
		return anrede;
	}

	public void setAnrede(String anrede) {
		this.anrede = anrede;
		setChanged();
		notifyObservers();
	}

	public String getName() {
		return nachname;
	}

	public void setName(String name) {
		nachname = name;
		setChanged();
		notifyObservers();
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
		setChanged();
		notifyObservers();
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
		setChanged();
		notifyObservers();
	}

	public String getNummer() {
		return nummer;
	}

	public void setNummer(String nummer) {
		this.nummer = nummer;
		setChanged();
		notifyObservers();
	}

	public String getZusatzZeile() {
		return zusatzzeile;
	}

	public void setZusatzZeile(String zusatzZeile) {
		this.zusatzzeile = zusatzZeile;
		setChanged();
		notifyObservers();
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
		setChanged();
		notifyObservers();
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
		setChanged();
		notifyObservers();
	}

	public String getDatum() {
		return rechnungsdatum;
	}

	public void setDatum(String datum) {
		this.rechnungsdatum = datum;
		setChanged();
		notifyObservers();
	}

	public void writeToDb() throws SQLException{

		//neuen auftrag erzeugen
		Calendar cal = new GregorianCalendar();
		String year = cal.get(GregorianCalendar.YEAR) + "";
		int i_month = (cal.get(GregorianCalendar.MONTH) + 1);
		String month = "";
		if(i_month<10){
			month = "0" + i_month;
		}else{
			month = i_month + "";
		}
				
		String day = cal.get(GregorianCalendar.DATE) + "";
		
		MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("marc-ehrsam.de");
        dataSource.setUser("web1187");
        dataSource.setPassword("dechemax");
        dataSource.setDatabaseName("usr_web1187_8");
        dataSource.setPort(3306);
       
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement(
                Statement.CLOSE_ALL_RESULTS, Statement.RETURN_GENERATED_KEYS);
       
        statement.executeUpdate("insert into auftrag_kunde (auftrag, kunde, text, datum) values (NULL, '" + getKundenNummer() + "', '', '" + year + "-" + month + "-" + day + "')", Statement.RETURN_GENERATED_KEYS);
     
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if(generatedKeys.next()){
            System.out.println(generatedKeys.getInt(1));    
        }
		
		statement.close();
		
		//neue rechnung erzeugen und erste pos einfügen
		
		Iterator it = getPositionen().iterator();
		
		statement = connection.createStatement(Statement.CLOSE_ALL_RESULTS, Statement.RETURN_GENERATED_KEYS);
		statement.executeUpdate("insert into rechnung (renr, pos, art, re_epreis) values (NULL, '1', '" + ((Position)it.next()).getArtNr() + "', "+ "'20'" + ")" , Statement.RETURN_GENERATED_KEYS);
		generatedKeys = statement.getGeneratedKeys();
		
		//neue Rechnungsnummer erzeugen und speichern
		//für pos 1
		String renr = "000000";
		if(generatedKeys.next()){
			renr = generatedKeys.getInt(1) + "";
			setRechnungsNummer(renr);
		}
		//für jede position
		int pos = 2;
		while(positionen.size()>=pos){
			Position currentPos = (Position)it.next();
			statement.executeUpdate("insert into rechnung (renr, pos, art, re_epreis) values (" + renr + ", '" + pos + "', '" + currentPos.getProdukt().getArtNr() + "', '"+ currentPos.getEinzelpreis() + "')");
			pos++;
		}
		
		connection.close();
	}

	@Override
	public void setCustomer(AbstractCustomer customer) {
		super.setCustomer(customer);
		setKundenNummer(customer.getKundenNummer());
		setChanged();
		notifyObservers();
	}
	
}
