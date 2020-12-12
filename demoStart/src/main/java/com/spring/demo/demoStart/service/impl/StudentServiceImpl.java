package com.spring.demo.demoStart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.demo.demoStart.dao.StudentDao;
import com.spring.demo.demoStart.pojo.Student;
import com.spring.demo.demoStart.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentDao studentDao;
	
	@Override
	public void insert(Student stu) {
		studentDao.insert(stu);
	}

}
