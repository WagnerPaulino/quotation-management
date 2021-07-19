package com.quotationmanagement.specification;

import org.springframework.data.jpa.domain.Specification;

import com.quotationmanagement.domain.Quotation;

public class QuotationSpecification {

	public static Specification<Quotation> findByStockId(String stockId) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("stockId"), stockId);
	}

}
