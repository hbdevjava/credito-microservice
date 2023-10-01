package io.hbdev.msavaliador.infra.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.hbdev.msavaliador.domain.model.DadosSolicitacaoEmissaoCartao;

@Component
public class SolicitaçaoEmissaoCartaoPublisher {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private Queue queueEmissaoCartoes;
	
	// NO Publisher É FEITA A SERIALIZAÇAO (CONVERTO OS DADOS PARA JSON) -> JA NO
	// Subscriber É FEITA A
	// DECERIALIZAÇAO OU SEJA (POSSO DE JSON PARA DADOS)
	
	
	public void solicitacaoCartao(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException{
		var json = convertIntoJason(dados);
		//rabbitTemplate -> PUBLICA A MSG PRA FILA EmissaoCartoes
		rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(), json);
	}
	
	//METODO PARA CONVERTER DADOS EM JSON
		private String convertIntoJason(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException {
			//CONVERTE STRING EM JSON
			ObjectMapper mapper = new ObjectMapper();
			var json = mapper.writeValueAsString(dados);
			return json;
			
		}

	
}
