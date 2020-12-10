package springftl.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/referrer")
public class ReferrerController {

	@RequestMapping("/test")
	public String getStudent(HttpServletRequest request)
	{
		String header = request.getHeader("referer");
		if (header == null){
			return "无权访问";
		}
		System.out.println(header);
		return "访问成功";
	}
}
