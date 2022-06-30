package com.vastag.msavaliadorcredito.application.services;

import java.math.BigDecimal;

import com.vastag.msavaliadorcredito.application.exceptions.ComunicaoAssincronaEntreServicosException;
import com.vastag.msavaliadorcredito.application.exceptions.DadosClienteNotFoundException;
import com.vastag.msavaliadorcredito.domain.DadosSolicitacaoEmissaoCartao;
import com.vastag.msavaliadorcredito.domain.ProtocoloSolicitacaoCartao;
import com.vastag.msavaliadorcredito.domain.RetornoAvaliacaoCliente;
import com.vastag.msavaliadorcredito.domain.SituacaoCliente;

public interface IAvaliadorCreditoService {

	public SituacaoCliente obterSituacaoCliente(String cpf)
			throws DadosClienteNotFoundException, ComunicaoAssincronaEntreServicosException;
	
	public RetornoAvaliacaoCliente avaliar(String cpf, BigDecimal renda)
			throws DadosClienteNotFoundException, ComunicaoAssincronaEntreServicosException;
	
	public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados);
}
