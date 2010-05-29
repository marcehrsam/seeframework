package mod_orders.gui;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyRenderer extends DefaultTableCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4187512491293606727L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		
	}

	@Override
	protected void setValue(Object value) {
		if(value instanceof String){
			if((value.equals("xxxxxx"))||(value.equals("n/v"))){
				ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("remove-icon.png"));
				setIcon(icon);
				//setHorizontalAlignment(JLabel.CENTER);
				setText((String)value);
				
			}else{
				ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("accept-icon.png"));
				setIcon(icon);
				setText((String)value);
			}
		}
	}

	
	
	
}
