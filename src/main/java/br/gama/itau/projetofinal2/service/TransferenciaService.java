package br.gama.itau.projetofinal2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gama.itau.projetofinal2.model.Conta;
import br.gama.itau.projetofinal2.repositorio.ContaRepo;

@Service
public class TransferenciaService {

    @Autowired
    private ContaRepo contaRepository;
    
    public boolean TransferirValores(int contaOrigem, int contaDestino, double valor) {
        Conta contaOrigemObj = contaRepository.findById(contaOrigem)
                .orElseThrow(() -> new IllegalArgumentException("Conta de origem inválida"));
        Conta contaDestinoObj = contaRepository.findById(contaDestino)
                .orElseThrow(() -> new IllegalArgumentException("Conta de destino inválida"));

        if (valor > 0) {
            contaOrigemObj.setSaldo(contaOrigemObj.getSaldo() - valor);
            contaDestinoObj.setSaldo(contaDestinoObj.getSaldo() + valor);
            contaRepository.save(contaOrigemObj);
            contaRepository.save(contaDestinoObj);
            return true;
        } else {
            return false;
        }
    }
}
