package com.vastag.cliente.application.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vastag.cliente.application.services.IClienteService;
import com.vastag.cliente.domain.Cliente;
import com.vastag.cliente.infrastructure.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("clienteServiceImpl")
public class ClienteService implements IClienteService {
	
	private final ClienteRepository repo;

	@Override
	public Cliente save(Cliente c) {
		return repo.save(c);
	}

	@Override
	public Optional<Cliente> getByCPF(String cpf) {
		return repo.findByCpf(cpf);
	}

}
