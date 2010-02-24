package mod_products.gui;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProductLogoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7043671605271193189L;

	public ProductLogoPanel(){
		setLayout(new BorderLayout());
		
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("logo.png"));
		icon.setImage(icon.getImage().getScaledInstance(200, 200, Image.SCALE_FAST));
		JLabel logoLab = new JLabel(icon);
		add(logoLab, BorderLayout.CENTER);
	}
	
}
