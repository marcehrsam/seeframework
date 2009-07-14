package tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import base.Config;

public class Debug {
	
	private static boolean debugEnabled = true;
	
	private Debug(){
		//nur eine instanz erlaubt
	}
	
	public static void toErrorLog(String msg) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(Config.getInstance().ERROR_LOG, true));
		writer.write(msg);
		writer.newLine();
		writer.close();
	}
	
	public static void out(String msg){
		if(debugEnabled){
			System.out.println("[DEBUG]: " + msg);		
		}
		
		//in logfile ausgeben
		try {
			toErrorLog(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
