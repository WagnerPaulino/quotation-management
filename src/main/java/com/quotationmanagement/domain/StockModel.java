package com.quotationmanagement.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(Include.NON_EMPTY)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" }, callSuper = false)
public class StockModel {

	private String id;

	private String description;
}
