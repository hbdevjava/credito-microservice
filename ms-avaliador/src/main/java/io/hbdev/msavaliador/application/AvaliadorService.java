package io.hbdev.msavaliador.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliadorService {
	

	
	public SituacaoCliente obterSituacaoCliente(String cpf)
			throws DadosClienteNotFoundException, ErrorComunicacaoMicroserviceException {
		try {
			ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClients.dadosCliente(cpf);
			ResponseEntity<List<CartaoCliente>> dadosCartaoResponse = cartoesResourceClients.getCartoesByCliente(cpf);

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
