package com.vastag.mscartoes.application.controller.dto;

import java.math.BigDecimal;

import com.vastag.mscartoes.domain.Cartao;
import com.vastag.mscartoes.domain.enums.BandeiraCartao;

import lombok.Data;

@Data
public class CartaoSaveDTO {

	private String nome;
	private BandeiraCartao bandeira;
	private BigDecimal renda;
	private BigDecimal limiteBasico;
	
	public Cartao toModel() {
		return new Cartao(nome, bandeira, renda, limiteBasico);
	}
	
}
