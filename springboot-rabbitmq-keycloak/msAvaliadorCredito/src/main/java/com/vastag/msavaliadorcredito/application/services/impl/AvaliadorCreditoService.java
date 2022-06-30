package com.vastag.msavaliadorcredito.application.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vastag.msavaliadorcredito.application.exceptions.ComunicaoAssincronaEntreServicosException;
import com.vastag.msavaliadorcredito.application.exceptions.DadosClienteNotFoundException;
import com.vastag.msavaliadorcredito.application.exceptions.ErroSolicitacaoEmissaoCartaoException;
import com.vastag.msavaliadorcredito.application.services.IAvaliadorCreditoService;
import com.vastag.msavaliadorcredito.domain.Cartao;
import com.vastag.msavaliadorcredito.domain.CartaoAprovado;
import com.vastag.msavaliadorcredito.domain.CartaoCliente;
import com.vastag.msavaliadorcredito.domain.DadosCliente;
import com.vastag.msavaliadorcredito.domain.DadosSolicitacaoEmissaoCartao;
import com.vastag.msavaliadorcredito.domain.ProtocoloSolicitacaoCartao;
import com.vastag.msavaliadorcredito.domain.RetornoAvaliacaoCliente;
import com.vastag.msavaliadorcredito.domain.SituacaoCliente;
import com.vastag.msavaliadorcredito.infrastructure.broker.queue.SolicitacaoEmissaoCartaoPublisher;
import com.vastag.msavaliadorcredito.infrastructure.repositories.CartoesResourceRepository;
import com.vastag.msavaliadorcredito.infrastructure.repositories.ClienteResourceRepository;

import feign.FeignException.FeignClientException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("avaliadorCliente")
public class AvaliadorCreditoService implements IAvaliadorCreditoService {

	private final ClienteResourceRepository clienteRepo;
	private final CartoesResourceRepository cartoesRepo;
	private final SolicitacaoEmissaoCartaoPublisher emissaoPublisher;

	@Override
	public SituacaoCliente obterSituacaoCliente(String cpf)
			throws DadosClienteNotFoundException, ComunicaoAssincronaEntreServicosException {

		try {
			ResponseEntity<DadosCliente> dadosClienteResponse = clienteRepo.dadosClienteByCPF(cpf);
			ResponseEntity<List<CartaoCliente>> dadosCartoesResponse = cartoesRepo.getCartoesByCliente(cpf);

			return SituacaoCliente.builder().cliente(dadosClienteResponse.getBody())
					.cartoes(dadosCartoesResponse.getBody()).build();
		} catch (FeignClientException e) {

			if (e.status() == HttpStatus.NOT_FOUND.value()) {
				throw new DadosClienteNotFoundException();
			}
			throw new ComunicaoAssincronaEntreServicosException(e.getMessage(), e.status());
		}

	}

	@Override
	public RetornoAvaliacaoCliente avaliar(String cpf, BigDecimal renda)
			throws DadosClienteNotFoundException, ComunicaoAssincronaEntreServicosException {
		try {
			ResponseEntity<DadosCliente> dadosClienteResponse = clienteRepo.dadosClienteByCPF(cpf);
			ResponseEntity<List<Cartao>> cartoesByRendaResponde = cartoesRepo.getCartoesRendaBetween(renda);

			List<Cartao> cartoes = cartoesByRendaResponde.getBody();
			var listaCartoesAprovados = cartoes.stream().map(cartao -> {

				DadosCliente dadosCliente = dadosClienteResponse.getBody();

				BigDecimal limiteBasico = cartao.getLimiteBasico();
				BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
				var fator = idadeBD.divide(BigDecimal.valueOf(10));
				BigDecimal limiteAprovado = fator.multiply(limiteBasico);

				CartaoAprovado aprovado = new CartaoAprovado();
				aprovado.setCartao(cartao.getNome());
				aprovado.setBandeira(cartao.getBandeira());
				aprovado.setLimiteAprovado(limiteAprovado);

				return aprovado;
			}).collect(Collectors.toList());

			return new RetornoAvaliacaoCliente(listaCartoesAprovados);

		} catch (FeignClientException e) {

			if (e.status() == HttpStatus.NOT_FOUND.value()) {
				throw new DadosClienteNotFoundException();
			}
			throw new ComunicaoAssincronaEntreServicosException(e.getMessage(), e.status());
		}
	}

	@Override
	public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados) {
		try {
			emissaoPublisher.solicitarCartao(dados);
			return new ProtocoloSolicitacaoCartao(UUID.randomUUID().toString());
		} catch (JsonProcessingException e) {
			throw new ErroSolicitacaoEmissaoCartaoException(e.getMessage());
		}
		
	}

}
