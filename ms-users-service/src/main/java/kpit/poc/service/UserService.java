package kpit.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kpit.poc.model.User;

@Service
public class UserService {
	//UserData userData = new UserData();
	//List<User> users = userData.getUsers();
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUsers() {
		//return users;
		return (List<User>) userRepository.findAll();
	}
	
	public User getUserByID(int id) {
		//return users.stream().filter(u -> u.getUserID()==id).findFirst().get();
		return userRepository.findById(id).get();
	}
	
	public void addUser(User user) {
		//users.add(user);
		userRepository.save(user);
	}
	
	public boolean updateUser(int id, User user) {
		/*for(int i=0;i<users.size();i++) {
			if(users.get(i).getUserID()==id) {
				users.set(i, user);
				return true;
			}
		}
		return false;*/
		userRepository.save(user);
		return true;
	}
	
	public void deleteUser(int id) {
		//users.removeIf(u -> u.getUserID() == id);
		userRepository.deleteById(id);
	}
}
