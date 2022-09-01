package org.fade.demo.springframework.boot.registry


import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.StandardEnvironment

class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {

	private static final Logger LOG = LoggerFactory.getLogger(MyEnvironmentPostProcessor)

	@Override
	void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		MyThreadPoolUtil.submit {
			while (true) {
				// set file path according to your situation
				def file = new File("D:\\IDEAProjects\\spring-boot-source-analysis\\spring-boot-demo\\config-registry\\src\\main\\resources\\application.properties")
				def properties = new Properties()
				properties.load(new FileInputStream(file))
				def sources = environment.propertySources
				def source = sources.get(MyPropertySource.MY_PROPERTY_SOURCE_NAME)
				def mySource = new MyPropertySource(properties)
				if (source) {
					// fixme 日志打印不起作用 logback
					LOG.info("配置源已存在，即将替换环境中已存在配置源")
					sources.replace(MyPropertySource.MY_PROPERTY_SOURCE_NAME, mySource)
				} else {
					LOG.info("配置源不存在，即将添加至环境中")
					sources.addAfter(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME, mySource)
				}
				Thread.sleep(3000)
			}
		}
	}

}
