package base;
import gui.MyPanel;

import java.util.Observable;

import javax.swing.JMenu;

public abstract class AbstractModule extends Observable implements IModule {
	
	protected MyPanel contentScreen = null;
	
	public abstract JMenu getMenu();
	public abstract void createMenu();
	public abstract String getMenuName();
	public abstract boolean stopAllActions();
	public abstract MyPanel getContentScreen();
	public abstract void setContentScreen();
	
}
