package Entities;

public class User extends Entity {

	public User(String userName, String userFullName, String userPassword, String userType) {
		super("User", null, null);
		entity.put("userName", userName);
		entity.put("userFullName", userFullName);
		entity.put("userPassword", userPassword);
		entity.put("userType", userType);
	}
	
	public String getUserName() {
		return entity.getString("userName");
	}

	public void setUserName(String userName) {
		entity.put("userName", userName);
	}

	public String getUserFullName() {
		return entity.getString("userFullName");
	}

	public void setUserFullName(String userFullName) {
		entity.put("userFullName", userFullName);
	}

	public String getUserPassword() {
		return entity.getString("userPassword");
	}

	public void setUserPassword(String userPassword) {
		entity.put("userPassword", userPassword);
	}

	public String getUserType() {
		return entity.getString("userType");
	}

	public void setUserType(String userType) {
		entity.put("userType", userType);
	}
}