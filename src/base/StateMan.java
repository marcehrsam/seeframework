package base;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import tools.Debug;

//Verwaltet stati
public class StateMan {

	private static StateMan instance = null; 
	
	private Map<Integer, MyState> states = null;
	
	//prioritäten
	
	public int NIEDRIG = -1;
	public int NORMAL = 0;
	public int HOCH = 1;
	
	public static final int OK = 0;
	public static final int ERR = 1;
	public static final int INITOK = 2;
	public static final int LOGIN_OK = 3;
	public static final int LOGIN_ERR = 4;
	public static final int NOUSERSELECTED = 5;
	public static final int LOGOUT_OK = 6;
	public static final int LOGOUT_ERR = 7;
	
	
	private StateMan(){
		states = new HashMap<Integer, MyState>();
		
		states.put(OK, new MyState("Alles in Ordnung.", Color.GREEN, NORMAL));
		states.put(ERR, new MyState("Es liegt ein Fehler vor.", Color.RED, NORMAL));
		states.put(INITOK, new MyState("Die Initialisierung wurde erfolgreich abgeschlossen. Bitte loggen Sie sich nun ein.", Color.GREEN, NORMAL));
		states.put(LOGIN_OK, new MyState("Login erfolgreich.", Color.GREEN, NORMAL));
		states.put(LOGIN_ERR, new MyState("Login fehlgeschlagen.", Color.RED, NORMAL));
		states.put(NOUSERSELECTED, new MyState("Bitte wählen Sie einen Benutzer aus!", Color.RED, NORMAL));
		states.put(LOGOUT_OK, new MyState("Logout erfolgreich.", Color.GREEN, NORMAL));
		states.put(LOGOUT_ERR, new MyState("Logout fehlgeschlagen.", Color.RED, NORMAL));
	}
	
	public static StateMan SM(){
		if(instance==null)instance = new StateMan();
		return instance;
	}
	
	public boolean addState(int key, MyState state){
		try{
			MyState st = states.put(key, state);
			if(st!=null){
				return true;
			}
			return false;
		}catch(Exception e){
				Debug.out("Fehler beim anlegen eines neuen Status.");
		}
		return false;
	}
	
	public MyState getState(int status){
		return states.get(status);
	}
}
