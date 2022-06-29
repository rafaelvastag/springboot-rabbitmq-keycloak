package com.vastag.mscartoes.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vastag.mscartoes.domain.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

	List<Cartao> findByRendaLessThanEqual(BigDecimal renda);

}
