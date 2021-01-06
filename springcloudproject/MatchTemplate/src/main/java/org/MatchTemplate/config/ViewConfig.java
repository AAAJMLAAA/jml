package org.MatchTemplate.config;

import org.apache.jasper.compiler.JspConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
public class ViewConfig {

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		String[] templatePaths = { "classpath:/templates/" };
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPaths(templatePaths);
		return configurer;
	}

	@Bean
	public FreeMarkerViewResolver ftlViewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setPrefix("");
		resolver.setCache(false);
		resolver.setSuffix(".ftl");
		resolver.setOrder(0);
		return resolver;
	}

	@Bean
	public FreeMarkerViewResolver htmlViewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setPrefix("");
		resolver.setCache(false);
		resolver.setSuffix(".html");
		resolver.setOrder(1);
		return resolver;
	}

	
	@Bean
	public InternalResourceViewResolver jspViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/META-INF/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(2);
		return viewResolver;
	}

}
