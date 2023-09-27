package io.hbdev.msavaliador.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.hbdev.msavaliador.domain.model.SituacaoCliente;

@RestController
@RequestMapping("/api/avaliacao-credito")
public class AvaliadorController {
	
	@Autowired
	private AvaliadorService avaliadorService;
	
	@GetMapping
	public String status() {
		return "Dora";
	}
	
	@GetMapping(value = "situacao-cliente", params = "cpf")
	public ResponseEntity<SituacaoCliente> consultasSituacaoCliente(@RequestParam("cpf") String cpf) {
		
			SituacaoCliente situacaoCliente = avaliadorService.obterSituacaoCliente(cpf);
			return ResponseEntity.ok(situacaoCliente);
	}

}
