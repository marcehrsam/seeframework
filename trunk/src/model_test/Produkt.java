package model_test;

import javax.swing.tree.TreeNode;

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

	@Override
	public boolean getAllowsChildren() {
		return false;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return null;
	}

	@Override
	public int getChildCount() {
		return 0;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}
	
	

}
