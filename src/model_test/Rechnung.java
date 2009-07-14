package model_test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import mod_customer.AbstractCustomer;
import mod_products.MD_ProductManager;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class Rechnung extends AbstractBeleg implements ICustomerHolder, TableModel{
	
	private Map<String, String> rechnung = null;
	
	private List<TableModelListener> tableModelListenerList = null;
	
	private Collection<Position> positionen = null;
	
	public final int POSPERPAGE = 10;
	
	//f�r sql abfrage
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
		
		positionen = new ArrayList<Position>();
		
		Produkt testprodukt = new Produkt("SE-0001", "IT-Service (Stundensatz)", 20 );
		Position testpos = new Position(testprodukt, 3);
		positionen.add(testpos);
		/*Position testpos1 = new Position(testprodukt, 2);
		positionen.add(testpos1);
		Position testpos2 = new Position(testprodukt, 3);
		positionen.add(testpos2);
		Position testpos3 = new Position(testprodukt, 4);
		positionen.add(testpos3);
		Position testpos4 = new Position(testprodukt, 5);
		positionen.add(testpos4);
		Position testpos5 = new Position(testprodukt, 6);
		positionen.add(testpos5);
		Position testpos6 = new Position(testprodukt, 7);
		positionen.add(testpos6);
		Position testpos7 = new Position(testprodukt, 8);
		positionen.add(testpos7);
		Position testpos8 = new Position(testprodukt, 9);
		positionen.add(testpos8);
		Position testpos9 = new Position(testprodukt, 10);
		positionen.add(testpos9);
		Position testpos10 = new Position(testprodukt, 11);
		positionen.add(testpos10);
			*/	
		rechnung = new HashMap<String, String>();
 		InitData();
		
	}
	
	private void InitData(){
		rechnung.put(RECHNUNG_ID, "050709-0001");
		rechnung.put(AUFTRAG_ID, "200609-0001");
		rechnung.put(KUNDE_ID, "Testkunde");
		rechnung.put(ANREDE, "Herr");
		rechnung.put(NAME, "Mustermann");
		rechnung.put(VORNAME, "Max");
		rechnung.put(STRASSE, "Musterstr.");
		rechnung.put(NUMMER, "123");
		rechnung.put(ZUSATZZEILE, "Musterteil");
		rechnung.put(PLZ, "01234");
		rechnung.put(ORT, "Musterstadt");
		rechnung.put(DATUM, "05.07.2009");
	}
	
	
	public void generatePdf() throws DocumentException, MalformedURLException, IOException{
		
		//test, ob rechnung leer ist
		if(positionen.size()==0){
			JOptionPane.showMessageDialog(null, "Bitte f�gen Sie zuerst eine Position ein!", "Fehler", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//initialisiere header
		Document doc = new Document(PageSize.A4);
		PdfWriter pdfw = PdfWriter.getInstance(doc, new FileOutputStream("Rechnung.pdf"));
		doc.open();
		PdfContentByte cb = pdfw.getDirectContent();
		
		//Schrift festlegen
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
		
		//z�hle seiten und initialisiere schleife
		int anzGes = calcPages();
		
		for(int i=1; i<(anzGes + 1); i++){
			
			createBanner(doc, cb);
			createEmpf�nger(cb);
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

	private void createFooter(PdfContentByte cb, BaseFont bf) {
		
		//zusatzinfos zur Zahlungsweise
		cb.beginText();
		cb.setFontAndSize(bf, 8);
		cb.setTextMatrix(100, 200);
		cb.showText("Bitte �berweisen Sie den Rechnungsbetrag");
		cb.setTextMatrix(100, 190);
		cb.showText("innerhalb der n�chsten 14 Tage auf folgendes Konto: ");
		cb.setTextMatrix(100, 170);
		cb.showText("Empf�nger: Marc Ehrsam");
		cb.setTextMatrix(100, 160);
		cb.showText("Kto: 12204426");
		cb.setTextMatrix(100, 150);
		cb.showText("BLZ: 120 300 00");
		cb.setTextMatrix(100, 140);
		cb.showText("Deutsche Kreditbank Berlin");
		
		cb.endText();
		/*
		cb.beginText();
		cb.setFontAndSize(bf, 8);
		cb.setTextMatrix(50, 70);
		cb.showText("Dieses Dokument wurde maschinell erstellt und ist ohne Unterschrift g�ltig.");
		cb.endText();
		*/
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
		cb.showText("Gesch�ftsf�hrung:");
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

	private void createStampSignature(PdfContentByte cb, BaseFont bf, Document doc) throws MalformedURLException, IOException, DocumentException {
		//stempel und unterschrift	
		cb.beginText();
		cb.setFontAndSize(bf, 10);
		cb.setTextMatrix(400, 150);
		cb.showText("_________________________");
		cb.setTextMatrix(420, 140);
		cb.setFontAndSize(bf, 8);
		//cb.showText("Stempel / Unterschrift");
		cb.endText();
		
		Image img = Image.getInstance("stempel_sw.png");
		img.scalePercent(10);
		img.setAbsolutePosition(400, 160);
		doc.add(img);
		
	}

	private void createTable(PdfContentByte cb, int page, int fromPages) {
		
		//spaltenverh�ltnisse
		PdfPTable posTable = new PdfPTable(new float[]{0.1f, 0.2f, 0.1f, 0.6f, 0.25f, 0.3f});
		
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
				posTable.addCell("1");
				posTable.addCell(pos.getArtNr());
				posTable.addCell(pos.getAnzahl() + "");
				posTable.addCell(pos.getBezeichnung());
				posTable.addCell(pos.getEinzelpreis()+ " EUR");
				//TODO: gesamtpreis abfragen
				posTable.addCell(pos.getEinzelpreis()*pos.getAnzahl() + " EUR");
			}
		}
		posTable.setTotalWidth(470);
			
		//falls letzte Seite -> summe einf�gen
		if(page>=fromPages){
			int max = positionen.size()%POSPERPAGE;
			for(int i=0; i<max; i++){
				Position pos = ((ArrayList<Position>)positionen).get((page-1)*10+i);
				//TODO: positionsnummern abfragen
				posTable.addCell("1");
				posTable.addCell(pos.getArtNr());
				posTable.addCell(pos.getAnzahl() + "");
				posTable.addCell(pos.getBezeichnung());
				posTable.addCell(pos.getEinzelpreis()+ " EUR");
				//TODO: gesamtpreis abfragen
				posTable.addCell(pos.getEinzelpreis()*pos.getAnzahl() + " EUR");
			}
			
			//TODO: Summe berechnen
			for(int i=0; i<10; i++){
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
			posTable.addCell("60.00 EUR");
		}
		
		//TODO: seite von/bis berechnen
		posTable.writeSelectedRows(0, -1, 80, 500, cb);
		
	}

	private void createRechteSeiteHeader(PdfContentByte cb, int anzAkt, int anzGes) throws DocumentException, IOException {
		
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

	private void createEmpf�nger(PdfContentByte cb) throws DocumentException, IOException {
		
		/*
		 * Variablen zur Positionierung des Empf�ngers
		 */
		int eKO_X = 50;
		int eKO_Y = 680;
		int eDelta_Y = 15;
		int eZeile = 0;
		
		//empf�nger
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

	private void createBanner(Document doc, PdfContentByte cb) throws MalformedURLException, IOException, DocumentException {
		//f�ge briefkopf ein
		//Image img = Image.getInstance("briefkopf.png");
		Image img = Image.getInstance("logo2.png");
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

	private int calcPages() {
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
		return true;
	}
	
	public void removeTableModelListener(TableModelListener l) {
		tableModelListenerList.remove(l);
		
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		switch (columnIndex){
			case 0:  break;
			//case 1:  setProduct(((ArrayList<Position>)positionen).get(rowIndex), (Produkt)MD_ProductManager.getInstance().getProdukt((String)value));
			case 1:  ((ArrayList<Position>)positionen).get(rowIndex).setArtNr((String)value);break;
			case 2:  ((ArrayList<Position>)positionen).get(rowIndex).setAnzahl(Integer.parseInt((String)value)); break;
			case 3:  ((ArrayList<Position>)positionen).get(rowIndex).setBezeichnung((String)value); break;
			case 4:  ((ArrayList<Position>)positionen).get(rowIndex).setEinzelpreis(Double.parseDouble((String)value)); break;
			default: break;
		}
		
		Iterator<TableModelListener> it = tableModelListenerList.iterator();
		while(it.hasNext()){
			TableModelListener l = (TableModelListener)it.next();
			setChanged();
			notifyObservers();
			//if(l!=null)l.notify();
		}
		
	}
	
	private void setProduct(Position altPos, Produkt product){
		((ArrayList<Position>)positionen).remove(altPos);
		Position neuPos = new Position(product, altPos.getAnzahl());
		((ArrayList<Position>)positionen).add(neuPos);
	}
	
	public void addPos(Position pos){

	}
	
	public void writeToDb() throws SQLException{
		/*		
		
		//Iteration �ber positionen
		Iterator<Integer> it = positionen.keyset().iterator();
		while(it.hasNext()){
			int no = it.next();
			Position pos = posititionen.get(no);
			
			String query = "INSERT INTO `se_framework_db1`.`auftraege` ( ";
			query +=	 "`" + AUFTRAG_ID + "`";
			query +=	",`" + "Position" + "`";
			query +=	",`" + "ArtNr" + "`";
			query +=	",`" + "Anzahl" + "`";
			query +=	",`" + "Einzelpreis" + "`";
			query +=	",`" + RECHNUNG_ID + "`";
			
			query += ") VALUES (";
			query +=	 "'" + rechnung.get(AUFTRAG_ID) + "'";
			query +=	", '" + pos.getPosNr() + "'";
			query +=	", '" + pos.getArtNr() + "'";
			query +=	", '" + pos.getAnzahl() + "'";
			query +=	", '" + pos.getEinzelpreis() + "'";
			query +=	", '" + rechnung.get(RECHNUNG_ID) + "'";
			query += ");";
			JOptionPane.showMessageDialog(null, query);
			SQL_Writer.inst().getStmt().execute(query);
		}
	*/	
	}

}
