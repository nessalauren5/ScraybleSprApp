package Entities;

public class User extends Entity {

	private static final String USER_TYPE = "userType";
	private static final String USER_PASSWORD = "userPassword";
	private static final String USER_FULL_NAME = "userFullName";
	private static final String USER_NAME = "userName";
	private static final String USER = "User";
	
	public User(String userName, String userFullName, String userPassword, String userType) {
		super(USER, userName, null);
		entity.put(USER_NAME, userName);
		entity.put(USER_FULL_NAME, userFullName);
		entity.put(USER_PASSWORD, userPassword);
		entity.put(USER_TYPE, userType);
	}
	
	public String getUserName() { return entity.getString(USER_NAME); }
	public void setUserName(String userName) { entity.put(USER_NAME, userName); }

	public String getUserFullName() { return entity.getString(USER_FULL_NAME); }
	public void setUserFullName(String userFullName) { entity.put(USER_FULL_NAME, userFullName); }

	public String getUserPassword() { return entity.getString(USER_PASSWORD); }
	public void setUserPassword(String userPassword) { entity.put(USER_PASSWORD, userPassword); }

	public String getUserType() { return entity.getString(USER_TYPE); }
	public void setUserType(String userType) { entity.put(USER_TYPE, userType); }
}