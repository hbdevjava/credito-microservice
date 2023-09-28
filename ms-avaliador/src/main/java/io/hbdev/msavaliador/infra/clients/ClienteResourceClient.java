package io.hbdev.msavaliador.infra.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.hbdev.msavaliador.domain.model.DadosCliente;

@FeignClient(value="ms-clientes", path = "/api/clientes")
//@FeignClient-> TB É UM CLIENTE HTTP (COMUNICAÇAO SINCRONA)
public interface ClienteResourceClient {
	
	@GetMapping
	String status();
	
	@GetMapping(params = "cpf")
	ResponseEntity<DadosCliente> dadosCliente(@RequestParam("cpf") String cpf);
}
