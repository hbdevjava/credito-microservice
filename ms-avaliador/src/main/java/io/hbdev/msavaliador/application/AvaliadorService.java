package io.hbdev.msavaliador.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import feign.FeignException;
import io.hbdev.msavaliador.application.ex.DadosClienteNotFoundException;
import io.hbdev.msavaliador.application.ex.ErroSolicitaçaoCartaoException;
import io.hbdev.msavaliador.application.ex.ErrorComunicacaoMicroserviceException;
import io.hbdev.msavaliador.domain.model.CartaoCliente;
import io.hbdev.msavaliador.domain.model.DadosCliente;
import io.hbdev.msavaliador.domain.model.SituacaoCliente;
import io.hbdev.msavaliador.infra.clients.CartaoResourceClient;
import io.hbdev.msavaliador.infra.clients.ClienteResourceClient;

@Service
public class AvaliadorService {

	@Autowired
	private ClienteResourceClient clienteClient;
	
	@Autowired
	private CartaoResourceClient cartaoClient;

		
	public SituacaoCliente obterSituacaoCliente(String cpf)
			throws DadosClienteNotFoundException, ErrorComunicacaoMicroserviceException {
		try {
			ResponseEntity<DadosCliente> dadosClienteResponse = clienteClient.dadosCliente(cpf);
			ResponseEntity<List<CartaoCliente>> dadosCartaoResponse = cartaoClient.getCartoesByCliente(cpf);

			return SituacaoCliente.builder()
					.cliente(dadosClienteResponse.getBody())
					.cartoes(dadosCartaoResponse.getBody())
					.build();
		} catch (FeignException.FeignClientException e) {
			int status = e.status();
			if (HttpStatus.NOT_FOUND.value() == status) {	
				throw new DadosClienteNotFoundException();
			}
			throw new ErrorComunicacaoMicroserviceException(e.getMessage(), status);
		}

	}
	

}
