package org.MatchTemplate.controller;

import org.MatchTemplate.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

	@GetMapping("/testHtml")
	public String testHtml()
	{
		return "hellow1";
	}
	
	@GetMapping("/testJsp")
	public String testJsp()
	{
		return "hellow2";
	}
	
	@GetMapping("/testFtl")
	public String testFtl()
	{
		return "hellow3";
	}
	
	@GetMapping("/tetsJsonData")
	@ResponseBody
	public User tetsJsonData()
	{
		return new User("23","23");
	}
}
