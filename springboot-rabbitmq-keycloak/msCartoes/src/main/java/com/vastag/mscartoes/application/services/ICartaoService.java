package com.vastag.mscartoes.application.services;

import java.math.BigDecimal;
import java.util.List;

import com.vastag.mscartoes.domain.Cartao;

public interface ICartaoService {

	public Cartao save(Cartao c);
	
	public List<Cartao> getCartoesRendaMenorIgual(BigDecimal renda);
	
}
