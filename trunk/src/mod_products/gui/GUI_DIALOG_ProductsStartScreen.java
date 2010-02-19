package mod_products.gui;

import gui.MyPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import mod_products.MD_ProductManager;

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
		setLayout(new BorderLayout());
		
		JScrollPane leftScroller = new JScrollPane();
		JTree productList = new JTree(MD_ProductManager.getInstance().getRoot());
		leftScroller.setViewportView(productList);
		this.add(leftScroller, BorderLayout.WEST);
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		JButton newProductBtn = new JButton("Neues Produkt");
		southPanel.add(newProductBtn);
		JButton changeProductBtn = new JButton("Produkt ändern");
		southPanel.add(changeProductBtn);
		JButton deleteProductBtn = new JButton("Produkt löschen");
		southPanel.add(deleteProductBtn);
		this.add(southPanel, BorderLayout.SOUTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.BLACK);
		this.add(centerPanel, BorderLayout.CENTER);
		
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
