package org.fade.demo.springframework.boot.registry

import org.springframework.boot.SpringApplication
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.core.env.ConfigurableEnvironment

class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {

	@Override
	void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

	}

}
