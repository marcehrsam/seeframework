package gui;

import java.awt.Graphics;
import java.awt.Image;

public class MyImagePanel extends MyPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4788139225271794034L;
	
	private Image img = null;
	
	public MyImagePanel(Image image){
		this.img = image;
		repaint();
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	
}
