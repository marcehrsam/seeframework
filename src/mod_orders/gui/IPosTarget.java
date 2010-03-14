package mod_orders.gui;

import model_test.Position;

public interface IPosTarget {

	public boolean addPos(Position position);
	public boolean removePos(Position position);
	public Position getSelectedPosition();
	
}
