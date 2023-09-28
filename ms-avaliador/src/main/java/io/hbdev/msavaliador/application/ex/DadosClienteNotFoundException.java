package io.hbdev.msavaliador.application.ex;

public class DadosClienteNotFoundException extends Exception{
	
	public DadosClienteNotFoundException() {
		super("Dados do Cliente Não Foi encontrado");
	}
}
