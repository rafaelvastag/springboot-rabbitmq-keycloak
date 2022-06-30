package com.vastag.msavaliadorcredito.infrastructure.repositories;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vastag.msavaliadorcredito.domain.DadosCliente;

@FeignClient(value = "msclientes", path = "/clientes")
public interface ClienteResourceRepository {

	@GetMapping(params = "cpf")
	public ResponseEntity<DadosCliente> dadosClienteByCPF(@RequestParam(value = "cpf") String cpf);
}
