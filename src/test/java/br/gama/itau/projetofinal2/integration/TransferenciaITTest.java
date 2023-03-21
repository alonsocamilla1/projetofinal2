package br.gama.itau.projetofinal2.integration;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.gama.itau.projetofinal2.model.Conta;
import br.gama.itau.projetofinal2.repositorio.ContaRepo;
import br.gama.itau.projetofinal2.util.GenerateConta;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TransferenciaITTest  {
    
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContaRepo contaRepo;

    @Test
    public void transferirValores_return200AndTransferenciaConcluida_whenSucess() {
        Conta contaOrigem = GenerateConta.contaValida();
        Conta contaDestino = GenerateConta.contaValida2();

        contaRepo.save(contaOrigem);
        contaRepo.save(contaDestino);

        ResponseEntity<Void> response = restTemplate.postForEntity("/contas/transferencia?contaOrigem={contaOrigem}&contaDestino={contaDestino}&valor={valor}", null, Void.class, contaOrigem.getNumeroConta(), contaDestino.getNumeroConta(), 5.0);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void transferirValores_return400AndTransferenciaNaoConcluida_whenFail() {
        Conta contaOrigem = GenerateConta.contaValida();
        Conta contaDestino = GenerateConta.contaValida2();

        contaRepo.save(contaOrigem);
        contaRepo.save(contaDestino);

        ResponseEntity<Void> response = restTemplate.postForEntity("/contas/transferencia?contaOrigem={contaOrigem}&contaDestino={contaDestino}&valor={valor}", null, Void.class, contaOrigem.getNumeroConta(), contaDestino.getNumeroConta(), 50.0);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }  
    

}