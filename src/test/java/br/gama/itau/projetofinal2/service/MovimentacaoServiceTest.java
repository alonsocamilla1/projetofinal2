package br.gama.itau.projetofinal2.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.gama.itau.projetofinal2.exception.NotFoundException;
import br.gama.itau.projetofinal2.model.Conta;
import br.gama.itau.projetofinal2.model.Movimentacao;
import br.gama.itau.projetofinal2.repositorio.MovimentacaoRepo;
import br.gama.itau.projetofinal2.util.GenerateMovimentacao;

@ExtendWith(MockitoExtension.class)
public class MovimentacaoServiceTest {

    @InjectMocks
    private MovimentacaoService service;

    @Mock
    private MovimentacaoRepo repo;

    @Test
    public void cadastrarMovimentacao_returnMovimentacaoCadastrada_whenMovimentacaoExist() {
        BDDMockito.when(repo.save(ArgumentMatchers.any(Movimentacao.class)))
                .thenReturn(GenerateMovimentacao.moviValida());

        Movimentacao novaMovi = GenerateMovimentacao.novaMoviToSave();
        Movimentacao moviCadastrada = service.cadastrarMovimentacao(novaMovi);

        assertThat(moviCadastrada).isNotNull();
        assertThat(moviCadastrada.getNumSeq()).isPositive();
        assertThat(moviCadastrada.getValor()).isEqualTo(novaMovi.getValor());
        
        verify(repo, Mockito.times(1)).save(novaMovi);
    }

    @Test
    public void cadastrarMovimentacao_returnNull_whenMovimentacaoNotExist() {
        Movimentacao novaMovi = GenerateMovimentacao.moviValida();
        Movimentacao moviCadastrada = service.cadastrarMovimentacao(novaMovi);

        assertThat(moviCadastrada).isNull();

        verify(repo, Mockito.times(1)).save(novaMovi);
    }

    @Test
    public void recuperarTodas_returnTodasAsMovimentacoesCadastradas_whenSucesso() {
       // preparação
      List<Movimentacao> lista = new ArrayList<>();
      lista.add(GenerateMovimentacao.novaMoviToSave());
      lista.add(GenerateMovimentacao.novaMoviToSave2());
      BDDMockito.when(repo.findByConta(ArgumentMatchers.any(Conta.class)))
                .thenReturn(lista);

        //ação
        List<Movimentacao> listaCompleta = service.recuperarTodas(1);

        // verificações
        assertThat(listaCompleta).isNotNull();
        assertThat(listaCompleta.size()).isEqualTo(2);
    }

    @Test
    public void recuperarTodas_returnNotFoundException_whenListaIsEmpty() {
       
      List<Movimentacao> lista = new ArrayList<>();
      //lista.add(GenerateMovimentacao.novaMoviToSave());
      BDDMockito.when(repo.findByConta(ArgumentMatchers.any(Conta.class)))
                .thenReturn(lista);

        assertThrows(NotFoundException.class, () ->{
            service.recuperarTodas(0);
        });
        
    }

}
