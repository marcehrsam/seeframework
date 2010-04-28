package mod_mainmenu;

import gui.MyPanel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JPanel;

import mod_login.MD_Login;
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
	public JPanel getContentScreen() {
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
	public void setContentScreen(MyPanel screen) {
		

	}

	@Override
	public boolean stopAllActions() {
		// TODO Auto-generated method stub
		return true;
	}
	
	//Hauptmenü
	private JPanel getMainScreen(){
		JPanel ret = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 0, 0, 0);
		//für jedes (registrierte) Modul werden buttons erzeugt.
		for(AbstractModule mod : Framework.FW().getModules()){
			JButton btn = new JButton(new ACT_BT_KLICK_setActiveModule(mod));
			btn.setPreferredSize(new Dimension(200, 60));
            ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("logo2.png"));
            icon.setImage(icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			btn.setIcon(icon);
			gbc.gridy++;
			ret.add(btn, gbc);
			if(mod == MD_Login.getInstance()){
				btn.setEnabled(false);
			}
		}
		
		return ret;
	}

	@Override
	public boolean readConfigFile() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getDataFromServer() {
		// TODO Auto-generated method stub
		return false;
	}

}
