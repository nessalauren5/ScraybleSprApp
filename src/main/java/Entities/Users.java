package Entities;

import java.util.HashMap;

public class Users extends HashMap<String, User> {

	private static final long serialVersionUID = 1L;
	private Users() {}
	private static Users users = new Users();
	public static Users GetUsers() {
		return users;
	}
}
