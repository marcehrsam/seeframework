package mod_login;

import gui.MyPanel;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import tools.Debug;

import mod_login.action.ACT_MI_KLICK_AsAdmin;
import mod_login.action.ACT_MI_KLICK_Logout;
import mod_login.gui.GU_MP_LoginStartScreen;
import mod_user.MyUser;
import mod_user.User;
import base.AbstractModule;
import base.Framework;

public class MD_Login extends AbstractModule {

	private static MD_Login instance = null;
	
	public final String LOGIN = "Login";
	public final String START = "Start";
	public final String EXIT = "Beenden";
	public final String CONFIGFILE = "login.dat";
	
	private MyUser currentUser = null;
	
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
	public JPanel getContentScreen() {
		return new GU_MP_LoginStartScreen();
	}

	@Override
	public void setContentScreen(MyPanel screen) {
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
			currentUser = null;
			setChanged();
			notifyObservers();
			Debug.out("User abgemeldet");
			return true;
		}
		Debug.out("User konnte nicht abgemeldet werden. F1");
		return false;
	}
	
	public boolean login(MyUser user){
		if(user==null){
			Debug.out("Kein User zum einloggen gewählt!");
			return false;
		}
		if(currentUser == null){
			currentUser = user;
			setChanged();
			notifyObservers();
			Debug.out("User angemeldet");
			return true;
		}
		Debug.out("User konnte nicht angemeldet werden, da noch eine Sitzung aktiv ist. F2");
		return false;
	}

	@Override
	public boolean getDataFromServer() {
		// TODO Auto-generated method stub
		return false;
	}

}
