package com.quotationmanagement.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quotationmanagement.service.StockManagerApiService;

@RestController
@RequestMapping("/stockcache")
public class StockRest {

	@Autowired
	private StockManagerApiService stockManager;

	@DeleteMapping
	public void resetCache() {
		this.stockManager.resetCache();
	}

}
