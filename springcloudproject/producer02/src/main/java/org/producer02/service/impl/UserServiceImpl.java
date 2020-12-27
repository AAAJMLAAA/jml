package org.producer02.service.impl;

import java.util.List;

import org.producer02.dao.UserDao;
import org.producer02.pojo.User;
import org.producer02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void addUser(User user) {
		userDao.save(user);
	}

	@Override
	public List<User> query() {
		return userDao.findAll();
	}

}
