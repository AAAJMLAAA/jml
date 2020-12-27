package com.spring.databasemigration.databasemigration.conftroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.databasemigration.databasemigration.event.DataSourceEvent;
import com.spring.databasemigration.databasemigration.service.TableService;
import com.spring.databasemigration.databasemigration.service.TextThreadService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private TableService tableService;
	@Autowired
	private TextThreadService textThreadService;
	
	@PostMapping(value="/add")
	public String addUser() throws Exception
	{
		textThreadService.dataTranslation();
		return "success";
	}
	
}
