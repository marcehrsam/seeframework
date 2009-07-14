package mod_login.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import mod_mainmenu.MD_Main;
import base.Framework;
import base.StateMan;

public class ACT_MI_KLICK_AsAdmin extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5408176640509127164L;
	
	public final String ASADMIN = "Als Administrator anmelden";
	
	public ACT_MI_KLICK_AsAdmin(){
		putValue(AbstractAction.NAME, ASADMIN);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		//TODO: Masterlogin deaktivieren
		String pw = JOptionPane.showInputDialog("Bitte geben Sie das Masterkennwort ein:");
		if(pw==null){
			Framework.FW().setState(StateMan.SM().getState(StateMan.LOGIN_ERR));
			return;
		}
		if(pw.equals("")){
			Framework.FW().setState(StateMan.SM().getState(StateMan.LOGIN_OK));
			//hier Variationspunkt!!!
			Framework.FW().setActiveModule(MD_Main.getInstance());
		}
	}

}
