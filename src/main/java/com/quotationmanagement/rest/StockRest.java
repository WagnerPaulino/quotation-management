package com.quotationmanagement.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quotationmanagement.service.StockManagerApiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stockcache")
@RequiredArgsConstructor
public class StockRest {

	private final StockManagerApiService stockManager;

	@DeleteMapping
	public void resetCache() {
		this.stockManager.resetCache();
	}

}
