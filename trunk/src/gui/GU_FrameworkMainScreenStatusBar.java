package gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class GU_FrameworkMainScreenStatusBar extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5916936190070105301L;
	
	@SuppressWarnings("unused")
	private GU_FrameworkMainScreen mainScreen = null;
	
	public GU_FrameworkMainScreenStatusBar(GU_FrameworkMainScreen ms){
		this.mainScreen = ms;
	}

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	

}
