package mod_orders;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model_test.AbstractBeleg;

public class MD_OrdersTableModel implements TableModel {
	
	public static final int ANZ_COLUMNS = 5;
	
	private ArrayList<TableModelListener> lList = null;
	
	public MD_OrdersTableModel(){
		this.lList = new ArrayList<TableModelListener>();
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		lList.add(l);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		//TODO: Zahl anpassen, aktuell nur ANZ_COLUMNS Spalten
		if((columnIndex > -1)&&(columnIndex < ANZ_COLUMNS)){
			return String.class;
		}
		return null;
	}

	@Override
	public int getColumnCount() {
		return ANZ_COLUMNS;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex){
		case 0: return "Auftrag";
		case 1: return "Datum";
		case 2: return "Typ";
		case 3: return "Name";
		case 4: return "Beschreibung";
			default: return "unbenannt";
		}
	}

	@Override
	public int getRowCount() {
		return MD_Orders.getInstance().getAllBelege(Order.OFFEN).size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
		case 0: return MD_Orders.getInstance().getAllBelege(Order.OFFEN).get(rowIndex).getID();
		case 1: return MD_Orders.getInstance().getAllBelege(Order.OFFEN).get(rowIndex).getDate();
		case 2: return getType(rowIndex);
		case 3: return MD_Orders.getInstance().getAllBelege(Order.OFFEN).get(rowIndex).getKunde().getName();
		case 4: return "Hier steht die Beschreibung";
			default : return null;
		}
	}
	
	private String getType(int rowIndex){
		String ret = "";
		AbstractBeleg beleg = MD_Orders.getInstance().getAllBelege(Order.OFFEN).get(rowIndex);
		if(beleg instanceof Order){
			ret = "Angebot";
		}else{
			ret = "unbekannt";
		}
		return ret;
	}
	
//	private String getTest(int rowIndex){
//		ArrayList<Order> bel = MD_Orders.getInstance().getAllBelege(Order.OFFEN);
//		Order o = bel.get(rowIndex);
//		String id = o.getID();
//		return id;
//	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		lList.remove(l);

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch(columnIndex){
		case 0: MD_Orders.getInstance().getAllBelege(Order.OFFEN).get(rowIndex).setID((String)aValue); break;
		case 1: break;
		case 2: break;
		case 3: MD_Orders.getInstance().getAllBelege(Order.OFFEN).get(rowIndex).getKunde().setName((String)aValue);
		case 4: break;
			default : break;
		}
	}

	public ArrayList<AbstractBeleg> getBelege(){
		return MD_Orders.getInstance().getAllBelege(Order.OFFEN);
	}
	
	public AbstractBeleg getBelegWithRowNo(int rowIndex){
		return getBelege().get(rowIndex);
	}
	
}
