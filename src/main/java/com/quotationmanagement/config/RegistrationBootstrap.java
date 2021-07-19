package com.quotationmanagement.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.quotationmanagement.service.StockManagerApiService;

@Component
public class RegistrationBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private StockManagerApiService stockManager;

	private final Logger logger = LoggerFactory.getLogger(RegistrationBootstrap.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		logger.info("Trying register");
		stockManager.registration();
	}
}
