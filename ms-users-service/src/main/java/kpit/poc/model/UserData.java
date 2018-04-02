package kpit.poc.model;

import java.util.ArrayList;
import java.util.List;
public class UserData {
	
	List<User> users = new ArrayList<>();
	public UserData() {
		users.add(new User(111, "Prabhash", "prabhash@gmail", "7071"));
		users.add(new User(112, "Akash", "akash@gmail", "7071"));
	}
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
