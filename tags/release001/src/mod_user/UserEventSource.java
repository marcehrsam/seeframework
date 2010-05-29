package mod_user;

public interface UserEventSource {
	
	public void addUserEventListener(UserEventListener l);
	public void removeUserEventListener(UserEventListener l);
	public void userAddedEvent(User user);
	public void userRemovedEvent(User user);

}
