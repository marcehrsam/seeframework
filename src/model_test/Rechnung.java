package model_test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import mod_customer.AbstractCustomer;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public abstract class Rechnung extends AbstractBeleg implements ICustomerHolder, TableModel, Observer{
	
	private Map<String, String> rechnung = null;
	
	private List<TableModelListener> tableModelListenerList = null; 
	
	public final int POSPERPAGE = 10;
	
//	für sql abfrage
	public final String RECHNUNG_ID = "REID";
	public final String AUFTRAG_ID = "Auftrag";
	public final String KUNDE_ID = "KdNrA";
	public final String ANREDE = "Anrede";
	public final String NAME = "Name";
	public final String VORNAME = "Vorname";
	public final String STRASSE = "Strasse";
	public final String NUMMER = "Nummer";
	public final String ZUSATZZEILE = "Ortsteil";
	public final String PLZ = "PLZ";
	public final String ORT = "Ort";
	public final String DATUM = "Auftragsdatum";
	
	public Rechnung(){
		//TODO: id setzen
		super(null);
		
		tableModelListenerList = new ArrayList<TableModelListener>();
		
		Produkt testprodukt = new Produkt("0000001", "IT-Service (Stundensatz)", 20 );
		Position testpos = new Position(testprodukt, 1);
		positionen.add(testpos);
		rechnung = new HashMap<String, String>();
 		InitData();
		
	}
	
	public Rechnung(AbstractBeleg order) {
		super(null);
		//nur nicht berechnete produkte 
		for(Position p: order.getPositionen()){
			if(!p.isBillAvailable()){
				this.positionen.add(p);
			}
		}
	}

	protected void InitData(){
		rechnung.put(RECHNUNG_ID, "050709-0001");
		rechnung.put(AUFTRAG_ID, "200609-0001");
		rechnung.put(KUNDE_ID, "Testkunde");
		rechnung.put(ANREDE, "Herr");
		rechnung.put(NAME, "Treuger");
		rechnung.put(VORNAME, "Horst");
		rechnung.put(STRASSE, "In den Folgen");
		rechnung.put(NUMMER, "18");
		rechnung.put(ZUSATZZEILE, "");
		rechnung.put(PLZ, "98704");
		rechnung.put(ORT, "Langewiesen");
		rechnung.put(DATUM, "05.07.2009");
	}
		
	public void generatePdf() throws DocumentException, MalformedURLException, IOException{
		
		//test, ob rechnung leer ist
		if(positionen.size()==0){
			JOptionPane.showMessageDialog(null, "Bitte fügen Sie zuerst eine Position ein!", "Fehler", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//initialisiere header
		Document doc = new Document(PageSize.A4);
		PdfWriter pdfw = PdfWriter.getInstance(doc, new FileOutputStream("belege_pdf/" + rechnung.get(RECHNUNG_ID) + "_" + rechnung.get(NAME) + ".pdf"));
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

	protected void createFooter(PdfContentByte cb, BaseFont bf) {
	
		//footer
		cb.beginText();
		cb.setFontAndSize(bf, 8);
		cb.setTextMatrix(20, 50);
		cb.showText("______________________________________________________________" +
				    "_______________________________________________________________");
		
		cb.setTextMatrix(30, 40);
		cb.showText("Ehrsam IT");
		cb.setTextMatrix(30, 30);
		cb.showText("Marc Ehrsam");
		cb.setTextMatrix(30, 20);
		cb.showText("St. Petersburger Str. 21");
		cb.setTextMatrix(30, 10);
		cb.showText("01069 Dresden");
		
		cb.setTextMatrix(180, 40);
		cb.showText("Kontakt:");
		cb.setTextMatrix(180, 30);
		cb.showText("admin@marc-ehrsam.de");
		cb.setTextMatrix(180, 20);
		cb.showText("Handy (0172/8435129)");
		cb.setTextMatrix(180, 10);
		cb.showText("www.marc-ehrsam.de");
		
		cb.setTextMatrix(280, 40);
		cb.showText("Geschäftsführung:");
		cb.setTextMatrix(280, 30);
		cb.showText("Marc Ehrsam");
		
		cb.setTextMatrix(380, 40);
		cb.showText("DKB");
		cb.setTextMatrix(380, 30);
		cb.showText("Konto 12204426");
		cb.setTextMatrix(380, 20);
		cb.showText("BLZ 120 300 00");
		
		cb.setTextMatrix(480, 40);
		cb.showText("St.-Nr.: 201/215/05127");
		cb.setTextMatrix(480, 30);
		cb.showText("Finanzamt Dresden I");
		//finalisiere text
		cb.endText();
		
	}

	protected void createStampSignature(PdfContentByte cb, BaseFont bf, Document doc) throws MalformedURLException, IOException, DocumentException {
		
		//zusatzinfos zur Zahlungsweise
		cb.beginText();
		cb.setFontAndSize(bf, 8);
		cb.setTextMatrix(100, 200);
		cb.showText("Bitte überweisen Sie den Rechnungsbetrag");
		cb.setTextMatrix(100, 190);
		cb.showText("innerhalb der nächsten 14 Tage auf folgendes Konto: ");
		cb.setTextMatrix(100, 170);
		cb.showText("Empfänger: Marc Ehrsam");
		cb.setTextMatrix(100, 160);
		cb.showText("Kto: 12204426");
		cb.setTextMatrix(100, 150);
		cb.showText("BLZ: 120 300 00");
		cb.setTextMatrix(100, 140);
		cb.showText("Deutsche Kreditbank Berlin");
		
		cb.endText();
				
		//stempel und unterschrift	
		cb.beginText();
		cb.setFontAndSize(bf, 10);
		cb.setTextMatrix(400, 150);
		cb.showText("_________________________");
		cb.setTextMatrix(420, 140);
		cb.setFontAndSize(bf, 8);
		//cb.showText("Stempel / Unterschrift");
		cb.endText();
		
		Image img = Image.getInstance(ClassLoader.getSystemResource("stempel_sw.png"));
		img.scalePercent(10);
		img.setAbsolutePosition(400, 160);
		doc.add(img);
		
	}

	protected void createTable(PdfContentByte cb, int page, int fromPages) {
		
		//spaltenverhältnisse
		PdfPTable posTable = new PdfPTable(new float[]{0.1f, 0.2f, 0.1f, 0.55f, 0.3f, 0.3f});
		
		//table header
		posTable.addCell("Pos.");
		posTable.addCell("ArtNr.");
		posTable.addCell("Anz.");
		posTable.addCell("Bezeichnung");
		posTable.addCell("Einzelpreis");
		posTable.addCell("Gesamtpreis");
		
		if(page!=fromPages){
			for(int i=0; i<10; i++){
				Position pos = ((ArrayList<Position>)positionen).get((page-1)*10+i);
				//TODO: positionsnummern abfragen
				posTable.addCell(((ArrayList<Position>)positionen).indexOf(pos)+1 + "");
				posTable.addCell(pos.getArtNr());
				posTable.addCell(pos.getAnzahl() + "");
				posTable.addCell(pos.getBezeichnung());
				
				posTable.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT);
				DecimalFormatSymbols dfs = new DecimalFormatSymbols();
				dfs.setDecimalSeparator('.');
				dfs.setGroupingSeparator('.');
				DecimalFormat df = new DecimalFormat("0000.00", dfs);
				String einzelPreisMitFuehrendenNullen = df.format(pos.getEinzelpreis());
				String einzelpreisFormatiert = replaceZeros(einzelPreisMitFuehrendenNullen);
				posTable.addCell(einzelpreisFormatiert + " EUR");
				//posTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
				
				//TODO: gesamtpreis abfragen
				posTable.addCell(pos.getEinzelpreis()*pos.getAnzahl() + " EUR");
			}
		}
		posTable.setTotalWidth(470);
			
		//falls letzte Seite -> summe einfügen
		if(page>=fromPages){
			int max = positionen.size()%POSPERPAGE;
			if(max==0)max=10;
			for(int i=0; i<max; i++){
				Position pos = ((ArrayList<Position>)positionen).get((page-1)*10+i);
				//TODO: positionsnummern abfragen
				posTable.addCell(((ArrayList<Position>)positionen).indexOf(pos)+1 + "");
				posTable.addCell(pos.getArtNr());
				posTable.addCell(pos.getAnzahl() + "");
				posTable.addCell(pos.getBezeichnung());
				
				posTable.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT);
				DecimalFormatSymbols dfs = new DecimalFormatSymbols();
				dfs.setDecimalSeparator('.');
				dfs.setGroupingSeparator('.');
				DecimalFormat df = new DecimalFormat("0000.00", dfs);
				String einzelPreisMitFuehrendenNullen = df.format(pos.getEinzelpreis());
				String einzelpreisFormatiert = replaceZeros(einzelPreisMitFuehrendenNullen);
				posTable.addCell(einzelpreisFormatiert + " EUR");
				
				//TODO: gesamtpreis abfragen
				posTable.addCell(pos.getEinzelpreis()*pos.getAnzahl() + " EUR");
			}
			
			//TODO: Summe berechnen
			for(int i=0; i<(10-max); i++){
				posTable.addCell(" ");
				posTable.addCell(" ");
				posTable.addCell(" ");
				posTable.addCell(" ");
				posTable.addCell(" ");
				posTable.addCell(" ");
			}
			
			//abfragen, ob letzte Seite -> falls ja, dann kommt noch der Gesamtpreis in die Tabelle
			posTable.addCell("");
			posTable.addCell("");
			posTable.addCell("");
			posTable.addCell("");
			posTable.addCell("Summe:");
			//TODO: Gesamtpreis ermitteln
			posTable.addCell(getSum() + " EUR");
			
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
			
			cb.setFontAndSize(bf, 8);
			cb.beginText();
			cb.setTextMatrix(190, 280);
			cb.showText("(Alle Beträge inkl. 19% MWSt)");
			cb.endText();
		}
		
		//TODO: seite von/bis berechnen
		posTable.writeSelectedRows(0, -1, 80, 500, cb);
		
	}

	protected String replaceZeros(String einzelPreisMitFuehrendenNullen) {
		char[] str = einzelPreisMitFuehrendenNullen.toCharArray();
		for(int i=0; i<einzelPreisMitFuehrendenNullen.length(); i++){
			if(str[i]=='0'){
				str[i] = ' ';
			}else{
				//Abbruchbed., wenn erste zahl auftaucht
				if(str[i]=='.'){
					//restauriere letzte 0 vor komma und beende
					str[i-1] = '0';	
				}
				return new String(str);
			}
		}
		return new String(str);
	}

	protected void createRechteSeiteHeader(PdfContentByte cb, int anzAkt, int anzGes) throws DocumentException, IOException {
		
		String seite = "" + anzAkt + "/" + anzGes;
		cb.beginText();
		
		/*
		 *	Variablen zur Positionierung der rechten Seite des Headers
		 */

		int rKO_X = 400;
		int rKO_Y = 700;
		int rDelta_Y = 12;
		int rDelta_X = 40;
		int rZeile = 0;

		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		cb.setFontAndSize(bf, 8);

		cb.setTextMatrix(rKO_X, rKO_Y-rZeile*rDelta_Y);
		cb.showText("Re.-Nr.:");
		cb.setTextMatrix(rKO_X + rDelta_X, rKO_Y-rZeile*rDelta_Y);
		cb.showText(getRechnungsNummer().toString());
		rZeile++;

		cb.setTextMatrix(rKO_X, rKO_Y-rZeile*rDelta_Y);
		cb.showText("Datum:");
		cb.setTextMatrix(rKO_X + rDelta_X, rKO_Y-rZeile*rDelta_Y);
		cb.showText(getDatum());
		rZeile++;

		cb.setTextMatrix(rKO_X, rKO_Y-rZeile*rDelta_Y);
		cb.showText("Seite:");
		cb.setTextMatrix(rKO_X + rDelta_X, rKO_Y-rZeile*rDelta_Y);
		cb.showText(seite);
		rZeile++;
		
		cb.endText();
		
	}

	protected void createEmpfänger(PdfContentByte cb) throws DocumentException, IOException {
		
		/*
		 * Variablen zur Positionierung des Empfängers
		 */
		int eKO_X = 50;
		int eKO_Y = 680;
		int eDelta_Y = 15;
		int eZeile = 0;
		
		//empfänger
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		cb.beginText();
		cb.setFontAndSize(bf, 10);
		cb.setTextMatrix(eKO_X, eKO_Y - eZeile*eDelta_Y);
		cb.showText(getAnrede());
		eZeile++;
		cb.setTextMatrix(eKO_X, eKO_Y - eZeile*eDelta_Y);
		cb.showText(
				getVorname()
				+ " "
				+ getName());
		eZeile++;
		cb.setTextMatrix(eKO_X, eKO_Y - eZeile*eDelta_Y);
		cb.showText(
				getStrasse()
				+ " "
				+ getNummer());
		eZeile++;
		eZeile++;
		cb.setTextMatrix(eKO_X, eKO_Y - eZeile*eDelta_Y);
		cb.showText(
				getPlz()
				+ " "
				+ getOrt());
		eZeile++;
		
		cb.endText();
	}

	protected void createBanner(Document doc, PdfContentByte cb) throws MalformedURLException, IOException, DocumentException {
		//füge briefkopf ein
		//Image img = Image.getInstance("briefkopf.png");
		Image img = Image.getInstance(ClassLoader.getSystemResource("logo2.png"));
		img.scalePercent(14);
		img.setAbsolutePosition(220, 710);
		doc.add(img);
		
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		
		cb.beginText();
		cb.setFontAndSize(bf, 10);
		
		cb.setTextMatrix(320, 800);
		cb.showText("Ehrsam IT");
		cb.setTextMatrix(320, 785);
		cb.showText("Marc Ehrsam");
		cb.setTextMatrix(320, 770);
		cb.showText("St. Petersburger Str. 21");
		cb.setTextMatrix(320, 740);
		cb.showText("01069 Dresden");
		
		
		cb.endText();
	}

	protected int calcPages() {
		int complete = positionen.size()/POSPERPAGE;
		double partial = positionen.size()%POSPERPAGE;
		if(partial != 0){
			return complete + 1;
		}
		return complete;
	}

	public String getRechnungsNummer() {
		return rechnung.get(RECHNUNG_ID);
	}

	public void setRechnungsNummer(String rechnungsNummer) {
		rechnung.put(RECHNUNG_ID, rechnungsNummer);
		setChanged();
		notifyObservers();
	}

	public String getKundenNummer() {
		return rechnung.get(KUNDE_ID);
	}

	public void setKundenNummer(String kundenNummer) {
		rechnung.put(KUNDE_ID, kundenNummer);
		setChanged();
		notifyObservers();
	}

	public String getAnrede() {
		return rechnung.get(ANREDE);
	}

	public void setAnrede(String anrede) {
		rechnung.put(anrede, anrede);
		setChanged();
		notifyObservers();
	}

	public String getName() {
		return rechnung.get(NAME);
	}

	public void setName(String name) {
		rechnung.put(NAME, name);
		setChanged();
		notifyObservers();
	}

	public String getVorname() {
		return rechnung.get(VORNAME);
	}

	public void setVorname(String vorname) {
		rechnung.put(VORNAME, vorname);
		setChanged();
		notifyObservers();
	}

	public String getStrasse() {
		return rechnung.get(STRASSE);
	}

	public void setStrasse(String strasse) {
		rechnung.put(STRASSE, strasse);
		setChanged();
		notifyObservers();
	}

	public String getNummer() {
		return rechnung.get(NUMMER);
	}

	public void setNummer(String nummer) {
		rechnung.put(NUMMER, nummer);
		setChanged();
		notifyObservers();
	}

	public String getZusatzZeile() {
		return rechnung.get(ZUSATZZEILE);
	}

	public void setZusatzZeile(String zusatzZeile) {
		rechnung.put(ZUSATZZEILE, zusatzZeile);
		setChanged();
		notifyObservers();
	}

	public String getPlz() {
		return rechnung.get(PLZ);
	}

	public void setPlz(String plz) {
		rechnung.put(PLZ, plz);
		setChanged();
		notifyObservers();
	}

	public String getOrt() {
		return rechnung.get(ORT);
	}

	public void setOrt(String ort) {
		rechnung.put(ORT, ort);
		setChanged();
		notifyObservers();
	}

	public String getDatum() {
		return rechnung.get(DATUM);
	}

	public void setDatum(String datum) {
		rechnung.put(DATUM, datum);
		setChanged();
		notifyObservers();
	}

	//nur indirekt bindung an den kunden!!!
	public void setCustomer(AbstractCustomer customer) {
		setAnrede(customer.getAnrede());
		setName(customer.getName());
		setVorname(customer.getVorname());
		setStrasse(customer.getStrasse());
		setNummer(customer.getNummer());
		setZusatzZeile(customer.getZusatzZeile());
		setPlz(customer.getPlz());
		setOrt(customer.getOrt());
		
		setChanged();
		notifyObservers();
	}

	public void addTableModelListener(TableModelListener l) {
		tableModelListenerList.add(l);
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public int getColumnCount() {
		return 5;
	}

	public String getColumnName(int columnIndex) {
		switch(columnIndex){
		case 0: return "POS";		//Pos
		case 1: return "Art";	//ArtNr
		case 2: return "Anz";		//Anz
		case 3: return "Bezeichnung";	//Bezeichnung
		case 4: return "Einzelpreis";	//Einzelpreis
		default : return "unbenannt";
		}
	}

	public int getRowCount() {
		return positionen.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex){
		case 0:  {
			return rowIndex + 1;
		}
		case 1:  return ((ArrayList<Position>)positionen).get(rowIndex).getProdukt().getArtNr();
		case 2:  return ((ArrayList<Position>)positionen).get(rowIndex).getAnzahl() + "";
		case 3:  return ((ArrayList<Position>)positionen).get(rowIndex).getProdukt().getBezeichnung();
		case 4:  return ((ArrayList<Position>)positionen).get(rowIndex).getEinzelpreis() + "";
		default: return "kein Wert";
		}
		
		//return "testwert";
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		//pos ist nicht editierbar
		if(columnIndex==0)return false;
		else if(columnIndex==1) return false;
		else if(columnIndex==3) return false;
		return true;
	}
	
	public void removeTableModelListener(TableModelListener l) {
		tableModelListenerList.remove(l);
		
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		switch (columnIndex){
			case 0:  break;
			//case 1:  setProduct(((ArrayList<Position>)positionen).get(rowIndex), (Produkt)MD_ProductManager.getInstance().getProdukt((String)value));
			//case 1:  ((ArrayList<Position>)positionen).get(rowIndex).setArtNr((String)value);break;
			case 2:  ((ArrayList<Position>)positionen).get(rowIndex).setAnzahl(Double.parseDouble((String)value)); break;
			//case 3:  ((ArrayList<Position>)positionen).get(rowIndex).setBezeichnung((String)value); break;
			case 4:  ((ArrayList<Position>)positionen).get(rowIndex).setEinzelpreis(Double.parseDouble((String)value)); break;
			default: break;
		}
		
		Iterator<TableModelListener> it = tableModelListenerList.iterator();
		while(it.hasNext()){
			@SuppressWarnings("unused")
			TableModelListener l = (TableModelListener)it.next();
			setChanged();
			notifyObservers();
			//if(l!=null)l.notify();
		}
		
	}
		
	

	@Override
	public void update(Observable o, Object arg) {
		for(TableModelListener l : tableModelListenerList){
			l.tableChanged(null);
		}
		
	}
	
	public double getSum(){
		double summe = 0;
		
		for(Position p : positionen){
			double teilSumme = p.getAnzahl()*p.getEinzelpreis();
			summe+=teilSumme;
		}
	
		return summe;
	}

	@Override
	public boolean canAddBeleg() {
		return true;
	}

	public abstract void writeToDb() throws SQLException;
	
}
