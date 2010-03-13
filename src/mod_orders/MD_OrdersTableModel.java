package mod_orders;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class MD_OrdersTableModel implements TableModel {
	
	private MD_Orders parent = null;
	public static final int ANZ_COLUMNS = 5;
	
	private ArrayList<TableModelListener> lList = null;
	
	public MD_OrdersTableModel(MD_Orders parent){
		this.parent = parent;
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
		// TODO Anzahl selektierter Spalten angeben
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		lList.remove(l);

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

}
