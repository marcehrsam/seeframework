package mod_orders;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import mod_customer.Customer;
import model_test.AbstractBeleg;

public class Order extends AbstractBeleg implements TableModel{

	private ArrayList<AbstractBeleg> belege;
	private ArrayList<TableModelListener> listenerList = null;
	public static final int ANZ_COLUMNS = 5; 
		
	public Order(Customer kunde){
		super("xxxxxx");
		setKunde(kunde);
		Init();
	}
	
	public Order(){
		super("xxxxxx");
		Init();
	}

	private void Init(){
		belege = new ArrayList<AbstractBeleg>();
		listenerList = new ArrayList<TableModelListener>();
		
	}
	
	public ArrayList<AbstractBeleg> getBelege() {
		return belege;
	}

	public void setBelege(ArrayList<AbstractBeleg> belege) {
		this.belege = belege;
	}

	@Override
	public boolean canAddBeleg() {
		return true;
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		if(l!=null)listenerList.add(l);
	}

	@Override
	public Class<?> getColumnClass(int columnId) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return ANZ_COLUMNS;
	}

	@Override
	public String getColumnName(int columnId) {
		switch(columnId){
		case 0: return "Status";
		case 1: return "Art";
		case 2: return "Anz";
		default: return "unbenannt";
		}
	}

	@Override
	public int getRowCount() {
		return positionen.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		//TODO
		return null;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		if(l!=null)listenerList.remove(l);
		
	}

	@Override
	public void setValueAt(Object o, int row, int column) {
		// TODO Auto-generated method stub
		
	}
	
}
