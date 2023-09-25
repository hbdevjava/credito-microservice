package io.hbdev.mscartoes.infra.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.hbdev.mscartoes.domain.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long>{
	
	//-> RECEBE A RENDA EM BigDecimal E RETORNA A LISTA DE CARTOES COM  RENDA MENOR OU = RENDA DO CLIENTE 
	List<Cartao> findByRendaLessThanEqual(BigDecimal renda);
}
