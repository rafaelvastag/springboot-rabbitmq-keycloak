package com.vastag.msavaliadorcredito.infrastructure.broker.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

	@Value("${mq.queues.emissao-cartoes}")
	private String missaoCartoesQueue;

	@Bean
	public Queue queueEmissaoCartoes() {
		return new Queue(missaoCartoesQueue, true);
	}
}
