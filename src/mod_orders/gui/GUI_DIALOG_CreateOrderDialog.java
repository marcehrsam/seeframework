package mod_orders.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import mod_billing.action.ACT_BT_KLICK_CloseDialog;
import mod_customer.Customer;
import mod_orders.Order;
import mod_orders.action.ACT_BT_SaveNewOrder;
import model_test.Position;
import tools.TO_JFrame;

public class GUI_DIALOG_CreateOrderDialog extends JDialog implements IOrderHolder, IPosTarget, Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6829234352640045508L;

	private JPanel northPanel;
	private JPanel centerPanel;
	private JPanel southPanel;
	
	private JButton saveBtn;
	private JButton cancelButton;
	
	private Order newOrder = null;
	private GridBagConstraints gbc;
	
	private JTable orderTab = null;
	
	public GUI_DIALOG_CreateOrderDialog(){
		newOrder = new Order();
		//TODO: kunde wählen
		Customer cust1 = new Customer();
		newOrder.addObserver(this);
		newOrder.setKunde(cust1);
		InitLayout();
		
	}

	private void InitLayout() {
		setSize(new Dimension(700, 700));
		TO_JFrame.getInstance().centerJFrame(this);
		setTitle("Angebot anlegen");
		setLayout(new BorderLayout());
		
		northPanel = createNorthPanel();
		add(northPanel, BorderLayout.NORTH);
		
		centerPanel = new JPanel(new BorderLayout());
		add(centerPanel, BorderLayout.CENTER);
		
		JPanel centerNorth = new JPanel();
		centerPanel.add(centerNorth, BorderLayout.NORTH);
		
		JButton plusBt = new JButton(new ACT_BT_KLICK_AddPosToAbstractBeleg(this));
		plusBt.setPreferredSize(new Dimension(20, 20));
		centerNorth.add(plusBt);
		
		JButton minusBt = new JButton(new ACT_BT_KLICK_RemovePosFromAbstractBeleg(this));
		minusBt.setPreferredSize(new Dimension(20, 20));
		centerNorth.add(minusBt);
		
		JButton editBt = new JButton();
		editBt.setPreferredSize(new Dimension(20, 20));
		centerNorth.add(editBt);
		
		JScrollPane centerCenter = new JScrollPane();
		orderTab = new JTable(newOrder);
		centerCenter.setViewportView(orderTab);
		centerPanel.add(centerCenter, BorderLayout.CENTER);
		
		southPanel = new JPanel();
		saveBtn = new JButton(new ACT_BT_SaveNewOrder(this));
		cancelButton = new JButton(new ACT_BT_KLICK_CloseDialog(this));
		
		southPanel.add(saveBtn);
		southPanel.add(cancelButton);
		this.add(southPanel, BorderLayout.SOUTH);
		
		setAlwaysOnTop(true);
		setVisible(true);
	}

	private JPanel createNorthPanel() {
		northPanel = new JPanel();
		northPanel.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 1;
		gbc.gridy = 1;
		Insets insets = new Insets(5, 20, 0, 0);
		gbc.insets = insets;
		
		gbc.gridy++;
		JLabel jlAllFields = new JLabel("Kundennummer");
		northPanel.add(jlAllFields, gbc);
		gbc.gridx++;
		JTextField jtf = new JTextField(10);
		northPanel.add(jtf, gbc);
		gbc.gridx++;
		JButton sBt = new JButton("Go");
		northPanel.add(sBt, gbc);
		gbc.gridx--;
		gbc.gridx--;
		
		
		gbc.gridy++;
		JLabel jlKdNr = new JLabel("Vorname");
		northPanel.add(jlKdNr, gbc);
		gbc.gridx++;
		JTextField jtfKd = new JTextField(15);
		jtfKd.setEditable(false);
		northPanel.add(jtfKd, gbc);
		gbc.gridx--;
		
		gbc.gridy++;
		JLabel jlName = new JLabel("Name");
		northPanel.add(jlName, gbc);
		gbc.gridx++;
		JTextField jtfName = new JTextField(15);
		jtfName.setEditable(false);
		northPanel.add(jtfName, gbc);
		gbc.gridx--;
		
		gbc.gridy++;
		JLabel jlPlz = new JLabel("PLZ");
		northPanel.add(jlPlz, gbc);
		gbc.gridx++;
		JTextField jtfPlz = new JTextField(5);
		jtfPlz.setEditable(false);
		northPanel.add(jtfPlz, gbc);
		gbc.gridx--;
		
		gbc.gridy++;
		JLabel jlAddress = new JLabel("Adresse");
		northPanel.add(jlAddress, gbc);
		gbc.gridx++;
		JTextField jtfAddr = new JTextField(25);
		jtfAddr.setEditable(false);
		northPanel.add(jtfAddr, gbc);
		gbc.gridx--;
		
//		gbc.gridy++;
//		JLabel jlOrderState = new JLabel("Auftragsstatus");
//		northPanel.add(jlOrderState, gbc);
//		gbc.gridx++;
//		Object[] listData = {"", "offen", "in Bearb.", "erledigt"};
//		JComboBox jlfOrder = new JComboBox(listData);
//		northPanel.add(jlfOrder, gbc);
//		gbc.gridx--;
		
//		gbc.gridy++;
//		JCheckBox jcOpenBill = new JCheckBox("mit offenen Rechnungen");
//		northPanel.add(jcOpenBill, gbc);
		
		return northPanel;
	}

	@Override
	public Order getOrder() {
		return newOrder;
	}

	@Override
	public void closeDialog() {
		this.dispose();
	}

	@Override
	public boolean addPos(Position position) {
		newOrder.addPos(position);
		return true;
	}

	@Override
	public boolean removePos(Position position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Position getSelectedPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(orderTab!=null){
			orderTab.setVisible(false);
			orderTab.setVisible(true);
		}
		
		
	}
	
}
