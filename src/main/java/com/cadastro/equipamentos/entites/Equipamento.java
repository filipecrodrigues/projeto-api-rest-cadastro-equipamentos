package com.cadastro.equipamentos.entites;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity  //é uma entidade persistente (mapeada para uma tabela no banco de dados).
public class Equipamento {

    @Id //Marca o campo como chave primária da tabela.
    private String numeroDeSerie;
    private String marca;
    private String modelo;
    private LocalDate dataDeEntrega;

    //Construtor padrão
    public Equipamento() {}

    //metodos getters e setters
    public Equipamento(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDate getDataDeEntrega() {
        return dataDeEntrega;
    }

    public void setDataDeEntrega(LocalDate dataDeEntrega) {
        this.dataDeEntrega = dataDeEntrega;
    }
}
