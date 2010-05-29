package gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class ACT_MI_KLICK_ConfigApp extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4251001180568365087L;
	public final String APP = "Programm";
	
	public ACT_MI_KLICK_ConfigApp(){
		putValue(AbstractAction.NAME, APP);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		@SuppressWarnings("unused")
		GU_ConfigDialog_App dialog = new GU_ConfigDialog_App();

	}

}
