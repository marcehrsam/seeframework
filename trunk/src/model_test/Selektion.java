package model_test;

import gui.MyPanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class Selektion extends MyPanel {

	private GridBagConstraints gbc = null;
	
	public Selektion(){
		InitLayout();
		setBorder(new EtchedBorder());
	}
	
	private void InitLayout() {
		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 1;
		gbc.gridy = 1;
		Insets insets = new Insets(5, 20, 0, 0);
		gbc.insets = insets;
		
		gbc.gridy++;
		JCheckBox jlAllFields = new JCheckBox("Alle Felder");
		add(jlAllFields, gbc);
		gbc.gridx++;
		JTextField jtf = new JTextField(25);
		add(jtf, gbc);
		gbc.gridx--;
		
		
		gbc.gridy++;
		JLabel jlKdNr = new JLabel("Kundennummer");
		add(jlKdNr, gbc);
		gbc.gridx++;
		JTextField jtfKd = new JTextField(10);
		add(jtfKd, gbc);
		gbc.gridx--;
		
		gbc.gridy++;
		JLabel jlName = new JLabel("Name");
		add(jlName, gbc);
		gbc.gridx++;
		JTextField jtfName = new JTextField(20);
		add(jtfName, gbc);
		gbc.gridx--;
		
		gbc.gridy++;
		JLabel jlPlz = new JLabel("PLZ");
		add(jlPlz, gbc);
		gbc.gridx++;
		JTextField jtfPlz = new JTextField(5);
		add(jtfPlz, gbc);
		gbc.gridx--;
		
		gbc.gridy++;
		JLabel jlAddress = new JLabel("Adresse");
		add(jlAddress, gbc);
		gbc.gridx++;
		JTextField jtfAddr = new JTextField(25);
		add(jtfAddr, gbc);
		gbc.gridx--;
		
		gbc.gridy++;
		JLabel jlOrderState = new JLabel("Auftragsstatus");
		add(jlOrderState, gbc);
		gbc.gridx++;
		Object[] listData = {"", "offen", "in Bearb.", "erledigt"};
		JComboBox jlfOrder = new JComboBox(listData);
		add(jlfOrder, gbc);
		gbc.gridx--;
		
		gbc.gridy++;
		JCheckBox jcOpenBill = new JCheckBox("mit offenen Rechnungen");
		add(jcOpenBill, gbc);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
