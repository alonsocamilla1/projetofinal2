package br.gama.itau.projetofinal2.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gama.itau.projetofinal2.model.Cliente;
import br.gama.itau.projetofinal2.repositorio.ClienteRepo;
import lombok.RequiredArgsConstructor;
import br.gama.itau.projetofinal2.exception.NotFoundException;
@Service
@RequiredArgsConstructor
public class ClienteService {
    
    @Autowired
    private ClienteRepo repo;
    
    public Cliente cadastrarCliente(Cliente novoCliente) {
        if(novoCliente.getIdCliente() > 0) {
            throw new NotFoundException("cliente já cadastrado");
        }
        Cliente clienteInserido = repo.save(novoCliente);
        return clienteInserido; 
    }
    
    
    public List<Cliente> recuperarTodos() {
        return (List<Cliente>) repo.findAll();
    }
    
    public Cliente reacuperrPeloID(int id) {
        Optional<Cliente> clienteOptional = repo.findById(id);

        if (clienteOptional.isEmpty()) {
            throw new NotFoundException("cliente não encontrado");
        }

        Cliente clienteEncontrado = clienteOptional.get();
        return clienteEncontrado;
    }
}