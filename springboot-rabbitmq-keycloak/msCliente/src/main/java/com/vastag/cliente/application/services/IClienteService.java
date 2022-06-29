package com.vastag.cliente.application.services;

import java.util.Optional;

import com.vastag.cliente.domain.Cliente;

public interface IClienteService {
	
	public Cliente save(Cliente c);

	public Optional<Cliente> getByCPF(String cpf);
}
