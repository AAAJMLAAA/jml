package springftl.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class WebConfigurer implements WebMvcConfigurer{

	/**
	 * 解决跨域问题
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		 registry.addMapping("/**")
         .allowedOrigins("*")
         .maxAge(3600)
         .allowedHeaders("*");
		WebMvcConfigurer.super.addCorsMappings(registry);
	}
	
	/**
	 * @Description: 添加自定义的过滤器
	 * @date: 2020年11月15日 上午10:05:24
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<RequestXssFilter> registerFilter() {
		FilterRegistrationBean<RequestXssFilter> registration = new FilterRegistrationBean<RequestXssFilter>();
		//String errUrl = "login.html";
	    registration.addUrlPatterns("/*");      
	//	registration.addInitParameter("ERR_URL", errUrl);   
		registration.setFilter(new RequestXssFilter());
	    registration.setName("jml");  
	    registration.setOrder(1);
		return registration;

	}
}
