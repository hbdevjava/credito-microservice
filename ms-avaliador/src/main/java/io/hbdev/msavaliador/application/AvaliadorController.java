package io.hbdev.msavaliador.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.hbdev.msavaliador.application.ex.DadosClienteNotFoundException;
import io.hbdev.msavaliador.application.ex.ErrorComunicacaoMicroserviceException;
import io.hbdev.msavaliador.domain.model.SituacaoCliente;

@RestController
@RequestMapping("/api/avaliacao-credito")
public class AvaliadorController {

	@Autowired
	AvaliadorService avaliadorService;

	@GetMapping
	public String status() {
		return "OK HEBERT e DORA vcs estao bem?";
	}

	
	@GetMapping(value = "situacao-cliente", params = "cpf")
	public ResponseEntity consultasSituacaoCliente(@RequestParam("cpf") String cpf) {
		try {
			SituacaoCliente situacaoCliente = avaliadorService.obterSituacaoCliente(cpf);
			return ResponseEntity.ok(situacaoCliente);
		} catch (DadosClienteNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (ErrorComunicacaoMicroserviceException e) {
			return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
		}	
	}
	
}