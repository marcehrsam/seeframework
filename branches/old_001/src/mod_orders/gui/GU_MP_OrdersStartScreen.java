package mod_orders.gui;

import gui.MyPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mod_orders.MD_Orders;
import mod_orders.MD_OrdersTableModel;
import mod_orders.action.ACT_TR_OpenNewOrderForm;
import mod_orders.action.ROOT_OrderTreeActions;
import model_test.AbstractBeleg;
import model_test.Selektion;

public class GU_MP_OrdersStartScreen extends MyPanel implements Observer, IOrderHolder, ListSelectionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9115370153035676377L;

	private JTable tab;
	
	public GU_MP_OrdersStartScreen(){
		InitLayout();
		MD_Orders.getInstance().addObserver(this);
	}
	
	private void InitLayout() {
		setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel(new BorderLayout());
		add(northPanel, BorderLayout.NORTH);
		
		JPanel northLeftPanel = new Selektion();
		northPanel.add(northLeftPanel, BorderLayout.WEST);
		
		JScrollPane treeScroller = new JScrollPane();
		JTree actionTree = new JTree(new ROOT_OrderTreeActions());
		treeScroller.setViewportView(actionTree);
		treeScroller.setPreferredSize(new Dimension(10, 10));
		northPanel.add(treeScroller, BorderLayout.CENTER);
		
		
		JPanel centerPanel = new JPanel(new BorderLayout());
		
		//hier die buttons links
		JPanel centerLeftPanel = new JPanel();
		centerLeftPanel.setBorder(new EtchedBorder());
		
		JButton bt = new JButton(new ACT_TR_OpenNewOrderForm());
		bt.setText("PushMe");
		centerLeftPanel.add(bt);
		
//		JButton calcBt = new JButton(new ACT_BT_KLICK_CalcOrder(this));
//		centerLeftPanel.add(calcBt);
		
		centerPanel.add(centerLeftPanel, BorderLayout.WEST);
		//hier die Vorgangstabelle
		if(!MD_Orders.getInstance().tableModelInitialisiert){
			MD_Orders.getInstance().createTableModel();
		}
		tab = new JTable(MD_Orders.getInstance().getTableModel());
		
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
		//tab.setDefaultRenderer(Object.class, new MyRenderer());
		tab.getColumnModel().getColumn(0).setCellRenderer(new MyRenderer());
		//tab.getColumnModel().getColumn(1).setCellRenderer(new MyRenderer());
		//tab.getColumnModel().getColumn(2).setCellRenderer(new MyRenderer());

		
		JScrollPane scroller = new JScrollPane();
		scroller.setViewportView(tab);
		centerPanel.add(scroller, BorderLayout.CENTER);
		
		centerPanel.setBackground(Color.red);
		add(centerPanel, BorderLayout.CENTER);
	}

	@Override
	public void update(Observable obs, Object o) {
		//tab.invalidate();
		tab.setVisible(false);
		tab.setVisible(true);
		
		
	}

	@Override
	public void closeDialog() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractBeleg getOrder() {
		int row = tab.getSelectedRow();
		return ((MD_OrdersTableModel)MD_Orders.getInstance().getTableModel()).getBelegWithRowNo(row);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
	
	}

}
