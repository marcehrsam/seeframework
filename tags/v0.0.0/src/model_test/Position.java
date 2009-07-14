package model_test;

public class Position {
	
	private String artNr = "SE-0000";
	private int anzahl = 1;
	private String bezeichnung = "unbenannt";
	private double einzelpreis = 0;
	private Produkt produkt = null;
	
	/*public Position(){
		this.produkt = new Produkt();
		this.anzahl = 0;
	}
	*/
	
	public Position(Produkt produkt, int anzahl){
		this.produkt = produkt;
		this.anzahl = anzahl;
	}
	
	public String getArtNr() {
		return artNr;
	}
	public void setArtNr(String artNr) {
		this.artNr = artNr;
	}
	public int getAnzahl() {
		return anzahl;
	}
	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public double getEinzelpreis() {
		return einzelpreis;
	}
	public void setEinzelpreis(double einzelpreis) {
		this.einzelpreis = einzelpreis;
	}

	public Produkt getProdukt() {
		return produkt;
	}

	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}

}
