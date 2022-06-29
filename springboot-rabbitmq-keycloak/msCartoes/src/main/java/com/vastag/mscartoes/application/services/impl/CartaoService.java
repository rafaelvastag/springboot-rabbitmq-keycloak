package com.vastag.mscartoes.application.services.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vastag.mscartoes.application.services.ICartaoService;
import com.vastag.mscartoes.domain.Cartao;
import com.vastag.mscartoes.infrastructure.repository.CartaoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartaoService implements ICartaoService {

	private final CartaoRepository repo;

	@Override
	public Cartao save(Cartao c) {
		return repo.save(c);
	}

	@Override
	public List<Cartao> getCartoesRendaMenorIgual(BigDecimal renda) {
		return repo.findByRendaLessThanEqual(renda);
	}
}
