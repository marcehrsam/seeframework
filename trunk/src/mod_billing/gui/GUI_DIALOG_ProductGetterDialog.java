package mod_billing.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import mod_billing.action.ACT_BT_KLICK_CloseDialog;
import mod_billing.action.ACT_BT_KLICK_OK_AddProductGetterDialog;
import mod_products.IProductGetter;
import mod_products.IProductSource;
import mod_products.MD_ProductManager;
import model_test.PGroup;
import model_test.Produkt;
import tools.TO_JFrame;

public class GUI_DIALOG_ProductGetterDialog extends JDialog implements IProductSource, TreeSelectionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2590581149783445487L;
	
	private final String TITLE = "Artikel hinzufügen";
	
	private IProductGetter target = null;
	private Produkt selectedProduct = null;
	JButton okButton = null;
	JButton cancelButton = null;
	JTree productTree = null;
	
	public GUI_DIALOG_ProductGetterDialog(IProductGetter target){
		this.target = target;
		setSize(new Dimension(500, 400));
		setTitle(TITLE);
		setLayout(new BorderLayout());
		TO_JFrame.getInstance().centerJFrame(this);
		
		InitLayout();
		updateButtons();
		setModal(true);
		setAlwaysOnTop(true);
		setVisible(true);
	}

	private void InitLayout() {
		JPanel northPanel = new JPanel();
		getContentPane().add(northPanel, BorderLayout.NORTH);
		
		JScrollPane scroller = new JScrollPane();
		northPanel.add(scroller, BorderLayout.NORTH);
		scroller.setPreferredSize(new Dimension(400, 300));
		
		productTree = new JTree(MD_ProductManager.getInstance());
		productTree.setBackground(this.getBackground());
		scroller.setViewportView(productTree);
		
		productTree.addTreeSelectionListener(this);
		productTree.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					if(selectedProduct!=null){
						okButton.doClick();
					}
				}else if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
					dispose();
				}
			}
		});
		
		JPanel southPanel = new JPanel();
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		okButton = new JButton(new ACT_BT_KLICK_OK_AddProductGetterDialog(this));
		southPanel.add(okButton);
		
		cancelButton = new JButton(new ACT_BT_KLICK_CloseDialog(this));
		southPanel.add(cancelButton);
		
	}

	@Override
	public int getCount() {
		//TODO
		return 1;
	}

	public Produkt getProduct() {
		return selectedProduct;
	}

	@Override
	public List<PGroup> getProducts() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public IProductGetter getTarget(){
		return this.target;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {

		Object o = e.getPath().getLastPathComponent();
		if(o instanceof Produkt){
			selectedProduct = (Produkt)o;
			updateButtons();
		}else{
			selectedProduct = null;
			updateButtons();
		}
	}
	
	private void updateButtons(){
		if(selectedProduct==null){
			okButton.setEnabled(false);
		}else{
			okButton.setEnabled(true);
		}
	}

}
