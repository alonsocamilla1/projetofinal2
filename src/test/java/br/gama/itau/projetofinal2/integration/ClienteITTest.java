package br.gama.itau.projetofinal2.integration;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import java.util.ArrayList;
//import java.util.List;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import br.gama.itau.projetofinal2.model.Cliente;
import br.gama.itau.projetofinal2.repositorio.ClienteRepo;
import br.gama.itau.projetofinal2.util.GenerateCliente;


// carrega o contexto do Spring para teste usando uma porta aleatória
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClienteITTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClienteRepo clienteRepo;

    @BeforeEach
    public void setup() {
        clienteRepo.deleteAll();
    }

    @Test
    public void cadastrarCliente_returnClienteCadastrado_whenDadosClienteValido() throws Exception {
        Cliente novoCliente = GenerateCliente.novoClienteToSave();

        ResultActions resposta = mockMvc.perform(post("/clientes")
                        .content(objectMapper.writeValueAsString(novoCliente))
                        .contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isCreated())
                .andExpect(jsonPath("$.nomeCliente", CoreMatchers.is(novoCliente.getNomeCliente())));
    }

    @Test
 public void recuperarTodos_returnClienteInserido_whenDadosClienteValido() throws Exception {
     Cliente novoCliente = GenerateCliente.novoClienteToSave();
        clienteRepo.save(novoCliente);

     ResultActions resposta = mockMvc.perform(get("/clientes/")
                     .contentType(MediaType.APPLICATION_JSON));

     resposta.andExpect(status().isOk())
             .andExpect(jsonPath("$[0].nomeCliente", CoreMatchers.is(novoCliente.getNomeCliente())));
 }

    @Test 
    public void recuperarPeloID_returnCliente_whenIdExist() throws Exception {
        Cliente novoCliente = GenerateCliente.novoClienteToSave();

        Cliente clienteCriado = clienteRepo.save(novoCliente);

        ResultActions resposta = mockMvc.perform(get("/clientes/{id}", clienteCriado.getIdCliente())
                .contentType(MediaType.APPLICATION_JSON));

        // verificar os resultados
        resposta.andExpect(status().isOk())
                .andExpect(jsonPath("$.idCliente", CoreMatchers.is(clienteCriado.getIdCliente())));
    }
}

