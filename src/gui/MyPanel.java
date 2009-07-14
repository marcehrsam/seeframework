package gui;

import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class MyPanel extends AbstractPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5887374153345051458L;

	public MyPanel(boolean isDoubleBuffered){
		super(isDoubleBuffered);
	}
	
	public MyPanel(LayoutManager layout){
		super(layout);
	}
	
	public MyPanel(LayoutManager layout, boolean  isDoubleBuffered){
		super(layout, isDoubleBuffered);
	}
	
	public MyPanel(){
		super();
	}

	@Override
	public boolean checkInput() {
		return false;
	}
	
}
