package mod_user;

import gui.MyPanel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.ListDataListener;

import mod_user.action.ACT_MI_KLICK_AddUser;
import mod_user.action.ACT_MI_KLICK_ChangePassword;
import mod_user.action.ACT_MI_KLICK_Privileges;

import base.AbstractModule;
import base.Framework;
import base.StateMan;

public class MD_User extends AbstractModule implements ComboBoxModel{

	private List<MyUser> userList = null;
	
	private User selection = null;
	
	private static MD_User instance = null;
	
	private List<ListDataListener> listDataListenerList = null;
	
	private JMenu menu = null;
	public final String MENUNAME = "Benutzer";
	
	private MD_User(){
		userList = new ArrayList<MyUser>();
		listDataListenerList = new ArrayList<ListDataListener>();
		
		MyUser testUser = new MyUser("Administrator", "Test");
		userList.add(testUser);
		
	}
	
	public static MD_User getInstance(){
		if(instance == null)instance = new MD_User();
		return instance;
	}
	
	@Override
	public void createMenu() {
		menu = new JMenu(MENUNAME);
		
		JMenuItem miAddUser = new JMenuItem(new ACT_MI_KLICK_AddUser());
		menu.add(miAddUser);
		
		JMenuItem miChangePwd = new JMenuItem(new ACT_MI_KLICK_ChangePassword());
		menu.add(miChangePwd);
		
		JMenuItem miPrivileges = new JMenuItem(new ACT_MI_KLICK_Privileges());
		menu.add(miPrivileges);

	}

	@Override
	public MyPanel getContentScreen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JMenu getMenu() {
		if(menu==null)createMenu();
		return menu;
	}

	@Override
	public String getMenuName() {
		return MENUNAME;
	}

	@Override
	public boolean stopAllActions() {
		// TODO Auto-generated method stub
		return false;
	}

	public Object getSelectedItem() {
		return selection;
	}

	public void setSelectedItem(Object user) {
		selection = (User)user;
	}

	public void addListDataListener(ListDataListener listener) {
		listDataListenerList.add(listener);		
	}

	public Object getElementAt(int pos) {
		return userList.get(pos);
	}

	public int getSize() {
		return userList.size();
	}

	public void removeListDataListener(ListDataListener listener) {
		listDataListenerList.remove(listener);
	}

	@Override
	public void setContentScreen() {
		// TODO Auto-generated method stub
		
	}

	public boolean addUser(MyUser user){
		boolean ok = userList.add(user);
		setChanged();
		notifyObservers();
		Framework.FW().setState(StateMan.SM().getState(StateMan.OK));
		return ok;
	}
	
}
