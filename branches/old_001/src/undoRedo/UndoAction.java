/*
 * 
 */
package undoRedo;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import base.Framework;


// TODO: Auto-generated Javadoc
/**
 * The Class UndoAction.
 */
public class UndoAction extends AbstractAction{
	
	public final String BACK = "Zurücksetzen";
	
	
	public UndoAction(){
		putValue(NAME, BACK);
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7499971568155575152L;

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		Framework.FW().getUndoManager().undo();
		Framework.FW().refreshUndoRedo();
	}

}
