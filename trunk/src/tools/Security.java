package tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {
	
	private static Security instance = null;
	
	private Security(){
		
	}
	
	public static Security getInstance(){
		if(instance == null)instance = new Security();
		return instance;
	}
	
	public String toMD5(String input){
		
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.reset();
	        md5.update(input.getBytes());
	        
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(md5.toString()==null)throw new NullPointerException("Fehler beim hashen!");
		return md5.toString();
	}

}
