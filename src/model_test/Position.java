package model_test;

public class Position {
	
	private double anzahl = 1;
	private double einzelpreis = 0;
	private Produkt produkt = null;
	
	/*public Position(){
		this.produkt = new Produkt();
		this.anzahl = 0;
	}
	*/
	
	public Position(Produkt produkt, double anzahl){
		this.produkt = produkt;
		this.anzahl = anzahl;
		this.einzelpreis = produkt.getPreis();
	}
	
	public String getArtNr() {
		return produkt.getArtNr();
	}
	public double getAnzahl() {
		return anzahl;
	}
	public void setAnzahl(double anzahl) {
		this.anzahl = anzahl;
	}
	public String getBezeichnung() {
		return produkt.getBezeichnung();
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
		this.einzelpreis = produkt.getPreis();
	}

}
