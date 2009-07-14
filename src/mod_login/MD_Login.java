package mod_login;

import gui.MyPanel;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import mod_login.action.ACT_MI_KLICK_Logout;
import mod_login.gui.GU_MP_LoginStartScreen;
import mod_user.User;
import base.AbstractModule;
import base.Framework;

public class MD_Login extends AbstractModule {

	private static MD_Login instance = null;
	
	public final String LOGIN = "Login";
	public final String START = "Start";
	public final String EXIT = "Beenden";
	public final String CONFIGFILE = "login.dat";
	
	protected JMenu menu = null;
	
	private MD_Login(){
		super();
	}
	
	public static MD_Login getInstance(){
		if(instance == null) instance = new MD_Login();
		return instance;
	}
		
	@Override
	public void createMenu() {
		menu = new JMenu(getMenuName());
				
		JMenuItem miAsAdmin = new JMenuItem(new ACT_MI_KLICK_AsAdmin());
		menu.add(miAsAdmin);
		
		JSeparator sep = new JSeparator();
		menu.add(sep);
		
		JMenuItem miLogout = new JMenuItem(new ACT_MI_KLICK_Logout());
		menu.add(miLogout);
		
	}

	@Override
	public JMenu getMenu() {
		if(menu == null)createMenu();
		return menu;
	}

	@Override
	public String getMenuName() {
		return LOGIN;
	}

	@Override
	public boolean stopAllActions() {
		return true;
	}

	@Override
	public MyPanel getContentScreen() {
		return new GU_MP_LoginStartScreen();
	}

	@Override
	public void setContentScreen() {
		// im Loginmodul gibt es nur einen Screen -> Methode wird nicht benutzt
	}

	public boolean comparePasswords(User user, String inputHash) {
		//String input = Security.getInstance().toMD5(inputHash);
		return user.getPwdHash().equals(inputHash);
	}

	@Override
	public boolean readConfigFile() {
		//für login kein configfile notwendig
		return true;
	}

	public boolean logout() {
		boolean allActionsDone = Framework.FW().getActiveModule().stopAllActions();
		if(allActionsDone){
			Framework.FW().setActiveModule(MD_Login.getInstance());
			return true;
		}
		return false;
		
	}

}
