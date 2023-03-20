package br.gama.itau.projetofinal2.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import br.gama.itau.projetofinal2.exception.NotFoundException;
import br.gama.itau.projetofinal2.model.Cliente;
import br.gama.itau.projetofinal2.model.Conta;
import br.gama.itau.projetofinal2.repositorio.ClienteRepo;
import br.gama.itau.projetofinal2.repositorio.ContaRepo;

import org.springframework.stereotype.Service;

@Service
public class ContaService {
    
    @Autowired
    private ContaRepo repo;

    @Autowired
    private ClienteRepo clienteRepository;
    
    public Conta adicionarConta(Conta novaConta) {
        if(novaConta.getNumeroConta() > 0) {
            throw new NotFoundException("conta já cadastrado"); 
        }
        Conta contaAdicionada = repo.save(novaConta);
        return contaAdicionada;

    }

    public Conta recuperarPeloNumero(Integer numeroConta) {
        Optional<Conta> contaOptional = repo.findById(numeroConta);
        if (contaOptional.isPresent()) {
            return contaOptional.get();
        } else {
            throw new NotFoundException("Conta não encontrado");
        }
    }

    public List<Conta> recuperarContasPeloCliente(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return repo.findByCliente(cliente);
        } else {
            throw new NotFoundException("O Id do cliente não existe");
        }
    }

    public void alterarDados(Conta conta, Double valor) {
        conta.setSaldo(valor);
        repo.save(conta);
    }
}
