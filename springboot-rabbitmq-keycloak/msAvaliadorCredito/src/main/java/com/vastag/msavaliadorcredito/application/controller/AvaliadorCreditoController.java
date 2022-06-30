package com.vastag.msavaliadorcredito.application.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vastag.msavaliadorcredito.application.exceptions.ComunicaoAssincronaEntreServicosException;
import com.vastag.msavaliadorcredito.application.exceptions.DadosClienteNotFoundException;
import com.vastag.msavaliadorcredito.application.exceptions.ErroSolicitacaoEmissaoCartaoException;
import com.vastag.msavaliadorcredito.application.services.IAvaliadorCreditoService;
import com.vastag.msavaliadorcredito.domain.DadosAvaliacao;
import com.vastag.msavaliadorcredito.domain.DadosSolicitacaoEmissaoCartao;
import com.vastag.msavaliadorcredito.domain.ProtocoloSolicitacaoCartao;
import com.vastag.msavaliadorcredito.domain.SituacaoCliente;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/avaliador-credito")
public class AvaliadorCreditoController {

	@Qualifier("obterSituacaoCliente")
	private final IAvaliadorCreditoService service;

	@GetMapping(value = "/status")
	public String status() {
		log.info("Obtendo o status do microservico de clientes");
		return "ok";
	}

	@GetMapping(value = "/situacao-cliente", params = "cpf")
	public ResponseEntity<?> consultaSituacaoCliente(@RequestParam("cpf") String cpf) {
		SituacaoCliente situacaoCliente;
		try {
			situacaoCliente = service.obterSituacaoCliente(cpf);
			return ResponseEntity.ok(situacaoCliente);
		} catch (DadosClienteNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (ComunicaoAssincronaEntreServicosException e) {
			return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> avaliar(@RequestBody DadosAvaliacao dados)
			throws DadosClienteNotFoundException, ComunicaoAssincronaEntreServicosException {
		return ResponseEntity.ok(service.avaliar(dados.getCpf(), dados.getRenda()));
	}

	@PostMapping("solicitacoes-cartao")
	public ResponseEntity<?> solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartao dados) {
		try {
			ProtocoloSolicitacaoCartao protocoloSolicitacaoCartao = service
					.solicitarEmissaoCartao(dados);
			return ResponseEntity.ok(protocoloSolicitacaoCartao);
		} catch (ErroSolicitacaoEmissaoCartaoException e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
}
