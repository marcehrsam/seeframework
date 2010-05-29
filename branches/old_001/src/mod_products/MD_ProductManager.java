package mod_products;

import gui.MyPanel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import tools.MyDatabaseStructureFactory;

import mod_products.action.ACT_MI_KLICK_DeleteProduct;
import mod_products.action.ACT_MI_KLICK_EditProduct;
import mod_products.action.ACT_MI_KLICK_NewProduct;
import mod_products.gui.GUI_DIALOG_ProductsStartScreen;
import model_test.PGroup;
import model_test.Produkt;
import base.AbstractModule;

public class MD_ProductManager extends AbstractModule implements IProductTree{
//Verwaltet alle Produkte
//entweder direkt auf DB aufsetzen oder mit collections realisieren	
	
	private static MD_ProductManager instance = null;
	
	private List<IProductTree> db = null;
	
	protected JMenu menu = null;
	
	public final String MENUNAME = "Produkte";
	
	private MD_ProductManager(){
		db = new ArrayList<IProductTree>();
		
		PGroup serv = new PGroup("Service");
		db.add(serv);
		
		Produkt p1 = new Produkt("P1");
		serv.addTreeItem(p1);
	
		InitDB();
	}
	
	public void setDB(ArrayList<IProductTree> sqlDB){
		this.db = null;
		this.db = sqlDB;
		setChanged();
		notifyObservers();
	}
	
	//ließt die Daten aus der Datenbank
	private void InitDB(){
		/*
		String query = "SELECT * FROM `produkte` WHERE 1;";
		SQL_Writer sqlWriter = new SQL_Writer();
		//ResultSet rs = null;
		try {
			ResultSet rs = sqlWriter.getResult(query);
			while(rs.next()){
				Produkt prod = new Produkt();
				prod.setArtNr(rs.getString(Produkt.ARTNR));
				prod.setBezeichnung(rs.getString(Produkt.BEZEICHNUNG));
				prod.setPreis(Double.parseDouble(rs.getString(Produkt.EINZELPREIS)));
				addProdukt(prod);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		*/
	}
	
	public boolean addProdukt(Produkt prod, IProductTree parent){
		//TODO: NPE abfangen
		return parent.addTreeItem(prod);
	}

	public static MD_ProductManager getInstance(){
		if(instance == null)instance = new MD_ProductManager();
		return instance;
	}
	
	public Produkt getProdukt(String artNr){
//		if(db.size()>0){
//			for(PGroup pg : db){
//				for(int i = 0; i<pg.getChildCount(); i++){
//					if(pg.getChildAt(i).getArtNr().equals(artNr)){
//						return pg.getChildAt(i);
//					}
//				}
//			}
//		}
		
		return null;
	}

	@Override
	public void createMenu() {
		menu = new JMenu(getMenuName());
		JMenuItem miAddProduct = new JMenuItem(new ACT_MI_KLICK_NewProduct());
		menu.add(miAddProduct);
		JMenuItem miEditProduct = new JMenuItem(new ACT_MI_KLICK_EditProduct());
		menu.add(miEditProduct);
		JMenuItem miDeleteProduct = new JMenuItem(new ACT_MI_KLICK_DeleteProduct());
		menu.add(miDeleteProduct);
		
	}

	@Override
	public JMenu getMenu() {
		if(menu==null)createMenu();
		return menu;
	}

	@Override
	public String getMenuName() {
		return MENUNAME;	
	}

	@Override
	public boolean stopAllActions() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public JPanel getContentScreen() {
		// TODO Auto-generated method stub
		return new GUI_DIALOG_ProductsStartScreen();
	}

	@Override
	public void setContentScreen(MyPanel screen) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean readConfigFile() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addTreeItem(IProductTree item) {
		if(item instanceof PGroup){
			boolean ok = db.add(item);
			setChanged();
			notifyObservers();
			return ok;
		}
		return false;
	}

	@Override
	public String getName() {
		return "Produkte";
	}

	@Override
	public void insert(MutableTreeNode node, int index) {
		if(node instanceof IProductTree){
			addTreeItem((IProductTree)node);
		}else{
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public void remove(int index) {
		if(db.size()>index){
			db.remove(index);
			setChanged();
			notifyObservers();
		}
	}

	@Override
	public void remove(MutableTreeNode node) {
		if(db.contains(node)){
			db.remove(node);
			setChanged();
			notifyObservers();
		}
		
	}

	@Override
	public void removeFromParent() {
		// TODO nu
		
	}

	@Override
	public void setParent(MutableTreeNode arg0) {
		// TODO nu
		
	}

	@Override
	public void setUserObject(Object o) {
		// TODO Datenelement kapseln???
		// TODO nu
		
	}

	@Override
	public Enumeration<IProductTree> children() {
		return Collections.enumeration(db);
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		if(db.size()>childIndex){
			return db.get(childIndex);
		}
		return null;
	}

	@Override
	public int getChildCount() {
		//TODO: rekursiv zählen
		return db.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		return db.indexOf(node);
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

	public String toString(){
		return "Datenbank";
	}

	@Override
	protected ArrayList<String> getSQLTableStrings() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(MyDatabaseStructureFactory.S_PRODUCTS);
		return list;
	}
	
}
