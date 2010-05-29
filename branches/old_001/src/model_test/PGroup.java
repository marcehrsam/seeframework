package model_test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Observable;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import mod_products.IProductTree;

public class PGroup extends Observable implements IProductTree{
	//die klasse wird als produktgruppe benutzt
	
	private List<IProductTree> children = null;
	
	private String bezeichnung;
	
	public PGroup(String bezeichnung){
		children = new ArrayList<IProductTree>();
		setBezeichnung(bezeichnung);
	}
	
	@Override
	public boolean addTreeItem(IProductTree item) {
		boolean ok = children.add(item);
		setChanged();
		notifyObservers();
		return ok;
	}

	@Override
	public String getName() {
		return getBezeichnung();
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		//index spielt keine Rolle
		//knoten wird ans ende der Liste eingefügt
		if(child instanceof IProductTree){
			children.add((IProductTree)child);
			setChanged();
			notifyObservers();
		}else{
			throw new UnsupportedOperationException();
		}
		
	}

	@Override
	public void remove(int index) {
		if(children.size()>index){
			children.remove(index);
			setChanged();
			notifyObservers();
		}else{
			//ArrayindexOutOfBounds
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public void remove(MutableTreeNode node) {
		if(children.contains(node)){
			children.remove(node);
			setChanged();
			notifyObservers();
		}
		
	}

	@Override
	public void removeFromParent() {
		// TODO wird nicht benutzt
		
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		// TODO wird nicht benutzt
		
	}

	@Override
	public void setUserObject(Object object) {
		// TODO kann man als Datenspeicher nutzen
		// TODO im Moment nicht benutzt
		
	}

	@Override
	public Enumeration<IProductTree> children() {
		// TODO Auto-generated method stub
		return Collections.enumeration(children);
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return children.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return children.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		//TODO Fall Gruppe->Gruppe->node ???
		return children.indexOf(node);
	}

	@Override
	public TreeNode getParent() {
		// TODO nu
		return null;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}
	

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
		setChanged();
		notifyObservers();
	}
	
	public String toString(){
		return getBezeichnung();
	}
}