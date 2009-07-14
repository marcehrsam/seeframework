package base;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEditSupport;

import mod_login.MD_Login;
import tools.Debug;
import undoRedo.UndoAdaptor;

public class Framework extends Observable{

	//the framework
	private static Framework instance = null;
	
	//modules installed in the framework
	private List<AbstractModule> modules = null;
	
	//for undo/redo
	UndoManager undoManager;
	
	UndoableEditSupport undoSupport;
	
	private AbstractModule activeModule = null;
	
	private MyState fwState = null; 
		
	/**
	 * Constructor for the framework.
	 * Instantiates the class.
	 */
	private Framework(){
		modules = new ArrayList<AbstractModule>();
		
		//for undo
		undoManager = new UndoManager();
		undoSupport = new UndoableEditSupport();
		undoSupport.addUndoableEditListener(new UndoAdaptor());
		refreshUndoRedo();
	}
	
	public void Init(){
		//Konfiguration initialisieren
		Config.getInstance().Init();

	}
	
	
	/**
	 * 
	 * @return the instance.
	 */
	public static Framework FW(){
		if(instance==null){
			instance = new Framework();
		}
		return instance;
	}

	/**
	 * Returns the modules installed in the framework.
	 * @return
	 */
	public List<AbstractModule> getModules() {
		return modules;
	}

	/**
	 * Removes a module from the framework.
	 * @param module
	 */
	public void removeModule(IModule module) {
		modules.remove(module);
		setChanged();
		notifyObservers();
	}

	/**
	 * Adds a module to the framework.
	 * @param module
	 */
	public void addModule(AbstractModule module) {
		modules.add(module);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Adds a module to the framework.
	 * @param module
	 */
	public void addModule(AbstractModule module, boolean isActive) {
		modules.add(module);
		if(isActive){
			if(activeModule==null){
				activeModule = module;
			}else{
				setActiveModule(module);
			}
		}
		
		setChanged();
		notifyObservers();
	}
	
	//for undo
	public UndoManager getUndoManager(){
		return undoManager;
	}
	
	public void refreshUndoRedo(){
		Debug.out("GUI updaten");
	}
	
	public UndoableEditSupport getUndoSupport(){
		return undoSupport;
	}

	public AbstractModule getActiveModule() {
		if(activeModule!=null)return activeModule;
		else return getDefaultModule();
	}
	
	public boolean setActiveModule(AbstractModule module){
		if(activeModule==null){
			activeModule = module;
			setChanged();
			notifyObservers();
			Debug.out("ActiveModule hart gesetzt.[setActiveModule]");
		}
		
		//erst noch alle vorgänge beenden
		if(activeModule.stopAllActions()){
			activeModule = module;
			setChanged();
			notifyObservers();
			Debug.out("Framework: activeModule gewechselt.");
			return true;
		}
		try {
			Debug.toErrorLog("Framework: Fehler bei wechsel des activeModule.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Debug.out("Modul konnte nicht gewechselt werden, da noch nicht alle Vorgänge beendet wurden.");
		return false;
		
	}
	
	public AbstractModule getDefaultModule(){
		return MD_Login.getInstance();
	}
	
	public MyState getStatus(){
		return fwState;
	}
	
	public void setState(MyState status){
		fwState = status;
		setChanged();
		notifyObservers();
	}
	
}
