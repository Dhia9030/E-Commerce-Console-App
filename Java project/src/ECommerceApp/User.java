package ECommerceApp;

import java.util.*;

public class User {
	private String username;
	private String password;
	public boolean isAdmin;

	public User(String username, String password, boolean isAdmin) {
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}
}