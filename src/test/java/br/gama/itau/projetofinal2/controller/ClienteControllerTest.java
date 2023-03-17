package br.gama.itau.projetofinal2.controller;

/*import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;

//import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.parser.MediaType;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
//import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gama.itau.projetofinal2.model.Cliente;
import br.gama.itau.projetofinal2.service.ClienteService;
import br.gama.itau.projetofinal2.util.GenerateCliente;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockBean
    private ClienteService service;

   /* @InjectMocks
    private ClienteController controller;

    private Cliente cliente;
    private List<Cliente> clientes;

     @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setNomeCliente("João");

        clientes = new ArrayList<>();
        clientes.add(cliente);
     }

    @Test
    public void CadastrarCliente_returnClienteCadastrado_whenDadosValidos() throws Exception{
        Cliente novoCliente = GenerateCliente.novoClienteToSave();
        Cliente clienteValido = service.cadastrarCliente(novoCliente);

        BDDMockito.when(service.cadastrarCliente(ArgumentMatchers.any(Cliente.class)))
        .thenReturn(clienteValido);
        
        ResultActions resposta = mockMvc.perform(post("/clientes")
                    .content(objectMapper.writeValueAsString(novoCliente))
                    .contentType(MediaType.APPLICATIO));  

        verify(service, times(1)).cadastrarCliente(cliente);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }

    @Test
    public void testCadastrarClienteNull() {
        when(service.cadastrarCliente(cliente)).thenReturn(null);

        ResponseEntity<Cliente> response = controller.cadastrarCliente(cliente);

        verify(service, times(1)).cadastrarCliente(cliente);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testRecuperarTodos() {
        when(service.recuperarTodos()).thenReturn(clientes);

        ResponseEntity<List<Cliente>> response = controller.recuperarTodos();

        verify(service, times(1)).recuperarTodos();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientes, response.getBody());
    }

    @Test
    public void testRecuperarPeloID() {
        when(service.recuperarPeloID(cliente.getIdCliente())).thenReturn(cliente);

        ResponseEntity<Cliente> response = controller.recuperarPeloID(cliente.getIdCliente());

        verify(service, times(1)).recuperarPeloID(cliente.getIdCliente());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }

}*/

