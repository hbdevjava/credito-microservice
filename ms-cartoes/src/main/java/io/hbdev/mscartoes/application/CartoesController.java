package io.hbdev.mscartoes.application;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.hbdev.mscartoes.application.representation.CartaoPorClienteResponse;
import io.hbdev.mscartoes.application.representation.CartaoSaveRequest;
import io.hbdev.mscartoes.domain.Cartao;
import io.hbdev.mscartoes.domain.ClienteCartao;
@RestController
@RequestMapping("/api/cartoes")
public class CartoesController {
	
	@Autowired
	private CartaoService service;
	
	@Autowired
	private ClienteCartaoService cartaoService;
	
	@GetMapping
    public String status(){
        return "Hebert Brito";
    }
	
	@PostMapping
	public ResponseEntity catastra(@RequestBody CartaoSaveRequest request) {
		Cartao cartao = request.toModel();
		service.save(cartao);
		return ResponseEntity.status(HttpStatus.CREATED).build();
		
	}	
	
	//-> LISTA DE CARTOES CORRESPONDENTE COM A RENDA DO CLIENTE 
	//-> (ex: RENDA ATE 5K ELE VAI LISTAR CARTOES COM ESSA RENDA ATE 5K)
		@GetMapping(params = "/renda")
		public ResponseEntity<List<Cartao>> getCartoesRendaAteh(@RequestParam Long renda){
			List<Cartao> list = service.getCartaoRendaMenorIgual(renda);
			return ResponseEntity.ok(list);
		}
		
		@GetMapping(params = "/cpf")
		public ResponseEntity<List<CartaoPorClienteResponse>> getCartoesByCliente(@RequestParam("cpf")  String cpf){
			List<ClienteCartao> lista = cartaoService.listaCartaoByCpf(cpf);
			List<CartaoPorClienteResponse> resultList = lista.stream()
					.map(CartaoPorClienteResponse::fromModel)
					.collect(Collectors.toList());
			
			return ResponseEntity.ok(resultList);
		}
		
}
