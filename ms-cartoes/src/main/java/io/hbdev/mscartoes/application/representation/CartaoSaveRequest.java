package io.hbdev.mscartoes.application.representation;

import java.math.BigDecimal;

import io.hbdev.mscartoes.domain.BandeiraCartoes;
import io.hbdev.mscartoes.domain.Cartao;
import lombok.Data;

@Data
//-> DTO (DATA TRANSFER OBJECT)
public class CartaoSaveRequest {
	
	private String nome;
	private BandeiraCartoes bandeira;
	private BigDecimal renda;
	private BigDecimal limite;
	
	public Cartao toModel() {
		return new Cartao(nome, bandeira, renda, limite);
	}
}
