package model_test;

public class Produkt extends AbstractProdukt{
	
	public Produkt() {
		super();
	}
	
	public Produkt(String bezeichnung){
		super(bezeichnung);
	}
	
	public Produkt(String bezeichnung, double preis){
		super(bezeichnung, preis);
	}
	
	public Produkt(String artNr, String bezeichnung){
		super(artNr, bezeichnung);
	}
	
	public Produkt(String artNr, String bezeichnung, double preis){
		super(artNr, bezeichnung, preis);
	}

}
