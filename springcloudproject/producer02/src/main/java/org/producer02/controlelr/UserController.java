package org.producer02.controlelr;

import java.util.List;

import org.producer02.pojo.User;
import org.producer02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping(value="add")
	public String createUser(User user){
		userService.addUser(user);
		return "操作成功！";
	}
	
	@PostMapping(value="query")
	public String queryUser(){
		List<User> query = userService.query();
		return "操作成功！" + query.size();
	}
	
}
