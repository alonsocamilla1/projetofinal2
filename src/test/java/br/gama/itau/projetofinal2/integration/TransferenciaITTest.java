package br.gama.itau.projetofinal2.integration;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import br.gama.itau.projetofinal2.model.Cliente;
import br.gama.itau.projetofinal2.model.Conta;
import br.gama.itau.projetofinal2.repositorio.ClienteRepo;
import br.gama.itau.projetofinal2.repositorio.ContaRepo;
import br.gama.itau.projetofinal2.util.GenerateCliente;
import br.gama.itau.projetofinal2.util.GenerateConta;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TransferenciaITTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ContaRepo contaRepository;

    @Autowired
    private ClienteRepo clienteRepo;

    @Test
    public void transferirValores_returnTransferenciaConcluida_whenSucess() throws Exception {
        Cliente clienteToSave = GenerateCliente.novoClienteToSave();
        Cliente clienteCriado = clienteRepo.save(clienteToSave);


        Conta novaContaOrigem = GenerateConta.novaContaComCliente(clienteCriado.getIdCliente());
        Conta novaContaDestino = GenerateConta.novaContaCliente2(clienteCriado.getIdCliente());
        double valor = 5.0;

        // teste de integração, precisa dos dados válidos no BD
        Conta contaOrigem = contaRepository.save(novaContaOrigem);
        Conta contaDestino = contaRepository.save(novaContaDestino);
       
        ResultActions resposta = mockMvc.perform((post("/contas/transferencia?contaOrigem={contaOrigem}&contaDestino={contaDestino}&valor={valor}", contaOrigem.getNumeroConta(), contaDestino.getNumeroConta(), valor )
                                .contentType(MediaType.APPLICATION_JSON)));

        resposta.andExpect(status().isOk());
                
    }
    
}
