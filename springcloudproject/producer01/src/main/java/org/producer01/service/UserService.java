package org.producer01.service;

import java.util.List;

import org.producer01.pojo.User;

public interface UserService {
	
	void addUser(User user);
	
	List<User> query();
}
