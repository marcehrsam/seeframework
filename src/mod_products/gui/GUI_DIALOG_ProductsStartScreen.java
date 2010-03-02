package mod_products.gui;

import gui.MyPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Observable;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import mod_products.IProductSource;
import mod_products.IProductTree;
import mod_products.MD_ProductManager;
import model_test.PGroup;
import model_test.Produkt;
import tools.Debug;

public class GUI_DIALOG_ProductsStartScreen extends MyPanel implements TreeSelectionListener, IProductSource{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1386368073874526174L;

	private JButton newProductBtn = null;
	private JButton changeProductBtn = null;
	private JButton deleteProductBtn = null;
	private JButton addProductGroupBtn = null;
	
	private JPanel content = this;
	private JTree productList = null;
	private JScrollPane leftScroller = null;
	private JPanel centerPanel = null;
	
	//TEMP
	private TreePath selectThis = null;
	private int i;
	
	public GUI_DIALOG_ProductsStartScreen(){
		InitLayout();
		MD_ProductManager.getInstance().addObserver(this);
	}
	
	// Returns a TreePath containing the specified node.
//	public TreePath getPath(TreeNode node) {
//		List list = new ArrayList(); 
//		// Add all nodes to list 
//		while (node != null) {
//			list.add(node);
//			node = node.getParent();
//		}
//		Collections.reverse(list);
//		// Convert array of nodes to TreePath 
//		return new TreePath(list.toArray());
//	 
//	}
	
	private void InitLayout() {
		setLayout(new BorderLayout());
		
		leftScroller = new JScrollPane();
		leftScroller.setPreferredSize(new Dimension(200, 600));
		productList = new JTree(MD_ProductManager.getInstance());
		productList.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		productList.addTreeSelectionListener(this);
		leftScroller.setViewportView(productList);
		this.add(leftScroller, BorderLayout.WEST);
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		
		newProductBtn= new JButton(new AbstractAction() {	
			private static final long serialVersionUID = 8643407197950669567L;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				content.remove(centerPanel);
				Produkt neuesProdukt = new Produkt();
				
				MD_ProductManager.getInstance().addProdukt(neuesProdukt, getSelectedTreeItem());
				refreshProductTree();
				productList.expandPath(searchNode(neuesProdukt));
				productList.setSelectionPath(searchNode(neuesProdukt));
				
//				productList.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {				
//					@Override
//					public void valueChanged(TreeSelectionEvent e) {
//						System.out.println(e.getPath());
//						productList.getSelectionModel().setSelectionPath(e.getPath());
//						productList.repaint();
//					}
//				});

				centerPanel = new ProductViewPanel(neuesProdukt);
				content.add(centerPanel, BorderLayout.CENTER);
				centerPanel.revalidate();		
			}
		});
		newProductBtn.setText("Neues Item");
		southPanel.add(newProductBtn);
		addProductGroupBtn = new JButton("Neue Gruppe");
		southPanel.add(addProductGroupBtn);
		changeProductBtn = new JButton("Ändern");
		southPanel.add(changeProductBtn);
		deleteProductBtn = new JButton("Löschen");
		southPanel.add(deleteProductBtn);
		
		JButton testBtn = new JButton(new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 5654425512996916994L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				searchNode((IProductTree)productList.getSelectionPath().getLastPathComponent());
			}
		});
		testBtn.setText("Test");
		southPanel.add(testBtn);
		
		this.add(southPanel, BorderLayout.SOUTH);
		
		centerPanel = new ProductLogoPanel();
		this.add(centerPanel, BorderLayout.CENTER);
		//disableSouthButtons();
	}

	@Override
	public void update(Observable obs, Object o) {
		
		((DefaultTreeModel) productList.getModel()).reload();
		//productList.setSelectionRow(2);
		
	}

	private void disableSouthButtons(){
		newProductBtn.setEnabled(false);
		changeProductBtn.setEnabled(false);
		deleteProductBtn.setEnabled(false);
		addProductGroupBtn.setEnabled(false);
	}

	@Override
	public void valueChanged(TreeSelectionEvent o) {
		
		//für produktliste (baum)
		if(o.getPath().getLastPathComponent()!=null){
			Object selection = o.getPath().getLastPathComponent();
			if(selection instanceof Produkt){
				newProductBtn.setEnabled(false);
				changeProductBtn.setEnabled(true);
				deleteProductBtn.setEnabled(true);
				addProductGroupBtn.setEnabled(false);
				
				this.remove(centerPanel);
				centerPanel = new ProductViewPanel((Produkt) selection);
				this.add(centerPanel, BorderLayout.CENTER);
				centerPanel.revalidate();
								
			}else if(selection instanceof PGroup){
				newProductBtn.setEnabled(true);
				changeProductBtn.setEnabled(true);
				deleteProductBtn.setEnabled(true);
				addProductGroupBtn.setEnabled(true);
				
				this.remove(centerPanel);
				centerPanel = new ProductGroupPanel((PGroup) selection);
				this.add(centerPanel, BorderLayout.CENTER);
				centerPanel.revalidate();
				
			}else{
				newProductBtn.setEnabled(false);
				changeProductBtn.setEnabled(false);
				deleteProductBtn.setEnabled(false);
				addProductGroupBtn.setEnabled(false);
				
				this.remove(centerPanel);
				centerPanel = new ProductLogoPanel();
				this.add(centerPanel, BorderLayout.CENTER);
				centerPanel.revalidate();
			}
		}
		
	}
	
	public IProductTree getSelectedTreeItem(){
		Object ret = productList.getSelectionPath().getLastPathComponent();
		if(ret instanceof PGroup){
			return (PGroup) ret;
		
		}else if(ret instanceof MD_ProductManager){
			return (MD_ProductManager)ret;
		}else{
			throw new UnsupportedOperationException();
			//return null;
		}
		 

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Produkt getProduct() {
		//return getSelectedTreeItem();
		return null;
	}

	@Override
	public List<PGroup> getProducts() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void refreshProductTree(){
		productList = new JTree(MD_ProductManager.getInstance());
		leftScroller.setViewportView(productList);
	}
	
	public void selectTreeItem(IProductTree item){
		//Tree aufklappen und item markieren
	}
	
	public TreePath searchNode(IProductTree item){
		i=0;		
		IProductTree rootItem = (IProductTree)productList.getModel().getRoot();
		List<Object> pathList = new ArrayList<Object>();
		List<Object> resultList = searchNode(item, rootItem, null);
		TreePath path = null;
		if(resultList != null){
			Collections.reverse(resultList);
			Object[] pathArray = resultList.toArray();
			System.out.println(pathArray.toString());
			path = new TreePath(pathArray);
		}
		return path;
	}
	
	public List<Object> searchNode(IProductTree item, IProductTree group, List<Object> oList){
		if(group.equals(item)){
			System.out.println(i + " : gefunden");
			if(oList == null){
				oList = new ArrayList<Object>();
			}
			oList.add(group);
			return oList;
		}
		Enumeration<IProductTree> tEnum = group.children();
		if(tEnum!=null){
			while(tEnum.hasMoreElements()){
				IProductTree nextItem = tEnum.nextElement();
				i++;
				List<Object> res = searchNode(item, nextItem, oList);
				if(res!=null){
					res.add(group);
					return res;
				}
			}
		}
		
		return null;
	}
	
}
