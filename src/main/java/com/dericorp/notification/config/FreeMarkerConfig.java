package com.dericorp.notification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
public class FreeMarkerConfig {
	
	@Bean
	public FreeMarkerConfigurationFactoryBean getFreeMarkerConfig() {
		FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("/templates/");
        //bean.setTemplateLoaderPath("classpath:/templates/");
        return bean;
	}

}
