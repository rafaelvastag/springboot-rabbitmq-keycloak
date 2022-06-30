package com.vastag.msavaliadorcredito.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartaoCliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String bandeira;
	private BigDecimal limiteLiberado;
}
