package io.hbdev.msclientes.application.representation;

import io.hbdev.msclientes.domain.Cliente;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
//-> CLASSE DE DTO (DATA TRANSFER OBJECT)
public class ClienteSaveRequest {

	private String cpf;
	private String nome;
	private Integer idade;
	
	//-> TRANSFORMA ESSE OBJETO ClienteSaveRequest EM Cliente PARA PODER SER PERSISTIDA NO BANCO DE DADOS
	public Cliente toModel() {
		return new Cliente(cpf, nome, idade);
	}
	
}
