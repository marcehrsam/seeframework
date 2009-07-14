package base;


public class InitModuleThread implements Runnable {

	private AbstractModule module = null;
	
	public InitModuleThread(AbstractModule module){
		this.module = module;
	}
	
	public void run() {
		if(module!=null){
			module.readConfigFile();
		}	
	}

}
