package springftl.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller")
public class TestController {
	
	@PostMapping(value="/test")
	public String test(String name)
	{
		System.out.println(name);
		return "测试数据"+name;
	}
	
	@PostMapping(value="/t2")
	public String test2(String name)
	{
		System.out.println(name);
		return "测试数据"+name;
	}
	@RequestMapping(value="/check")
	public String checkParams()
	{
		return "非法请求";
	}
}
