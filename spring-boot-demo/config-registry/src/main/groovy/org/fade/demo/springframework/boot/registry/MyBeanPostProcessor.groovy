package org.fade.demo.springframework.boot.registry

import org.springframework.beans.BeansException
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.core.env.ConfigurablePropertyResolver
import org.springframework.util.ReflectionUtils

import java.lang.reflect.Field
import java.util.concurrent.ConcurrentHashMap

class MyBeanPostProcessor implements BeanPostProcessor, ApplicationRunner {

	private final Map<Field, Object> map = new ConcurrentHashMap<>(16)

	private final ConfigurablePropertyResolver propertyResolver

	MyBeanPostProcessor(ConfigurablePropertyResolver propertyResolver) {
		this.propertyResolver = propertyResolver
	}

	@Override
	Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		def clz = bean.class
		ReflectionUtils.doWithFields(clz) {
			def value = it.getAnnotation(Value)
			if (value) {
				map[it] = bean
			}
		}
		super.postProcessAfterInitialization(bean, beanName)
	}

	@Override
	void run(ApplicationArguments args) throws Exception {
		MyThreadPoolUtil.submit {
			while (true) {
				for (entry in map) {
					def field = entry.getKey()
					def obj = entry.getValue()
					def text = field.getAnnotation(Value).value()
					def value = this.propertyResolver.resolvePlaceholders(text)
					def access = field.canAccess(obj)
					field.setAccessible(true)
					field.set(obj, value)
					field.setAccessible(access)
				}
			}
		}
	}

}
