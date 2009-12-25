package mod_user;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrivilegeProfiles {

	private static PrivilegeProfiles instance = null;
	
	private Map<String, Set<Integer>> profiles = null;
	
	private static final String ADMIN = "admin";
	
	public final int LOGIN = 0;
	public final int USER = 1;
	
	private PrivilegeProfiles(){
		profiles = new HashMap<String, Set<Integer>>();
		initDefaults();
	}
	
	private void initDefaults() {
		
		//kaskadiert aufbauen
		
		//hier werden die Rechte für admins festgelegt
		Set<Integer> adminSet = new HashSet<Integer>();
		adminSet.add(LOGIN);
		adminSet.add(USER);
		profiles.put(ADMIN, adminSet);
			
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
