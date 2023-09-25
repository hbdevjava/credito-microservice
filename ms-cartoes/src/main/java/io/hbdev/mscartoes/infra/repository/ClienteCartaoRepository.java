package io.hbdev.mscartoes.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.hbdev.mscartoes.domain.ClienteCartao;

public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long> {
	
	List<ClienteCartao> findByCpf(String cpf);
	//-> ESSE METODO FAZ UMA BUSCA PELO CPF DENTRO DE UMA LISTA DE List<ClienteCartao>
}
