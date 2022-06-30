package com.vastag.msavaliadorcredito.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DadosAvaliacao {
	private String cpf;
	private BigDecimal renda;
}
