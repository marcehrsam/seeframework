package mod_mainmenu.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import base.AbstractModule;
import base.Framework;

public class ACT_BT_KLICK_setActiveModule extends AbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;
	private AbstractModule module = null;
	
	public ACT_BT_KLICK_setActiveModule(AbstractModule mod){
		module = mod;
		if(module != null){
			putValue(AbstractAction.NAME, module.getMenuName());
		}
	}
	
	public void actionPerformed(ActionEvent arg0) {
		Framework.FW().setActiveModule(module);
	}

}
