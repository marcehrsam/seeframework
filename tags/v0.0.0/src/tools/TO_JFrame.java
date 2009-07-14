package tools;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

public class TO_JFrame {
	
	private static TO_JFrame instance = null;
	
	private TO_JFrame(){
		
	}
	
	public static TO_JFrame getInstance(){
		if(instance==null) instance = new TO_JFrame();
		return instance;
	}
	
	public void centerJFrame(Container container){
		Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        container.setLocation((int)(screenDimension.getWidth()-container.getWidth())/2,
                        (int)(screenDimension.getHeight()-container.getHeight())/2); 
	}

}
