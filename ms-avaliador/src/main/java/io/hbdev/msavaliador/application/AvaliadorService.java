package io.hbdev.msavaliador.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import feign.FeignException;
import io.hbdev.msavaliador.application.ex.DadosClienteNotFoundException;
import io.hbdev.msavaliador.application.ex.ErrorComunicacaoMicroserviceException;
import io.hbdev.msavaliador.domain.model.Cartao;
import io.hbdev.msavaliador.domain.model.CartaoAprovado;
import io.hbdev.msavaliador.domain.model.CartaoCliente;
import io.hbdev.msavaliador.domain.model.DadosCliente;
import io.hbdev.msavaliador.domain.model.RetornoAvaliacaoCliente;
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

			return SituacaoCliente.builder().cliente(dadosClienteResponse.getBody())
					.cartoes(dadosCartaoResponse.getBody()).build();
		} catch (FeignException.FeignClientException e) {
			int status = e.status();
			if (HttpStatus.NOT_FOUND.value() == status) {
				throw new DadosClienteNotFoundException();
			}
			throw new ErrorComunicacaoMicroserviceException(e.getMessage(), status);
		}

	}

	public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda)
			throws DadosClienteNotFoundException, ErrorComunicacaoMicroserviceException {
		try {
			//FUI NA LISTA DE CLIENTES E PEGUEI OS DADOS DELE
			ResponseEntity<DadosCliente> dadosClienteResponse = clienteClient.dadosCliente(cpf);
			//FUI NA LISTA DE CARTOES E PEGUEI OS QUE A REDA FOI ESPECIFICADA NA BUSCA
			ResponseEntity<List<Cartao>> cartaoResponde = cartaoClient.getCartoesRendaAteh(renda);
			//PEGUEI A LISTA DE CARTOES QUE ELE PODERIA TER
			List<Cartao> cartoes = cartaoResponde.getBody();
			//FOI FEITO O MAPEAMENTO PRA SABER QUANTOS CARTOES ESTÃO LIBERADOS PARA ESSE PERFIL DE CLIENTE
		    var listaCartoesAprovados =	cartoes.stream().map(cartao -> {
		    	DadosCliente dadosCliente = dadosClienteResponse.getBody();
		    	
		    	
		    	BigDecimal limiteBasico = cartao.getLimiteBasico();
		    	BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
		    	var fator = idadeBD.divide(BigDecimal.valueOf(10));
		    	BigDecimal limiteAprovado = fator.multiply(limiteBasico);
		    	
		    	CartaoAprovado aprovado = new CartaoAprovado();
				aprovado.setCartao(cartao.getNome());
				aprovado.setBandeira(cartao.getBandeira());
				aprovado.setLimiteAprovado(limiteAprovado);
				
				return aprovado;
		    }).collect(Collectors.toList());
		    
		    	return new RetornoAvaliacaoCliente(listaCartoesAprovados);
			
		} catch (FeignException.FeignClientException e) {
			int status = e.status();
			if (HttpStatus.NOT_FOUND.value() == status) {
				throw new DadosClienteNotFoundException();
			}
			throw new ErrorComunicacaoMicroserviceException(e.getMessage(), status);
		}
	}
}
