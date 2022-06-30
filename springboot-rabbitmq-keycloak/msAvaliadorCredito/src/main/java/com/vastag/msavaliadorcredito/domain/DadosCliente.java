package com.vastag.msavaliadorcredito.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class DadosCliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private Integer idade;
}
