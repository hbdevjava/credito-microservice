package io.hbdev.msavaliador.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import io.hbdev.msavaliador.domain.model.DadosCliente;
import io.hbdev.msavaliador.domain.model.SituacaoCliente;
import io.hbdev.msavaliador.infra.clients.ClienteResourceClient;

@Service
public class AvaliadorService {
	
	@Autowired
	private ClienteResourceClient clienteClient;
	
	public SituacaoCliente obterSituacaoCliente(String cpf) {
			//OBTER DADOS DE -> MS-CLIENTES
			//OBTER CARTOES DO CLIENTE -> MS-CARTOES
		
			ResponseEntity<DadosCliente> dadosClienteResponse = clienteClient.dadosCliente(cpf);
			

			return SituacaoCliente.builder()
					.cliente(dadosClienteResponse.getBody())
					.build();
		
	}

}
