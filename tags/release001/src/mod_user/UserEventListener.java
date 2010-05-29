package mod_user;

import java.util.EventListener;

public interface UserEventListener extends EventListener {

	public void UserAdded(User user);
	public void UserRemoved(User user);
	
}
