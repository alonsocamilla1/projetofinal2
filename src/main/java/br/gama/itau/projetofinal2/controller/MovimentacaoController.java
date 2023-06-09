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
import org.springframework.web.bind.annotation.RestController;

import br.gama.itau.projetofinal2.model.Conta;
import br.gama.itau.projetofinal2.model.Movimentacao;
import br.gama.itau.projetofinal2.service.ContaService;
import br.gama.itau.projetofinal2.service.MovimentacaoService;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @Autowired
    private ContaService contaService;

    
    @PostMapping
    public Movimentacao cadastrarMovimentacao(@RequestBody Movimentacao movimentacao) {
      
        Conta conta = contaService.recuperarPeloNumero(movimentacao.getConta().getNumeroConta());
        double saldo = conta.getSaldo();

        if (movimentacao.getTipoOperacao() == 1) {
            saldo = saldo + movimentacao.getValor();
        } else {
            saldo = saldo - movimentacao.getValor();
        }

        contaService.alterarDados(conta, saldo);

        Movimentacao movimentacaoInserido = movimentacaoService.cadastrarMovimentacao(movimentacao);
        
        return movimentacaoInserido;

    }
    @GetMapping("/{numeroConta}")
    public ResponseEntity<List<Movimentacao>> recuperarTodas(@PathVariable int numeroConta) {
        List<Movimentacao> movimentacao = movimentacaoService.recuperarTodas(numeroConta);
        return new ResponseEntity<>(movimentacao, HttpStatus.OK);
 }
}
