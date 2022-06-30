package com.vastag.msavaliadorcredito.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RetornoAvaliacaoCliente {
	private List<CartaoAprovado> cartoes;
}
