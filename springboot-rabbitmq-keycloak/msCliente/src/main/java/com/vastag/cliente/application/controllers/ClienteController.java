package com.vastag.cliente.application.controllers;

import java.net.URI;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vastag.cliente.application.dto.ClienteSaveDTO;
import com.vastag.cliente.application.services.IClienteService;
import com.vastag.cliente.domain.Cliente;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Qualifier(value = "clienteServiceImpl")
	private final IClienteService service;

	@GetMapping(value = "/status")
	public String status() {
		log.info("Obtendo o status do microservico de clientes");
		return "ok";
	}

	@GetMapping
	public ResponseEntity<Cliente> dadosClienteByCPF(@PathParam(value = "cpf") String cpf) {
		Optional<Cliente> cliente = service.getByCPF(cpf);
		return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<URI> save(@RequestBody ClienteSaveDTO request) {
		var cliente = request.toModel();
		service.save(cliente);

		URI headerLocation = ServletUriComponentsBuilder.fromHttpUrl("http://localhost:8080/clientes/").query("cpf={cpf}")
				.buildAndExpand(cliente.getCpf()).toUri();

		return ResponseEntity.created(headerLocation).build();
	}

}
