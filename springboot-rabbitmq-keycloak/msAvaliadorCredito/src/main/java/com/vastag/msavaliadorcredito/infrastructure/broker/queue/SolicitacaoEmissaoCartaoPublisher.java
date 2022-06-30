package com.vastag.msavaliadorcredito.infrastructure.broker.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vastag.msavaliadorcredito.domain.DadosSolicitacaoEmissaoCartao;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SolicitacaoEmissaoCartaoPublisher {

	private final RabbitTemplate rabbitTemplate;
	private final Queue queueEmissaoCartoes;
	
	public void solicitarCartao(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException {
		var message = convertObjectToJson(dados);
		rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(), message);
	}
	
	private String convertObjectToJson(Object dados) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dados);
	}
}
