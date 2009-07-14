package mod_login;

import gui.MyPanel;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import tools.Security;

import mod_login.action.ACT_MI_KLICK_AddUser;
import mod_login.gui.GU_MP_LoginStartScreen;
import mod_user.User;
import base.AbstractModule;

public class MD_Login extends AbstractModule {

	private static MD_Login instance = null;
	
	public final String LOGIN = "Login";
	public final String NEWUSER = "Neuen Benutzer anlegen";
	public final String ASADMIN = "Als Administrator anmelden";
	public final String START = "Start";
	public final String EXIT = "Beenden";
	
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
		
		//TODO: actions hinzufügen
		JMenuItem miAddUser = new JMenuItem(new ACT_MI_KLICK_AddUser());
		menu.add(miAddUser);
		
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
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public MyPanel getContentScreen() {
		return new GU_MP_LoginStartScreen();
	}

	@Override
	public void setContentScreen() {
		// TODO Auto-generated method stub
		
	}

	public boolean comparePasswords(User user, String inputHash) {
		//String input = Security.getInstance().toMD5(inputHash);
		return user.getPwdHash().equals(inputHash);
	}

}
