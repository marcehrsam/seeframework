package gui;

import javax.swing.JTextField;
import javax.swing.text.Document;

public class MyTextField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6935041132845563109L;

	public MyTextField(int columns){
		super(columns);
	}
	
	public MyTextField(String text){
		super(text);
	}
	
	public MyTextField(String text, int columns){
		super(text, columns);
	}
	
	public MyTextField(Document doc, String text, int columns){
		super(doc, text, columns);
	}
	
	public MyTextField(){
		super();
	}
	
}
