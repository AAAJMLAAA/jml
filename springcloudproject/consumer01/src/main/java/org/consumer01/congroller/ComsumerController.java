package org.consumer01.congroller;

import org.consumer01.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/comsumer")
public class ComsumerController {
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String uri = "http://PRODUCER02";
	@PostMapping(value="add")
	public String createUser(User user){
		return restTemplate.postForObject(uri+"/user/add", user, String.class);
	}
	
	@PostMapping(value="query")
	public String queryUser(){
		//return restTemplate.getForObject(uri+"/user/query", String.class);
		return restTemplate.postForObject(uri+"/user/query", null, String.class);
	}
	
}
