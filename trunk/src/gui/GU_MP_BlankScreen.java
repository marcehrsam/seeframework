package gui;

import java.util.Observable;

import javax.swing.JLabel;

public class GU_MP_BlankScreen extends MyPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7655496856842145503L;
	
	public GU_MP_BlankScreen(){
		this.add(new JLabel("Na, was haben wir wieder Falsch gemacht ;-)"));
	}

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	
}
