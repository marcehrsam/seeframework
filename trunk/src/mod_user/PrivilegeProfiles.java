package mod_user;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrivilegeProfiles {

	private static PrivilegeProfiles instance = null;
	
	private Map<String, Set<Integer>> profiles = null;
	
	public static final String ADMIN = "admin";
	public static final String USER = "user";
	
	public final int R_LOGIN = 0;
	public final int R_USER = 1;
	
	private PrivilegeProfiles(){
		profiles = new HashMap<String, Set<Integer>>();
		initDefaults();
	}
	
	private void initDefaults() {
		
		//kaskadiert aufbauen
		
		//hier werden die Rechte für admins festgelegt
		Set<Integer> adminSet = new HashSet<Integer>();
		adminSet.add(R_LOGIN);
		adminSet.add(R_USER);
		profiles.put(ADMIN, adminSet);
		
		//user rechte
		Set<Integer> userSet = new HashSet<Integer>();
		userSet.add(R_LOGIN);
		userSet.add(R_USER);
		profiles.put(USER, userSet);
	}

	public static PrivilegeProfiles P(){
		if(instance == null){
			instance = new PrivilegeProfiles();
		}
		return instance;
	}
	
	public Set<Integer> getRights(String type){
		return profiles.get(type);
	}
}
