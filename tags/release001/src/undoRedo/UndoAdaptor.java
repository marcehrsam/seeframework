/*
 * 
 */
package undoRedo;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoableEdit;

import base.Framework;

// TODO: Auto-generated Javadoc
/**
 * The Class UndoAdaptor.
 */
public class UndoAdaptor implements UndoableEditListener{

	/* (non-Javadoc)
	 * @see javax.swing.event.UndoableEditListener#undoableEditHappened(javax.swing.event.UndoableEditEvent)
	 */
	public void undoableEditHappened(UndoableEditEvent evt) {
		UndoableEdit edit = evt.getEdit();
		Framework.FW().getUndoManager().addEdit(edit);
		Framework.FW().refreshUndoRedo();
		
		
	}

}
