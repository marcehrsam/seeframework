package mod_products.gui;

import java.util.Observable;

import mod_products.MD_ProductManager;

import gui.MyPanel;

public class GUI_DIALOG_ProductsStartScreen extends MyPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1386368073874526174L;

	public GUI_DIALOG_ProductsStartScreen(){
		InitLayout();
		MD_ProductManager.getInstance().addObserver(this);
	}
	
	private void InitLayout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
