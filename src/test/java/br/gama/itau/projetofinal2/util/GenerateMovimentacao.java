package br.gama.itau.projetofinal2.util;

import java.time.LocalDate;

import br.gama.itau.projetofinal2.model.Movimentacao;

public class GenerateMovimentacao {
    
    public static Movimentacao novaMoviToSave() {
        return Movimentacao.builder()
            .dataOperacao(LocalDate.now())
            .valor(1.0)
            .tipoOperacao(1)
            .descricao("aaa")
            .conta(GenerateConta.contaValida())
            .build();
    }
    public static Movimentacao novaMovimentacaoTo() {
        return Movimentacao.builder()
            .dataOperacao(LocalDate.now())
            .valor(1.0)
            .tipoOperacao(1)
            .conta(GenerateConta.contaValida())
            .descricao("aa")
            .build();
    }

    public static Movimentacao moviValida() {
        return Movimentacao.builder()
            .numSeq(1)
            .dataOperacao(LocalDate.now())
            .valor(1.0)
            .tipoOperacao(1)
            .conta(GenerateConta.contaValida())
            .descricao("aaa")
            .build();
    }

    public static Movimentacao novaMoviToSave2 () {
        return Movimentacao.builder()
            .dataOperacao(LocalDate.now())
            .valor(2.0)
            .tipoOperacao(2)
            .conta(GenerateConta.contaValida())
            .descricao("aaa")
            .build();
    }

    public static Movimentacao moviValida2() {
        return Movimentacao.builder()
            .numSeq(2)
            .dataOperacao(LocalDate.now())
            .valor(2.0)
            .tipoOperacao(2)
            .conta(GenerateConta.contaValida())
            .descricao("aaa")
            .build();
    }
}