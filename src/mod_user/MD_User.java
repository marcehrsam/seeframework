package mod_user;

import gui.MyPanel;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.ComboBoxModel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.ListDataListener;

import mod_user.action.ACT_MI_KLICK_AddUser;
import mod_user.action.ACT_MI_KLICK_ChangePassword;
import mod_user.action.ACT_MI_KLICK_Privileges;
import mod_user.gui.GU_MP_UserStartScreen;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import tools.Debug;
import base.AbstractModule;
import base.Framework;
import base.StateMan;

public class MD_User extends AbstractModule implements ComboBoxModel, UserEventSource{

	private List<MyUser> userList = null;
	
	private User selection = null;
	
	private static MD_User instance = null;
	
	private List<ListDataListener> listDataListenerList = null;
	private List<UserEventListener> userEventListenerList = null;
	
	private MyPanel currentScreen = null;
	
	private JMenu menu = null;
	public final String MENUNAME = "Benutzer";
	public final String CONFIGFILE = "user.dat";
	
	private MD_User(){
		userList = new ArrayList<MyUser>();
		listDataListenerList = new ArrayList<ListDataListener>();
		userEventListenerList = new ArrayList<UserEventListener>();
		this.addUserEventListener(Framework.FW());
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
	public JPanel getContentScreen() {
		if(currentScreen!= null){
			return currentScreen;
		}else{
			return new GU_MP_UserStartScreen();
		}
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
		return true;
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
	public void setContentScreen(MyPanel screen) {
		//Observer entfernen
		if(contentScreen!=null){
			MD_User.getInstance().deleteObserver(contentScreen);
		}
		contentScreen = screen;
		setChanged();
		notifyObservers();
	}

	public boolean addUser(MyUser user){
		boolean ok = userList.add(user);
		setChanged();
		notifyObservers();
		if(ok){
			userAddedEvent(user);
			Framework.FW().setState(StateMan.SM().getState(StateMan.USER_ADDED));
			Debug.out("User hinzugefügt");
		}else{
			Framework.FW().setState(StateMan.SM().getState(StateMan.ERR));
		}
		return ok;
	}
	
	public boolean removeUser(MyUser user){
		userRemovedEvent(user);
		setChanged();
		notifyObservers();
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean readConfigFile() {
	
		try{
			//sql-abfrage hier
			connectToDB();
			if(!getDataFromServer()){
				throw new NullPointerException("Fehler beim Einlesen der Kundendaten [SQL].");
			}
		}catch(Exception ex){
			//liesst benutzer und passwörter ein (aus user.dat)
			//TODO: auf Hashs umstellen
			try {
				SAXBuilder builder = new SAXBuilder();
				Document doc = builder.build(CONFIGFILE);
				Element root = doc.getRootElement();
				List<Element> users = root.getChildren("User");
				for(Element el : users){
					String name = el.getAttributeValue("name");
					String pass = el.getChildText("Pass");
					MyUser user = new MyUser(name, pass);
					String type = el.getChildText("Rights");
					Set<Integer> rights = PrivilegeProfiles.P().getRights(type);
					user.setRights(rights);
					userList.add(user);
				}
			} catch (JDOMException e) {
				Debug.out("Fehler beim Datenimport mit JDOM [MD_Login/readConfigFile] F1");
			} catch (IOException e) {
				Debug.out("Fehler beim Datenimport mit JDOM [MD_Login/readConfigFile] F2");
			}
			ex.printStackTrace();
			return true;
		}
		
		return true;
	}

	public void addUserEventListener(UserEventListener l) {
		userEventListenerList.add(l);
	}

	public void removeUserEventListener(UserEventListener l) {
		userEventListenerList.remove(l);	
	}

	public void userAddedEvent(User user) {
		for(UserEventListener l : userEventListenerList){
			l.UserAdded(user);
		}
	}

	public void userRemovedEvent(User user) {
		for(UserEventListener l : userEventListenerList){
			l.UserRemoved(user);
		}
	}

	@Override
	public boolean getDataFromServer() {
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM user;");
			while(rs.next()){
				MyUser nUser = new MyUser(rs.getString("name"), rs.getString("pass"));
				String type = rs.getString("rights");
				Set<Integer> rights = PrivilegeProfiles.P().getRights(type);
				nUser.setRights(rights);
				userList.add(nUser);
			}
			setChanged();
			notifyObservers();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
}
