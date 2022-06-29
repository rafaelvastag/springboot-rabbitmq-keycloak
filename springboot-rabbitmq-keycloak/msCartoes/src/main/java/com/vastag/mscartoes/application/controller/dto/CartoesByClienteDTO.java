package com.vastag.mscartoes.application.controller.dto;

import java.math.BigDecimal;

import com.vastag.mscartoes.domain.ClienteCartao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartoesByClienteDTO {
	private String nome;
	private String bandeira;
	private BigDecimal limiteLiberado;
	
	public static CartoesByClienteDTO fromModel(ClienteCartao c) {
		return new CartoesByClienteDTO(c.getCartao().getNome(), c.getCartao().getBandeira().toString(), c.getLimite());
	}
}
