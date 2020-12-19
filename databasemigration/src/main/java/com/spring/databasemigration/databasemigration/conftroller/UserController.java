package com.spring.databasemigration.databasemigration.conftroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.databasemigration.databasemigration.event.DataSourceEvent;
import com.spring.databasemigration.databasemigration.service.TableService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private TableService tableService;
	
	@PostMapping(value="/add")
	public String addUser() throws Exception
	{
		new DataSourceEvent("开始转化");
		 tableService.dataTranslation();
		 new DataSourceEvent("转化结束");
		return "success";
	}
	
}
