package org.producer02.service;

import java.util.List;

import org.producer02.pojo.User;

public interface UserService {
	
	void addUser(User user);
	
	List<User> query();
}
