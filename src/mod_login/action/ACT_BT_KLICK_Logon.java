package mod_login.action;

import gui.MyPanel;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import mod_login.MD_Login;
import mod_login.gui.GU_MP_LoginStartScreen;
import mod_mainmenu.MD_Main;
import mod_orders.MD_Orders;

import base.Framework;
import base.StateMan;

public class ACT_BT_KLICK_Logon extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 122015717812282881L;
	
	public final String LOGON = "Login";
	
	private GU_MP_LoginStartScreen screen = null;
	
	public ACT_BT_KLICK_Logon(GU_MP_LoginStartScreen screen){
		putValue(AbstractAction.NAME, LOGON);
		this.screen = screen;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(screen.checkInput()){
			boolean loginSuccesful = MD_Login.getInstance().login(screen.getUser());
			if(loginSuccesful){
				Framework.FW().setState(StateMan.SM().getState(StateMan.LOGIN_OK));
				//hier Variationspunkt!!!
				Framework.FW().setActiveModule(MD_Main.getInstance());
			}else{
				Framework.FW().setState(StateMan.SM().getState(StateMan.TWICE_LOGGED_IN));
			}
		}else{
			Framework.FW().setState(StateMan.SM().getState(StateMan.LOGIN_ERR));
		}
	}

}
