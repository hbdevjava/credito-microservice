package io.hbdev.mscartoes.infra.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.hbdev.mscartoes.domain.Cartao;
import io.hbdev.mscartoes.domain.ClienteCartao;
import io.hbdev.mscartoes.domain.DadosSolicitacaoEmissaoCartao;
import io.hbdev.mscartoes.infra.repository.CartaoRepository;
import io.hbdev.mscartoes.infra.repository.ClienteCartaoRepository;

@Component
public class EmissaoCartaoSubscriber {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private ClienteCartaoRepository clienteCartaoRepository;

	// NO Publisher É FEITA A SERIALIZAÇAO (CONVERTO OS DADOS PARA JSON) -> JA NO
	// Subscriber É FEITA A
	// DESSERIALIZAÇAO OU SEJA (POSSO DE JSON PARA DADOS)

	@RabbitListener(queues = "${mq.queues.emissao-cartoes}")
	public void receberSolicitacaoEmissao(@Payload String payload) {
		//SEMPRE QUE FOR FAZER SERIALIZAÇAO OU DESSERIALIZAÇAO ELE LANÇA UMA EXCEPTION -> JsonProcessingException
		try {
			ObjectMapper mapper = new ObjectMapper();
			DadosSolicitacaoEmissaoCartao dados = mapper.readValue(payload, DadosSolicitacaoEmissaoCartao.class);
			Cartao cartao = cartaoRepository.findById(dados.getIdCartao()).orElseThrow();
			ClienteCartao clienteCartao = new ClienteCartao();
			clienteCartao.setCartao(cartao);
			clienteCartao.setCpf(dados.getCpf());
			clienteCartao.setLimite(dados.getLimiteLiberado());
			
			clienteCartaoRepository.save(clienteCartao);
			
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
	}

}
