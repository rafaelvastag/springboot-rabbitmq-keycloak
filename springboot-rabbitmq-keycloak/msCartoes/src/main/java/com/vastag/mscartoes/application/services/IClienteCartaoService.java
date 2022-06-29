package com.vastag.mscartoes.application.services;

import java.util.List;

import com.vastag.mscartoes.domain.ClienteCartao;

public interface IClienteCartaoService {

	public ClienteCartao save(ClienteCartao c);
	
	public List<ClienteCartao> getClienteCartoesByCPF(String cpf);
	
}
