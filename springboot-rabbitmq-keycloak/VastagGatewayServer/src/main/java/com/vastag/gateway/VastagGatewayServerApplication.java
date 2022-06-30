package com.vastag.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class VastagGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VastagGatewayServerApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder
				.routes()
					.route(r -> r.path("/clientes/**").uri("lb://msclientes"))
					.route(r -> r.path("/cartoes/**").uri("lb://mscartoes"))
					.route(r -> r.path("/avaliador-credito/**").uri("lb://msavaliadorcredito"))
				.build();
	}
}
