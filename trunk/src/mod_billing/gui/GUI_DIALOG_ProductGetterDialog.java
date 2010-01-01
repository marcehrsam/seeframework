package mod_billing.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import mod_billing.action.ACT_BT_KLICK_CloseDialog;
import mod_billing.action.ACT_BT_KLICK_OK_AddProductGetterDialog;
import mod_products.IProductGetter;
import mod_products.IProductSource;
import model_test.Produkt;

import tools.TO_JFrame;

public class GUI_DIALOG_ProductGetterDialog extends JDialog implements IProductSource{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2590581149783445487L;
	
	private final String TITLE = "Artikel hinzufügen";
	
	private IProductGetter target = null;
	
	public GUI_DIALOG_ProductGetterDialog(IProductGetter target){
		this.target = target;
		setSize(new Dimension(500, 400));
		setTitle(TITLE);
		setLayout(new BorderLayout());
		TO_JFrame.getInstance().centerJFrame(this);
		
		InitLayout();
		setModal(true);
		setAlwaysOnTop(true);
		setVisible(true);
	}

	private void InitLayout() {
		JPanel northPanel = new JPanel();
		getContentPane().add(northPanel, BorderLayout.NORTH);
		
		JPanel southPanel = new JPanel();
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		JButton okButton = new JButton(new ACT_BT_KLICK_OK_AddProductGetterDialog(this));
		southPanel.add(okButton);
		
		JButton cancelButton = new JButton(new ACT_BT_KLICK_CloseDialog(this));
		southPanel.add(cancelButton);
		
	}

	@Override
	public int getCount() {
		//TODO
		return 1;
	}

	@Override
	public Produkt getProduct() {
		// TODO Auto-generated method stub
		return new Produkt("Testprodukt");
	}

	@Override
	public List<Produkt> getProducts() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public IProductGetter getTarget(){
		return this.target;
	}
	

}
