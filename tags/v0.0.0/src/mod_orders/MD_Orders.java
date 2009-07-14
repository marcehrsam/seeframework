package mod_orders;

import gui.GU_MP_BlankScreen;
import gui.MyPanel;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import base.AbstractModule;

public class MD_Orders extends AbstractModule{

	private static MD_Orders instance = null;
	
	public final String MENUNAME = "Auftrag";
	
	private JMenu menu = null;
	
	private MD_Orders(){
		
	}
	
	public static MD_Orders getInstance(){
		if(instance == null)instance = new MD_Orders();
		return instance;
	}
	
	@Override
	public void createMenu() {
		menu = new JMenu(MENUNAME);
		
		JMenuItem miNewOrder = new JMenuItem("Neu");
		menu.add(miNewOrder);
		
		JMenuItem miShowOrder = new JMenuItem("Anzeigen");
		menu.add(miShowOrder);
		
		JMenuItem miEditOrder = new JMenuItem("�ndern");
		menu.add(miEditOrder);
		
	}

	@Override
	public MyPanel getContentScreen() {
		return new GU_MP_BlankScreen();
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
	public void setContentScreen() {
		// TODO Auto-generated method stub
		
	}

}
