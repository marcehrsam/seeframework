package model_test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Observable;

import javax.swing.tree.TreeNode;

public class AbstractProdukt extends Observable implements TreeNode{
	//die klasse wird als produktgruppe benutzt
	
	public static final String ARTNR = "ArtNr";
	public static final String BEZEICHNUNG = "Bezeichnung";
	public static final String EINZELPREIS = "PreisStck";
	
	protected String artNr = "SE-0001";
	protected String bezeichnung = "IT Service (Stundensatz)";
	protected double preis = 20;
	
	private TreeNode parent = null;
	private ArrayList<AbstractProdukt> children = null;
	
	public AbstractProdukt(){
		children = new ArrayList<AbstractProdukt>();
		
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
		setChanged();
		notifyObservers();
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
		setChanged();
		notifyObservers();
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
		setChanged();
		notifyObservers();
	}

	@Override
	public Enumeration<AbstractProdukt> children() {
		return Collections.enumeration(children);
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return (TreeNode)((ArrayList<AbstractProdukt>)children).get(childIndex);
	}

	@Override
	public int getChildCount() {
		return children.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		return children.indexOf(node);
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public boolean isLeaf() {
		if((children == null)||(children.size()==0)){
			return true;
		}
		return false;
	}
	
	public void setParent(TreeNode parent){
		this.parent = parent;
		setChanged();
		notifyObservers();
	}
	
	public boolean addProdukt(AbstractProdukt child){
		return children.add(child);
	}

	public String toString(){
		return artNr + "->" + bezeichnung;
	}
	
}
