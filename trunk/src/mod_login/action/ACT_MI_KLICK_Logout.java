package mod_login.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import mod_login.MD_Login;

import base.Framework;
import base.StateMan;

public class ACT_MI_KLICK_Logout extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6450148400149234639L;
	
	public final String LOGOUT = "Logout";
	
	public ACT_MI_KLICK_Logout(){
		putValue(AbstractAction.NAME, LOGOUT);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		boolean ok = MD_Login.getInstance().logout();
		if(!ok){
			Framework.FW().setState(StateMan.SM().getState(StateMan.LOGOUT_ERR));
		}else{
			Framework.FW().setState(StateMan.SM().getState(StateMan.INITOK));
		}
	}

}
