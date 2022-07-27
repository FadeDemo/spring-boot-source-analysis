package org.fade.demo.springframework.boot.registry

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.ConfigurablePropertyResolver

@Configuration
class MyConfig {

	@Bean
	MyBeanPostProcessor myBeanPostProcessor(ConfigurablePropertyResolver propertyResolver) {
		new MyBeanPostProcessor(propertyResolver)
	}

}
