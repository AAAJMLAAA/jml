package com.spring.demo.demoStart.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.demoStart.pojo.Student;
import com.spring.demo.demoStart.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping(value="/insert")
	public String insertT()
	{
		Student stu = new Student();
		stu.setBirthday(new Date());
		stu.setId(UUID.randomUUID().toString());
		stu.setName("jml");
		stu.setPwd("123");
		studentService.insert(stu);
		return "操作成功";
	}
	
	@ExceptionHandler(value = Exception.class)
	public String exception(Exception e)
	{
		return e.getMessage();
	}
}
