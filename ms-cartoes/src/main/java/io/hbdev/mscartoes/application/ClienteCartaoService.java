package io.hbdev.mscartoes.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.hbdev.mscartoes.domain.ClienteCartao;
import io.hbdev.mscartoes.infra.repository.ClienteCartaoRepository;

@Service
public class ClienteCartaoService {
	
	@Autowired
	private ClienteCartaoRepository cartaoRepository;
	
	public List<ClienteCartao> listaCartaoByCpf(String cpf){
		return cartaoRepository.findByCpf(cpf);
	}
	
}
