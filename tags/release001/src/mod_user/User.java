package mod_user;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import tools.Debug;

import base.Framework;
import base.StateMan;

public abstract class User extends Observable{

	private String userName = null;
	private String pwdHash = null;
	
	private Set<Integer> privileges = null;
	
	//TODO: Rechte festlegen
	
	public User(String name, String password){
		setUserName(name);
		setPassword(password);
		privileges = new HashSet<Integer>(); 
		
	}
	
	public boolean grantAccess(int priv){
		if(privileges.contains(priv)){
			Framework.FW().setState(StateMan.SM().getState(StateMan.ACCESSGRANTED));
			return true;
		}else{
			Framework.FW().setState(StateMan.SM().getState(StateMan.ACCESSDENIED));
			return false;
		}
	}
	
	public boolean addRight(int right){
		if(privileges.contains(right)){
			//mache nix
			Debug.out("Rechte nicht hinzugefügt, da sie schon vorhanden sind.");
			Framework.FW().setState(StateMan.SM().getState(StateMan.ERR));
			return false;
		}else{
			Framework.FW().setState(StateMan.SM().getState(StateMan.OK));
			Debug.out("Rechte hinzugefügt.");
			return privileges.add(right);
		}
	}
	
	public boolean removeRight(int right){
		if(privileges.contains(right)){
			Framework.FW().setState(StateMan.SM().getState(StateMan.OK));
			Debug.out("Rechte entfernt");
			return privileges.remove(right);
		}else{
			Debug.out("Rechte nicht entfernt, da sie nicht gegeben sind.");
			Framework.FW().setState(StateMan.SM().getState(StateMan.ERR));
			return false;
		}
	}
	
	public void setRights(Set<Integer> rights){
		if(rights != null){
			privileges = rights;
			Debug.out("Rechte gesetzt");
			Framework.FW().setState(StateMan.SM().getState(StateMan.OK));
		}else{
			Debug.out("Rechte konnten nicht gesetzt werden, da param==null [User/setRights]");
			Framework.FW().setState(StateMan.SM().getState(StateMan.ERR));
		}
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
