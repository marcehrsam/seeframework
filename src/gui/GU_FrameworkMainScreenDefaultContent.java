package gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class GU_FrameworkMainScreenDefaultContent extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1171186630502448393L;
	
	private GU_FrameworkMainScreen mainScreen = null;
	
	public GU_FrameworkMainScreenDefaultContent(GU_FrameworkMainScreen ms){
		this.mainScreen = ms;
		setBackground(Color.BLUE);
		
	}

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
