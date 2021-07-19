package com.quotationmanagement.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.quotationmanagement.domain.Quotation;
import com.quotationmanagement.repository.QuotationRepository;
import com.quotationmanagement.service.QuotationService;

@RestController
@RequestMapping("/quotations")
public class QuotationRest {

	@Autowired
	private QuotationRepository quotationRepository;

	@Autowired
	private QuotationService quotationService;

	@GetMapping
	public List<Quotation> findAll() {
		return this.quotationRepository.findAll();
	}

	@GetMapping("/{stockId}")
	public Quotation findByStockId(@PathVariable String stockId) {
		return this.quotationService.findByStockId(stockId);
	}

	@PostMapping
	public Quotation insert(@RequestBody @Valid Quotation quotation) {
		return this.quotationService.insert(quotation);
	}

	@PutMapping
	public Quotation update(@RequestBody @Valid Quotation quotation) {
		return this.quotationService.update(quotation);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		this.quotationRepository.deleteById(id);
	}

}
