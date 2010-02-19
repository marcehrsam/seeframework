package mod_products;

import gui.MyPanel;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.tree.TreeNode;

import tools.Debug;

import mod_products.action.ACT_MI_KLICK_DeleteProduct;
import mod_products.action.ACT_MI_KLICK_EditProduct;
import mod_products.action.ACT_MI_KLICK_NewProduct;
import mod_products.gui.GUI_DIALOG_ProductsStartScreen;
import model_test.AbstractProdukt;
import model_test.Produkt;
import base.AbstractModule;

public class MD_ProductManager extends AbstractModule{
//Verwaltet alle Produkte
//entweder direkt auf DB aufsetzen oder mit collections realisieren	
	
	private static MD_ProductManager instance = null;
	
	private Collection<AbstractProdukt> db = null;
	
	//wurzel vom produktbaum
	private AbstractProdukt root = null;
	
	protected JMenu menu = null;
	
	public final String MENUNAME = "Produkte";
	
	private MD_ProductManager(){
		db = new ArrayList<AbstractProdukt>();
		root = new AbstractProdukt("Produkte");
		Produkt p1 = new Produkt("TestProdukt");
		root.addProdukt(p1);
		InitDB();
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
	
	private void addProdukt(AbstractProdukt prod) {
		db.add(prod);
	}
	
	private boolean addProdukt(AbstractProdukt prod, AbstractProdukt parent){
		if(parent != null){
			return parent.addProdukt(prod);
		}
		Debug.out("MD_ProductManager, Produkt wurde nicht hinzugefügt.");
		return false;
	}

	public static MD_ProductManager getInstance(){
		if(instance == null)instance = new MD_ProductManager();
		return instance;
	}
	
	public AbstractProdukt getProdukt(String artNr){
		if(db.size()>0){
			for(AbstractProdukt p : db){
				if(p.getArtNr().equals(artNr)){
					return p;
				}
			}
		}
		
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
	
	public AbstractProdukt getRoot(){
		return this.root;
	}
	
}
