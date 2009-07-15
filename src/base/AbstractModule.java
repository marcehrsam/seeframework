package base;
import gui.MyPanel;

import java.util.Observable;

import javax.swing.JMenu;
import javax.swing.JPanel;

public abstract class AbstractModule extends Observable implements IModule {
	
	public AbstractModule(){
		//konfiguration in neuem thread einlesen
		Thread tInit = new Thread(new InitModuleThread(this));
		tInit.start();
	}
	
	protected MyPanel contentScreen = null;
	
	public abstract JMenu getMenu();
	public abstract void createMenu();
	public abstract String getMenuName();
	public abstract boolean stopAllActions();
	public abstract JPanel getContentScreen();
	public abstract void setContentScreen(MyPanel screen);
	public abstract boolean readConfigFile();
	
}
