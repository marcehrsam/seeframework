package mod_products.gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import model_test.PGroup;

public class ProductGroupPanel extends JPanel {

	public ProductGroupPanel(PGroup selection) {
		// TODO Auto-generated constructor stub
		setBackground(Color.BLACK);
		add(new JButton("Gruppe"));
	}

}
