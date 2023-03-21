package br.gama.itau.projetofinal2.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numeroConta;

    private int agencia;
    private int tipoConta;
    private double saldo;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @JsonIgnoreProperties("contas")
    private Cliente cliente;
    
    @OneToMany(mappedBy = "conta")
    @JsonIgnoreProperties("conta")
    private List<Movimentacao> movimentacoes;
    
}


