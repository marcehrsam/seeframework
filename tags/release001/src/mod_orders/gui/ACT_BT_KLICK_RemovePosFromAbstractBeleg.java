package mod_orders.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

public class ACT_BT_KLICK_RemovePosFromAbstractBeleg extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1955333903695015609L;

	private IPosTarget target = null;
	
	public ACT_BT_KLICK_RemovePosFromAbstractBeleg(IPosTarget target){
		this.target = target;
		putValue(AbstractAction.SMALL_ICON, new ImageIcon(ClassLoader.getSystemResource("minus.png")));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		target.removePos(target.getSelectedPosition());
	}

}
