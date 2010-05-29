/*
 * 
 */
package undoRedo;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import base.Framework;


// TODO: Auto-generated Javadoc
/**
 * The Class RedoAction.
 */
public class RedoAction extends AbstractAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3074741022490082014L;

	public RedoAction(){
		putValue(NAME, "Wiederholen");
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		Framework.FW().getUndoManager().redo();
		Framework.FW().refreshUndoRedo();
	}

}
