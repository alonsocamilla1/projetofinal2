package br.gama.itau.projetofinal2.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.gama.itau.projetofinal2.model.Conta;
import br.gama.itau.projetofinal2.repositorio.ContaRepo;
import br.gama.itau.projetofinal2.util.GenerateConta;

@ExtendWith(MockitoExtension.class)
public class TranferenciaServiceTest {

    @InjectMocks
    private TransferenciaService transferenciaService;

    @Mock
    private ContaService contaService;
    
    @Mock 
    private ContaRepo contaRepo;

    @Test
    public void transferirValores_returnTransferenciaConcluida_whenSucess() {
        Conta contaOrigem = GenerateConta.contaValida();
        Conta contaDestino = GenerateConta.contaValida2();
        
        BDDMockito.when(contaRepo.findById(contaOrigem.getNumeroConta()))
                .thenReturn(Optional.of(contaOrigem));
        BDDMockito.when(contaRepo.findById(contaDestino.getNumeroConta()))
                .thenReturn(Optional.of(contaDestino));

        boolean transferencia = transferenciaService
            .TransferirValores(contaOrigem.getNumeroConta(), contaDestino.getNumeroConta(), 5.0);

            assertThat(transferencia).isTrue();
    }
}
