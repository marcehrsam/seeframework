package gui;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public abstract class AbstractPanel extends JPanel{
	
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
