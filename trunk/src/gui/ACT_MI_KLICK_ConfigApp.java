package gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

public class ACT_MI_KLICK_ConfigApp extends AbstractAction {

	public final String APP = "Programm";
	
	public ACT_MI_KLICK_ConfigApp(){
		putValue(AbstractAction.NAME, APP);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		GU_ConfigDialog_App dialog = new GU_ConfigDialog_App();

	}

}
