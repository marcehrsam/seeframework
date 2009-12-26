package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/*
 * Created on 20.02.2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class SplashScreen extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3972149106050237914L;
	
	private int defaultScreenWidthMargin = 0;//50
	private int defaultScreenHeightMargin = 0;//37
	private Timer timer;
	
	final JProgressBar progBar;
	
	/**
	 * @param file
	 */
	public SplashScreen(File file, int w, int h, long millis) {
		setLayout(null);
		int newW = w + defaultScreenWidthMargin;
		int newH = h + defaultScreenHeightMargin;
		setSize(newW, newH);
		setUndecorated(true);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int frmX = ((int) d.getWidth() - (w + defaultScreenWidthMargin)) / 2;
		int frmY = ((int) d.getHeight() - (h + defaultScreenHeightMargin)) / 2;
		setLocation(frmX, frmY);
		
		this.setBackground(Color.WHITE);
		
		JLabel text1 = new JLabel("SEE");
		text1.setFont(new Font("Arial",Font.BOLD , 30));
		text1.setBounds(10, 0, 300, 50);
		this.add(text1);
		
		JLabel text2 = new JLabel("Framework");
		text2.setFont(new Font("Arial",Font.BOLD , 18));
		text2.setBounds(70, 10, 300, 50);
		this.add(text2);
		
		JLabel text3 = new JLabel("(Alpha 0)");
		text3.setFont(new Font("Arial",Font.BOLD , 10));
		text3.setForeground(Color.RED);
		text3.setBounds(170, 10, 300, 50);
		this.add(text3);
		
		/*
		 JLabel loading = new JLabel("lädt...");
		 
		loading.setBounds(0, 380, 400, 10);
		this.add(loading);
		*/
		
		
		
		progBar = new JProgressBar();
		progBar.setBounds(0, 380, 400, 20);
		this.add(progBar);
		
		Image img = new ImageIcon("d:/logo.png").getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
		JLabel bild = new JLabel(new ImageIcon(img));
		bild.setBackground(Color.WHITE);
		bild.setBounds(0,0,400,400);
		this.add(bild);
				
		
		  this.setAlwaysOnTop(true);
		  
		
		setVisible(true);
		timer = new Timer();
		timer.schedule(new ExitTimerTask(this), millis);		
		
	}
	public static void main(String[] args) {
		new SplashScreen(new File("d:/logo.png"), 400, 400, 5000l);
		
	}
	
	class ExitTimerTask extends TimerTask {
		private JFrame frm;
		public ExitTimerTask(JFrame frm) {
			this.frm = frm;
		}
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.TimerTask#run()
		 */
		public void run() {
			Runnable runnable = new Runnable() {
			    public void run() {
			    	for (int i = 0; i < 100; i++) {
			        // Als Beispiel für eine
			        // rechenintensive Operation
			        try { Thread.sleep(10); } 
			        catch (InterruptedException ex) {}
			        progBar.setValue(i);
			     }
			  }};
			//runnable.run();
			//Thread t1 = new Thread(runnable);
			//t1.run();
			MainClass.main(null);
			
			frm.setVisible(false);
			frm.dispose();
			//System.exit(0);
			
		}
	}
}