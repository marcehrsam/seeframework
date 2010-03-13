package mod_orders.gui;

import gui.MyPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;

import mod_orders.MD_Orders;
import model_test.Selektion;

public class GU_MP_OrdersStartScreen extends MyPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9115370153035676377L;

	public GU_MP_OrdersStartScreen(){
		InitLayout();
	}
	
	private void InitLayout() {
		setLayout(new BorderLayout());
		
		MyPanel northPanel = new Selektion();
		add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel(new BorderLayout());
		
		//hier die buttons links
		JPanel centerLeftPanel = new JPanel();
		centerLeftPanel.setBorder(new EtchedBorder());
		JButton bt = new JButton("PushMe");
		centerLeftPanel.add(bt);
		centerPanel.add(centerLeftPanel, BorderLayout.WEST);
		//hier die Vorgangstabelle
		JTable tab = new JTable(MD_Orders.getInstance().getTableModel());
		tab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for(int i=0; i<5; i++){
			tab.getColumnModel().getColumn(i).setResizable(false);
		}
		tab.getColumnModel().getColumn(0).setMinWidth(150);
		tab.getColumnModel().getColumn(0).setMaxWidth(150);
		tab.getColumnModel().getColumn(1).setMinWidth(100);
		tab.getColumnModel().getColumn(1).setMaxWidth(100);
		tab.getColumnModel().getColumn(2).setMinWidth(100);
		tab.getColumnModel().getColumn(2).setMaxWidth(100);
		tab.getColumnModel().getColumn(3).setMinWidth(200);
		tab.getColumnModel().getColumn(3).setMaxWidth(200);
		tab.getColumnModel().getColumn(4).setMinWidth(400);
		tab.getColumnModel().getColumn(4).setMaxWidth(400);
		JScrollPane scroller = new JScrollPane();
		scroller.setViewportView(tab);
		centerPanel.add(scroller, BorderLayout.CENTER);
		
		centerPanel.setBackground(Color.red);
		add(centerPanel, BorderLayout.CENTER);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
