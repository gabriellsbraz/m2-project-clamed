package com.senai.clamed.DEVinPharmacy.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
