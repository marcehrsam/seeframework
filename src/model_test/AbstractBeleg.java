package model_test;

import java.util.Observable;

public abstract class AbstractBeleg extends Observable implements IBeleg{

	//Belegnummer
	private String id;
	
	public AbstractBeleg(String id){
		this.id = id;
	}
	
	public String getID(){
		return this.id;
	}
	
	public void setID(String id){
		this.id = id;
	}
	
}
