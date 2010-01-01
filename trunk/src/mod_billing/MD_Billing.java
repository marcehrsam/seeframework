package mod_billing;

import gui.MyPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import mod_billing.action.ACT_BT_KLICK_START_EditBill;
import mod_billing.action.ACT_MI_KLICK_ChangeOrder;
import mod_billing.action.ACT_MI_KLICK_CreateOrder;
import mod_billing.action.ACT_MI_KLICK_NewBill;
import mod_billing.action.ACT_MI_KLICK_ShowOrders;
import mod_billing.gui.GUI_DIALOG_BillingStartScreen;
import model_test.Rechnung;
import base.AbstractModule;

/**
 * Module for Creating bills.
 * 
 * @author Marc_2
 *
 */
public class MD_Billing extends AbstractModule implements TableModel, MouseListener, KeyListener, Observer{

	private static MD_Billing instance = null;
	
	private List<Rechnung> rechnungen = null;
	
	public final String NEWBILL = "Neue Rechnung anlegen";
	public final String BILL = "Rechnung";
		
	protected JMenu menu = null;
	
	private List<TableModelListener> tableModelListenerList = null;
	
	private MD_Billing(){
		super();
		rechnungen = new ArrayList<Rechnung>();
		tableModelListenerList = new ArrayList<TableModelListener>();
	}
	
	public static MD_Billing getInstance(){
		if(instance==null)instance = new MD_Billing();
		return instance;
	}
	
	public JMenu getMenu() {
		if(menu==null)createMenu();		
		return menu;
	}
	
	public void createMenu(){
		menu = new JMenu(getMenuName());
		//über Actions realisieren
		JMenuItem miShowOrders = new JMenuItem(new ACT_MI_KLICK_ShowOrders());
		menu.add(miShowOrders);
		JMenuItem miCreateOrder = new JMenuItem(new ACT_MI_KLICK_CreateOrder());
		menu.add(miCreateOrder);
		JMenuItem miChangeOrder = new JMenuItem(new ACT_MI_KLICK_ChangeOrder());
		menu.add(miChangeOrder);
		//JMenuItem miAddMDSEBilling = new JMenuItem(new AddMDSEBillingToFW());
		//menu.add(miAddMDSEBilling);
		JMenuItem miNewBill = new JMenuItem(new ACT_MI_KLICK_NewBill());
		menu.add(miNewBill);
	}

	public String getMenuName(){
		return BILL;
	}

	@Override
	public boolean stopAllActions() {
		//TODO
		return true;
	}

	@Override
	public JPanel getContentScreen() {
		
		return new GUI_DIALOG_BillingStartScreen();
	}

	@Override
	public void setContentScreen(MyPanel screen) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean readConfigFile() {
		//TODO
		return false;
	}

	//TableModel
	
	@Override
	public void addTableModelListener(TableModelListener tml) {
		tableModelListenerList.add(tml);
		
	}

	@Override
	public Class<?> getColumnClass(int column) {
		switch(column){
		case 0: return String.class;//Name
		case 1: return String.class;//Re.-Nr
		case 2: return Double.class;//Betrag
		case 3: return String.class;//gebucht
		default: return String.class;
		}
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(int column) {
		switch(column){
		case 0: return "Kunde";
		case 1: return "Rechnungsnummer";
		case 2: return "Betrag";
		case 3: return "gebucht";
		default: return "UNBENANNT";
		}
	}

	@Override
	public int getRowCount() {
		return rechnungen.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Rechnung re = null;
		try{
			re = rechnungen.get(row);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		if(re == null){
			tools.Debug.out("[MD_Billing ERR01] re==null");
			return null;
		}

		switch(column){
		case 0: return re.getName();
		case 1: return re.getNummer();
		case 2: return 0;
		case 3: return "nein";
		default: return null;
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener tml) {
		tableModelListenerList.remove(tml);
	}

	@Override
	public void setValueAt(Object value, int row, int column) {
		// TODO Auto-generated method stub
		
	}

	public boolean addRechnung(Rechnung re){
		for(int i=0; i<40; i++){
			rechnungen.add(re);
		}
		boolean check = rechnungen.add(re);
		re.addObserver(this);
		for(TableModelListener l : tableModelListenerList){
			l.tableChanged(null);
		}
		return check;
	}
	
	public boolean removeRechnung(Rechnung re){
		boolean check = rechnungen.remove(re);
		re.deleteObserver(this);
		return check;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		//keine Aktion	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// keine Aktion
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//keine Aktion
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// keine Aktion
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getClickCount()==2){
			JButton tempBtn = new JButton(new ACT_BT_KLICK_START_EditBill(getRechnung(((JTable)e.getSource()).getSelectedRow())));
			tempBtn.doClick();
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// keine Aktion
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			JButton tempBtn = new JButton(new ACT_BT_KLICK_START_EditBill(getRechnung(((JTable)e.getSource()).getSelectedRow())));
			tempBtn.doClick();
		}
	}

	private Rechnung getRechnung(int reNoInList) {
		return rechnungen.get(reNoInList);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// keine Aktion
	}

	@Override
	public void update(Observable o, Object arg) {
		for(TableModelListener l : tableModelListenerList){
			l.tableChanged(null);
		}
		
	}
	
}
