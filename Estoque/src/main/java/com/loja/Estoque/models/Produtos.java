package com.loja.Estoque.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(name = "titulo")
    public String titulo;
    @Column(name = "qtd")
    public int qtd;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Precos.class)
    @JoinColumn(name = "precos_id", referencedColumnName = "pid")
    public Precos precos;
}
