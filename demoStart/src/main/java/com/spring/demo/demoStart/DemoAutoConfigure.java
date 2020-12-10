package com.spring.demo.demoStart;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = DemoProperties.class)
public class DemoAutoConfigure {

	/**
	 * 用来控制是否启动配置类
	 * @Description: 
	 * @date: 2020年12月10日 下午9:34:19
	 * @param userProperties
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(name="person")
	//@ConditionalOnProperty(prefix = "spring.user", value = "enabled", havingValue = "true")
	public Person userClient(DemoProperties demoProperties) {
		return new Person(demoProperties);
	}
}
