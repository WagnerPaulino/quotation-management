package com.quotationmanagement.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.quotationmanagement.domain.StockModel;

@Service
public class StockManagerApiService {

	@Autowired
	private Environment env;

	@Autowired
	private RestTemplate restTemplate;

	private final Logger logger = LoggerFactory.getLogger(StockManagerApiService.class);

	public String getBaseUrl() {
		return env.getProperty("stock-manager-url");
	}

	@Cacheable(value = "stock", key = "#stockId")
	public StockModel findStock(String stockId) {
		logger.info("fetching stockId from stock manager: " + stockId);
		String url = this.getBaseUrl() + "/stock/" + stockId;
		logger.info(url);
		return restTemplate.getForObject(url, StockModel.class);
	}

	@Caching(evict = { @CacheEvict(value = "stock", allEntries = true) })
	public void resetCache() {
		logger.info("Reseting stock manager cache");
	}

	public void registration() {
		Map<String, String> resgistrationBody = new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("host", env.getProperty("quotation-management-host"));
				put("port", env.getProperty("quotation-management-port"));
			}
		};
		String url = this.getBaseUrl() + "/notification/";
		logger.info("Registering to: " + url);
		restTemplate.postForLocation(url, resgistrationBody);
	}

}
