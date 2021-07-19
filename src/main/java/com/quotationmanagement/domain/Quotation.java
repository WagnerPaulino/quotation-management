package com.quotationmanagement.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "QUOTATION")
@JsonInclude(Include.NON_EMPTY)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" }, callSuper = false)
public class Quotation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_QUOTATION")
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "SEQ_QUOTATION", sequenceName = "SEQ_QUOTATION")
	private Long id;

	@Column(unique = true)
	@NotNull(message = "StockId can't be null")
	private String stockId;

	@ElementCollection
	@CollectionTable(name = "quote_stock", joinColumns = { @JoinColumn(name = "quotation_id") })
	@MapKeyColumn(name = "date_col")
	@Column(name = "value_col")
	@JsonProperty("quotes")
	private Map<LocalDate, BigDecimal> quotes = new HashMap<LocalDate, BigDecimal>();
}
