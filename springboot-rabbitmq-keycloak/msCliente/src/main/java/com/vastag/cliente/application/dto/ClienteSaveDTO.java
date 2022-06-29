package com.vastag.cliente.application.dto;

import java.io.Serializable;

import com.vastag.cliente.domain.Cliente;

import lombok.Data;

@Data
public class ClienteSaveDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cpf;
	private String nome;
	private Integer idade;

	public Cliente toModel() {
		return new Cliente(cpf, nome, idade);
	}

}
