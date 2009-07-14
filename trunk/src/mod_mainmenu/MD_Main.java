package mod_mainmenu;

import gui.MyPanel;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;

import mod_mainmenu.action.ACT_BT_KLICK_setActiveModule;
import base.AbstractModule;
import base.Framework;

public class MD_Main extends AbstractModule {

	private static MD_Main instance = null;
	
	private MD_Main() {
		// TODO Auto-generated constructor stub
	}
	
	public static MD_Main getInstance(){
		if(instance==null)instance = new MD_Main();
		return instance;
	}
	
	@Override
	public void createMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	public MyPanel getContentScreen() {
		return getMainScreen();
	}

	@Override
	public JMenu getMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMenuName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setContentScreen() {
		

	}

	@Override
	public boolean stopAllActions() {
		// TODO Auto-generated method stub
		return true;
	}
	
	//Hauptmenü
	private MyPanel getMainScreen(){
		MyPanel ret = new MyPanel();
		//für jedes (registrierte) Modul werden buttons erzeugt.
		for(AbstractModule mod : Framework.FW().getModules()){
			JButton btn = new JButton(new ACT_BT_KLICK_setActiveModule(mod));
            ImageIcon icon = new ImageIcon("logo2.png");
            icon.setImage(icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			btn.setIcon(icon);
			ret.add(btn);
		}
		
		return ret;
	}

}
