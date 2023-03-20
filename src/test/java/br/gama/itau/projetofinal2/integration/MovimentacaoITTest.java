package br.gama.itau.projetofinal2.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gama.itau.projetofinal2.model.Movimentacao;
import br.gama.itau.projetofinal2.repositorio.ClienteRepo;
import br.gama.itau.projetofinal2.repositorio.ContaRepo;
import br.gama.itau.projetofinal2.repositorio.MovimentacaoRepo;
import br.gama.itau.projetofinal2.util.GenerateCliente;
import br.gama.itau.projetofinal2.util.GenerateConta;
import br.gama.itau.projetofinal2.util.GenerateMovimentacao;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class MovimentacaoITTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ContaRepo contaRepo;

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private MovimentacaoRepo repo;

    @BeforeEach
    public void setup() {
        clienteRepo.deleteAll();
        contaRepo.deleteAll();
        repo.deleteAll();
    }

    @Test
    public void cadastrarMovimentacao_returnMovimentacaoInserida_whenMovimentacaoValida() throws Exception {
        clienteRepo.save(GenerateCliente.novoClienteToSave());

        contaRepo.save(GenerateConta.novaContaToSave());

        Movimentacao novoMovi = GenerateMovimentacao.novaMoviToSave();

        ResultActions resposta = mockMvc.perform(post("/movimentacoes")
                .content(objectMapper.writeValueAsString(novoMovi))
                .contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isOk())
                .andExpect(jsonPath("$.conta.numeroConta", CoreMatchers.is(novoMovi.getConta().getNumeroConta())))
                .andExpect(jsonPath("$.descricao", CoreMatchers.is(novoMovi.getDescricao())));
    }

    @Test
    public void recuperarTodas_returnListMovimentacao_whenSuccess() throws Exception {
        clienteRepo.save(GenerateCliente.novoClienteToSave());
        contaRepo.save(GenerateConta.novaContaToSave());

        List<Movimentacao> lista = new ArrayList<>();
        Movimentacao novaMoviToSave = GenerateMovimentacao.novaMoviToSave();
        lista.add(novaMoviToSave);
        lista.add(GenerateMovimentacao.novaMoviToSave2());

        repo.saveAll(lista);
        
        ResultActions resposta = mockMvc
                .perform(get("/movimentacoes/{numeroConta}", novaMoviToSave.getConta().getNumeroConta())
                        .contentType(MediaType.APPLICATION_JSON));
                        
        resposta.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(lista.size())))
                .andExpect(
                        jsonPath("$[0].descricao", CoreMatchers.is(GenerateMovimentacao.moviValida().getDescricao())));
    }
}