package base;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import tools.Debug;

//Verwaltet stati
public class StateMan {

	private static StateMan instance = null; 
	
	private Map<Integer, MyState> states = null;
	
	//priorit�ten
	
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
	public static final int ACTION_CANCELED = 8;
	public static final int USER_ADDED = 9;
	public static final int TWICE_LOGGED_IN = 10;
	public static final int ACCESSGRANTED = 11;
	public static final int ACCESSDENIED = 12;
	public static final int NO_PRODUCT_SELECTED = 13;
	public static final int PRODUCT_ADDED = 14;
	public static final int BILL_CREATED = 15;
	public static final int BILL_CREATION_ERROR = 16;
	
	
	
	private StateMan(){
		states = new HashMap<Integer, MyState>();
		
		states.put(OK, new MyState("Alles in Ordnung.", Color.GREEN, NORMAL));
		states.put(ERR, new MyState("Es liegt ein Fehler vor.", Color.RED, NORMAL));
		states.put(INITOK, new MyState("Die Initialisierung wurde erfolgreich abgeschlossen. Bitte loggen Sie sich nun ein.", Color.GREEN, NORMAL));
		states.put(LOGIN_OK, new MyState("Login erfolgreich.", Color.GREEN, NORMAL));
		states.put(LOGIN_ERR, new MyState("Login fehlgeschlagen.", Color.RED, NORMAL));
		states.put(NOUSERSELECTED, new MyState("Bitte w�hlen Sie einen Benutzer aus!", Color.RED, NORMAL));
		states.put(LOGOUT_OK, new MyState("Logout erfolgreich.", Color.GREEN, NORMAL));
		states.put(LOGOUT_ERR, new MyState("Logout fehlgeschlagen.", Color.RED, NORMAL));
		states.put(ACTION_CANCELED, new MyState("Aktion abgebrochen.", Color.BLUE, NORMAL));
		states.put(USER_ADDED, new MyState("Benutzer hinzugef�gt.", Color.GREEN, NORMAL));
		states.put(TWICE_LOGGED_IN, new MyState("Login fehlgeschlagen. Bitte loggen Sie sich zuerst aus.", Color.RED, NORMAL));
		states.put(ACCESSGRANTED, new MyState("Aktion zugelassen.", Color.GREEN, NIEDRIG));
		states.put(ACCESSDENIED, new MyState("Aktion nicht zugelassen.", Color.RED, HOCH));
		states.put(NO_PRODUCT_SELECTED, new MyState("Bitte eine Kategorie w�hlen!", Color.RED, NORMAL));
		states.put(PRODUCT_ADDED, new MyState("Produkt erfolgreich hinzugef�gt.", Color.GREEN, NORMAL));
		states.put(BILL_CREATED, new MyState("Rechnung erfolgreich erstellt.", Color.GREEN, NORMAL));
		states.put(BILL_CREATION_ERROR, new MyState("Fehler beim Erstellen!", Color.RED, HOCH));
		
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
