package io.hbdev.msavaliador.infra.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.hbdev.msavaliador.domain.model.CartaoCliente;




@FeignClient(value="ms-cartoes", path = "/api/cartoes")
public interface CartaoResourceClient {

		@GetMapping("cpf")
		ResponseEntity<List<CartaoCliente>> getCartoesByCliente(@RequestParam("cpf")  String cpf);
		
		
	}