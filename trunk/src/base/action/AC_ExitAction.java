package base.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class AC_ExitAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6356370844730280098L;
	
	public final String EXIT = "Beenden";
	
	public AC_ExitAction(){
		putValue(AbstractAction.NAME , EXIT);
	}

	public void actionPerformed(ActionEvent evt) {
		System.exit(0);
	}

}
