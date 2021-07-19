package com.quotationmanagement.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.quotationmanagement.domain.Quotation;
import com.quotationmanagement.repository.QuotationRepository;

@SpringBootTest
public class QuotationServiceTest {

	@Autowired
	private StockManagerApiService stockManager;

	private QuotationRepository quotationRepository;

	private QuotationService quotationService;

	@BeforeEach
	void setUp() {
		quotationRepository = mock(QuotationRepository.class);
		quotationService = new QuotationService(quotationRepository, stockManager);
	}

	@AfterEach
	void tearDown() {
		clearInvocations(quotationRepository);
	}

	private Quotation getQuotationTest() {
		Quotation q = new Quotation();
		q.setStockId("petr4");
		q.setQuotes(new HashMap<LocalDate, BigDecimal>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put(LocalDate.now(), BigDecimal.valueOf(10));
			}
		});
		return q;
	}

	@Test
	public void whenUserSaveQuotation() {
		Quotation q = this.getQuotationTest();
		quotationService.insert(q);
		verify(quotationRepository, times(1)).save(any(Quotation.class));
	}

}
