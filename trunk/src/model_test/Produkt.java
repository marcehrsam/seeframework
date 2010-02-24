package model_test;

import java.util.Enumeration;
import java.util.Observable;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import mod_products.IProductTree;

public class Produkt extends Observable implements IProductTree{
	
	public static final String ARTNR = "ArtNr";
	public static final String BEZEICHNUNG = "Bezeichnung";
	public static final String EINZELPREIS = "PreisStck";
	
	protected String artNr = "keine";
	protected String bezeichnung = "unbenannt";
	protected double preis = 0;
	
	public Produkt() {
		super();
	}
	
	public Produkt(String bezeichnung){
		this.bezeichnung = bezeichnung;
	}
	
	public Produkt(String bezeichnung, double preis){
		this.bezeichnung = bezeichnung;
		this.preis = preis;
	}
	
	public Produkt(String artNr, String bezeichnung){
		this.artNr = artNr;
		this.bezeichnung = bezeichnung;
	}
	
	public Produkt(String artNr, String bezeichnung, double preis){
		this.artNr = artNr;
		this.bezeichnung = bezeichnung;
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
	public boolean addTreeItem(IProductTree item) {
		// TODO: wird nicht benutzt, da Produkt ein Blatt ist
		return false;
	}

	@Override
	public String getName() {
		return getBezeichnung();
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		// TODO nu
		
	}

	@Override
	public void remove(int index) {
		// TODO nu
		
	}

	@Override
	public void remove(MutableTreeNode node) {
		// TODO nu
		
	}

	@Override
	public void removeFromParent() {
		// TODO nu
		
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		// TODO nu
		
	}

	@Override
	public void setUserObject(Object object) {
		// TODO nu
		
	}

	@Override
	public Enumeration<IProductTree> children() {
		// TODO nu
		return null;
	}

	@Override
	public boolean getAllowsChildren() {
		return false;
	}

	@Override
	public TreeNode getChildAt(int arg0) {
		// TODO nu
		return null;
	}

	@Override
	public int getChildCount() {
		// TODO nu
		return 0;
	}

	@Override
	public int getIndex(TreeNode arg0) {
		// TODO nu
		return 0;
	}

	@Override
	public TreeNode getParent() {
		// TODO nu
		return null;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}
	
	public String toString(){
		return getBezeichnung();
	}
	
}
