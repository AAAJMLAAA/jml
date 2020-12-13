package com.spring.demo.demoStart;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@PostMapping(value="/insert")
	public String insertT()
	{
		return "操作成功";
	}
}
