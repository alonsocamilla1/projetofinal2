package br.gama.itau.projetofinal2.util;

import br.gama.itau.projetofinal2.model.Cliente;
import br.gama.itau.projetofinal2.model.Conta;

public class GenerateConta {
    public static Conta novaContaToSave() {
        return Conta.builder()
            .agencia(1111)
            .tipoConta(2)
            .saldo(10.0)
            .build();
    }

public static Conta contaValida() {
    return Conta.builder()
            .numeroConta(1)
            .agencia(1111)
            .tipoConta(2)
            .saldo(10.0)
            .build();
    }

    public static Conta novaContaToSave2() {
        return Conta.builder()
            .agencia(2222)
            .tipoConta(1)
            .saldo(20.0)
            .build();
    }

    public static Conta contaValida2() {
        return Conta.builder()
            .numeroConta(2)
            .agencia(2222)
            .tipoConta(1)
            .saldo(20.0)
            .build();
    }

    public static Conta contaValidaComCliente() {
        return Conta.builder()
            .numeroConta(3)
            .agencia(3333)
            .tipoConta(1)
            .saldo(30.0)
            .cliente(GenerateCliente.clienteValido())
            .build();
    }
    public static Conta novaContaComCliente(int idCliente) {
        return Conta.builder()
            .agencia(3333)
            .tipoConta(1)
            .saldo(30.0)
            .cliente(Cliente.builder().idCliente(idCliente).build())
            .build();
    }
    public static Conta novaContaCliente2(int idCliente) {
        return Conta.builder()
            .agencia(2222)
            .tipoConta(1)
            .saldo(30.0)
            .cliente(Cliente.builder().idCliente(idCliente).build())
            .build();
    }

    public static Conta contaValidaComCliente2() {
        return Conta.builder()
            .numeroConta(4)
            .agencia(4444)
            .tipoConta(4)
            .saldo(40.0)
            .cliente(GenerateCliente.clienteValido())
            .build();
    }

    public static Conta novaContaToSaveComCliente() {
        return Conta.builder()
            .agencia(1111)
            .tipoConta(1)
            .saldo(10.0)
            .cliente(GenerateCliente.clienteValido())
            .build();
    }

    public static Conta novaContaToSaveComCliente2() {
        return Conta.builder()
            .agencia(2222)
            .tipoConta(2)
            .saldo(20.0)
            .cliente(GenerateCliente.clienteValido())
            .build();
    }

    public static Conta novaContaToSaveComCliente(int idCliente) {
        return Conta.builder()
            .agencia(1111)
            .tipoConta(1)
            .saldo(10.0)
            .cliente(Cliente.builder().idCliente(idCliente).build())
            .build();
    }

    public static Conta novaContaToSaveComCliente2(int idCliente) {
        return Conta.builder()
            .agencia(2222)
            .tipoConta(2)
            .saldo(20.0)
            .cliente(Cliente.builder().idCliente(idCliente).build())
            .build();
    }
}
