package io.hbdev.msclientes.application;





import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.hbdev.msclientes.application.representation.ClienteSaveRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/clientes")
@Slf4j
public class ClienteController {

	@Autowired
    private ClienteService serviceClienteService;

    @GetMapping
    public String status(){
    	log.info("Obtendo Status do MS de clientes");
        return "Vikas Wrestler";
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf){
    	var cliente = serviceClienteService.getByCPF(cpf);
    	if(cliente.isEmpty()){
    		return ResponseEntity.notFound().build();
    	}
    	return ResponseEntity.ok(cliente);
    }
    
    @PostMapping
    public ResponseEntity save(@RequestBody ClienteSaveRequest request){
        var cliente = request.toModel();
        serviceClienteService.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

}