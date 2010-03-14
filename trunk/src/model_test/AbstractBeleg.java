package model_test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import mod_customer.Customer;

public abstract class AbstractBeleg extends Observable implements IBeleg, Observer{

	//Belegnummer
	private String id;
	private ArrayList<AbstractBeleg> belege;
	private boolean closed = false;
	
	private Customer rechnungsKunde = null;
	private Date belegDat = null;
	
	protected Collection<Position> positionen = null;
	
	public AbstractBeleg(String id){
		this.id = id;
		belege = new ArrayList<AbstractBeleg>();
		belegDat = new Date(new java.util.Date().getTime());
		positionen = new ArrayList<Position>();
	}
	
	public String getID(){
		return this.id;
	}
	
	public void setID(String id){
		this.id = id;
		setChanged();
		notifyObservers();
	}
	
	public abstract boolean canAddBeleg();
	
	public boolean addBeleg(AbstractBeleg be){
		if(canAddBeleg()&&!isClosed()){
			boolean ret = belege.add(be);
			setChanged();
			notifyObservers();
			return ret;
		}
		return false;
	}
	
	protected ArrayList<AbstractBeleg> getBelege() {
		return belege;
	}

	protected void setBelege(ArrayList<AbstractBeleg> belege) {
		this.belege = belege;
		setChanged();
		notifyObservers();
	}
	
	public boolean removeBeleg(AbstractBeleg be){
		while(belege.contains(be)){
			belege.remove(be);	
		}
		for(AbstractBeleg b : belege){
			b.removeBeleg(be);
		}
		setChanged();
		notifyObservers();
		return true;
	}
	
	public boolean isClosed(){
		return closed;
	}
	
	public void setClosed(boolean closed) {
		this.closed = closed;
		setChanged();
		notifyObservers();
	}
	
	public Customer getKunde(){
		return rechnungsKunde;
	}
	
	public void setKunde(Customer kunde){
		rechnungsKunde = kunde;
		setChanged();
		notifyObservers();
	}
	
	public Date getDate(){
		return belegDat;
	}
	
	public void setDate(Date date){
		this.belegDat = date;
		setChanged();
		notifyObservers();
	}
	
	@SuppressWarnings("unused")
	private void setProduct(Position altPos, Produkt product){
		((ArrayList<Position>)positionen).remove(altPos);
		Position neuPos = new Position(product, altPos.getAnzahl());
		((ArrayList<Position>)positionen).add(neuPos);
	}
	
	public void addPos(Position pos){
		positionen.add(pos);
		
		setChanged();
		notifyObservers();
		update(null, null);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
	}
	
	public Collection<Position> getPositionen(){
		return positionen;
	}
	
	public String toString(){
		return getID();
	}
	
}
