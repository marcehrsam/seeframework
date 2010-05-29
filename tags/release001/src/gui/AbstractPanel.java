package gui;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public abstract class AbstractPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6798490335631726443L;

	public AbstractPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	public AbstractPanel(LayoutManager layout) {
		super(layout);
	}

	public AbstractPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	public AbstractPanel() {
		super();
	}

	public abstract boolean checkInput();

}
