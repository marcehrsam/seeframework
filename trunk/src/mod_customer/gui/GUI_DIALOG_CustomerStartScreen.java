package mod_customer.gui;

import gui.MyPanel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mod_customer.action.ACT_BT_KLICK_CreateCustomer;

public class GUI_DIALOG_CustomerStartScreen extends MyPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2883181473109486922L;

	private GridBagConstraints gbc = null;
	private JButton btOverView = null;
	private JButton btSearch = null;
	private JButton btAdd = null;
	private JButton btManage = null;
	private JButton btDbMan = null;
	
	public GUI_DIALOG_CustomerStartScreen(){
		InitLayout();
	}
	
	private void InitLayout() {
		
		setLayout(new BorderLayout());
		
		JLabel northLabel = new JLabel("Kundenorganisation");
		add(northLabel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		
		centerPanel.setLayout(new GridBagLayout());
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		btOverView = new JButton("Übersicht");
		btSearch = new JButton("Suche starten");
		btAdd = new JButton(new ACT_BT_KLICK_CreateCustomer(null));
		btManage = new JButton("Verwaltung");
		btDbMan = new JButton("DB Verwaltung");
		
		gbc.gridy++;
		centerPanel.add(btOverView, gbc);
		gbc.gridx++;
		centerPanel.add(btSearch, gbc);
		gbc.gridx--;
		gbc.gridy++;
		centerPanel.add(btAdd, gbc);
		gbc.gridx++;
		centerPanel.add(btManage, gbc);
		gbc.gridx--;
		gbc.gridy++;
		centerPanel.add(btDbMan, gbc);
		
		
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
