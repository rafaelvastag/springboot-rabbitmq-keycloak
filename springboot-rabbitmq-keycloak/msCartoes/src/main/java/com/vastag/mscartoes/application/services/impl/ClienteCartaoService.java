package com.vastag.mscartoes.application.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vastag.mscartoes.application.services.IClienteCartaoService;
import com.vastag.mscartoes.domain.ClienteCartao;
import com.vastag.mscartoes.infrastructure.repository.ClienteCartaoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService implements IClienteCartaoService {

	private final ClienteCartaoRepository repo;

	@Override
	public ClienteCartao save(ClienteCartao c) {
		return repo.save(c);
	}

	@Override
	public List<ClienteCartao> getClienteCartoesByCPF(String cpf) {
		return repo.findByCpf(cpf);
	}
}
