package com.lms.configuration;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@EnableAutoConfiguration
public class ApplicationConfiguration {

	private static MessageSourceAccessor messageSourceAccessor;

	@PostConstruct
	private static void initMessageSourceAccessor() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:message");
		messageSourceAccessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
	}
	public static MessageSourceAccessor getMessageAccessor() {
		return messageSourceAccessor;
	}
}
