package gui;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class MyButton extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -472082404721498434L;

	public MyButton(Action action){
		super(action);
	}
	
	public MyButton(Icon icon){
		super(icon);
	}
	
	public MyButton(String text){
		super(text);
	}
	
	public MyButton(String text, Icon icon){
		super(text, icon);	
	}
	
	public MyButton(){
		super();
	}

}
