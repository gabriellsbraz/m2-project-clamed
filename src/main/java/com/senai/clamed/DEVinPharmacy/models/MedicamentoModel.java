package com.senai.clamed.DEVinPharmacy.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_MEDICAMENTO")
public class MedicamentoModel {

    @Id
    private Integer nRegistro;
    private String nome;
    private String lab;
    private String dosagem;
    private String descricao;
    private Float preco;
    private TipoMedicamento tipoMedicamento;

    public Integer getnRegistro() {
        return nRegistro;
    }

    public void setnRegistro(Integer nRegistro) {
        this.nRegistro = nRegistro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public TipoMedicamento getTipoMedicamento() {
        return tipoMedicamento;
    }

    public void setTipoMedicamento(TipoMedicamento tipoMedicamento) {
        this.tipoMedicamento = tipoMedicamento;
    }
}
