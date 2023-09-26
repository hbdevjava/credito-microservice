package io.hbdev.msavaliador.domain.model;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor//-> CRIA CONTRUTOR COM TODOS OS ARGUMENTOS
@NoArgsConstructor//-> CRIA CONSTRUTOR SEM ARGUMENTOS (vazio)
@Builder
//-> FACILITA A CRIAÇAO DO OBJ DO TIPO SituacaoCliente
public class SituacaoCliente {
	
	private DadosCliente cliente;
	private List<CartaoCliente> cartoes;
	
}
