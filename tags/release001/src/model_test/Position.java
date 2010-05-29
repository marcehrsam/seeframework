package model_test;

import java.util.Observable;

public class Position extends Observable{
	
	private double anzahl = 1;
	private double einzelpreis = 0;
	private Produkt produkt = null;
	private boolean billAvailable = false;
	
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
		setChanged();
		notifyObservers();
	}
	public String getBezeichnung() {
		return produkt.getBezeichnung();
	}
	public double getEinzelpreis() {
		return einzelpreis;
	}
	public void setEinzelpreis(double einzelpreis) {
		this.einzelpreis = einzelpreis;
		setChanged();
		notifyObservers();
	}

	public Produkt getProdukt() {
		return produkt;
	}

	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
		this.einzelpreis = produkt.getPreis();
		setChanged();
		notifyObservers();
	}

	public boolean isBillAvailable() {
		return billAvailable;
	}
	
	public void setBillAvailable(boolean value){
		this.billAvailable = value;
		setChanged();
		notifyObservers();
	}

}
