package spring_message_comsumer.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import spring_message_comsumer.pojo.Student;
import spring_message_comsumer.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@GetMapping(value="/insert")
	public String insert()
	{
		for (int x = 0;x < 100;x++)
		{
			Student stu = new Student();
			stu.setId(UUID.randomUUID().toString());
			stu.setBirthday(new Date());
			stu.setName("jml"+x);
			stu.setPwd("123"+x);
			studentService.insert(stu);
		}
		
		return "OK";
	}
	
	@GetMapping(value="/query")
	public String query() throws JsonProcessingException
	{
		List<Student> queryCondition = studentService.queryCondition(null);
		long queryConditionCount = studentService.queryConditionCount(null);
		System.out.println(queryConditionCount);
		ObjectMapper objectMapper = new ObjectMapper();
		String writeValueAsString = objectMapper.writeValueAsString(queryCondition);
		return writeValueAsString;
	}
	
}
