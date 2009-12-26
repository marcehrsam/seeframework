package mod_billing.gui;

import gui.MyPanel;

import java.awt.BorderLayout;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JPanel;

import mod_billing.MD_Billing;
import mod_billing.action.ACT_MI_KLICK_NewBill;
import mod_mainmenu.MD_Main;
import mod_mainmenu.action.ACT_BT_KLICK_setActiveModule;

public class GUI_DIALOG_BillingStartScreen extends MyPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7035486821313044098L;

	public GUI_DIALOG_BillingStartScreen(){
		InitLayout();
		MD_Billing.getInstance().addObserver(this);
	}
	
	private void InitLayout() {
		setLayout(new BorderLayout());
		
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		add(southPanel, BorderLayout.SOUTH);
		
		JButton neueRechnungButton = new JButton(new ACT_MI_KLICK_NewBill());
		southPanel.add(neueRechnungButton);
		
		JButton aendernButton = new JButton("Ändern");
		southPanel.add(aendernButton);
		
		JButton loeschenButton = new JButton("Löschen");
		southPanel.add(loeschenButton);
		
		JButton zurueckButton = new JButton(new ACT_BT_KLICK_setActiveModule(MD_Main.getInstance()));
		zurueckButton.setText("Zurück");
		southPanel.add(zurueckButton);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
