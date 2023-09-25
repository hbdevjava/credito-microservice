package io.hbdev.msclientes.application;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.hbdev.msclientes.application.representation.ClienteSaveRequest;

import java.net.URI;

@RestController
@RequestMapping("clientes")
public class ClienteController {

	@Autowired
    private ClienteService serviceClienteService;

    @GetMapping
    public String status(){
        return "Vikas Wrestler";
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

    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf){
        var cliente = serviceClienteService.getByCPF(cpf);
        if(cliente.isEmpty()){
        	return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }
}