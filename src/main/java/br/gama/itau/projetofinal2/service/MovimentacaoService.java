package br.gama.itau.projetofinal2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gama.itau.projetofinal2.exception.NotFoundException;
import br.gama.itau.projetofinal2.model.Conta;
import br.gama.itau.projetofinal2.model.Movimentacao;
import br.gama.itau.projetofinal2.repositorio.MovimentacaoRepo;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepo movimentacaoRepo;
    
    public Movimentacao cadastrarMovimentacao(Movimentacao movimentacao) {
        
        Movimentacao movimentacaoInserido = movimentacaoRepo.save(movimentacao);
        return movimentacaoInserido;
    }
    
    public List<Movimentacao> recuperarTodas(int numeroConta){
        Conta conta = new Conta();
        conta.setNumeroConta(numeroConta);
        List<Movimentacao> listaMov = movimentacaoRepo.findByConta(conta);

        if (listaMov.isEmpty()) {
            throw new NotFoundException("movimentaçao não existe");
        }
        return listaMov;
    }
}
