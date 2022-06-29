package com.vastag.mscartoes.application.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vastag.mscartoes.application.controller.dto.CartaoSaveDTO;
import com.vastag.mscartoes.application.controller.dto.CartoesByClienteDTO;
import com.vastag.mscartoes.application.services.ICartaoService;
import com.vastag.mscartoes.application.services.IClienteCartaoService;
import com.vastag.mscartoes.domain.Cartao;
import com.vastag.mscartoes.domain.ClienteCartao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/cartoes")
public class CartoesController {

	private final ICartaoService service;
	private final IClienteCartaoService cartaoClienteservice;

	@GetMapping(value = "/status")
	public String status() {
		log.info("Obtendo o status do microservico de clientes");
		return "ok";
	}

	@GetMapping(params = "renda")
	public ResponseEntity<List<Cartao>> getCartoesRendaBetween(@RequestParam(value = "renda") BigDecimal renda) {
		List<Cartao> cartoes = service.getCartoesRendaMenorIgual(renda);
		return ResponseEntity.ok(cartoes);
	}

	@GetMapping(params = "cpf")
	public ResponseEntity<List<CartoesByClienteDTO>> getCartoesByCliente(@RequestParam(value = "cpf") String cpf) {
		List<ClienteCartao> cartoes = cartaoClienteservice.getClienteCartoesByCPF(cpf);
		return ResponseEntity.ok(cartoes.stream().map(CartoesByClienteDTO::fromModel).collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<URI> save(@RequestBody CartaoSaveDTO request) {
		var cartao = request.toModel();
		service.save(cartao);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
