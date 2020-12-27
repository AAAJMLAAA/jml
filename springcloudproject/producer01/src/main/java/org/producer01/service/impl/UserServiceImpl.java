package org.producer01.service.impl;

import java.util.List;

import org.producer01.dao.UserDao;
import org.producer01.pojo.User;
import org.producer01.service.UserService;
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
