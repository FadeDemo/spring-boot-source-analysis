package org.fade.demo.springframework.boot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 应用程序配置
 *
 * @author fade
 * @date 2022/07/19
 */
@Configuration
public class AppConfig {

	@Value("${test}")
	private String test;

	@Value("${default:222}")
	private String var;

	public AppConfig() {
		System.out.println("app config ...");
	}

}
