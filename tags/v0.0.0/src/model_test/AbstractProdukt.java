package model_test;

public abstract class AbstractProdukt {
	
	public static final String ARTNR = "ArtNr";
	public static final String BEZEICHNUNG = "Bezeichnung";
	public static final String EINZELPREIS = "PreisStck";
	
	protected String artNr = "SE-0001";
	protected String bezeichnung = "IT Service (Stundensatz)";
	protected double preis = 20;
	
	public AbstractProdukt(){
		
	}
	
	public AbstractProdukt(String bezeichnung){
		this();
		this.bezeichnung = bezeichnung;
	}
	
	public AbstractProdukt(String bezeichnung, double preis){
		this(bezeichnung);
		this.preis = preis;
	}
	
	public AbstractProdukt(String artNr, String bezeichnung){
		this(bezeichnung);
		this.artNr = artNr;
	}
	
	public AbstractProdukt(String artNr, String bezeichnung, double preis){
		this(artNr, bezeichnung);
		this.preis = preis;
	}

	public String getArtNr() {
		return artNr;
	}

	public void setArtNr(String artNr) {
		this.artNr = artNr;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}
	
	

}
