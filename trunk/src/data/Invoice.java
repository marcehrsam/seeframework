package data;

import io.Exporter;
import io.InvoiceExporter;

import java.util.Map;

import model_test.Position;

public class Invoice extends Voucher{
	
	//Rechnungsdaten
	private String anrede;
	private String name;
	private String vorname;
	private String strasse;
	private String nummer;
	private String adresse2;
	private String zip;
	private String ort;
	private String beschreibung;
	
	private Map<Integer, Position> positions;
	

	public Invoice(String voucherNumber){
		super(voucherNumber);
	}

	@Override
	public Exporter setExporter() {
		return new InvoiceExporter(this);
	}

}
