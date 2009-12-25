package main;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
	private int defaultScreenWidthMargin = 0;//50
	private int defaultScreenHeightMargin = 0;//37
	private Timer timer;
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
		
		
		JLabel text1 = new JLabel("SEE");
		text1.setFont(new Font("Arial",Font.BOLD , 30));
		text1.setBounds(10, 0, 300, 50);
		this.add(text1);
		
		JLabel text2 = new JLabel("Framework");
		text2.setFont(new Font("Arial",Font.BOLD , 18));
		text2.setBounds(70, 10, 300, 50);
		this.add(text2);
		
		JLabel loading = new JLabel("lädt...");
		loading.setBounds(0, 380, 400, 10);
		this.add(loading);
		
		Image img = new ImageIcon("d:/logo.png").getImage().getScaledInstance(400, 400, Image.SCALE_FAST);
		JLabel bild = new JLabel(new ImageIcon(img));
		bild.setBounds(0,0,400,400);
		this.add(bild);
		
		
		
		/*JButton bt = new JButton("Test");
		bt.setPreferredSize(new Dimension(100,100));
		bt.setBackground(Color.RED);
		this.add(bt, BorderLayout.SOUTH);
		*/
		
		setVisible(true);
		/*if (picture == null)
			picture = createImage(w, h);
		 */
		timer = new Timer();
		timer.schedule(new ExitTimerTask(this), millis);
		// TODO Auto-generated constructor stub
		
		
	}
	public static void main(String[] args) {
		new SplashScreen(new File("d:/logo.png"), 400, 400, 5000l);
	}
	/*
	   public void paint(Graphics g) {
		if (picture != null && capture != null) {
			capture.getGraphics().drawImage(picture,
					0 + defaultScreenWidthMargin / 2,
					0 + defaultScreenHeightMargin / 2, this);
			g.drawImage(capture, 0, 0, this);
			g.drawString("Test", 50, 50);
		}
	}
	*/
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
			// TODO Auto-generated method stub
			MainClass.main(null);
			frm.setVisible(false);
			frm.dispose();
			//System.exit(0);
			
		}
	}
}