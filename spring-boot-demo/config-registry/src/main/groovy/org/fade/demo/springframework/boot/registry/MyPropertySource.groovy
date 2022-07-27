package org.fade.demo.springframework.boot.registry

import org.springframework.core.env.PropertySource

class MyPropertySource extends PropertySource<Properties> {

	static final String MY_PROPERTY_SOURCE_NAME = "fade"

	MyPropertySource(Properties properties) {
		this(MY_PROPERTY_SOURCE_NAME, properties)
	}

	private MyPropertySource(String name, Properties properties) {
		super(name, properties)
	}

	@Override
	Object getProperty(String propertyName) {
		getSource().get(propertyName)
	}

}
