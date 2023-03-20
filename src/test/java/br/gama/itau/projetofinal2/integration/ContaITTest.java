package br.gama.itau.projetofinal2.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gama.itau.projetofinal2.model.Cliente;
import br.gama.itau.projetofinal2.model.Conta;
import br.gama.itau.projetofinal2.repositorio.ClienteRepo;
import br.gama.itau.projetofinal2.repositorio.ContaRepo;
import br.gama.itau.projetofinal2.util.GenerateCliente;
import br.gama.itau.projetofinal2.util.GenerateConta;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ContaITTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ContaRepo contaRepo;

    @Autowired
    private ClienteRepo clienteRepo;

   

    @BeforeEach
    public void setup() {
        contaRepo.deleteAll();
    }

    @Test
    public void adicionarConta_returnContaAdicionada_whenContaValida() throws Exception {
        
        Conta novaConta = GenerateConta.novaContaToSave();
        
        ResultActions resposta = mockMvc.perform(post("/contas")
                .content(objectMapper.writeValueAsString(novaConta))
                .contentType(MediaType.APPLICATION_JSON));
                
        resposta.andExpect(status().isCreated())
                .andExpect(jsonPath("$.agencia", CoreMatchers.is(novaConta.getAgencia())));
    }

    @Test
    public void recuperarPeloNumero_returnConta_whenNumeroExist() throws Exception {
        
        Conta novaConta = GenerateConta.novaContaToSave();
        Conta contaCriada = contaRepo.save(novaConta);
        
        ResultActions resposta = mockMvc.perform(get("/contas/{id}", contaCriada.getNumeroConta())
                .contentType(MediaType.APPLICATION_JSON));
                
        resposta.andExpect(status().isOk())
                .andExpect(jsonPath("$.agencia", CoreMatchers.is(contaCriada.getAgencia())));
    }

    @Test
    public void recuperarContasPeloCliente_returnListaConta_whenIdExist() throws Exception {
        Cliente novoCliente = GenerateCliente.clienteValido();
        Cliente clienteCriado = clienteRepo.save(novoCliente);

        List<Conta> listaContas = new ArrayList<>();
        listaContas.add(GenerateConta.novaContaToSaveComCliente());
        listaContas.add(GenerateConta.novaContaToSaveComCliente2());

        contaRepo.saveAll(listaContas);
        
        ResultActions resposta = mockMvc.perform(get("/contas/cliente/{id}", clienteCriado.getIdCliente())
                .contentType(MediaType.APPLICATION_JSON));
                
        resposta.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(listaContas.size())))
                .andExpect(jsonPath("$[0].cliente.idCliente", CoreMatchers.is(GenerateConta.novaContaToSaveComCliente().getCliente().getIdCliente())))
                .andExpect(jsonPath("$[1].cliente.idCliente", CoreMatchers.is(GenerateConta.novaContaToSaveComCliente2().getCliente().getIdCliente())));
    }

  
}