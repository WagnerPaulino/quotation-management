package com.quotationmanagement.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.quotationmanagement.domain.Quotation;
import com.quotationmanagement.domain.StockModel;
import com.quotationmanagement.repository.QuotationRepository;
import com.quotationmanagement.specification.QuotationSpecification;

@Service
public class QuotationService {

	private QuotationRepository quotationRepository;

	private StockManagerApiService stockManager;

	@Autowired
	public QuotationService(QuotationRepository quotationRepository, StockManagerApiService stockManager) {
		this.quotationRepository = quotationRepository;
		this.stockManager = stockManager;
	}

	private void validateStockId(String stockId) {
		StockModel stock = this.stockManager.findStock(stockId);
		Assert.isTrue(stock != null && stock.getId() != null && stock.getId().equals(stockId),
				"The following StockId is invalid or does not exist: " + stockId);
	}

	private void validateUniqueQuotation(String stockId) {
		Boolean isPresent = this.quotationRepository.findOne(QuotationSpecification.findByStockId(stockId)).isPresent();
		Assert.isTrue(!isPresent, "Already exist a quotation for: " + stockId);
	}

	public Quotation findByStockId(String stockId) {
		return this.quotationRepository.findOne(QuotationSpecification.findByStockId(stockId)).orElseThrow(
				() -> new EntityNotFoundException("Quotation with following stockId was not found: " + stockId));
	}

	public Quotation insert(Quotation quotation) {
		this.validateUniqueQuotation(quotation.getStockId());
		this.validateStockId(quotation.getStockId());
		return this.quotationRepository.save(quotation);
	}

	public Quotation update(Quotation quotation) {
		this.validateStockId(quotation.getStockId());
		Quotation quotationSaved = this.findByStockId(quotation.getStockId());
		quotation.setId(quotationSaved.getId());
		this.quotationRepository.save(quotation);
		return quotation;
	}

}
