package br.gama.itau.projetofinal2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gama.itau.projetofinal2.model.Conta;
import br.gama.itau.projetofinal2.service.ContaService;
import br.gama.itau.projetofinal2.service.TransferenciaService;



@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;
    
    @Autowired
    TransferenciaService transferenciaService;
    
    // - /contas (POST) - para cadastrar uma nova conta, chamando o serviço
    // adicionarConta, podendo retornar 201 ou 400
    @PostMapping // OK
    public ResponseEntity<Conta> adicionarConta(@RequestBody Conta novaConta) {
        Conta contaInserida = contaService.adicionarConta(novaConta);
        if (contaInserida == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<Conta>(contaInserida, HttpStatus.CREATED);
    }


    // - /contas/{id} (GET)- chama o serviço recuperarPeloNumero,
    // podendo retornar 200 ou 404
    @GetMapping("/{numeroConta}")
    public Conta recuperarPeloNumero(@PathVariable Integer numeroConta) {
        return contaService.recuperarPeloNumero(numeroConta);
    }

    
    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<Conta>> recuperarContasPeloCliente(@PathVariable Integer id) {
        List<Conta> conta = contaService.recuperarContasPeloCliente(id);
        return ResponseEntity.ok().body(conta);
    }
    
    @PostMapping("/transferencia")
    public boolean transferencia(@RequestParam("contaOrigem") int contaOrigem,
                                 @RequestParam("contaDestino") int contaDestino,
                                 @RequestParam("valor") double valor) {
        return transferenciaService.TransferirValores(contaOrigem, contaDestino, valor);
    }
}    
    
    
