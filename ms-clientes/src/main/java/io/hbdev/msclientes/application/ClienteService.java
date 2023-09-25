package io.hbdev.msclientes.application;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.hbdev.msclientes.domain.Cliente;
import io.hbdev.msclientes.infra.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);

	}
	
	
	public Optional<Cliente> getByCPF(String cpf){
		return clienteRepository.findByCpf(cpf);
	}

}
