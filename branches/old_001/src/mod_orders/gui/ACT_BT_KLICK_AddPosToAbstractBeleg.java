package mod_orders.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import model_test.Position;
import model_test.Produkt;

public class ACT_BT_KLICK_AddPosToAbstractBeleg extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1561433639935315292L;

	private IPosTarget target = null;
	
	public ACT_BT_KLICK_AddPosToAbstractBeleg(IPosTarget target){
		this.target = target;
		putValue(AbstractAction.SMALL_ICON, new ImageIcon(ClassLoader.getSystemResource("plus.png")));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Position pos = new Position(new Produkt(), 1);
		target.addPos(pos);
	}

}
