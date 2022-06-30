package com.vastag.msavaliadorcredito.domain;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SituacaoCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	private DadosCliente cliente;
	private List<CartaoCliente> cartoes;
}
