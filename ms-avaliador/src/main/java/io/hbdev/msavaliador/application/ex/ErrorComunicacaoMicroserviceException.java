package io.hbdev.msavaliador.application.ex;

import lombok.Getter;

public class ErrorComunicacaoMicroserviceException extends Exception{
	
	@Getter
	private Integer status;
	
	public ErrorComunicacaoMicroserviceException(String msg, Integer status) {
		super(msg);
		this.status = status;
	}
}
