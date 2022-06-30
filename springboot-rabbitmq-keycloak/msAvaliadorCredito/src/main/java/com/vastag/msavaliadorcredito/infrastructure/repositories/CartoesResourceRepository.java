package com.vastag.msavaliadorcredito.infrastructure.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vastag.msavaliadorcredito.domain.Cartao;
import com.vastag.msavaliadorcredito.domain.CartaoCliente;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartoesResourceRepository {

	@GetMapping(params = "cpf")
	public ResponseEntity<List<CartaoCliente>> getCartoesByCliente(@RequestParam(value = "cpf") String cpf);
	
	@GetMapping(params = "renda")
	public ResponseEntity<List<Cartao>> getCartoesRendaBetween(@RequestParam(value = "renda") BigDecimal renda);
}
