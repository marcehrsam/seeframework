package mod_user;

import java.util.Observable;

import tools.Security;

public abstract class User extends Observable{

	private String userName = null;
	private String pwdHash = null;
	
	//TODO: Rechte festlegen
	
	public User(String name, String password){
		setUserName(name);
		setPassword(password);
		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
		setChanged();
		notifyObservers();
	}
	public String getPwdHash() {
		return pwdHash;
	}
	public void setPassword(String password) {
		//this.pwdHash = Security.getInstance().toMD5(password);
		this.pwdHash = password;
		setChanged();
		notifyObservers();
	}
	
	public String toString(){
		return userName;
	}
	
}
